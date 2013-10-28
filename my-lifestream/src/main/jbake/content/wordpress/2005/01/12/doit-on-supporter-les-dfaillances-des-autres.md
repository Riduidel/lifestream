type: post
status: published
title: Doit-on supporter les d??faillances des autres ?
tags: code, informatique, java
date: Wed Jan 12 12:11:00 CET 2005
~~~~~~
# Doit-on supporter les d??faillances des autres ?

  Dans tout projet web/J2EE se pose à un moment la question complexe des bugs de navigateurs.

Qu'il s'agisse de IE, de Firefox, d'Opera ou d'autres. Et, à chaque fois que ça arrive, c'est la responsabilité du développeur de corriger les bugs.

J'ai toujours trouvé que c'était la pire des idées.

En effet, cela requiert un effort important de l'équipe de développement pour identifier le bug du navigateur, pour implémenter un contournement (qu'il soit déja connu ou non) et pour le tester sur le navigateur problématique (la partie la plus délicate étant parfois de trouver une machine disposant de ce navigateur (pensons par exemple à IE 5.5)). Pourquoi ne pas plutôt penser au mouvement de standardisation du web ? De nos jours, les webmasters tendent à promouvoir de nombreux standards W3C : XHTML, CSS, l'accessibilité, les RIA, ... qui sont tous utilisés dans les projets web modernes.

Je pense qu'un grand pas en avant pourrait être fait si les développeurs J2EE étaient plus conscients de ça. En effet, une partie non négligeable du web est constituée maintenant de sites J2EE. Et, malheureusement, je trouve rarement des gens conscients, que ce soit du côté du client ou de celui des développeurs, des conséquences d'un tel mouvement. Le principal avantage pour le développeur de la standardisation pourrait être énoncé simplement : "Votre navigateur est pourri et je ne le corrigerai pas !". C'est difficile à entendre. Mais c'est la triste réalité. Tous les chefs de projets devraient vraiment penser à ça, s'ils ne souhaitent pas que leur projet s'englue dans les "incompatibilités de navigateurs".via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)