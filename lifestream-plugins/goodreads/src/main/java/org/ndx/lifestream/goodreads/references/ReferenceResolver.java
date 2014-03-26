package org.ndx.lifestream.goodreads.references;

import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.ndx.lifestream.goodreads.Author;
import org.ndx.lifestream.goodreads.Book;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;
import org.ndx.lifestream.goodreads.Serie;

import com.dooapp.gaedo.finders.FinderCrudService;
import com.dooapp.gaedo.finders.QueryBuilder;
import com.dooapp.gaedo.finders.QueryExpression;

public abstract class ReferenceResolver<ReferenceType extends Reference> implements Callable<Void>{
	private static final Logger logger = Logger.getLogger(DirectAuthorReferenceResolver.class.getName());
	protected final ReferenceType reference;
	protected final FinderCrudService<BookInfos, BookInfosInformer> service;
	public ReferenceResolver(ReferenceType reference,
			FinderCrudService<BookInfos, BookInfosInformer> service) {
		super();
		this.reference = reference;
		this.service = service;
	}
	@Override
	public Void call() throws Exception {
		try {
			resolveReference(reference, service);
		} catch(Exception e) {
			if (logger.isLoggable(Level.WARNING)) {
				logger.log(Level.WARNING, "unable to resolve reference "+reference, e);
			}
		}
		return null;
	}
	protected abstract void resolveReference(ReferenceType reference, FinderCrudService<BookInfos, BookInfosInformer> service);

	protected void improveAuthorReferenceWith(Reference reference, BookInfos found) {
		if (found instanceof Author) {
			Author author = (Author) found;
			reference.setDestination(author);
		}
	}

	protected void improveBookReferenceWith(Reference reference, BookInfos found) {
		BookInfos destination = reference.getDestination();
		if (destination == null) {
			if ((found instanceof Book) || (found instanceof Serie)) {
				reference.setDestination(found);
			}
		} else {
			if (destination instanceof Serie) {
				if (found instanceof Serie) {
					reference.setDestination(found);
				}
			} else {
				if ((found instanceof Book) || (found instanceof Serie)) {
					reference.setDestination(found);
				}
			}
		}
	}

	protected Iterable<BookInfos> resolveReferenceByTitleLookup(final String referenceName, FinderCrudService<BookInfos, BookInfosInformer> service) {
		return service.find().matching(new QueryBuilder<BookInfosInformer>() {

			@Override
			public QueryExpression createMatchingExpression(BookInfosInformer informer) {
				return informer.getTitle().equalsToIgnoreCase(referenceName.trim());
			}
		}).getAll();
	}

	protected Collection<BookInfos> resolveFuzzyReference(FinderCrudService<BookInfos, BookInfosInformer> service, String searchedText) {
		Collection<BookInfos> found = resolveRefereferenceByUnaccentedBrowsing(searchedText, service);
		if(found.size()==0) {
			// now playing the devil's game with levenstein
			found = resolveReferenceBYDangerousLevenshteinDistanceUsage(searchedText, service);
		}
		return found;
	}

	private Collection<BookInfos> resolveReferenceBYDangerousLevenshteinDistanceUsage(String searchedText,
					FinderCrudService<BookInfos, BookInfosInformer> service) {
		return LevenshteinLookup.getInstance().resolve(searchedText, service);
	}


	protected Collection<BookInfos> resolveRefereferenceByUnaccentedBrowsing(String searchedText, FinderCrudService<BookInfos, BookInfosInformer> service) {
		return UnaccentedLookup.getInstance().resolve(searchedText, service);
	}
}
