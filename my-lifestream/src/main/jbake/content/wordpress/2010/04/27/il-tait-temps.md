type: post
status: published
title: Il ??tait temps
tags: euler, groovy, maths
date: Tue Apr 27 16:44:12 CEST 2010
~~~~~~
# Il ??tait temps

Ca faisait un moment que je tournais autour de la suite de Fibonacci du [probl??me 2](http://projecteuler.net/index.php?section=problems&id=2). Et, cette fois, m??me si ma solution n'est pas optimale (en particulier dans le calcul du nombre d'??l??ments de ma suite), j'ai enfin bien compris l'??nonc??, ce qui m'a permis d'??crire la solution assez vite.  




[https://gist.github.com/380791](https://gist.github.com/380791)

Bon, encore une fois, il y a un parcours assez rigolo de mon tableau/liste qui est effectu?? par un [findAll](http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html#findAll(groovy.lang.Closure)). Ca, c'est le genre de chose qui manque vraiment ?? Java, mais qui est impl??ment?? dans [les collections google](http://code.google.com/p/google-collections/), ce qui est une excellente id??e (et montre ??galement que Java n'a pas besoin des closures).