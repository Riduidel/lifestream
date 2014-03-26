package org.ndx.lifestream.goodreads.references;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;

import org.apache.commons.lang3.StringUtils;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Build a lazy map linking texts to book and try to find if searched text is in
 *
 * @author ndx
 *
 */
class LevenshteinLookup {
	private static class Holder {
		static final LevenshteinLookup INSTANCE = new LevenshteinLookup();
	}

	public static LevenshteinLookup getInstance() {
		return Holder.INSTANCE;
	}

	private Map<String, Collection<BookInfos>> textsToBooks = Collections.synchronizedMap(new TreeMap<String, Collection<BookInfos>>());

	private Collection<String> currentlySearched = Collections.synchronizedSet(new TreeSet<String>());

	public Collection<BookInfos> resolve(String searchedText, FinderCrudService<BookInfos, BookInfosInformer> service) {
		if (!textsToBooks.containsKey(searchedText)) {
			if (currentlySearched.contains(searchedText)) {
				synchronized (searchedText) {
					while (!textsToBooks.containsKey(searchedText)) {
						try {
							searchedText.wait(100);
						} catch (InterruptedException e) {
						}
					}
					return textsToBooks.get(searchedText);
				}
			} else {
				currentlySearched.add(searchedText);
				textsToBooks.put(searchedText, new LevenshteinListBuilder().resolveReferences(searchedText, service));
				currentlySearched.remove(searchedText);
			}
		}
		return textsToBooks.get(searchedText);
	}

	private static class LevenshteinListBuilder {

		public Collection<BookInfos> resolveReferences(String searchedText, FinderCrudService<BookInfos, BookInfosInformer> service) {
			SortedMap<Integer, Collection<BookInfos>> distances = new TreeMap<>();
			for (BookInfos b : service.findAll()) {
				int distance = StringUtils.getLevenshteinDistance(searchedText, b.getTitle());
				if (distances.get(distance) == null) {
					distances.put(distance, new LinkedList<BookInfos>());
				}
				distances.get(distance).add(b);
			}
			// now get first non null value
			int index = 0;
			int maxDistance = searchedText.length();
			while(!distances.containsKey(index) && index<=maxDistance) { index++; }
			if(distances.containsKey(index))
				return distances.get(index);
			else
				return Collections.emptyList();
		}
	}
}