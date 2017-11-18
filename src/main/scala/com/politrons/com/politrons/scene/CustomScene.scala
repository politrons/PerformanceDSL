package com.politrons.com.politrons.scene

import io.gatling.core.structure.ScenarioBuilder

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
trait CustomScene {

  def create(uri: String, body:String=""): ScenarioBuilder

}
