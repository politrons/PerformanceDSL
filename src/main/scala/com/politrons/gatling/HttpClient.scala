package com.politrons.gatling

import java.util.Properties

import io.gatling.core.Predef._
import io.gatling.http.Predef._


object HttpClient {

  val properties = new Properties() {
    put("contentType", "application/json; charset=UTF-8")
  }

//  implicit val getHeaders: Map[String, String] = HttpClient.headers

  val conf = http
    .maxConnectionsPerHost(100000)
//    .baseURL(Config.host)
//    .headers(HttpClient.getHeaders(properties))
    .acceptHeader("application/json")

}
