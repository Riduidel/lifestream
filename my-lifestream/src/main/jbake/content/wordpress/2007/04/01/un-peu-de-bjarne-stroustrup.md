type: post
status: published
title: Un peu de Bjarne Stroustrup
tags: code, informatique
date: Sun Apr 01 15:25:00 CEST 2007
~~~~~~
# Un peu de Bjarne Stroustrup

Sur le site de Michel Volle, je suis tombé sur [des extraits d'une interview du créateur du C++](http://www.volle.com/travaux/qpgm.htm). Je ne peux que vous conseiller de lire cette interview, on ne peut plus exacte, dont je vais me permettre (à mon tour) de citer quelques extraits.

> Il existe donc d’excellents logiciels. Cependant la qualité moyenne des programmes est à pleurer. Leur structure est affreuse et il saute aux yeux que les programmeurs n’ont pas été attentifs à la cohérence, aux algorithmes, aux structures de données ni à la maintenabilité. Les utilisateurs ne peuvent pas s’en rendre compte parce qu’ils ne lisent pas le code : ils voient seulement les programmes se planter.

Pfff ... si je n'avais jamais vu ça de mes yeux, je dirais que c'est du mépris de sa part. Mais pourtant, c'est on ne peut plus vrai (j'ai moi-même fait parfois ce genre de choses).

> La performance pose souvent problème : les programmeurs neutralisent les progrès du matériel en empilant dans leurs programmes couche sur couche d’abstractions compliquées, ce qui ralentit la réponse des interfaces ainsi que le démarrage et la fermeture des applications.

Il est difficile, en tant que développeur Java, de ne pas se sentir visé par cette phrase. Et pour le coup, c'est vrai. Chaque bibliothèque, chaque framework qu'on utilise nous rajoute un paquet de classes et de méthodes à exécuter. Du coup, la performance en prend un sacré coup dans l'aile. Regardez par exemple le temps de lancement d'un Eclipse ou d'un NetBeans sur une machine moderne. C'est lamentablement lent. Et dans le cas de l'application que je développe, c'est encore pire, puisqu'on est à environ 1 minute de temps de démarrage ... Le seul problème, c'est que je ne vois pas vraiment de solution simple, mis à part, comme le font certain, écrire soi-même chaque couche de l'application, et vérifier qu'elle fonctionne de manière optimale pour l'application.

> Il est dangereux de considérer la programmation comme une tâche banale que pourraient remplir des programmeurs formés en quelques mois. Nous n’admettrions pas cela pour des plombiers, des comptables, des architectes, ni pour ceux qui conçoivent les ponts et les trains. Aujourd’hui trop de programmeurs n’ont pas été assez formés et manquent d’expérience.

Et c'est Stroustrup qui dit ça ! Depuis les USA ! Que dire alors de la situation française ou un gars comme moi, avec ses six ans d'expérience, est vu comme un senior, limite trop vieux pour coder ? Faudrait peut-être aussi changer la vision qu'ont les entreprises des développeurs.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)