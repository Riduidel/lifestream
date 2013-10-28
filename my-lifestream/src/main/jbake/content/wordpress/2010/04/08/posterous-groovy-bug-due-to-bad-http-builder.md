type: post
status: published
title: Posterous groovy bug due to bad HTTP Builder dependency. 
tags: backup, bug, grape, groovy, httpbuilder, posterous, posterousbackup
date: Thu Apr 08 09:17:05 CEST 2010
~~~~~~
# Posterous groovy bug due to bad HTTP Builder dependency. 

I allow myself to post this on my posterous blog, as a follow-up to my message concerning [the availability of the script](http://riduidel.posterous.com/posterous-backup-beta-reached).

2010/4/7 E*** B******* L*** <******>  
 >> Hi Nicolas,

Hi,??

>  
> I just tried to use your script on my Ubuntu machine but it can not find the following class:>

> unable to resolve class groovyx.net.http.URIBuilder  
 >  
> Can you maybe tell me how I can install this class to be available in Java? I found it with Google but don't know how to make it available for your script / the JVM.> [http://www.jarvana.com/jarvana/view/org/codehaus/groovy/http-builder/0.4.1/http-builder-0.4.1.jar!/groovyx/net/http/URIBuilder.class?classDetails=ok](http://www.jarvana.com/jarvana/view/org/codehaus/groovy/http-builder/0.4.1/http-builder-0.4.1.jar!/groovyx/net/http/URIBuilder.class?classDetails=ok)



Will have to dive deep in groovy internals for that, I hope you'll understand the whole.

The posterous groovy script, for getting all the entries, make use of the groovy version of HTTP Builder : [http://groovy.codehaus.org/modules/http-builder/](http://groovy.codehaus.org/modules/http-builder/)This dependency is resolved using groovy specific mechanism : [grape](http://groovy.codehaus.org/Grape). You can see the @Grab instruction for HTTP builder at line 289 :



<span style="font-family:courier new, monospace;">@Grab(group='org.codehaus.groovy.modules.http-builder', module='http-builder', version='0.5.0-RC2')</span><span style="font-family:courier new, monospace;"><br></br></span>

I'll make the guess you don't know at all groovy grape. It's a kind of "live" version of Ruby Gems : your program dependencies are expressed using annotations in the body of your program. Once run by the groovy runtime, each time one of these annotations is encountered, grape checks if the dependency is already known (that's to say already present as <span style="font-family:courier new, monospace;">~/.groovy/grapes/"group"/"module"/jars/"module.version"</span>). If not, it is downloaded and its checksum is verified.

Currently, the HTTP Builder website says on its front page



> So I had a couple people mention that they had trouble using Grape to download RC3 due to a bad checksum on the POM file. I tried re-deploying it, (thinking it was a bad upload) and now I'm getting a _bunch_ of people saying they can't download it!  
  


> As a work-around, please add the following line to ~/.groovy/grapeConfig.xml: <property name="ivy.checksums" value=""/>. This should allow Grape to ignore the checksum and download the file, until I've got the checksum problem resolved. Stay tuned!  
  


May I suggest you to do the mentionned operation ? Notice however this operation seems to disable checksum verification on all jars, not only on HTTBuilder one. So, don't forget to remvoe that line once the application is run successfully !>

> Greetings from Germany,  
>Thanks for the feedback !