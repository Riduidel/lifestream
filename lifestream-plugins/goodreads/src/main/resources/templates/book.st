
<if(book.url)>
[See it on Goodreads.com](<book.url>)
<endif>

<if(book.image)>
!["what, no image of <book.title> ?"](<book.image> "Image of <book.title>, courtesy from Goodreads.com")
<endif>

<if(book.description)>
<book.description>
<endif>

<if(series)>
## Series

<series:{serieLink |
 * <serieLink>}>
<endif>

## Review

<book.review>