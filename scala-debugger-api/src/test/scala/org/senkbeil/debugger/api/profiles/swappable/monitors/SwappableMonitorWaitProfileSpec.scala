package org.senkbeil.debugger.api.profiles.swappable.monitors

import org.scalamock.scalatest.MockFactory
import org.scalatest.{FunSpec, Matchers, ParallelTestExecution}
import org.senkbeil.debugger.api.lowlevel.JDIArgument
import org.senkbeil.debugger.api.profiles.ProfileManager
import org.senkbeil.debugger.api.profiles.swappable.SwappableDebugProfile
import org.senkbeil.debugger.api.profiles.traits.DebugProfile

class SwappableMonitorWaitProfileSpec extends FunSpec with Matchers
  with ParallelTestExecution with MockFactory
{
  private val mockDebugProfile = mock[DebugProfile]
  private val mockProfileManager = mock[ProfileManager]

  private val swappableDebugProfile = new Object with SwappableDebugProfile {
    override protected val profileManager: ProfileManager = mockProfileManager
  }

  describe("SwappableMonitorWaitProfile") {
    describe("#onMonitorWaitWithData") {
      it("should invoke the method on the underlying profile") {
        val arguments = Seq(mock[JDIArgument])

        (mockProfileManager.retrieve _).expects(*)
          .returning(Some(mockDebugProfile)).once()

        (mockDebugProfile.onMonitorWaitWithData _).expects(arguments).once()

        swappableDebugProfile.onMonitorWaitWithData(arguments: _*)
      }

      it("should throw an exception if there is no underlying profile") {
        val arguments = Seq(mock[JDIArgument])

        (mockProfileManager.retrieve _).expects(*).returning(None).once()

        intercept[AssertionError] {
          swappableDebugProfile.onMonitorWaitWithData(arguments: _*)
        }
      }
    }
  }
}
