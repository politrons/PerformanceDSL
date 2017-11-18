Author  Pablo Perez Garcia

![My image](src/main/resources/img/dsl-icon.png)


A Test DSL based on ScalaTest and ScalaZ.

This open souce project provide a DSL based on [Free monads](http://eed3si9n.com/learning-scalaz/Free+Monad.html) of [ScalaZ](https://github.com/scalaz/scalaz).
It´s using also [ScalaTest](http://www.scalatest.org/) framework to provide the sites and scenarios.

## Use

* clone the project
```
git clone https://github.com/politrons/TestDSL.git
```
* Run the test example
```
sbt test

```

## Create your own Test and Interpreter

Free monads are a bunch of algebras that we can glue each other and then create those pipelines as DSLs.

Free monads are meant to be used to separate strucuture and behave on your program, that´s why your DSL always need
an interpreter that understand and give behave of the strucutre of it.

In our example we create a test that is using the DSL to test a shopping basket.

```
      Given("an empty basket", Basket(List()))
        .When("I add product 'coca-cola'")
        .And("I add product 'milk'")
        .And("I add product 'ham'")
        .And("I add product 'cornflakes'")
        .And("I remove product 'ham'")
        .Then("The number of products is '3'")
```

As you can expect giving this DSL we are not doing nothing. Just a glue to contact one monad to another in this pipeline.

In order to give a behave to this strucutre we need to implement our own interpreter.

The interpreter it should be the bussines logic of your application. In this case all the functionality related with shopping basket.

Now we can give our DSL a behave.

```
  override def interpreter(action: String, anyBasket: Any): Any = {
    action match {
      case "an empty basket" => anyBasket
      case ADD_PRODUCT(product) => addProduct(anyBasket, product)
      case REMOVE_PRODUCT(product) => removeProduct(anyBasket, product)
      case NUMBER_OF_PRODUCTS(numberOfProducts) => checkNumberOfProducts(anyBasket, numberOfProducts)
      case _ => throw new RuntimeException(s"Error action not controlled")
    }
  }

```

Finally to run our pipeline to be used by the interpreter we need to add the run at the end of the pipeline.

```
runScenario
```

## Re-use DSL with multiple interpreters

To see examples using the same DSL for:

* [Future](src/test/scala-2.11/com/politrons/dsl/FutureExampleIT.scala)
* [Option](src/test/scala-2.11/com/politrons/dsl/OptionExampleIT.scala)
* [Either](src/test/scala-2.11/com/politrons/dsl/EitherExampleIT.scala)



