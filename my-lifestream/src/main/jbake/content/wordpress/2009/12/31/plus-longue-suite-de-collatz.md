type: post
status: published
title: Plus longue suite de Collatz 
tags: euler, groovy, m??moire, maths
date: Thu Dec 31 16:41:14 CET 2009
~~~~~~
# Plus longue suite de Collatz 

Je me suis tranquillement attaqu?? au [probl??me 14](http://projecteuler.net/index.php?section=problems&id=14)??avec une solution qui stockait les s??quences, seuleemnt elle explosait toute la RAM  




[https://gist.github.com/266752](https://gist.github.com/266752)

J'ai donc du passer ?? une solution plus maligne utilisant les expandos :



[https://gist.github.com/266767](https://gist.github.com/266767)

Alors bien s??r, j'obtiens un temps d'ex??cution assez atroce, mais j'ai aussi largement ??conomis?? la m??moire, et en plus j'ai utilis?? un gadget assez marrant, non ?Bon, finallement, ??a ne marche pas. je vais essayer de remplacer ma r??cursion par une it??ration, et on verra ce qu'il se passe ...Mais pour l'instant, je rentre ?? ma maison !