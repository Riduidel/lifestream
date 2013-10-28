type: post
status: published
title: C'est vraiment des boulets chez Adobe !
tags: eclipse, flashbuilder, java
date: Fri Dec 14 15:01:34 CET 2012
~~~~~~
# C'est vraiment des boulets chez Adobe !

[C'est vraiment des boulets chez Adobe !](https://blogs.adobe.com/jasonsj/tag/outofmemoryerror "C'est vraiment des boulets chez Adobe !")

Je cite la meilleure ligne de ce conseil rapide :


> Choose a new value for Xmx
Oh le con ! Il ne sait pas qu'il vaut mieux prendre une valeur plus grande que la précédente ? il ne sait pas non plus que la JVM 32 bits (oui, parce que FlashBuilder est conçu sur une base d'Eclipse 32 bits, ce qui arrache les yeux quand on a du Java 64 - et un Eclipse 64 - complètement fonctionnels) ne peut pas allouer plus de 1024M (de mémoire, hein,c e chiffre est peut-être faux) ?

Et surtout, surtout, il ne sait pas que FlashBuilder est honteusement rempli de memory leaks (à un point tel que c'end evient gênant pour les développeurs) et que même les options les plus aggressives de GC ne suffisent pas à corriger ça ?

Eh bien non, il ne le sait pas.

Et c'est normal, parce qu'apparement, chez Adobe, on ne sait pas que FlashBuilder ne vaut même pas la corde pour le pendre.