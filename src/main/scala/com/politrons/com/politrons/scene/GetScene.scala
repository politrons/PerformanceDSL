package com.politrons.com.politrons.scene

import io.gatling.core.Predef._
import io.gatling.core.structure.ScenarioBuilder
import io.gatling.http.Predef._

import scala.concurrent.duration._

class GetScene(name: String) extends CustomScene{

  def create(uri: String, body:String=""): ScenarioBuilder = {
    scenario(name)
      .exec(http("custom scene")
        .get(uri)
        .check(status.is(200)))
      .pause(Duration(1, SECONDS))
  }

}
