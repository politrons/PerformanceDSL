package com.politrons.dsl

import com.politrons.com.politrons.scene.{CustomScene, GetScene, _}
import com.politrons.gatling.HttpClient
import io.gatling.core.Predef.{global, nothingFor, rampUsers, _}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._
import scalaz.Free.liftF
import scalaz.~>

/**
  * Created by Pablo Perez Garcia on 06/07/2017.
  *
  * This is the main class of the test framework. Using scalaz we create a DSL where the client can use
  * the common TDD components Given, When, Then.
  *
  * Thanks to this DSL we can separate structure from behaviour of our application.
  *
  * The implementation of this DSL is using the interpreter scenario which apply the logic of the DSL.
  * That interpreter must be implemented by the consumer of the DSL.
  */
trait PerformanceDSL extends Simulation with Actions {

  def Get: ActionMonad[Any] = {
    liftF[Action, Any](_Get())
  }

  def Post: ActionMonad[Any] = {
    liftF[Action, Any](_Post())
  }

  def Put: ActionMonad[Any] = {
    liftF[Action, Any](_Put())
  }

  def Delete: ActionMonad[Any] = {
    liftF[Action, Any](_Delete())
  }

  override def interpreter: Action ~> Id = new (Action ~> Id) {
    def apply[A](a: Action[A]): Id[A] = a match {
      case _Get() => new GetScene("GetScenario")
      case _Post() => new PostScene("PostScenario")
      case _Put() => new PutScene("PutScenario")
      case _Delete() => new DeleteScene("DeleteScenario")
      case _To(uri, scene) => processUri(uri, scene)
      case _WithBody(body, sceneType) => createSimulationWithBody(body, sceneType)
      case _WithUsers(number, simulationInfo) => updateSimulationWithUsers(number, simulationInfo)
      case _RunScenario(simulationInfo) => runScenario(simulationInfo); "Done"
      case _ => throw new IllegalArgumentException("No action allowed by the DSL")
    }
  }

  private def processUri[A](uri: String, scene: CustomScene): Any = {
    scene match {
      case _: GetScene => new SimulationInfo(ScenarioInfo(), scene.create(uri))
      case _ => new SceneInfo(uri, scene)
    }
  }

  private def createSimulationWithBody[A](body: String, sceneInfo: (String, CustomScene)): SimulationInfo = {
    new SimulationInfo(ScenarioInfo(), sceneInfo.scene.create(sceneInfo.uri, body))
  }

  private def updateSimulationWithUsers[A](number: Int, simulationInfo: SimulationInfo): SimulationInfo = {
    val newSimulationInfo = simulationInfo.scenarioInfo.copy(numberUsers = number)
    new SimulationInfo(newSimulationInfo, simulationInfo.scenarioBuilder)
  }

  def runScenario(simulationInfo: SimulationInfo): Unit = {
    setUpScenario(simulationInfo._1, simulationInfo._2)
  }

  def setUpScenario(scenario: ScenarioInfo, scn: ScenarioBuilder): Unit = {
    setUp(
      scn.inject(nothingFor(1.seconds), rampUsers(scenario.numberUsers) over Duration(10, SECONDS)))
      .protocols(HttpClient.conf)
      .assertions(global.successfulRequests.perMillion.is(1000000))
      .assertions(global.responseTime.max.lessThan(scenario.maxResponseTime))
      .assertions(global.responseTime.mean.lessThan(scenario.meanResponseTime))
      .assertions(global.responseTime.percentile1.lessThan(scenario.percentile1))
      .assertions(global.responseTime.percentile2.lessThan(scenario.percentile2))
      .assertions(global.responseTime.percentile3.lessThan(scenario.percentile3))
      .assertions(global.responseTime.percentile4.lessThan(scenario.percentile4))
  }


}




