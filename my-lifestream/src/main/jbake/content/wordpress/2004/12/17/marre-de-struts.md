type: post
status: published
title: Marre de Struts !
tags: code, informatique, java
date: Fri Dec 17 13:48:27 CET 2004
~~~~~~
# Marre de Struts !

Comme d'habitude, Struts m'agace. J'ai une action, lanc??e par le menu, qui affiche une JSP. Logiquement, ?? partir de cette JSP, j'ai un lien (ou, dans mon cas, un formulaire) qui lance une autre action Struts. Et devinez quoi ? Ca ne marche pas. Et c'est fatigant de devoir encore une fois passer par toute la gamme de fichiers de configuration XML. Entre le struts-config.xml, le tiles-defs.xml, les JSP, les actions, c'est vraiment trop lourd et inefficace.Ca l'est d'autant plus que j'ai d??ja eu l'occasion de voir ce qu'aurait pu ??tre Struts, sous la forme de [Apache Beehive](http://incubator.apache.org/beehive/), et de son IDE (indispensable pour ??viter le bazar de la gestion manuelle de ces fichiers o?? tout le monde s'inter-r??f??rence) [Pollinate](http://www.eclipse.org/pollinate/).[EDIT] Bon, j'ai trouv??. Et je me doutais que ce serait ce genre de petite salet?? aga??ante. Un slash en trop devant un nom d'action. C'est rien, hein, juste de la distraction ! Oui, mais de la distraction comme ??a, qui vous prend la demi-journ??e, je peux m'en passer, merci bien !via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)