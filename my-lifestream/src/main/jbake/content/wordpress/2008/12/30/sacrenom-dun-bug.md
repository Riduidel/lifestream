type: post
status: published
title: sacr??nom d'un bug ! 
tags: build, java, majickproperties, maven, test
date: Tue Dec 30 22:26:46 CET 2008
~~~~~~
# sacr??nom d'un bug ! 

C'est le genre de trucs qui m'??nervent, ??a.

J'ai voulu ??tre s??rieux, j'ai fait une tonne de tests unitaires, et sans m??me avoir de rapports de couverture de test dans maven, je suis d??ja certain d'avoir au moins 75 % du code test??.

Enfin, test?? ... dans Eclipse.

Parce que dans Maven, c'est une autre histoire. J'ai une esp??ce de [sale foutu bug](http://code.google.com/p/majick-properties/issues/detail?id=1) qui fait que mon premier test unitaire Swing (qui utilise bien s??r FEST-Swing) ne s'arr??te pas.

Ah, si mon fan ??tait l?? ! Il me dirait bien s??r que c'est un sale probl??me d'utilisation de l'EDT. Ouais, enfin bon, quand je regarde le code de [mon test](http://code.google.com/p/majick-properties/source/browse/trunk/majick-properties/src/test/java/org/ndx/majick/ui/enumeration/EnumUIProviderTest.java), il n'y a pas grand chose qui me choque.

Et encore moins quand j'utilise [les m??thodes de FEST-Swing](http://fest.easytesting.org/swing/wiki/pmwiki.php?n=FEST-Swing.EDT) pour m'assurer que je fais tout dans l'EDT.

Bref, je vais ??tre bon pour fouiller.

Ce que ??a a de particuli??rement aga??ant, c'est que comme le test ne se termine pas, je n'arrive pas ?? faire de 

mvn deploy

Et ??a, ??a m'aga??ce avec une force !

Bah ! Je trouverai la solution demain.