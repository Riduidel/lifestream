type: post
status: published
title: Ah ben voil?? ! 
tags: biginteger, euler, groovy, maths
date: Wed Jan 13 16:14:47 CET 2010
~~~~~~
# Ah ben voil?? ! 

Je croyais que je n'avais pas la r??ponse au [probl??me 16](http://projecteuler.net/index.php?section=problems&id=16), pourtant trivial, alors que c'??tait juste une histoire de copier-coller loup??.  




[https://gist.github.com/276261](https://gist.github.com/276261)  


Dans cette solution, juste deux choses ?? noter :
* Cr??er et utiliser un [BigInteger](http://groovy.codehaus.org/Groovy+Math), c'est enfin facile et agr??alble en Groovy (alors qu'en Java, sans surcharge d'op??rateurs, c'est limite frustrant)
* La m??thode [findAll](http://groovy.codehaus.org/groovy-jdk/java/lang/String.html#findAll(java.lang.String regex, groovy.lang.Closure closure)) avec une expression r??guli??re est quand m??me bien pratique dans mon cas. J'aurais quasiment pu faire un coup de map/collect, mais bon, c'??tait pas compl??tement indispensable.

Ce qui me motive d'ailleurs pour reprendre les probl??mes d'Euler, apr??s avoir ??t?? ??chaud?? par la [conjecture de Syracuse](http://riduidel.posterous.com/conjecture-de-syracuse) ...