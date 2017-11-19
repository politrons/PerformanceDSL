package com.politrons.dsl

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
class ExamplePerformance extends PerformanceDSL {

  Get.to("http://www.google.com")
    .withUsers(30)
    .withDuration(60)
    .~> ::

}
