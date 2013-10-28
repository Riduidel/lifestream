type: post
status: published
title: Vive les permutations !
tags: euler, groovy, maths
date: Thu Apr 29 11:25:28 CEST 2010
~~~~~~
# Vive les permutations !

Ca faisait quand m??me un moment que je mangeais du nombre premier (pas toujours avec succ??s, d'ailleurs, comme pour le probl??me 12 qui me r??siste encore). Et l??, le [probl??me 24](http://projecteuler.net/index.php?section=problems&id=24) me fait voyager au pays des [permutations](http://fr.wikipedia.org/wiki/Permutation) d'un ensemble.  
Je me souviens de mes cours de maths de pr??pa, o?? on a d??montr?? que toute permutation d'un ensemble peut se d??composer en un ensemble de transpositions. Et du coup, j'avais commenc?? ?? ??crire un truc qui le faisait ?? la main ... Jusqu'?? ce que je me rende compte que Groovy incluait une fonctionnalit?? de [calcul de toutes les permutations](http://groovy.codehaus.org/groovy-jdk/java/util/Collection.html#eachPermutation(groovy.lang.Closure)) d'un ensemble ! Ce qui a largement simplifi?? mon code ;-)



[https://gist.github.com/383364](https://gist.github.com/383364)

Du coup, le code ne fait quasiment plus rien, mis ?? part appeler la bonne m??thode avec les bons arguments et afficher joliment le r??sultat. Bon, ma m??thode a quand m??me l'inconv??nient notable de consomer environ 500 Mo de RAM pendant 1 minute ... Ce qui m'intrigue un peu, je dois dire, m??me si je b??tis une collection de quelques millions de cha??nes de caract??res ... Boarf, mettons ??a sur le dos du Groovy ;-)