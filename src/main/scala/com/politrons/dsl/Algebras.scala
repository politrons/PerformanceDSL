package com.politrons.dsl

import com.politrons.com.politrons.scene.CustomScene
import io.gatling.core.structure.ScenarioBuilder

import scalaz.Free

/**
  * Created by Pablo Perez Garcia on 07/07/2017.
  *
  * Here we define the algebras to be used as part of the DSL.
  *
  * All them we consider as Actions which we define the entry data and return type defined in the Action.
  */
trait Algebras {

  type Id[+A] = A

  sealed trait Action[A]

  type ActionMonad[A] = Free[Action, A]

  type SceneInfo = (String, CustomScene)

  type SimulationInfo = (ScenarioInfo, ScenarioBuilder)

  case class _Get() extends Action[Any]

  case class _Post() extends Action[Any]

  case class _Put() extends Action[Any]

  case class _Delete() extends Action[Any]

  case class _To(uri: String, scene: CustomScene) extends Action[Any]

  case class _WithBody(body: String, sceneInfo: SceneInfo) extends Action[Any]

  case class _WithUsers(number: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _WithDuration(number: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _MaxTime(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _MeanTime(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _Percentile1(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _Percentile2(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _Percentile3(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _Percentile4(time: Int, simulationInfo: SimulationInfo) extends Action[Any]

  case class _RunScenario(simulationInfo: SimulationInfo) extends Action[Any]

  case class ScenarioInfo(numberUsers: Int = 10,
                          duration: Int = 10,
                          maxResponseTime: Int = 1000,
                          meanResponseTime: Int = 150,
                          percentile1: Int = 300,
                          percentile2: Int = 400,
                          percentile3: Int = 500,
                          percentile4: Int = 2000)

  implicit class customSceneInfo(sceneType: SceneInfo) {
    def uri = sceneType._1
    def scene = sceneType._2
  }

  implicit class customSimulationInfo(simulation: SimulationInfo) {
    def scenarioInfo = simulation._1
    def scenarioBuilder = simulation._2
  }

}
