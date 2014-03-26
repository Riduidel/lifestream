package org.ndx.lifestream.goodreads.references;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.ndx.lifestream.goodreads.BookInfos;
import org.ndx.lifestream.goodreads.BookInfosInformer;

import com.dooapp.gaedo.finders.FinderCrudService;

/**
 * Build a lazy map linking texts to book and try to find if searched text
 * is in
 *
 * @author ndx
 *
 */
class UnaccentedLookup {
	private static class Holder {
		static final UnaccentedLookup INSTANCE = new UnaccentedLookup();
	}

	public static UnaccentedLookup getInstance() {
		return Holder.INSTANCE;
	}

	private Map<String, Collection<BookInfos>> textsToBooks = null;

	public Collection<BookInfos> resolve(String searchedText, FinderCrudService<BookInfos, BookInfosInformer> service) {
		Map<String, Collection<BookInfos>> books = loadBooks(service);
		String searchKey = getSearchKey(searchedText);
		if(books.containsKey(searchKey)) {
			return Collections.unmodifiableCollection(books.get(searchKey));
		} else {
			return Collections.emptyList();
		}

	}

	private Map<String, Collection<BookInfos>> loadBooks(FinderCrudService<BookInfos, BookInfosInformer> service) {
		if(textsToBooks==null) {
			synchronized(this) {
				if(textsToBooks==null) {
					textsToBooks = Collections.synchronizedMap(new TreeMap<String, Collection<BookInfos>>());
					for(BookInfos b : service.findAll()) {
						String searchKey = getSearchKey(b.getTitle());
						if(!textsToBooks.containsKey(searchKey)) {
							textsToBooks.put(searchKey, new ArrayList<BookInfos>());
						}
						textsToBooks.get(searchKey).add(b);
					}
				}
			}
		}
		return textsToBooks;
	}

	private String getSearchKey(String text) {
		return StringUtils.stripAccents(text).toLowerCase();
	}
}