type: post
status: published
title: J'ai pas fini de pester contre la fin de GReader
tags: admin, dns-323, rss, script, shell
date: Mon Jun 24 22:06:55 CEST 2013
~~~~~~
# J'ai pas fini de pester contre la fin de GReader

Parce que bon, [Selfoss ](http://selfoss.aditu.de/)est un bon lecteur de flux RSS.

Mais la mise à jour marche mal : j'ai régulièrement des blocages du process php qui fait la mise à jour.

Et comme je suis un bien piètre administrateur système Linux, je ne suis pas non plus sûr que [le script shell](https://github.com/Riduidel/my-dns-323/blob/master/config/selfoss/update-selfoss.sh) que j'ai écrit pour [éviter les exécutions parallèles](https://github.com/Riduidel/my-dns-323/issues/9) fonctionne. Du coup j'ai désactivé le cronjob pour revenir à des mises à jour depuis un navigateur web. Je vais peut-être louper des articles de Lifehacker, par exemple ... (vu le nombre d'articles qu'ils sortent). Mais au moins, je n'aurai plus à rebooter mon NAS à chaque fois qu'il y a un plantage dans la mise à jour des flux RSS.

Cela dit, si quelqu'un veut m'aider à améliorer le script, je suis preneur ;-)