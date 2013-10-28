type: post
status: published
title: Hibernate et objets EnhancedByCGLIB
tags: code, informatique, java
date: Wed Oct 18 11:59:00 CEST 2006
~~~~~~
# Hibernate et objets EnhancedByCGLIB

Hier, avec un collègue, on a eu un problème très agaçant avec des objets chargés grâce à la CGLIB dans Hibernate.

Ce qui se passe est, d'après ce que j'en comprend, plutôt simple :

Hibernate charge toutes les données de la base et, plutôt que de remplir directement les objets, place les données dans une structure quelconque et retourne des sous-classes construites par la CGLIB de mes classes, lesquelles seront remplies au premier appel d'une méthode de mes classes.

Seulement voilà, on a nous aussi notre framework qui, plutôt que d'appeler les méthodes, fait de l'introspection pour lire les champs (ce qui n'est peut-être pas si fameux que ça, dans un contexte JEE où tout le monde introspecte les classes métier à mort).

Donc, quand je lis une valeur de mon objet, je le fais sans que le [Proxy](http://java.sun.com/j2se/1.5.0/docs/api/java/lang/reflect/Proxy.html "Proxy (Java 2 Platform SE 5.0)") ait une chance d'être notifié, et donc sans que CGLIB puisse remplir les champs de mon objet. La solution ?

J'ai mis beaucoup de temps à la trouver, car elle n'est donnée nulle part où j'ai cherché.

En fait, les sous-classes CGLIB crées par Hibernate implémentent l'interface [HibernateProxy](http://www.hibernate.org/hib_docs/v3/api/org/hibernate/proxy/HibernateProxy.html "HibernateProxy (Hibernate API Documentation)"). Et, dans cette interface, d'après ce que je comprend, la méthode writeReplace permet de remplacer le proxy par le vrai objet. J'ai donc maintenant, à un endroit stratégique, une méthode dont le code contient :



[https://gist.github.com/266113](https://gist.github.com/266113)

Et voilà ! Plus de problèmes avec des objets com.sogeti.d2.metier.referentiels.phyto.BSS$$EnhancerByCGLIB$$cf4a3dfb ! Mais c'était vraiment chaud à résoudre !via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)