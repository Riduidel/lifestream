package org.ndx.lifestream.goodreads;

import groovy.xml.MarkupBuilder

/**
 * A class extracting content from the infamous Expando to a more "object" notion
 */
class Book {
	String title
	String author
	String additionnalAuthors
	String isbn10
	String isbn13
	Integer rating
	Integer average
	Integer pages = 0;
	String initialPublication
	String dateRead
	TreeSet tags = new TreeSet();
	String review
	/** private notes, as opposed to public #review */
	String notes
	Integer owns = line[columns['Owned Copies']] as Integer


	/**
	 * Save book as xml file in the given folder
	 * TODO replace by some template usage
	 */
	def save(String folder) {
		File toWrite = new File(folder+"/"+isbn13+".jsg.xml")
		def writer = new StringWriter()
		def xml = new MarkupBuilder(writer)

		xml.div(class:"book") {
			ul(class:"authors") {
				li(class:"author", author)
				if(additionnalAuthors.size()>0) {
					for(String author : additionnalAuthors.split(",")) {
						li(class:"author", author)
					}
				}
			}
			// TODO a little amazoning !
			div(id:"cover") {
				img(src:"http://books.google.com/books?vid=ISBN"+isbn13+"&printsec=frontcover")
			}
			div(id:"rating"){
				span(class:"progress", id:"personnal", rating)
				span(class:"progress", id:"average", average)
			}
			div(class:"initialPublication", initialPublication)

		}
		def bookHtml = writer.toString();

		writer = new StringWriter()
		xml = new MarkupBuilder(writer)
		xml.pi("xml":["version":"1.0"])
		if(xsl!=null && xsl.trim().length()>0) {
			xml.pi("xml-stylesheet":["href":xsl, "type":"text/xsl"])
		}
		xml.post() {
			title(title)
			book {
				title(title)
				author(author+(additionnalAuthors.size()>0 ? ", "+additionnalAuthors : ""))
				isbn10(isbn10)
				isbn13(isbn13)
				rating{
					personnal(rating)
					average(average)
				}
				initialPublication(initialPublication)
			}
			if(read!=null)
				date(read)
			tags {
				tags.each { t ->
					tag(t);
				}
			}
			body {
				yieldUnescaped("<![CDATA["+bookHtml+"<div class=\"review\">"+review+"</div>]]>")
			}
		}
		toWrite.setWritable(true);
		toWrite.parentFile.mkdirs();
		if(toWrite.exists()) {
			toWrite.delete()
		}
		toWrite.write(writer.toString(), 'UTF-8')
	}
}