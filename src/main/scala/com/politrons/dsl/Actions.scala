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

    def withDuration(number: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_WithDuration(number, any.asInstanceOf[SimulationInfo])))
    }

    def maxTime(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_MaxTime(time, any.asInstanceOf[SimulationInfo])))
    }

    def meanTime(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_MeanTime(time, any.asInstanceOf[SimulationInfo])))
    }

    def percentile1(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_Percentile1(time, any.asInstanceOf[SimulationInfo])))
    }

    def percentile2(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_Percentile2(time, any.asInstanceOf[SimulationInfo])))
    }

    def percentile3(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_Percentile3(time, any.asInstanceOf[SimulationInfo])))
    }

    def percentile4(time: Int): ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_Percentile4(time, any.asInstanceOf[SimulationInfo])))
    }

    def ~> : ActionMonad[Any] = {
      free.flatMap(any => liftF[Action, Any](_RunScenario(any.asInstanceOf[SimulationInfo])))
    }

    def :: : Id[Any] = free.foldMap(interpreter)
  }

  def interpreter: Action ~> Id

}
