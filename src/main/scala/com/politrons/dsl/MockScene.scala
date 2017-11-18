package com.politrons.dsl

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class MockScene(name: String) {

//  val msgId = Iterator.continually(Map("msgId" -> Random.alphanumeric.take(30).mkString))

  def create(): ScenarioBuilder = {
    scenario(name)
//      .exec(session => session.set("f2e_mock_service", "localhost:8500"))
//      .feed(msgId)
      .exec(http("Mock service")
        .get("http://google.com")
        .check(status.is(200)))
      .pause(Duration(1, SECONDS))
  }

}
