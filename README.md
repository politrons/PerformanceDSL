Author  Pablo Perez Garcia

![My image](src/main/resources/img/Gatling-logo.png)

An open souce library that provide a Performance DSL based on [Gatling](https://gatling.io/).

It´s also using [Free monads](http://eed3si9n.com/learning-scalaz/Free+Monad.html) of [ScalaZ](https://github.com/scalaz/scalaz).

## Use

* clone the project
```
https://github.com/politrons/PerformanceDSL

```
* Run the test example
```
sbt gatling:test

```
* Create jar
```
sbt package

```
* Add jar in your project dependency
```
target/scala-2.11/performancedsl_2.11-1.0.jar
```
* Make your performance test extends PerformanceDSL in order to use the DSL
```
class GetPerformanceTest extends PerformanceDSL {}
```

## Create your Performance tests

Using the DSL we can structure our gatling performance tests.

* We can create a gatling sumulation.
* For the execution we can provide the number of request(Users) in a perdiod of time
* For the metrics we cab check max time, mean time, and 50, 75, 95 and 99 percentile

### Performance DSL Examples

* Get

```
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
```
* Post

```
  Post.to("http://localhost:8080/test")
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
```

* Put

```
  Put.to("http://localhost:8080/test")
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
```

* Delete endpoint

```
  Delete.to("http://localhost:8080/test")
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

```


