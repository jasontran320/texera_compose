package edu.uci.ics.texera.workflow.operators.source.fetcher

import edu.uci.ics.texera.workflow.common.tuple.schema.OperatorSchemaInfo
import org.scalatest.BeforeAndAfter
import org.scalatest.flatspec.AnyFlatSpec

class URLFetcherOpExecSpec extends AnyFlatSpec with BeforeAndAfter {

  val resultSchema = new URLFetcherOpDesc().sourceSchema()

  it should "fetch url and output one tuple with raw bytes" in {
    val fetcherOpExec = new URLFetcherOpExec(
      "https://www.google.com",
      DecodingMethod.RAW_BYTES,
      OperatorSchemaInfo(Array.empty, Array(resultSchema))
    )
    val iterator = fetcherOpExec.produceTexeraTuple()
    assert(iterator.next().get(0).isInstanceOf[Array[Byte]])
    assert(!iterator.hasNext)
  }

  it should "fetch url and output one tuple with UTF-8 string" in {
    val fetcherOpExec = new URLFetcherOpExec(
      "https://www.google.com",
      DecodingMethod.UTF_8,
      OperatorSchemaInfo(Array.empty, Array(resultSchema))
    )
    val iterator = fetcherOpExec.produceTexeraTuple()
    assert(iterator.next().get(0).isInstanceOf[String])
    assert(!iterator.hasNext)
  }

}
