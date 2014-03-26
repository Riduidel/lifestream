package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;
import org.ndx.lifestream.goodreads.Serie;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Find author in the available book infos
 *
 * @author ndx
 *
 */
public class DirectBookReferenceResolver extends ReferenceResolver<DirectBookReference> {
	public DirectBookReferenceResolver(DirectBookReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		super(reference, service);
	}

	@Override
	protected void resolveReference(DirectBookReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		String searched = reference.getText();
		Iterable<BookInfos> matching = resolveReferenceByTitleLookup(searched, service);
		for (BookInfos found : matching) {
			improveBookReferenceWith(reference, found);
		}
	}
}