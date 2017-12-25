package com.politrons.dsl

import com.politrons.mock.HttpMockServer

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
class PostPerformanceTest extends PerformanceDSL {

  HttpMockServer.initializeService

  Post.to("http://localhost:8080/test")
    .withBody("identify ID")
    .withUsers(30)
    .withDuration(10)
    .maxTime(1000)
    .meanTime(150)
    .percentile1(300)
    .percentile2(400)
    .percentile3(500)
    .percentile4(2000)
    .~> ::

}
