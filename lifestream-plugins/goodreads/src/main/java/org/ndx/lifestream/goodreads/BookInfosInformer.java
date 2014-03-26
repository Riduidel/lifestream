package org.ndx.lifestream.goodreads;

import com.dooapp.gaedo.finders.Informer;
import com.dooapp.gaedo.finders.informers.StringFieldInformer;

public interface BookInfosInformer extends Informer<BookInfos> {
	StringFieldInformer getId();
	StringFieldInformer getTitle();
}
