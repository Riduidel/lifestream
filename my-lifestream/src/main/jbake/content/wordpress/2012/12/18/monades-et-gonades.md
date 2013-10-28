type: post
status: published
title: Monades et gonades
tags: javascript, monades, patterns
date: Tue Dec 18 19:24:58 CET 2012
~~~~~~
# Monades et gonades

Les monades, ca fait plaisir à tous les fans de programmation fonctionnelle. Mais c'est totallement incompréhensible ...

Enfin, c'est plutôt que, comme dit [Douglas Crockford](https://fr.wikipedia.org/wiki/Douglas_Crockford)

> Une fois qu'on comprend les monades, on ne peut plus expliquer les monades.
Il dit ça dans cet incroyablement excellente keynote de la YUIConf (qu('il faut absolument regarder pour pouvoir enfin regarder les scalafistes dans les yeux).

[youtube=http://www.youtube.com/watch?v=dkZFtimgAcM]

Bon, il faut savoir que j'ai toujours pensé que Douglas Crockford était un homme absolument brillant ... Enfin, toujours, toujours, tout au moins depuis que j'ai lu [ses excellents articles sur Javascript](http://crockford.com/) et en particulier "[Javascript : le langage de programmation le plus incompris du monde](http://microclub.ch/2012/10/21/javascript-le-langage-de-programmation-le-plus-incompris-du-monde/)", qui expliquaient clairement cette vérité cachée sur Javascript : c'est du Lisp déguisé en Java.

Bref, c'est excellent, et j'en ai tiré quelques citations assez amusantes

https://twitter.com/riduidel/status/280981496326676480

Et je ne parle pas du Python, là, mais d'un autre langage beaucoup moins connu. Et effectivement, l'absence de déclaration de variable est une plaie (en particulier quand on veut écrire un plugin [Xtext ](http://www.eclipse.org/Xtext/)pour ce langage).

https://twitter.com/riduidel/status/281003113975009281

Sa proposition de coloration contextuelle est très intéressante, mais pas forcément utile en Java, je crois (sauf à abuser des classes internes/locales).

https://twitter.com/riduidel/status/281009499018493952

Autrement dit ne mettez jamais null dans votre code, ou attendez-vous à des NullPointerException. Comment ne pas mettre null ? Facile ! Avec le [pattern NullObject](https://en.wikipedia.org/wiki/Null_Object_pattern).