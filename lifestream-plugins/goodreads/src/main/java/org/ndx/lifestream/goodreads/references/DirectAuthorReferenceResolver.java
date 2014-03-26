package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.Author;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Find author in the available book infos
 * @author ndx
 *
 */
public class DirectAuthorReferenceResolver extends ReferenceResolver<DirectAuthorReference> {
	public DirectAuthorReferenceResolver(DirectAuthorReference reference,
			FinderCrudService<BookInfos, BookInfosInformer> service) {
		super(reference, service);
	}

	@Override
	protected void resolveReference(DirectAuthorReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		Iterable<BookInfos> matching = resolveReferenceByTitleLookup(reference.getName(), service);
		for(BookInfos found : matching) {
			improveAuthorReferenceWith(reference, found);
		}
	}
}