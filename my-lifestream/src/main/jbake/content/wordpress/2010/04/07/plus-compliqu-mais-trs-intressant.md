type: post
status: published
title: Plus compliqu??, mais tr??s int??ressant 
tags: euler, expando, groovy, maths
date: Wed Apr 07 15:17:19 CEST 2010
~~~~~~
# Plus compliqu??, mais tr??s int??ressant 

Bon, l??, je dois avouer que j'ai un peu gal??r?? sur le [probl??me 18](http://projecteuler.net/index.php?section=problems&id=18). Pas vraiment ?? cause des maths, en fait, mais plut??t ?? cause des notions tordues de delegate, de this, de it, de owner ...??

[https://gist.github.com/358863](https://gist.github.com/358863)??Vous noterez donc l'usage abusif de l'expando, mais aussi, ce que je trouve tr??s mignon, la d??claration de comparateur comme une closure avec un op??rateur de comparaison sympa. Bon, c'est une solution brute force, j'en suis bien conscient, et je vais essayer de l'am??liorer (ce qui ?? la r??flexion doit se faire "facilement" en retournant le probl??me, c'est-??-dire en consid??rant qu'on part de toutes les bases, et en ??liminant ?? chaque fois celle qui donne une somme inf??rieure). Je crois m??me que je posterais cette solution am??lior??e (parce qu'apr??s tout, elle doit aussi bien me permettre de r??soudre le [probl??me 67](http://projecteuler.net/index.php?section=problems&id=67).??**EDIT **Mais c'est que je suis parfois pas malin, moi !En fait, ma solution est d??ja pas mauvaise, puisqu'elle ne calcule que l'arbre des meilleures sommes. Son seul inconv??nient est que, pour faire ??a, elle doit quand m??me d??ja calculer toutes les sommes de base, ce qui est long. Et, surtout, son principal inconv??nient et qu'elle effectue le calcul de mani??re r??cursive, ce qui pose des probl??mes de piles d'ex??cution (surtout que Groovy, pour un appel de m??thode interpr??t??, effectue en fait un gros paquet d'appels non interpr??t??s). Je crois que je vais passer ?? une solution sans expando, mais avec de groooosses balades dans des tableaux, ce qui ??tait en fait la solution ?? laquelle je pensais initiallement.??

**EDIT2** bon, apr??s cinq minutes de cogitations, j'ai trouv?? la solution "optimale", celle qui va me permettre de travailler sur des tableaux de taille infinie, ou presque, sans souffrir de piles d'appels infinies.??



[https://gist.github.com/358897](https://gist.github.com/358897)

Et celle-l?? a toutes les chances de marcher pour le probl??me 67, ce que je vais v??rifier maintenant. Ce qui est un succ??s aussi ph??nom??nal qu'il est rapide (parce que m??me avec cette quantit?? astronomique de chiffres, j'obtiens la r??ponse dans la <span style="text-decoration:underline;">seconde</span> !).