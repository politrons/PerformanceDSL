package com.politrons.mock

/**
  * Created by pabloperezgarcia on 06/02/2017.
  */

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.Http.ServerBinding
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

import scala.concurrent.Future

object HttpMockServer {

  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()

  // needed for the future flatMap/onComplete in the end
  implicit val executionContext = system.dispatcher

  var serverFuture: Option[Future[ServerBinding]] = None

  val localhost = "localhost"
  val port = 8080
  var order = Map[String, String]()

  /**
    * Akka http provide the bindAndHandler which allow you to bind using a dsl some endpoints in a specific address/port
    *
    */
  def initializeService = {
    if(serverFuture.isEmpty){
      serverFuture = Some(Http().bindAndHandle(routes, localhost, port))
      serverFuture.get.onComplete(_ => println(s"Server online at http://$localhost:$port/\nPress RETURN to stop..."))
    }
  }

  /**
    * Akka http provide a dsl to create the endpoints for the rest service
    * The ~ character pass the request form one route to the next one in the chain.
    */
  private def routes = {
    path("test") {
      get {
        complete(getVersionResponse)
      } ~ post {
        complete(getVersionResponse)
      } ~ put {
        complete(getVersionResponse)
      } ~ delete {
        complete(getVersionResponse)
      }
    }
  }

  private def getVersionResponse = {
    HttpEntity(ContentTypes.`application/json`, "Akka-http version 1.0")
  }

}
