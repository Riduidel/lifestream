type: post
status: published
title: Non mais quel abruti !
tags: format, maven
date: Thu May 05 17:55:15 CEST 2011
~~~~~~
# Non mais quel abruti !

L?? je me sens comme un con !  
Je viens de passer deux heures dans les pr??f??rences de s??curit?? de Windows parce que maven ne trouvait plus, tout ?? coup, mon localReposiotory.

Pourtant, l'erreur ??tait sous mes yeux :

[ERROR] Could not create local repository at C:Documents and  
[ERROR] Settingsndx.m2repository  
 [ERROR] For more information about the errors and possible solutions, please read the following articles:  
[ERROR] [Help 1] [http://cwiki.apache.org/confluence/display/MAVEN/LocalRepositoryNotAccessibleException](http://cwiki.apache.org/confluence/display/MAVEN/LocalRepositoryNotAccessibleException)  


Ben oui, comme une andouille, j'ai reformat?? mon settings.xml avec [XML Tidy](http://twu.ca/divisions/technology/sst/orion/blog/tidy-notepad-and-xml.html) dans [Notepad++](http://notepad-plus-plus.org/), qui a transform?? mon localRepository (pr??sent ?? cause d'un plugin qui r????crit les settings - je vous en reparlerai peut-??tre) en

 <localRepository>C:Documents and  
 Settingsndx.m2repository</localRepository>

Balot, non ?

**Compl??tement cr??tin, oui !**