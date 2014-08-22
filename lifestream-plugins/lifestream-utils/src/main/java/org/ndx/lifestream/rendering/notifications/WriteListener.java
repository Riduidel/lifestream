package org.ndx.lifestream.rendering.notifications;


/**
 * Listen when one input is written to output
 * @author ndx
 *
 */
public interface WriteListener {

	void inputWritten(WriteEvent event);

}
