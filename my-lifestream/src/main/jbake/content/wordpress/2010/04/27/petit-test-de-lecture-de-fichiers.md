type: post
status: published
title: Petit test de lecture de fichiers
tags: euler, groovy, maths
date: Tue Apr 27 10:28:47 CEST 2010
~~~~~~
# Petit test de lecture de fichiers

En gros, le [probl??me 22](http://projecteuler.net/index.php?section=problems&id=22) se r??sume ?? ??a : est-ce que je suis capable de lire correctement un fichier ? Eh bien oui, j'en suis capable !



[https://gist.github.com/380496](https://gist.github.com/380496)

  


Deux choses m'ont fait douter : d'abord, la taille des sommes qui impose le passage aux BigInteger. j'aurais pu utiliser la jolie syntaxe Groovy, mais les habitudes ont la vie dure ! Et puis aussi, et surtout m??me, le fait que dans ce probl??me, les indices d??marrent ?? 1, quand groovy, comme Java et pas mal d'autres, fait d??marrer les indices de collections ?? 0.  
