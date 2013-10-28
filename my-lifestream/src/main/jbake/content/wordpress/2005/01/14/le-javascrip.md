type: post
status: published
title: Le javascrip
tags: code, informatique, javascript
date: Fri Jan 14 12:33:00 CET 2005
~~~~~~
# Le javascrip

On dit souvent que le Javascript est un langage très limité, comme [ici](http://www.mparaz.com/w/archives/2005/01/11/the-limits-of-a-rich-web-ui/) :

> In general, pushing the UI to Javascript makes it hard to develop and debug. There are limited tools, the language is too lenient (no objects, weak typing), and testing involves cycling through webpages over and over again.

Ca m'énerve vraiment. Le Javascript a des objets (en guise de preuve, que faites vous avec votre document.write, node.childNodes, etc, ...), et le typage faible n'a jamais été un manque (les langages interprétés modernes comme le Perl, le Python ou Groovy l'ont assez montré). A mon avis, javascript est vraiment mal compris. C'est un langage incroyablement puissant et rapide. Oui, rapide. Ce qui est lent en Javascript n'est pas le calcul, mais l'intégration au navigateur. Ce qui peut être facile à comprendre : imaginez que vous deviez redessiner une page entière quand vous insérez un simple DIV. Ca doit bien être lent. En guise de pense-bête, voici quelques spécificités du Javascript :
* Les [Closures](http://nicolas.delsaux.free.fr/wordpress/index.php?p=28) comme dans ce puissant exemple, qui génère à la volée un tri sur une colonne d'une table :



[https://gist.github.com/265975](https://gist.github.com/265975)

C'est en javascript une manière facile de générer un tri multi critères.
* [L'héritage par prototype (par opposition à l'héritage de classe Java).](ww.crockford.com/javascript/inheritance.html)
* Evaluation dynamique (oui, c'est vraiment un point important quand on parle, par exemple, d'algorithmes auto-modifiants).

Et, quand on essaye de banir le Javascript, il ne faut pas oublier que des sociétés comme Google l'utilisent intensivement (par exemple, GMail).via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)