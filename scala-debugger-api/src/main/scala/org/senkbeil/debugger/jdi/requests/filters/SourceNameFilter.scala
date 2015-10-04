package org.senkbeil.debugger.jdi.requests.filters

import org.senkbeil.debugger.jdi.requests.processors.{SourceNameProcessor, JDIRequestProcessor}

/**
 * Represents a filter used to limit requests to reference types with whom a
 * source name matches the specified pattern.
 *
 * @note Only used by ClassPrepareEvent.
 *
 * @param sourceNamePattern Reference types with source names that do match this
 *                          pattern will be included, can only take normal
 *                          characters and wildcard "*", meaning "*.Foo" or
 *                          "java.*"
 */
case class SourceNameFilter(
  sourceNamePattern: String
) extends JDIRequestFilter {
  /**
   * Creates a new JDI request processor based on this filter.
   *
   * @return The new JDI request processor instance
   */
  override def toProcessor: JDIRequestProcessor =
    new SourceNameProcessor(this)
}
