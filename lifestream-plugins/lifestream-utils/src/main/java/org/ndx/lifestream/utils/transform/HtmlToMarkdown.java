package org.ndx.lifestream.utils.transform;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.overzealous.remark.Options;
import com.overzealous.remark.Remark;

public class HtmlToMarkdown {
	private static Remark remark;
	public static Remark getRemark() {
		if(remark==null) {
			synchronized(HtmlToMarkdown.class) {
				if(remark==null) {
					remark = new Remark(Options.multiMarkdown());
				}
			}
		}
		return remark;
	}

	/**
	 * Cleanup and transform html. Html is first cleaned up using htmlcleaner,
	 * then transformed using {@link #transformValidXhtml(String)}
	 *
	 * @param html
	 *            source html fragment
	 * @return markdown, what else !
	 */
	public static String transformHtml(String html) {
		if(html==null)
			return html;
		if(html.trim().length()==0)
			return html.trim();
		Document parsed = Jsoup.parse(html);
		return getRemark().convert(parsed.toString());
	}

}
