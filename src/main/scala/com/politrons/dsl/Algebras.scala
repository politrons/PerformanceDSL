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

  type SceneType = (String, CustomScene)

  case class _Get() extends Action[Any]

  case class _Post() extends Action[Any]

  case class _Put() extends Action[Any]

  case class _Delete() extends Action[Any]

  case class _To(uri: String, scene: CustomScene) extends Action[Any]

  case class _RunScenario(scenario:ScenarioBuilder) extends Action[Any]

  case class _WithBody(body: String, requestInfo: SceneType) extends Action[Any]


}
