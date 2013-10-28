type: post
status: published
title: groovy, ça sert décidément à tout
tags: groovy, script, shell, tree
date: Tue Mar 01 13:51:26 CET 2011
~~~~~~
# groovy, ça sert décidément à tout

Ce matin, je devais récupérer une arborescence de fichiers dans un fichier texte (une de ces tâches curieuses où on cherche à quoi correspond un dossier donné).Pas de bol, je devais faire ça sur une machine Linux bizarre : une [Suse](http://fr.wikipedia.org/wiki/SUSE) SLES 10, sans dépôts web.Et encore plus curieux, sur cette machine, la commande [tree](http://linux.die.net/man/1/tree), bien pratique (disponible aussi sous forme d'[un script shell](http://www.centerkey.com/tree/), je le sais maintenant grâce à [Thibaud Vibes](https://twitter.com/#!/ThibaudVibes/status/42520684932497408), mais également [disponible sous Windows](http://www.microsoft.com/resources/documentation/windows/xp/all/proddocs/en-us/tree.mspx?mfr=true)) n'existe pas.Alors évidement, j'aurais pu fouiller le web à la recherche d'un package cotnenant cette commande.Mais c'est pas mon style.Comme Java faisait partie du DVD d'installation de cette Suse, je l'ai installé.Et j'ai tout de suite enchaîné avec ... bien sûr, [l'installation de Groovy 1.7.8](http://groovy.codehaus.org/Installing+Groovy).Avant, finallement, d'écrire ce petit script :
 
[https://gist.github.com/848911](https://gist.github.com/848911)
 
Et franchement, ça m'a pris dix minutes pour l'écrire.Du coup, c'est encore un domaine où je vais utiliser de plus en plus [Groovy](http://groovy.codehaus.org/Groovy+CLI) : l'administration système.