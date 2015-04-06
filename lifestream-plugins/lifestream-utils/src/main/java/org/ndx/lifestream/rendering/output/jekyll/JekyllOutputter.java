package org.ndx.lifestream.rendering.output.jekyll;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.ndx.lifestream.rendering.OutputWriter;
import org.ndx.lifestream.rendering.model.Input;
import org.ndx.lifestream.rendering.model.Linkable;
import org.ndx.lifestream.rendering.output.AbstractOutputter;
import org.ndx.lifestream.rendering.output.FileNameUtils;
import org.ndx.lifestream.rendering.path.PathBuilder;
import org.ndx.lifestream.rendering.path.PathBuilderFinder;
import org.ndx.lifestream.rendering.path.PathNavigator;
import org.ndx.lifestream.rendering.path.RelativePath;
import org.ndx.lifestream.rendering.path.RelativePathBuilder;
import org.ndx.lifestream.utils.ThreadSafeSimpleDateFormat;

/**
 * Notice here file name is to be manipulated
 * @author ndx
 *
 */
public class JekyllOutputter extends AbstractOutputter implements OutputWriter {
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	private static final ThreadSafeSimpleDateFormat FORMATTER = new ThreadSafeSimpleDateFormat(DATE_FORMAT);
	
	public static class JekyllPathBuilderFinder extends PathBuilderFinder {
		private static class JekyllRelativePathBuilder extends RelativePathBuilder {
			public JekyllRelativePathBuilder(Input input) {
				super(input);
			}

			@Override
			public PathNavigator build(String extension) {
				Date writeDate = input.getWriteDate();
				List<String> returned = new ArrayList<>();
				Iterator<String> iterator = input.getExpectedPath().iterator();
				while(iterator.hasNext()) {
					String filename = FileNameUtils.simplify(iterator.next());
					if(iterator.hasNext()) {
						returned.add(filename);
					} else {
						filename = (writeDate==null ? "2001-01-01" : FORMATTER.format(writeDate))+"-"+filename.replace('/', '_')+".md";
						returned.add(filename);
					}
				}
				return new RelativePath(returned);
			}
			
		}

		@Override
		protected RelativePathBuilder newPathBuilderForInput(Input input) {
			return new JekyllRelativePathBuilder(input);
		}
	}
	
	@Override
	protected PathBuilderFinder getPathBuilderFinder() {
		return new JekyllPathBuilderFinder();
	}

	@Override
	public String href(Input from, Linkable to) {
		return href(from, to, HTML);
	}

	@Override
	public String link(Input from, Linkable to, String text) {
		return markdownLink(from, to, text, HTML);
	}

	@Override
	protected PathNavigator toRealPath(Linkable input) {
		return toRealPath(input, MARKDOWN);
	}
}
