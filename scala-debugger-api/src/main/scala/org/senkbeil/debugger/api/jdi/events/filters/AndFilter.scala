package org.senkbeil.debugger.api.jdi.events.filters

import org.senkbeil.debugger.api.jdi.events.JDIEventProcessor
import org.senkbeil.debugger.api.jdi.events.filters.processors.{AndFilterProcessor, MethodNameFilterProcessor}

/**
 * Represents a local filter that will return the result of ANDing multiple
 * filters together.
 *
 * @example AndFilter(Filter1, Filter2) will only pass if both filters pass
 *
 * @param filters The collection of filters to evaluate
 */
case class AndFilter(filters: JDIEventFilter*) extends JDIEventFilter {
  /**
   * Creates a new JDI event processor based on this filter.
   *
   * @return The new JDI event processor instance
   */
  override def toProcessor: JDIEventProcessor =
    new AndFilterProcessor(this)
}
