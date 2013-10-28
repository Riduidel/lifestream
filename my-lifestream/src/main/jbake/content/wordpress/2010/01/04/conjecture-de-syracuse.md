type: post
status: published
title: Conjecture de Syracuse 
tags: euler, groovy, maths
date: Mon Jan 04 10:30:00 CET 2010
~~~~~~
# Conjecture de Syracuse 

[La semaine dernière](http://riduidel.posterous.com/plus-longue-suite-de-collatz), ou plutôt l'année dernière, je me suis atttaqué à [un problème](http://projecteuler.net/index.php?section=problems&id=14) simple en apparence, mais complexe en vérité. [La conjecture de Syracuse](http://fr.wikipedia.org/wiki/Conjecture_de_Syracuse) est en effet très loin d'être facilement calculable. Ce qui est très embêtant. Parce que figurez-vous que l'une des caractéristiques des algorithmes informatiques (enfin, à mon sens) est qu'il sont, par nature, limités aux problèmes calculables. C'est-à-dire qu'il est très difficile pour un ordinateur de répondre à des questions du genre "de quelle couleur est le ciel" si on ne lui donne pas un référentiel de couleur.Et j'en ai fait la douloureuse expérience avec une version non récursive de mes précédents algorithmes :



[https://gist.github.com/268420](https://gist.github.com/268420)

Cette version, qui n'utilise donc ni récursion (comme la première version) ni objets complexes (comme la seconde version), provoque quand même des heap spaces. Et ça, c'est vraiment moche. Parce que ça indique que, malgré mes efforts, la quantité de BigInteger générés est astronomique. Alors je vais tenter une augmentation de heap. Et si ça ne marche pas, je serais quand même assez mal. Pour tout dire, il ne me restera plus que le stockage de ces nombres dans des fichiers pour sauver ma misérable existence ...

Cela dit, c'est un problème qui a l'avantage énorme d'être instructif, car même si je connais la méthode de résolution, je me heurte pour une fois à un problème non algorithmique, ce qui change agréablement des précédents. A mon avis, je l'aurais.

Ah, oui, au fait, bonne année !