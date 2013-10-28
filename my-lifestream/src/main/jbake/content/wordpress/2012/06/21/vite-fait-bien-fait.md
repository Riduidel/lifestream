type: post
status: published
title: Vite fait bien fait ...
tags: communaut??, maven, svn, web
date: Thu Jun 21 17:55:26 CEST 2012
~~~~~~
# Vite fait bien fait ...

J'avais cet apr??s-midi un bug emb??tant :??  
dans mon build maven, j'utilise le plugin [buildnumber-maven-plugin](http://mojo.codehaus.org/buildnumber-maven-plugin) pour g??n??rer un num??ro de build affichable dans l'application. Il contient le num??ro de r??vision SVN utilis?? pour construire la version.Sur ma machine de dev, pas de probl??me, ??a marche bien puisque j'ai le cleint SVN en ligne de commande (je ne me souviens plus trop pourquoi d'ailleurs ... peut-??tre pour faire des maven release). Bref, ??a marche bien.Mais sur les machines de mes coll??gues, ??a foire. Or, moi, le "[chez moi ??a marche](http://chezmoicamarche.com/)", j'aime pas trop. j'ai donc pass?? le buildnumber-maven-plugin en configuration svnjava ... ou javasvn ... enfin bref, avec [ce qu'il faut](http://mojo.codehaus.org/buildnumber-maven-plugin/using-svnjava.html) pour utiliser le [maven-scm-provider-svnjava](http://code.google.com/a/apache-extras.org/p/maven-scm-provider-svnjava/). 

Or, je ne sais pas si vous savez, mais SVN 1.7 introduit une [incompatibilit??](http://blog.elijaa.org/index.php?post/2011/10/20/Error-Working-copy-is-too-old-(format-10-created-by-Subversion-1.6)) dans le format du d??p??t local. 

Et l??, c'est le drame :

[ERROR] Failed to execute goal org.codehaus.mojo:buildnumber-maven-plugin:1.1:create (default) on project autocat: Cannot get the revision information from the scm repository :  
 [ERROR] Exception while executing SCM command. svn: The path 'gni' appears to be part of a Subversion 1.7 or greater  
[ERROR] working copy. Please upgrade your Subversion client to use this  
[ERROR] working copy.

Coup de bol, je connais [le compte twitter](https://twitter.com/olamy) de l'auteur du plugin.Donc, ni une ni deux, je questionne



> @[olamy](https://twitter.com/olamy) le buildnumber-maven-plugin semble indiquer qu'il fonctionne avec SVN 1.7 ... mais pas chez moi :-(— Nicolas Delsaux (@riduidel) [June 21, 2012](https://twitter.com/riduidel/status/215828043896324096)

et ni trois ni quatre j'ai ma r??ponse



> @[riduidel](https://twitter.com/riduidel) a documentation issue :-) check that [gist.github.com/2966395](https://t.co/4evf3RaL "https://gist.github.com/2966395")— olamy (@olamy) [June 21, 2012](https://twitter.com/olamy/status/215829383431204866)

Donc, si vous voulez utiliser buildnumber-maven-plugin avec SVN 1.7, la configuration compl??te, c'est  




[https://gist.github.com/2966608](https://gist.github.com/2966608)

Et ce genre de message, c'est tout l'int??r??t des r??seaux sociaux comme twitter : pouvoir contacter rapidement les bonnes personnes.