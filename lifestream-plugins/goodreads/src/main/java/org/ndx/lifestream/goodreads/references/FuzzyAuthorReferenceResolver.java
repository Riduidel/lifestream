package org.ndx.lifestream.goodreads.references;

import java.util.Collection;

import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Find fuzzy references to author names.
 *
 * @author ndx
 *
 */
public class FuzzyAuthorReferenceResolver extends ReferenceResolver<FuzzyAuthorReference> {

	public FuzzyAuthorReferenceResolver(FuzzyAuthorReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		super(reference, service);
	}

	/**
	 * Will be rather slow, as multiple tests are made. First we will
	 *
	 * @param reference
	 * @param service
	 * @see org.ndx.lifestream.goodreads.references.ReferenceResolver#resolveReference(org.ndx.lifestream.goodreads.references.Reference,
	 *      com.dooapp.gaedo.finders.FinderCrudService)
	 */
	@Override
	protected void resolveReference(FuzzyAuthorReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		Collection<BookInfos> found = resolveFuzzyReference(service, reference.getSearchedText());
		for (BookInfos book : found) {
			improveAuthorReferenceWith(reference, book);
		}
	}
}