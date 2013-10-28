type: post
status: published
title: C'est pas toujours facile d'être un mauvais admin
tags: admin, dns-323, php
date: Thu Jun 06 20:35:05 CEST 2013
~~~~~~
# C'est pas toujours facile d'être un mauvais admin

Au début de la semaine, [j'ai ajouté à MiniGalNano](https://github.com/Riduidel/MinigalNano/commit/eedaecc5e8605cbc9ec11e441c895b13c89c305e) une fonctionnalité de création des miniatures en tâche de fond.

L'un des éléments nécessaires à cette tâche de fond était un cronjob qui lance régulièrement un wget sur mon site, histoire de lancer le script d'une façon "pratique".

Je lme suis donc renseigné pour l'ajouter d'une façon qui [survive aux reboots](https://github.com/Riduidel/my-dns-323/issues/5). Parce que je n'étais pas vraiment sûr que ce que j'avais fait jusqu'à présent fonctionne correctement.

Ce que j'avais initialement fait, c'était de suivre [le wiki du DNS-323](http://dns323.kood.org/howto:cron) et d'ajouter deux scripts d'initialisation. Là, ne retrouvant plus immédiatement cette solution, j'en ai suivi [une autre qui m'a conduit à modifier le fichier fun_plug.local](http://nas-tweaks.net/77/fixing-the-ntp-time-synchronization-with-fonz-funplug-0-5-for-ch3snas-ch3mnas-dns-323-and-many-more/) (que j'ai ajouté par la même occasion au repository git de configuration).

Et à partir de ce moment, j'ai constaté que le CPU de mon DNS-323 restait toujours à 100 avec deux scripts php qui bouffent chacun 50 % du CPU.

Louche.

LOUCHE.

En farfouillant un peu, je me suis rendu compte que j'avais DEUX process crond qui tournaient !

Du coup, le crontab était lu en parallèle et chaque tâche s'exécutait en double.

Franchement idiot !

J'ai donc viré l'un des jobs cron, et depuis, j'ai retrouvé un peu de sérénité (même si entre temps je me suis rappelé que j'étais définitivement [le pire admin au monde](http://riduidel.wordpress.com/2008/12/19/mon-experience-malheureuse-dad/)). Bon, je ne vous cache pas que j'avais également envisagé sérieusement l'éventualité d'acheter un freeplug classic pour redémarrer un PC qui traîne dans ma cave et dont le CPU est bien plus puissant. Je me disais qu'avec un coup de wake-on-lan, je pourrais conserver le NAS comme verrou d'entrée, et ne démarrer le gros serveur qu'en cas de besoin.