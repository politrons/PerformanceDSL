package com.politrons.dsl

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
class GetPerformanceTest extends PerformanceDSL {

  Get.to("http://www.google.com")
    .withUsers(30)
    .withDuration(60)
    .maxTime(1000)
    .meanTime(150)
    .percentile1(300)
    .percentile2(400)
    .percentile3(500)
    .percentile4(2000)
    .~> ::

}