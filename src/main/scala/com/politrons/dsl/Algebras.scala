package com.politrons.dsl

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

  case class _Get() extends Action[Any]

}
