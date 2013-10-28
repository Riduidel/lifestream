type: post
status: published
title: Rechercher dans plusieurs Shaarli ?
tags: php search p2p
date: Thu Jun 20 20:57:24 CEST 2013
~~~~~~
# Rechercher dans plusieurs Shaarli ?

Sur la chatroom Shaarli, et ailleurs, certains souhaiteraient développer une recherche full-text distribuée dans les différents Shaarli.

Une implémentation est même apparue (sur le site [bananium](http://test.bananium.fr/test.php)).

Je n'ai rien contre l'idée. En fait, j'ai même plutôt tout pour ce genre d'idée ... Mais l'implémentation qui lance une requête à mon petit shaarli à chaque fois qu'un utilisateur lance une recherche me paraît .... frustre ? et surtout terriblement impactante pour mon serveur.

Pourquoi ?

Parce que chaque recherche est lancée directement sur mon site. Et donc plus le site de recherche sera chargé, plus mon serveur sera chargé. Et ça, ça va vite l'amener au DoS (ben oui, c'est un bête NAS qui fait office de serveur chez moi, et croyez-moi, il n'est pas bien puissant). D'autant plus vite d'ailleurs, que les rechargements de selfoss suffisent quasiment à le mettre à genoux. Alors imaginez 20 utilisateurs qui font leur recherche en même temps.

Cela dit, je crois qu'en fait tout cela vient d'un bon vieux syndrôme NIH - voire même encore plus bêtement d'une méconnaissance de ce qu'internet contient.

Parce que, j'en parlais ce matin, des moteurs de recherche open-source facilement déployables, il y en a ([seeks](http://www.seeks-project.info/site/), par exemple).

Et des librairies de moteur de recherche open-source en PHP intégrables, il y en a aussi un paquet :
* [Sphider](http://www.sphider.eu/) ou [Sphider+](http://www.sphider-plus.eu/index.php?f=0)
* [PhpDig](http://www.phpdig.net/)
* [iSearch](http://www.isearchthenet.com/isearch/)
* Les extensions de la [lib PHP standard](http://php.net/manual/en/refs.search.php) peuvent aussi fournir une bonne base (Solr par exemple)

Et qu'est-ce que ça apportera, tout ça ?

Eh bien c'est simple : au lieu de lancer la recherche en temps réel sur ma machine, et donc de faire varier la charge de ma machine en fonction directe du nombre d'utilisateurs de la recherche, tous ces moteurs de recherche vont indexer mon site de façon régulière (pouf le cronjob, voire même le PushPullPubHub - enfin, le truc au nom façon [PubPub](http://www.dailymotion.com/video/xr31q_telechat-the-very-best-of-pubpub_animals#.UcMLqj63FBk)), et surtout ils ne vont indexer mon contenu shaarli qu'une fois (par instance de moteur de recherche, évidement). Dans tous les cas, ça lissera bien la charge sur mon serveur, et en bonus je pourrai lui faire lire un robots.txt (le shaarli.txt n'existant pas encore).<span id="__caret">_</span>