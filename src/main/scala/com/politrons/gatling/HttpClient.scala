package com.politrons.gatling

import java.util.Properties

import io.gatling.core.Predef._
import io.gatling.http.Predef._
import io.gatling.http.protocol.HttpProtocolBuilder


object HttpClient {

  var headers: Map[String, String] = Map()

  val properties = new Properties() {
    put("contentType", "application/json; charset=UTF-8")
  }

  lazy val conf: HttpProtocolBuilder = http
    .maxConnectionsPerHost(100000)
    .headers(headers)
    .acceptHeader("application/json")

}
