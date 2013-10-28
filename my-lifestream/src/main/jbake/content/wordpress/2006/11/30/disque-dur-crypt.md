type: post
status: published
title: Disque dur crypt??
tags: informatique, os, ubuntu
date: Fri Dec 01 00:21:00 CET 2006
~~~~~~
# Disque dur crypt??

Pour faire suite à mon message sur [ma clé](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/ma-cle-usb-a-moi/), j'ai trouvé cet article expliquant [comment créer un disque dur crypté sur le Mac](http://blog.2blocksaway.com/2006/11/10/portable-encryption-on-mac-os-x/ "2 Blocks away: where *nix, security and Mac meet the general public &raquo; Portable encryption on Mac OS X!")d'une façon portable, c'est-à-dire lisible depuis un autre système.

Oui, enfin, pour ça, il faut juste que TrueCrypt puisse lire un disque crypté en [AES-256](http://fr.wikipedia.org/wiki/Standard_de_chiffrement_avanc&eacute; "http://fr.wikipedia.org/wiki/Standard_de_chiffrement_avancé") ... ce qui est le cas ! A tester, donc.

Résumons donc : j'ai coupé ma clé en 2 partitions. Sur la première, je vais bientôt avoir DamnSmallLinux. Sur la seconde, je vais avoir un morceau traité comme une partition séparée contenant mes données sensibles, un autre avec mes applications portables (si possible en Java pour tourner partout) et, enfin, suite au précieux conseil de Frederick Ros, je vais placer un `svn co` de pimki pour pouvoir faire avancer deux ou trois choses.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)