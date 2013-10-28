type: post
status: published
title: 5 web apps guidelines
tags: code, informatique, java
date: Mon Apr 18 11:36:00 CEST 2005
~~~~~~
# 5 web apps guidelines

  J'ai lu cet excellent article sur [le blog de Brian](http://kasparov.skife.org/blog-live/src/webapp-guidelines.writeback) et, comme tout blloggeur, J'aimerais ajouter mon propre point de vue, et mes méthodes pour mettre ces règles en valeur.

> Séparer les états de la vue et du modèle.

J'aime cette idée de séparer la vue et le modèle, même dans le stockage. Mais, malheureusement, si Tapestry rend ça facile, ça n'est pas le cas de Struts (je pense que Weblogic Workshop - ou [Pollinate](http://www.eclipse.org/pollinate/) - peut aider là-dessus).

> Gérer l'état de la vue comme de la mémoire gérée à la main

  J'ai trouvé une ruse plutôt pratique pour les problèmes de gestion de session. C'est en fait assez simple : n'utilisez jamais la Session directement. Pour la dernière application web sur laquelle j'ai travaillé, nous avons implémenté un objet User, dans lequel toutes les informations étaient stockées, et pas dans une Map : ces informations ont été stockées comme des attributs de l'objet. C'est très pratique. Mais ça n'est pas le meilleur. Le meilleur est : n'appelez jamais de méthodes de la session, mis à part pour récupérer ce User. Et cette récupération peut aussi être dans la classe User. Ce qui donne le squelette de code suivant :



[https://gist.github.com/266059](https://gist.github.com/266059)

Evidement, dans un tel cas, il existe aussi une méthode createUser, qui stocke directement le User en session. De cette manière, il n'y a plus de gestion de session à l'ancienne. A la place, les différents états de l'utilisateur peuvent être stockés dans le User, tout comme les modes d'affichage spécifiques.

> Ne changez jamais l'état du modèle avec un HTTP GET.

Ca devrait être évident, à mon avis.

> Traitez les outils de test comme des interfaces utilisateur alternatives.

C'est intéressant. Les applications sont à mon sens moins testables que les applications traditionnelles. En particulier les miennes ;-) Il semble que c'est à cause d'un couplage fort entre la vue, Struts (et, de par le fait, le conteneur web), et le modèle. Heureusement, je pense qu'en utilisant cet objet utilisateur, ça doit être faisable plutôt facilement.

> La complexité de la vue est habituellement plus importante que celle du modèle.

Ouais, complètement vrai.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)