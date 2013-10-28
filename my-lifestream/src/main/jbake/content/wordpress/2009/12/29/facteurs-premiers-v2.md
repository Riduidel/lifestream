type: post
status: published
title: Facteurs premiers v2 
tags: euler, groovy, maths
date: Tue Dec 29 15:33:33 CET 2009
~~~~~~
# Facteurs premiers v2 

Suite ?? de petits probl??mes pour r??soudre le probl??me 10, je suis tomb?? sur [une solution possible en perl](http://www.agapow.net/programming/python/euler/010). l'int??r??t de cette solution, c'est qu'elle utilise une variante pour calculer les nombres premiers que je trouve fascinante : associer ?? chaque nombre son carr?? pour ??viter les recherches sur les nombres trop grands.  
Je me suis donc dit que j'allais refaire [ma solution](http://riduidel.posterous.com/plus-grand-diviseur-premier-dun-nombre) au [probl??me 3](http://projecteuler.net/index.php?section=problems&id=3).  




[https://gist.github.com/265336](https://gist.github.com/265336)

Et franchement, ??a va quand m??me nettement plus vite ... enfin, je trouve. Le seul truc g??nant, c'est que je voulais initiallement mettre des breaks dans mon each, mais apparement, ??a n'est [pas possible](http://docs.codehaus.org/display/GroovyJSR/closure+syntax) en Groovy.bon, je vais me faire le probl??me 10 maintenant (et tant que j'y opense, apr??s, je m'attaquerais ?? l'??pineux probl??me de l'export des liens entre pages posterous).