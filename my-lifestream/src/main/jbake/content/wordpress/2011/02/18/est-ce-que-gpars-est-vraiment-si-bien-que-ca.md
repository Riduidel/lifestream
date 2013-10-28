type: post
status: published
title: Est-ce que GPars est vraiment si bien que ??a ?
tags: euler, gpars, groovy
date: Fri Feb 18 15:55:57 CET 2011
~~~~~~
# Est-ce que GPars est vraiment si bien que ??a ?

Depuis un moment, je vois appara??tre [GPars](http://gpars.codehaus.org/) sur mon radar (ou plus exactement, dans des [tweets](https://twitter.com/#!/glaforge/status/37273434102697984)[envoy??s](https://twitter.com/#!/glaforge/status/35452212645871616) par Guillaume Laforge).  
Et comme les probl??mes du project Euler sont souvent parall??lisables, je me suis dit que c'??tait une bonne occasion de tester ??a.  
J'ai donc d'abord cod?? une version "standard" de la solution au [probl??me 31](http://projecteuler.net/index.php?section=problems&id=31), n'utilisant GPars que pour faire un bon gros collectParallel(...), avant d'??crire la version que voici qui fait non seulement du collectParallel, mais aussi du runForkJoin, ce qui est vraiment r??jouissant.  




[https://gist.github.com/833742](https://gist.github.com/833742)  


Bon, j'aurais bien utilis?? ??galement [Perf4J](http://perf4j.codehaus.org/), pour remplacer mes bons vieux [System.currentTimeMillis()](http://download.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#currentTimeMillis()), mais je n'ai pas trouv?? comment. Et c'est bien dommage.  
Cela dit GPars est de la belle ouvrage. Je me souviens encore avec ??motion des [ExecutorService](http://download.oracle.com/javase/1.5.0/docs/api/java/util/concurrent/ExecutorService.html) et (encore longtemps avant), des inf??mes [Runnable](http://download.oracle.com/javase/1.4.2/docs/api/java/lang/Runnable.html) ?? enquiller dans un [Thread](http://download.oracle.com/javase/1.4.2/docs/api/java/lang/Thread.html). A c??t?? de ces horreurs, l'id??e d'??crire GParsPool.withPool(2) {-> ...} me remplit d'aise. Je dois toutefois l??g??rement nuancer mon propos. Comme [cette question](http://stackoverflow.com/questions/5040326/concurrentexception-nullpointerexception-in-gparspool-runforkjoin) que j'ai pos??e sur StackOverflow le montre, l'API de GParsPool n'est pas totallement document??e sur le site de GPars (m??me si elle l'est en revanche sur [le site de l'auteur](http://www.jroller.com/vaclav/entry/parallel_recursion), je dois d'ailleurs remercier encore ici sa r??ponse claire et rapide). Et ??a, je dois bien l'avouer, c'est p??nible.  
Bon, arr??tons-l??.  
J'ai quand m??me gagn?? un peu de temps d'ex??cution par rapport ?? la version initiale (particuli??rement bourrine, je dois bien le reconna??tre).  
J'ai en revahcne ??tg?? nettement moins vite qu'une version totallement r??cursive du m??me code, ce qui est normal, puisqu'il y a bien plus de synchronisation. Ce que j'ai gagn??, ??galement, par rapport ?? la version r??cursive, c'est de l'empilement d'appel, puisque l??, je fais beaucoup plus de passage de messages assynchrones, et beaucoup ??moins d'appels r??cursifs au m??me code.  
La conclusion est quand m??me claire : GPars, au moins pour ce que j'en ai fait l?? (et ce que je vais en faire dans les probl??mes suivants), c'est de la balle.  
