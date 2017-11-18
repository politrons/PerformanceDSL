package com.politrons.dsl


import scalaz.Free._
import scalaz._

/**
  * Created by Pablo Perez Garcia on 07/07/2017.
  *
  * Here we define the action functions which create the Free monads using the algebras that we defined, and passing the values received.
  * Those functions are the real DSL that the client will use in their implementations.
  */
trait Actions extends Algebras {

    def Performance: ActionMonad[Any] = {
      liftF[Action, Any](_Get())
    }

  implicit class customFree(free: ActionMonad[Any]) {
    ////
    ////    def Request: ActionMonad[Any] = {
    ////      free.flatMap(any => liftF[Action, Any](_Request(any.asInstanceOf[Method])))
    ////    }
    //
    //    def to(uri: String): ActionMonad[Any] = {
    //      free.flatMap(any => liftF[Action, Any](_To(uri, any.asInstanceOf[Method])))
    //    }
    //
    //    def resultAsString: ActionMonad[Any] = {
    //      free.flatMap(any => liftF[Action, Any](_Result(any.asInstanceOf[RequestInfo])))
    //    }
    //
    //    def isStatus(code:Int): ActionMonad[Any] = {
    //      free.flatMap(any => liftF[Action, Any](_isStatus(code,any.asInstanceOf[RequestInfo])))
    //    }

    def get: Id[Any] = free.foldMap(interpreter)
  }

  def interpreter: Action ~> Id

}
