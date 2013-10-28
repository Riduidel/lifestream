type: post
status: published
title: Le fait-maison, parfois, c'est moins bien
tags: groovy, homebrew, ibook, macosx, maven
date: Wed Jun 29 19:58:14 CEST 2011
~~~~~~
# Le fait-maison, parfois, c'est moins bien

Bon, vous le savez d??ja, j'ai r??install?? [Tiger sur mon iBook](http://riduidel.posterous.com/et-encore-un-ibook-qui-se-reveille). Et parmi les trucs que j'ai install??, il y avait [Homebrew](http://mxcl.github.com/homebrew/). Avait ? Ben oui, je l'ai remplac?? par [MacPorts](http://www.macports.org/). Pourquoi ? Ben parce que, en fait, dans la branche Tiger, il n'y avait pas maven. Et, vous l'avouerez, pour [g??n??rer un site web avec maven](http://riduidel.posterous.com/jadore-quand-un-plan-se-deroule-sans-accroc), ne pas avoir maven, c'est ballot.En revanche, installer groovy avec MacPorts, c'est pas facile. Il y a quelques JARs quasi-introuvables, et des [probl??mes de compilation avec le script Ant et ANTLR](http://docs.codehaus.org/display/GROOVY/Building+Groovy+from+Source). Cela dit, j'ai r??ussi, et en bonus j'ai une [chouette interface graphique](http://porticus.alittledrop.com/) (que ne fournit apparement pas Homebrew).Et maintenant, je vais tenter une authentique cascade : je vais installer ... git ([d'oh](http://riduidel.posterous.com/pourquoi-jai-un-a-priori-contre-git)) et essayer de m'en servir, puisqu'il semble bien que le couple infernal git/github soit en train de gagner la bataille du DVCS.Cela dit, une interface graphique compatible avec mon Tiger ne serait vraiment pas de refus ...