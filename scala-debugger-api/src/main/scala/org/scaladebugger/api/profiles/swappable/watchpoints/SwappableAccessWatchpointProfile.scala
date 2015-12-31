package org.scaladebugger.api.profiles.swappable.watchpoints

import org.scaladebugger.api.lowlevel.JDIArgument
import org.scaladebugger.api.pipelines.Pipeline.IdentityPipeline
import org.scaladebugger.api.profiles.swappable.SwappableDebugProfile
import org.scaladebugger.api.profiles.traits.watchpoints.AccessWatchpointProfile

import scala.util.Try

/**
 * Represents a swappable profile for access watchpoint events that redirects
 * the invocation to another profile.
 */
trait SwappableAccessWatchpointProfile extends AccessWatchpointProfile {
  this: SwappableDebugProfile =>

  override def onAccessWatchpointWithData(
    className: String,
    fieldName: String,
    extraArguments: JDIArgument*
  ): Try[IdentityPipeline[AccessWatchpointEventAndData]] = {
    withCurrentProfile.onAccessWatchpointWithData(
      className,
      fieldName,
      extraArguments: _*
    )
  }
}