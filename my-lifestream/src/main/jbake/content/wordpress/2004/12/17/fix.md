type: post
status: published
title: Fix !
tags: informatique, leblog, web
date: Fri Dec 17 12:36:00 CET 2004
~~~~~~
# Fix !

Bon, après une nuit de dure lutte (en fait, plutôt dix bonnes minutes ce matin) et l'aide appuyée de phpmysqladmin, j'ai corrigé mon bug de Google-Hilite.

Vous allez rire, parce que c'est encore une fois très bête : figurez-vous que j'ai réussi à activer **deux fois** Google-Hilite, ce qui provoquait une récursion d'inclusion du code PHP. D'où l'erreur précédement mentionnée. Donc, je suis allé dans wp_options supprimer une des mentions de Google-Hilite. Et ça remarche !

Bon, d'un autre côté, il n'y a **vraiment** pas de quoi pavoiser : je croyais que WordPress faisait partie des gros du blog, et là, en un instant, il démontre un niveau que je pourrais atteindre à mon avis assez rapidement. Donc c'est forcément décevant.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)