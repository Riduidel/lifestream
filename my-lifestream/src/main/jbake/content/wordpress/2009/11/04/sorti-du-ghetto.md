type: post
status: published
title: Sorti du ghetto 
tags: dev, java
date: Wed Nov 04 14:55:00 CET 2009
~~~~~~
# Sorti du ghetto 

Ces temps-ci, comme vous le savez peut-être, je ne fais plus vraiment beaucoup de Java.  
Pour m'entretenir, je me suis donc dit que j'allais voir dans quelle mesure [mon idée folle](http://riduidel.posterous.com/une-idee-que-je-nimplementerai-jamais-enfin-s) était réaliste ou pas. Et pour ça, évidement, la meilleure solution, c'est de tenter une implémentation "POC", c'est-à-dire pas belle, mais qui fasse tout ce que je veux.Et je découvre avec stupéfaction que le monde ne s'est pas arrêté il y a deux ans, et qu'il y a toujours des mecs qui développent des bibliothèques très pratiques en java. Oh, bien sûr, quand il faut les inclure dans une application Java Web Start, ça augmente les temps de download. Mais je vais vous dire, depuis environ [2 ou 3 mois](http://riduidel.posterous.com/et-cest-reparti-pour-un-tour), faire le JAR le plus petit du monde, ça ne m'intéresse plus. Surtout quand la contrepartie, c'est l'interdiction d'utiliser la moindre ligne de code étrangère. Et donc, j'utilise des bibliothèques externes, avec joie, devrais-je dire.J'ai donc découvert des trucs très bien, comme
* [Guice](http://code.google.com/p/google-guice/), que je connaissais déja de vue, mais qui est vraiment très agréable quand on peut réellement utiliser l'introspection.
* [glazed-lists](http://publicobject.com/glazedlists/), avec lequel je commence tout juste mes expérimentations, me paraît mériter plus qu'un coup d'oeil, tant il peut rendre de services (et éviter de réimplémentations fastidieuses).
* [google collections](http://code.google.com/p/google-collections/) et [guava](http://code.google.com/p/guava-libraries/) m'ont l'air de pouvoir amener quelques jolis éléments d'écriture à la limite de la programmation fonctionnelle (ce qui va nettement me rapprocher de Ruby, et dans le meilleur sens du terme).
* De manière plus anecdotique, jintellitype eest aussi sympatoche.
* Et bien sûr, [commons-configuration](http://commons.apache.org/configuration/), qui devrait me permettre d'éviter (enfin) de redéfinir mon  propre format de propriétés.Enfin, de mani-ère encore plus latérale, une fois que j'aurais fini l'implémentation Java de mon idée folle, je tenterai peut-être un truc fou : en refa

> **Aaaaaaaaah !** Saloperie de [clavier](http://www.logitech.com/index.cfm/keyboards/keyboard/devices/177&cl=fr,fr) !

> J'ai accidentellement tapé sur CTRL-machin qui a envoyé le mail ! 

Bref, je disais donc, en refaire une partie en [Griffon](http://griffon.codehaus.org/). Ca a l'air de permettre des choses assez mignonnes, et de manière très efficace (sans doute grâce à [Groovy](http://groovy.codehaus.org/) dont je m'imprègnerai aussi, et puis ça me fera mon langage de 2009/2010). Mais pour l'instant, je joue pas mal avec la complétion via glazed-lists, et c'est franchement chouette. 