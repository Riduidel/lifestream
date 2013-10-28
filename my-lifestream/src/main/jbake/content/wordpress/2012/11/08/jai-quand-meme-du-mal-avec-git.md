type: post
status: published
title: J'ai quand même du mal avec Git
tags: git, github, java, maven
date: Thu Nov 08 10:34:16 CET 2012
~~~~~~
# J'ai quand même du mal avec Git

Parce que bon, github, c'est vraiment un truc bien pensé, mais je comprend même pas comment ils ont pu construire un buisness aussi florissant sur une technologie aussi poussiéreusement foireuse.

Un exemple ?

J'ai passé deux heures à pester, mais alors vraiment pester, du genre

https://twitter.com/riduidel/status/266207240275517440

ou

https://twitter.com/riduidel/status/266213324889395201

Tout ça à cause d'une malencontreuse option "[autoCRLF](http://stackoverflow.com/a/2825829/15619)" qui ne fait pas du tout, mais alors **pas du tout** ce que je croyais. Pour mémoire, j'avais déja ajouté un fichier µ.gitattributes dans mes différents projets GitHub grâce aux [contributions](https://github.com/Riduidel/agorava-stackoverflow/pull/6) d'[antoine_sd](https://github.com/antoinesd) sur [agorava-stackoverflow](https://github.com/Riduidel/agorava-stackoverflow). Lui utilise en effet un Mac, et du coup, ces problèmes angoissants de fin de ligne (que je pensais voir réglés depuis Subversion au moins) étaient déja apparus.

Bon, et ce matin, en bonus, j'ai des problèmes de release liés apparement à des bugs ... curieux ... dans le plugin maven-javadoc-plugin qui font que la génération du site lors de la release fait tout planter. Bref, la joie.