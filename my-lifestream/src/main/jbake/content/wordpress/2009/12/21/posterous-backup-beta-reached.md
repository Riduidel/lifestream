type: post
status: published
title: Posterous backup : beta reached ! 
tags: backup, groovy, posterous, posterousbackup
date: Mon Dec 21 10:56:00 CET 2009
~~~~~~
# Posterous backup : beta reached ! 

<span style="font-size:xx-small;">Exceptionnellement, j'écris en anglais histoire de toucher la plus large audience possible ...</span>  


<span style="font-size:medium;">One easy way to backup all of your posterous blogs</span>  


As anybody (and as [the posterous creators stated](http://blog.posterous.com/the-posterous-api-lets-you-post-to-posterous)), I don't want my data to be locked in an application, be it as smart as posterous currently is.As a consequence, I've decided it was time to ensure my posts (this one like all the others) would remains even if posterous collapsed (what I absolutely not want).For that, relying upon posterous API, I wrote a groovy script that will backup all my posts, photos, sounds, and videos on the computer I use.

<span style="font-size:medium;">Organized backup</span>

<span style="font-size:x-small;">This backup is quite simply organized :</span><span style="font-size:medium;"><br></br></span><span style="font-size:x-small;">One folder for each site.<br></br></span><span style="font-size:x-small;">In each folder, entries keep the file name they have in posterous, followed by a nice .xml extension.<br></br></span><span style="font-size:x-small;">Each media associated to an entry uses the entry name, followed by _#anumer, where the #number is the media number.<br></br></span><span style="font-size:x-small;">Notice I also do URI replacement in the entry, for backup media to be used instead of posterous ones.<br></br></span>

<span style="font-size:x-small;">As an example, my own backup contains the following<br></br></span>

<span style="font-size:x-small;"><br></br></span><span style="font-family:Courier New, monospace;"><span style="font-size:x-small;">+---<a href="http://knackfx.posterous.com">knackfx.posterous.com</a><br></br>|   all-your-bases-are-belong-to-rest.xml<br></br> |   cant-wait-for-the-671.xml<br></br>|   knack-it.xml<br></br>|   netbeans-671-available-for-download.xml<br></br>|   rest-in-game.xml<br></br>|   serve-me-good-games.xml<br></br>|   whats-new-today.xml<br></br>| <br></br> ---<a href="http://riduidel.posterous.com">riduidel.posterous.com</a><br></br>   il-mavait-prevenu-le-bougre.xml<br></br>   il-mavait-prevenu-le-bougre_0.png<br></br>   il-mavait-prevenu-le-bougre_1.jpg<br></br>   tester-lutilisation-de-la-memo.xml<br></br>    tester-lutilisation-de-la-memo_0.gif<br></br>   tester-lutilisation-de-la-memo_1.jpg<br></br>   the-big-band-theory.xml<br></br>   the-gaf-collection-collected.xml<br></br>   the-gaf-collection-collected_0.jpg<br></br>    the-gaf-collection-collected_1.jpg<br></br>   xkcd-movie-narrative-charts-0.xml<p></p></span></span><span style="font-size:x-small;"><span style="font-family:Arial, helvetica, sans-serif;">Well, in fact, I only shows here an excerpt, since backup generates 205 files on my machine.</span></span><span style="font-family:Courier New, monospace;"><span style="font-size:x-small;"><br></br></span></span>

<span style="font-family:Arial, helvetica, sans-serif;"><span style="font-size:medium;">How to use</span></span><span style="font-size:x-small;"><span style="font-family:Arial, helvetica, sans-serif;"><br></br></span></span>

<span style="font-family:Arial, helvetica, sans-serif;">Here comes the hardest part : installing this backup script. There are two little prerequisites :</span><span style="font-family:Arial, helvetica, sans-serif;"><span style="font-size:medium;"><br></br></span></span><span style="font-family:Arial, helvetica, sans-serif;"></span>
1. Install a recent [Java](http://www.java.com/) (theorically, your machine should laready use a Java 5 or Java 6 compatible version, what you can check by issuing the java -version command in a shell)
2. Install a recent [Groovy](http://groovy.codehaus.org/Installing+Groovy) (which is just a little harder).
3. Download the [posterous.groovy](https://dl.dropbox.com/u/2753331/posterous.groovy) script (this is quite easier). You can put this script anywhere on your disk.

One all is downloaded, go in the script folder and

<pre>groovy posterous.groovy</pre>

should output the following



<pre>This is posterous export script v 0.1error: Missing required options: u, p, ousage: groovy posterous.groovy -u email@posterous -p password -ooutputFolder -h,--help provides full help and usage information -o,--output  An eventually existing output folder, where all data will be output. Beware, if some data exists in that folder, it may be overwritten. -p,--password  Unfortunatly one have to give its password to this little script -u,--username  Sets posterous mail address here</pre>Notice that first run may show error messages, since grape download (the tool used by groovy to pull dependencies from the interweb) has some issues regarding used dependencies.

<span style="font-size:medium;">The result</span>

So, as told before, this script generates a bunch of files on your machine.<span style="font-size:medium;"><br></br></span>Each of this file is an XML file containing all infos from posterous (in another format I plan to use to pull data from various websites).This format seems to me quite legible :

<post>  
  <title><!-- posterous title--></title>  
  <date>2008-12-31T17:48:00.000+0100<!-- posterous post date in a quite standard form --></date>  
 <author>  
  <name><!-- author name --></name>  
  <pic><!-- author pic --></pic>  
 </author>  
 <body><![CDATA[<!-- post body, protected from XML interpreting by the CDATA section  
    ]]></body>  
 <comments>  
 <comment>  
 <author>  
  <name></name>  
  <pic></pic>  
 </author>  
  <date></date>  
 <body><![CDATA[  
   ]]></body>  
 </comment>  
</comments>  
</post>  


I thinkk this format will allow you to execute any complementary treatment, as nothing since to be lost from original (expect medias file sizes, which you can see in your OS).Additionnaly notice that only medias stored by posterous are downloaded, not Flickr images or Youtube videos.

<span style="font-size:medium;">The license</span>

This script uses a creative commons license : [paternity, share-alike, no commercial use](http://creativecommons.org/licenses/by-nc-sa/3.0/).<span style="font-size:medium;"><br></br></span>

<span style="font-size:medium;">Feed me back !</span><span style="font-size:medium;"><br></br></span>

<span style="font-size:x-small;">Any feed back you can send me will be greatly appreciated ! Don't hesitate to use comments below to mark your appreciation.</span><span style="font-size:medium;"><br></br></span>