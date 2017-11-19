package com.politrons.dsl

/**
  * Created by pabloperezgarcia on 18/11/2017.
  */
class PostPerformanceTest extends PerformanceDSL {

  Post.to("http://www.google.com")
    .withBody("body")
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
