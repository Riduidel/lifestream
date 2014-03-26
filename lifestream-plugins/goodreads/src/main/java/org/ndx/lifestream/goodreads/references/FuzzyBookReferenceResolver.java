package org.ndx.lifestream.goodreads.references;

import java.util.Collection;

import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

public class FuzzyBookReferenceResolver extends ReferenceResolver<FuzzyBookReference> {

	public FuzzyBookReferenceResolver(FuzzyBookReference reference,
			FinderCrudService<BookInfos, BookInfosInformer> service) {
		super(reference, service);
	}

	@Override
	protected void resolveReference(FuzzyBookReference reference, FinderCrudService<BookInfos, BookInfosInformer> service) {
		Collection<BookInfos> references = resolveFuzzyReference(service, reference.getText());
		for(BookInfos b : references) {
			improveBookReferenceWith(reference, b);
		}
	}
}