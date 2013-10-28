type: post
status: published
title: Mon avis sur Play! 
tags: java, web
date: Thu Mar 18 10:06:33 CET 2010
~~~~~~
# Mon avis sur Play! 

David, comme je trouve ta question int??ressante (et ma r??ponse encore plus), je vais la renvoyer sur mon blog.  
??

Une ch'tite question : as-tu un retour d'XP sur le "framework" Play! [http://www.playframework.org/](http://www.playframework.org/)

Tu le sais que tu devrais poser la question sur la ML Java ([java@u-strasbg.fr](mailto:java@u-strasbg.fr)) ? Bon, je l'ai pas encore utilis??, mais j'ai ??cout?? [l'??pisode des castcodeurs ?? ce sujet](http://lescastcodeurs.com/2010/02/les-cast-codeurs-podcast-episode-17-interview-de-guillaume-bort-sur-play-framework/), j'ai un peu regard?? comment ??a marchait sur [le touilleur express](http://www.touilleur-express.fr/2010/02/12/version-play-de-lapplication-zencontact-de-zenika/) et puis c'est pas la premi??re fois que je vois des trucs du genre ??merger ...??

  
Et, si tu devais le comparer ?? Grails et en faisant abstraction du langage (Java vs Groovy au moins pour la partie serveur) , quelle est ou quelles sont les diff??rences majeures d'apr??s toi ?

Alors ??a c'est int??ressant, parce qu'il y a un peu de groovy dans play (c'est le cas pour els templates de page, dont les parties dynamiques sont ??crites en groovy - ce qui me para??t une bonne id??e).Contrairement ?? [grails](http://www.grails.org/), play ne s'installe pas dans un conteneur J2EE classique, mais dispose du sien, c'est ?? mon sens un inconv??nient en termes d'exploitation, mais je peux me tromper.Ensuite, il inclut un compilateur, ce qui est un avantage, parce qu'il d??tecte (en mode d??v) les changements fait dans les fichiers Java et recompile lui-m??me les classes, ce qui donne des messages d'erreur optimaux (au sens o?? dans ton message d'erreur tu auras la ligne dans le code source d'o?? elle vient). Bien s??r, quand la classe est recompil??e ?? la vol??e, elle est recharg??e dynamiquement et utilis??e lors de la prochaine requ??te. Et ??a, c'est possible parce que play est un framework stateless. c'est-??-dire que la session telle qu'elle existe dans une [appli web J2EE](http://java.sun.com/products/servlet/2.2/javadoc/javax/servlet/http/HttpSession.html) n'existe pas ici. Bon, bien sp??r, il y a une authentification ?? fournir, qui est stock??e dans un cookie du navigateur. ca veut dire que tu peux arr??ter ton serveur Play, le relancer, et tu seras toujours connect??. Je n'ai pas besoin de te dire que c'est tr??s pratique !Bref, play est un framework visant la cible des applications vite et bien d??velopp??es (comme par exemple [l'express board](http://www.touilleur-express.fr/2010/03/18/lexpress-board-beta-faites-du-bruit/) sur lequel bosse [le touilleur express](http://www.touilleur-express.fr/)). En quelque sorte, on pourrait dire (et bien des gens le disent) que play est une tentative du monde Java d'aller marcher sur les plate-bandes du monde PHP, mais avec tous nos avantages sp??cifiques (typage fort, ??normes possibilit??s de connexion ?? l'informatique d'entreprise, ...).

Evidement, ??a n'est pas mon id??e ;-)

Pour moi, play! est un framework d'avant-garde. Je vais prendre une m??taphore os??e avec un mouvement artistique que je connais un peu, le cyberpunk. le [cyberpunk](http://fr.wikipedia.org/wiki/Cyberpunk) fut,d ans les ann??es 80, un mouvement d'avant-garde pluridisciplinaire. Dans le champ de la litt??rature de science-fiction, il a donn?? des oeuvres comme [c??bl??](http://www.goodreads.com/book/show/3628875.C_bl_), [neuromancien](http://www.goodreads.com/book/show/22328.Neuromancer), et, dans une version tardive, [Le samoura?? virtuel](http://www.goodreads.com/book/show/45281.Le_Samoura_virtuel). il s'est ensuite diffus?? plus largement gr??ce en particulier ?? [Matrix](http://fr.wikipedia.org/wiki/Matrix). Aujourd'hui, le courant d'avant-garde du cyberpunk peut ??tre consid??r?? comme mort; cependant, ses id??es ont infus?? une grande partie de l'art.A mon avis, c'est ce qui va arriver ?? play. Il va rester un tr??s bon framework tr??s en pointe, mais ne va pas r??volutionner le monde, seulement lui apporter de nouvelles id??es (le c??t?? stateless, la compilation ?? la vol??e, le m??lange harmonieux Java/Groovy,...)

Merci d'avance ;-) Ben de rien, c'est toujours un plaisir de donn?? un avis mal ??clair?? :-)