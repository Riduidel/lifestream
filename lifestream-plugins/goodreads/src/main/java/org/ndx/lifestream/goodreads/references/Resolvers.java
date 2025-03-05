package org.ndx.lifestream.goodreads.references;

import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

public abstract class Resolvers {
	public static ReferenceResolver<?> resolverFor(Reference reference,
			FinderCrudService<BookInfos, BookInfosInformer> bookService) {
		if(reference instanceof DirectAuthorReference) {
			return new DirectAuthorReferenceResolver((DirectAuthorReference) reference, bookService);
		} else if(reference instanceof FuzzyAuthorReference) {
			return new FuzzyAuthorReferenceResolver((FuzzyAuthorReference) reference, bookService);
		} else if(reference instanceof DirectBookReference) {
			return new DirectBookReferenceResolver((DirectBookReference) reference, bookService);
		} else if(reference instanceof FuzzyBookReference) {
			return new FuzzyBookReferenceResolver((FuzzyBookReference) reference, bookService);
		}
		throw new UnsupportedOperationException("unable to handle reference type "+reference.getClass().getName());
	}

}
