type: post
status: published
title: Eratosth??ne ?? la rescousse
tags: euler, groovy, maths
date: Wed Apr 28 11:05:07 CEST 2010
~~~~~~
# Eratosth??ne ?? la rescousse

Vous connaissez pas [Eratosth??ne](http://fr.wikipedia.org/wiki/??ratosth??ne) ? Bon, je connaissais son [crible](http://fr.wikipedia.org/wiki/Crible_d'??ratosth??ne) de nom. Mais l??, pour la premi??re fois, je l'ai utilis?? dans du code Grooy pour optimiser tr??s violement une recherche de nombre premiers. Ce qui m'aide ??norm??ment pour ce fichu [probl??me 10](http://projecteuler.net/index.php?section=problems&id=10).  




[https://gist.github.com/381895](https://gist.github.com/381895)

L??, en fait, les feintes, c'est
1. Demander ?? Eratosth??ne de supprimer les nombres non premiers de mon tableau (ce quiv a plus vite que de voir pour chaque nombre quels sont ses diviseurs)
2. Utiliser un BigInteger pour la somme qui sera plus grande que [Integer.MAX_VALUE](http://java.sun.com/j2se/1.4.2/docs/api/java/lang/Integer.html#MAX_VALUE).