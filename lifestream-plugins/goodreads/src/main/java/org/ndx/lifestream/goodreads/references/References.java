package org.ndx.lifestream.goodreads.references;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.ndx.lifestream.goodreads.Book;

public class References {
	private static Pattern possibleReferencesFinder = Pattern
			.compile("\\[(a|author|b|book):([^\\]]*)\\]");

	/**
	 * Load all references from this book
	 * @param book book to find references in
	 * @param references map to put references in. Key is the canonical text for reference
	 * and value is a reference descriptor
	 */
	public void load(Book book, Map<String, Reference> references) {
		Collection<Reference> foundReferences = prepareReferences(book.getReview());
		Collection<Reference> usedReference = new ArrayList<>();
		for(Reference ref : foundReferences) {
			synchronized(references) {
				if(references.containsKey(ref.getId())) {
					ref = references.get(ref.getId());
				} else {
					references.put(ref.getId(), ref);
				}
			}
			usedReference.add(ref);
		}
		book.setReferences(usedReference);
	}

	private Reference buildReferenceFrom(String referenceText,
			String modeSelector, String referenceTextPart) {
		switch (modeSelector) {
		case "a":
			// sometimes goodreads isn't really clear about how "author:" are transformed into "a:"
			if(referenceText.indexOf('|')>0)
				return new DirectAuthorReference(referenceText, referenceTextPart);
			// we fallback to "fuzzy author mode"
		case "author":
			return new FuzzyAuthorReference(referenceText, referenceTextPart);
		case "b":
			if(referenceText.indexOf('|')>0)
				return new DirectBookReference(referenceText, referenceTextPart);
		case "book":
			return new FuzzyBookReference(referenceText, referenceTextPart);
		}
		throw new UnknownReferenceException("what do reference \""+referenceText+"\" mean ?");
	}

	public Collection<Reference> prepareReferences(String review) {
		Collection<Reference> returned = new ArrayList<>();
		Matcher matcher = possibleReferencesFinder.matcher(review);
		while (matcher.find()) {
			String pattern = matcher.group();
			returned.add(buildReferenceFrom(pattern, matcher.group(1),
					matcher.group(2)));
		}
		return returned;
	}

}
