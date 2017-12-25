package com.politrons.com.politrons.scene

import io.gatling.core.structure.ScenarioBuilder

import scala.util.Random

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
trait CustomScene {

  val msgId = Iterator.continually(Map("msgId" -> Random.alphanumeric.take(30).mkString))

  def create(uri: String, body:String=""): ScenarioBuilder

}
