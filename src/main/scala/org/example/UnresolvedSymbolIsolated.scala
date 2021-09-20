package org.example

import scala.language.implicitConversions

object UnresolvedSymbolIsolated extends App {
  trait Show[T] {
    def show(t: T): String
  }

  trait Ops[A] {
    def typeClassInstance: Show[A]

    def self: A

    def show: String = typeClassInstance.show(self)
  }

  implicit def toShow[A](target: A)(implicit tc: Show[A]): Ops[A] =
    new Ops[A] {
      val self: A = target
      val typeClassInstance: Show[A] = tc
    }

  implicit val exceptionShow: Show[MyException] = { _ => "show" }

  class MyException {
    def getMessage: String = this.show
  }

  println(new MyException().getMessage)
}
