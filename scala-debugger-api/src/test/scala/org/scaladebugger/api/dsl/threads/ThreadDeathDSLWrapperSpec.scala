package org.scaladebugger.api.dsl.threads

import org.scaladebugger.api.lowlevel.events.data.JDIEventDataResult
import org.scaladebugger.api.lowlevel.requests.JDIRequestArgument
import org.scaladebugger.api.pipelines.Pipeline
import org.scaladebugger.api.profiles.traits.info.events.ThreadDeathEventInfo
import org.scaladebugger.api.profiles.traits.requests.threads.ThreadDeathRequest

import scala.util.Success

class ThreadDeathDSLWrapperSpec extends test.ParallelMockFunSpec
{
  private val mockThreadDeathProfile = mock[ThreadDeathRequest]

  describe("ThreadDeathDSLWrapper") {
    describe("#onThreadDeath") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.ThreadDeathDSL

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(classOf[ThreadDeathEventInfo]))

        (mockThreadDeathProfile.tryGetOrCreateThreadDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onThreadDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeThreadDeath") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.ThreadDeathDSL

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(classOf[ThreadDeathEventInfo])

        (mockThreadDeathProfile.getOrCreateThreadDeathRequest _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onUnsafeThreadDeath(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onThreadDeathWithData") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.ThreadDeathDSL

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Success(Pipeline.newPipeline(
          classOf[(ThreadDeathEventInfo, Seq[JDIEventDataResult])]
        ))

        (mockThreadDeathProfile.tryGetOrCreateThreadDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onThreadDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }

    describe("#onUnsafeThreadDeathWithData") {
      it("should invoke the underlying profile method") {
        import org.scaladebugger.api.dsl.Implicits.ThreadDeathDSL

        val extraArguments = Seq(mock[JDIRequestArgument])
        val returnValue = Pipeline.newPipeline(
          classOf[(ThreadDeathEventInfo, Seq[JDIEventDataResult])]
        )

        (mockThreadDeathProfile.getOrCreateThreadDeathRequestWithData _).expects(
          extraArguments
        ).returning(returnValue).once()

        mockThreadDeathProfile.onUnsafeThreadDeathWithData(
          extraArguments: _*
        ) should be (returnValue)
      }
    }
  }
}
