type: post
status: published
title: Trouver le plus grand palindrome 
tags: euler, groovy, maths
date: Thu Dec 24 11:55:54 CET 2009
~~~~~~
# Trouver le plus grand palindrome 

Bon, [le probl??me 4](http://projecteuler.net/index.php?section=problems&id=4) ??tait assez facile, d'autant plus que Groovy propose quelques m??thodes tr??s pratiques pour travailler sur les collections.  


[https://gist.github.com/263142](https://gist.github.com/263142)Il y a juste une petite ruse math??matique pour diviser le temps de calcul par deux : la [multiplication](http://www.google.fr/url?sa=t&source=web&ct=res&cd=9&ved=0CCYQFjAI&url=http%3A%2F%2Ffr.wikipedia.org%2Fwiki%2FMultiplication&ei=B0czS6zPBdX74Aa-ss2qCA&usg=AFQjCNHxVq3CcnWoYwVVhpf12tF8-pbQpg&sig2=2LBLJK-M_13RdNScNniM0Q) est [commutative](http://fr.wikipedia.org/wiki/Commutativit??), ??a n'est donc pas n??cessaire de calculer l'int??gralit?? de ces multiplications. J'aurais pu aussi feinter en commen??ant assez haut, mais je n'en voyais pas l'int??r??t ... Surtout que ce script ne met que 2,812 s pour s'ex??cuter !  
Bref, pour en revenir au groovy, il y a dans ce script quelques trucs int??ressants :
* [findAll](http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html#findAll(groovy.lang.Closure closure)) dans une collection, prend une closure comme param??tre pour retourner uniquement les ??l??ments pour lesquels la closure retourne true.
* [reverse](http://groovy.codehaus.org/groovy-jdk/java/lang/String.html#reverse()) dans une String retourne la String, tout simplement.
* Enfin, [max](http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html#max()) retourne le maximum sans effort.