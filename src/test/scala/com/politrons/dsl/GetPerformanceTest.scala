package com.politrons.dsl

import com.politrons.mock.HttpMockServer

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
class GetPerformanceTest extends PerformanceDSL {

  HttpMockServer.initializeService

  Get.to("http://localhost:8080/test")
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