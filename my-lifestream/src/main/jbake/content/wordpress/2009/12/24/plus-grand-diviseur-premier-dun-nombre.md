type: post
status: published
title: Plus grand diviseur premier d'un nombre 
tags: alg??bre, euler, groovy, maths
date: Thu Dec 24 11:09:59 CET 2009
~~~~~~
# Plus grand diviseur premier d'un nombre 

J'ai beaucoup plus lutt?? sur [le troisi??me probl??me](http://projecteuler.net/index.php?section=problems&id=3), qui est essentiellement d?? au temps de calcul en O(n^2). Bon, et puis mon code est litt??ralement farci de logs pour me permettre de savoir ?? quelle vitesse je vais.  


[https://gist.github.com/263120](https://gist.github.com/263120)Ici, il n'y a pas vraiment de trucs sp??cifiques groovy, mais plut??t une sacr?? optimisation bas??e sur la limitation des recherches de facteurs premiers [sous la racine carr??e du nombre](http://fr.wikipedia.org/wiki/Essais_de_divisions). J'ai vu ??a en pr??pa, et ??a se d??montre somme toute facilement, d'apr??s mes souvenirs Le plus ??tonnant, c'est que ma premi??re version, brute force et non limit??e, ne semblait pas vouloir converger, alors que celle-ci me donne un temps de recherche de 13 secondes pour un nombre quand m??me assez grand (suffisament, en tout cas, pour qu'il y ait au moins 62000 nombres premiers inf??rieurs ?? sa racine carr??e). Pas mal, je trouve.