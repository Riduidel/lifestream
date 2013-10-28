type: post
status: published
title: Un peu de combinatoire 
tags: euler, groovy, maths
date: Thu Apr 08 11:50:59 CEST 2010
~~~~~~
# Un peu de combinatoire 

Bon, le [probl??me 15](http://projecteuler.net/index.php?section=problems&id=15) est quand m??me sacr??ment tordu.  
Cependant, comme d'habitude, sur [Functional Fun](http://blog.functionalfun.net/2008/07/project-euler-problem-15-city-grids-and.html), une premi??re r??ponse existait. J'ai donc essay??, mais je pense que je me suis vautr?? sans le savoir dans des probl??mes de longueur des entiers (souvent, les integers sont d??pass??s quand on joue avec des objets math??matiques comme le [triangle de Pascal](http://fr.wikipedia.org/wiki/Triangle_de_Pascal)).Comme ??a ne marchait pas, plut??t que d'accuser mon impl??mentation de ce triangle ?? l'aide d'entiers, j'ai ??t?? voir ailleurs. Je suis donc tomb?? sur [ce blog au nom imprononcable](http://mgccl.com/2007/10/20/hint-for-problem-15), qui m'a envoy?? ensuite vers [la r??ponse](http://realultimateprogramming.blogspot.com/2009/03/project-euler-problem-15.html), qui s'impl??mente tr??s (trop ?) facilement en Groovy.  




[https://gist.github.com/359939](https://gist.github.com/359939)

Vous remarquerez comme d'habitude les grands "G" qui marquent l'utilisation de BigDecimal (qui se fait incroyablement bien en Groovy, c'est le genre de sucre syntaxique qui manque vraiment au Java, je trouve) et le formatage fait pour ??viter que le nombre essaye de s'imprimer joliment avec une notation scientifique.

Pour le fun, voici la version r??alis??e avec le triangle de Pascal (qui donne **exactement** le m??me r??sultat).



[https://gist.github.com/359940](https://gist.github.com/359940)