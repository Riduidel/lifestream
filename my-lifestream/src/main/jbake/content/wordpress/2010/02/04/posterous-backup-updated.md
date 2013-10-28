type: post
status: published
title: Posterous backup updated ! 
tags: backup, groovy, posterous, posterousbackup
date: Thu Feb 04 14:50:00 CET 2010
~~~~~~
# Posterous backup updated ! 

So, thanks to [a comment from Richard](http://riduidel.posterous.com/posterous-backup-beta-reached#pcomment_commentunit_2087242), I discovered on saturday that my little posterous backup script did not backup private posts.  
 I initially found it weird since I knew that, in my script, I correctly set the basic authentication mechanism.  
In fact, it was due to the way posterous api is built, I think. From what I've understood, there is no session maintained on server side (weird choice, [Sachin](http://sachin.posterous.com/)).  
 So, from [Tom's advice](http://permalink.gmane.org/gmane.comp.lang.groovy.user/47128), I updated my script to ensure the basic authentication token where written on all requests.  
Now, Richard, posterous export should grab all your entries, even private ones !  
 Besides, I've also copied that script on [git](http://gist.github.com/294686) (since relying solely upon dropbox survival does not seems enough to me). So, if you want to update it, it must be rather easy, no ?