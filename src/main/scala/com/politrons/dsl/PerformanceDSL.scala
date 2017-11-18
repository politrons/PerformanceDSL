package com.politrons.dsl

import com.politrons.gatling.HttpClient
import io.gatling.core.Predef.{global, nothingFor, rampUsers, _}
import io.gatling.core.scenario.Simulation
import io.gatling.core.structure.ScenarioBuilder

import scala.concurrent.duration._
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
class PerformanceDSL extends Simulation with Actions {

  override def interpreter: Action ~> Id = new (Action ~> Id) {
    def apply[A](a: Action[A]): Id[A] = a match {
      case _Get() => setUpScenario(); null
      case _ => throw new IllegalArgumentException("No action allowed by the DSL")
    }
  }

  def setUpScenario(): Unit = {
    val numberUsers: Int = 30

    val scn: ScenarioBuilder = new MockScene("MockScenario").create()

    setUp(
      scn.inject(nothingFor(1.seconds), rampUsers(numberUsers) over Duration(10, SECONDS)))
      .protocols(HttpClient.conf)
      .assertions(global.successfulRequests.perMillion.is(1000000))
      .assertions(global.responseTime.max.lessThan(5000))
      .assertions(global.responseTime.mean.lessThan(100))
      .assertions(global.responseTime.percentile3.lessThan(500))
      .assertions(global.responseTime.percentile4.lessThan(2000))
  }
}




