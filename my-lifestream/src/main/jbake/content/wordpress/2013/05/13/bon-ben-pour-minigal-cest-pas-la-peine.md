type: post
status: published
title: Bon, ben pour MiniGal, c'est pas la peine
tags: dns323, maven, photo
date: Mon May 13 21:04:53 CEST 2013
~~~~~~
# Bon, ben pour MiniGal, c'est pas la peine

Avant de vous enflammer, je m'explique.

Je cherche donc une galerie (PHP ou non) capable de m'afficher mes images classées selon différents critères (tous stockés dans les tags IPTC, pas de panique) à partir de mon fidèle DNS-323. [MiniGal-Nano](https://github.com/sebsauvage/MinigalNano) partait plutôt bien (même si j'ai dû rajouter un script PHP créant les liens symboliques selon les chemins qui m'intéressaient), jusqu'à ce que je me rende compte que la génération des miniatures sur le serveur est lente. Très lente. Trop terriblement lente pour être même utilisable. Pour être clair, il faut une dizaine de secondes par miniature ! Et ça, dans le genre trop lent, c'est **trop** lent.

Donc j'ai réfléchi.

Et je me suis rendu compte, d'une part, que je n'en avais pas besoin (parce que je ne m'en suis toujours pas servi), et que d'autre part, si je devais utiliser le meilleur CPU pour générer mes miniatures avant de faire un upload optimisé, j'avais plutôt intérêt (attention les yeux) à m'écrire un plugin maven qui me permettrait de générer tout ça et de l'uploader via un quelconque wagon. Mais bon, ça, c'est si je dois un jour développer un truc analogue (d'ailleurs, si je dois le faire, je m'inspirerai sans doute de certaines fonctionnalités de [JoJoThumb](http://www.jojosoftware.de/jojothumb/english/)), qui correspond au jeux de fonctionnalités qui me plaît, mais pas implémenté d'une façon qui me plaît).