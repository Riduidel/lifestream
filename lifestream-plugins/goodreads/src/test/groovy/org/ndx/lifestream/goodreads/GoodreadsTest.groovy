import org.ndx.lifestream.goodreads.*

String mail = System.getProperty("goodreads.mail")
assert mail!=null
String password = System.getProperty("goodreads.password")
assert password!=null

Goodreads exporter = new Goodreads()
exporter.username = mail
exporter.password = password

List rows = exporter.getCSV()
assert rows.size()>0