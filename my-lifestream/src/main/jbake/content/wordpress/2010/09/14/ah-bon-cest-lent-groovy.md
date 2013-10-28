type: post
status: published
title: Ah bon , C'est lent Groovy ?
tags: groovy, java, script
date: Tue Sep 14 13:50:00 CEST 2010
~~~~~~
# Ah bon , C'est lent Groovy ?

Depuis que je me sers de Groovy pour scripter les tâches d'admin un peu rébarbatives qu'il peut parfois y avoir dans un projet de développement quelconque, je rencontre de plus en plus souvent deux questions assez proches (dont l'une au moins a été posée par [fabstream](http://fabstream.posterous.com/), avec toute la curiosité qui le caractérise).
* Mais c'est pas un peu lent Groovy ?
* Groovy, c'est sympa pour faire des scripts à la maison, mais au bureau, ça fait pas un peu amateur ?A ces deux questions, une seule répons : c'est vrai, mais c'est pas ça qui compte.Ce qui compte, avec mon utilisation de Groovy et son utilisation en général, c'est que Groovy est un langage très expressif, permettant de faire facilement et rapidement (en temps de développement) des trucs compliqués. Par exemple, hier encore, j'ai dû renommer des tonnes de fichiers dans subversion pour supprimer des caractères (genre #, espace et autres cochonneries). J'aurais pu le faire en [shell Linux](http://www.commandlinefu.com/commands/browse) (j'ai même essayé). Seulement, au bout d'un moment, j'en ai eu marre de patauger dans les options diverses de sed, find, et du shell. Et à ce moment-là, j'ai dégainé mon meilleur éditeur Groovy, et j'ai balancé ça :



[https://gist.github.com/577451](https://gist.github.com/577451)

(Les fautes de frappe sont d'origine)Eh ben devinez quoi ? En 30 minutes j'avais un script qui tournait, et que je comprenais. Et mon chef était content ! Et finallement, c'est ça qui compte bien plus que le temps d'exécution.Je vais prendre un cas plus général pour étayer mon propos. Si un jour je dois coder une application pour GAE, je la ferais d'abord avec [gaelyk](http://gaelyk.appspot.com/). Parce qu'avec gaelyk, je passerais pas des jours avant d'avoir un truc qui marche. Et si les requêtes sont un peu lentes à s'exécuter, ça ne me dérangera pas trop (parce que c'est chez google que ça chauffera un peu plus). Et si à un moment on a besoin d'optimiser le truc, on apssera alors à un framework un peu plus efficace, mais avec lequel travailler sera plus contraignant.

En fait, ce que j'essaye d'expliquer ici, c'est que si Groovy est [lent](http://www.codecommit.com/blog/java/groovys-performance-is-not-subjective) en termes d'exécution, il est en revanche très efficace en termes de développement. Et que hélas, contrairement à ce qu'on pense la plupart du temps, le second facteur est **nettement** plus important que le premier. C'est d'ailleurs pour ça, à mon avis, qu'un outil copmme [gradle](http://www.gradle.org/) marche si bien : il se place précisément là où les développeurs préfèrent investir dans du lisible que dans de l'efficace (et je dis ça alors que je n'utilise jamais gradle).