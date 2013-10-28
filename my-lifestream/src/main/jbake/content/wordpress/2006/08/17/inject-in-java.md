type: post
status: published
title: inject in Java ?
tags: code, informatique, ruby
date: Thu Aug 17 11:49:00 CEST 2006
~~~~~~
# inject in Java ?

Comment décrire ce que représente l'[inject](http://www.ruby-doc.org/core/classes/Enumerable.html#M003398 "Module: Enumerable") en Ruby à quelqu'un qui fait du Java ? Gros challenge, mais que je viens de réussir, donc je le note pour plus tard. On va faire un certain nombre de suppositions sur le code de la JVM. La première supposition, c'est qu'il existe quelque part cette interface :



[https://gist.github.com/266100](https://gist.github.com/266100)

La seconde, c'est que le code de l'interface Collection (par exemple) est modifié de la façon suivante :



[https://gist.github.com/266101](https://gist.github.com/266101)

avec cette implémentation :



[https://gist.github.com/266102](https://gist.github.com/266102)

Ce qui nous permettrait d'écrire



[https://gist.github.com/266103](https://gist.github.com/266103)

Ca n'a pas l'élégance du Ruby, j'en conviens, mais c'est pas trop mal pour du Java. Et, en fait, je ne comprend pas trop pourquoi ça n'est pas possible. Je m'en vais de ce pas demander à quelques javaistes plus talentueux.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)