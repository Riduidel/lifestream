type: post
status: published
title: You will be assimilated
tags: biginteger, euler, groovy
date: Tue Feb 08 19:36:10 CET 2011
~~~~~~
# You will be assimilated

Il suffisait ??videment que [j'en parle](http://riduidel.posterous.com/resistance-is-futile) pour trouver la solution (certains ont appel?? ??a la technique du balayeur).  
Bon, en fait, quand j'avais tent?? ma premi??re approche brute-force, j'avais utilis?? des [BigInteger](http://groovy.codehaus.org/Groovy+Math) (choix conservateur li?? au fait que je ne savais pas quelle serait la taille du nombre) aux performances .... [limit??es](http://stackoverflow.com/q/611732/15619). Depuis peu, comme je sais que les [Long](http://download.oracle.com/javase/6/docs/api/java/lang/Long.html) vont ?? peu pr??s jusqu'?? [beaucoup](http://download.oracle.com/javase/6/docs/api/java/lang/Long.html#MAX_VALUE), je rechigne nettement moins ?? m'en servir. Et les calculs sont nettement plus rapides. Du coup, mon algorithme qui semblait ne pas converger en BigInteger met maintenant 4 minutes 30 pour terminer.Vous allez ??videment me dire que 4 minutes 30, c'est beaucoup.C'est vrai. Surtout en comparaison de [cet exemple](http://keyzero.wordpress.com/2010/01/27/project-euler-problem-12/) qui met, lui, 54 secondes pour faire le m??me boulot. Seulement, si vous regardez mon code, l?? :



[https://gist.github.com/816494](https://gist.github.com/816494)

Vous constaterez que je stocke dans un [TreeSet](http://download.oracle.com/javase/6/docs/api/java/util/TreeSet.html) les diviseurs de mon nombre. C'est ??videment beaucoup plus long. Seulement ??a m'apporte un peu de confort au debug; Et personnellement, je pr??f??re un programme 3 fois plus lent, mais 3 fois plus debuggable.

Vous constaterez ??galement que j'ai essay?? de cr??er un cache dans une TreeMap, mais aucun des essais que j'ai fait ne m'a montr?? d'am??lioration des performances. Donc j'ai abandonn??. Et si vous vous demandez pourquoi les performances n'ont pas ??t?? am??lior??es, c'est bien simple : ajouter une entr??e dans un TreeSet ou une TreeMap prend un temps important, qui peut manger le gain de performance (qui dans ce cas est, il faut le reconna??tre, assez faible). Si par exemple on remplace le TreeSet par un ArrayList (je laisse l'exercice ?? votre sagacit??), on arrive ?? un temps d'ex??cution de ... 4 minutes. Non significatif donc. Curieux quand m??me, en fait.