package org.ndx.lifestream.wordpress;

import com.dooapp.gaedo.finders.Informer;
import com.dooapp.gaedo.finders.informers.StringFieldInformer;

public interface PostInformer extends Informer<Post> {
	StringFieldInformer getText();
	StringFieldInformer getSource();
}
