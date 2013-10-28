type: post
status: published
title: logger le chargement de beans avec Spring
tags: code, informatique, java
date: Wed Oct 04 18:47:00 CEST 2006
~~~~~~
# logger le chargement de beans avec Spring

Il semble que Spring fasse un truc bizarre ?? ces logs. Donc, pour les retrouver, il faut faire ce que propose le premier commentaire de ce post de [Digital Annealing](http://www.neilbartlett.com/mtweblog/archives/000112.html "Digital Annealing: Spring and Log4j and tomcat"), c'est-??-dire tout simplement ajouter au fichier log4j.properties la ligne[JAVA]log4j.logger.org.springframework=DEBUG[/JAVA]Et l??, les millions de lignes de log vont sortir.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)