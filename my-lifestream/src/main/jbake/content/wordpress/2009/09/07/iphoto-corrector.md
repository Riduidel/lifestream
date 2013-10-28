type: post
status: published
title: iPhoto corrector 
tags: iphoto, iptc, macosx, ruby, xmp
date: Mon Sep 07 12:22:37 CEST 2009
~~~~~~
# iPhoto corrector 

Bon, ben voil??, en une matin??e de travail, j'ai enfin ce que je voulais : un script qui, en partant des donn??es export??es par iPhoto, ??crit les m??tadonn??es XMP/IPTC qui m'int??ressent. Bon, pour l'instant, il n'y a que les mots-cl??s, mais maintenant que je ma??trise le truc, je vais sans doute en rajouter d'autres ... Enfin, si j'arrive ?? r??soudre les dramatiques probl??mes de lenteur du truc (qui sont peut-??tre d??es ?? ruby, peut-??tre ?? mon NAS - ben oui, les photos sont stock??es dessus).  
 Bref, ??a marche, et c'est l'essentiel.  
Ce qui est rigolo, en revanche, c'est que je n'utilise rien de ce que j'avais pr??vu :  

* je voulais utiliser hpricot, mais finallement, j'utilise [plist ](http://plist.rubyforge.org/)(parce que bon, la vision Apple du XML est franchement folklorique)
* je voulais utiliser ruby-xmp, mais finallement j'utilise [MiniExiftool ](http://miniexiftool.rubyforge.org/)(qui a le bon go??t d'??tre gratuite)
* Et en bonus, j'utilise [optiflag ](http://optiflag.rubyforge.org/)qui est vraiment pratique pour la ligne de commandeEt maintenant, la question cruciale : qu'est-ce que je fais de ce micro-script ?  
A priori, ??a n'int??resse que moi. Mais si toi, dans le fond, tu es int??ress?? par ce fix pour iPhoto, je veux bien le rendre public et peut-??tre m??me l'am??liorer ...