package com.politrons.com.politrons.scene

import java.util.UUID

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class PutScene(name: String) extends CustomScene {

  def create(uri: String, body: String): ScenarioBuilder = {
    scenario(name)
      .feed(msgId)
      .exec(http("custom scene")
        .post(uri)
        .header("msgId", _ => UUID.randomUUID().toString)
        .body(StringBody(body))
        .check(status.is(200)))
      .pause(Duration(1, SECONDS))
  }

}
