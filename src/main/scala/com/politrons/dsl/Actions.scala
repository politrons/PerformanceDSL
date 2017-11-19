package com.politrons.dsl


import com.politrons.com.politrons.scene.CustomScene

import scalaz.Free._
import scalaz._

/**
  * Created by Pablo Perez Garcia on 07/07/2017.
  *
  * Here we define the action functions which create the Free monads using the algebras that we defined, and passing the values received.
  * Those functions are the real DSL that the client will use in their implementations.
  */
trait Actions extends Algebras {

  implicit class customFree(free: ActionMonad[Any]) {

    def to(uri: String): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_To(uri, any.asInstanceOf[CustomScene])))
    }

    def withBody(body: String): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_WithBody(body, any.asInstanceOf[SceneInfo])))
    }

    def withUsers(number: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_WithUsers(number, any.asInstanceOf[SimulationInfo])))
    }

    def ~> : ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_RunScenario(any.asInstanceOf[SimulationInfo])))
    }

    def :: : Id[Any] = free.foldMap(interpreter)
  }

  def interpreter: Action ~> Id

}
