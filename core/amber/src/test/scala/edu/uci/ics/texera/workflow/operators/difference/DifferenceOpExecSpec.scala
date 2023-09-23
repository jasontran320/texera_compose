package edu.uci.ics.texera.workflow.operators.difference

import edu.uci.ics.amber.engine.common.InputExhausted
import edu.uci.ics.texera.workflow.common.tuple.Tuple
import edu.uci.ics.texera.workflow.common.tuple.schema.{Attribute, AttributeType, Schema}
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

import scala.util.Random

class DifferenceOpExecSpec extends AnyFlatSpec with BeforeAndAfter {
  var input1: Int = 0
  var input2: Int = 1
  var opExec: DifferenceOpExec = _
  var counter: Int = 0

  def tuple(): Tuple = {
    counter += 1
    val schema = Schema
      .newBuilder()
      .add(
        new Attribute("field1", AttributeType.STRING),
        new Attribute("field2", AttributeType.INTEGER),
        new Attribute("field3", AttributeType.BOOLEAN)
      )
      .build()
    Tuple
      .newBuilder(schema)
      .addSequentially(Array("hello", Int.box(counter), Boolean.box(true)))
      .build()
  }

  before {
    opExec = new DifferenceOpExec()
  }

  it should "open" in {

    opExec.open()

  }

  it should "work with basic two input streams with no duplicates" in {
    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList

    (0 to 7).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input1, null, null)
    })
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).isEmpty)

    (5 to 9).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input2, null, null)
    })

    val outputTuples: Set[Tuple] =
      opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).toSet
    assert(
      outputTuples.equals(commonTuples.slice(0, 5).toSet)
    )

    opExec.close()
  }

  it should "raise IllegalArgumentException when intersect with more than two input upstreams" in {

    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList
    assertThrows[IllegalArgumentException] {
      (1 to 100).map(_ => {
        opExec.processTexeraTuple(Left(tuple()), 2, null, null)
        opExec.processTexeraTuple(
          Left(commonTuples(Random.nextInt(commonTuples.size))),
          3,
          null,
          null
        )
      })

      val outputTuples: Set[Tuple] =
        opExec.processTexeraTuple(Right(InputExhausted()), 0, null, null).toSet
      assert(outputTuples.size <= 10)
      assert(outputTuples.subsetOf(commonTuples.toSet))
      outputTuples.foreach(tuple => assert(tuple.getField[Int]("field2") <= 10))
      opExec.close()
    }
  }

  it should "work with one empty input upstream after a data stream" in {
    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList

    (0 to 9).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input1, null, null)
    })
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).isEmpty)

    val outputTuples: Set[Tuple] =
      opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).toSet
    assert(outputTuples.equals(commonTuples.toSet))
    opExec.close()
  }

  it should "work with one empty input upstream after a data stream - other order" in {
    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList

    (0 to 9).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input2, null, null)
    })
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).isEmpty)

    val outputTuples: Set[Tuple] =
      opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).toSet
    assert(outputTuples.isEmpty)
    opExec.close()
  }

  it should "work with one empty input upstream before a data stream" in {
    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList

    assert(opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).isEmpty)
    (0 to 9).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input1, null, null)
    })

    val outputTuples: Set[Tuple] =
      opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).toSet
    assert(outputTuples.equals(commonTuples.toSet))
    opExec.close()
  }

  it should "work with one empty input upstream during a data stream" in {
    opExec.open()
    counter = 0
    val commonTuples = (1 to 10).map(_ => tuple()).toList

    (0 to 5).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input1, null, null)
    })
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).isEmpty)
    (6 to 9).map(i => {
      opExec.processTexeraTuple(Left(commonTuples(i)), input1, null, null)
    })

    val outputTuples: Set[Tuple] =
      opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).toSet
    assert(outputTuples.equals(commonTuples.toSet))
    opExec.close()
  }

  it should "work with two empty input upstreams" in {

    opExec.open()
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input1, null, null).isEmpty)
    assert(opExec.processTexeraTuple(Right(InputExhausted()), input2, null, null).isEmpty)
    opExec.close()
  }

}
