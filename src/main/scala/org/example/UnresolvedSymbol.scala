package org.example

import cats.Show
import cats.syntax.show._

object UnresolvedSymbol extends App {
  class MyException {
    def getMessage: String = this.show
  }

  implicit val exceptionShow: Show[MyException] = Show.show { _ => "show" }

  println(new MyException().getMessage)
}
