type: post
status: published
title: Spring and static variables
tags: code, informatique, java
date: Tue Mar 14 19:21:00 CET 2006
~~~~~~
# Spring and static variables

I'm beginning a project implying Spring, Hibernate (both good softwares) and Struts (...). In this project, I want to give a certain Struts abstract action class a property that will be used by subclasses. In fact, there will be two properties : the Hibernate SessionFactory, and a local object used to generate meta information on objects. But I can't find the best way to wire them with Spring. I've found at least two ways, but none satisfies me : 
1. Wire all actions in the spring configuration file for them to use both objects. This seesm to me rather a bad idea, since our application will involve lots of actions and, by the way, lots of repeating lines.
2. Make struts actions [BeanFactoryAware](http://www.springframework.org/docs/api/org/springframework/beans/factory/BeanFactoryAware.html). But to my mind, it goes against Spring philosophy.

 I did not mention the use of autowiring, since it goes against some best practices I found on ONJava. So, what could other solutions be ?

Je débute un projet avec Spring, Hibernate (deux bons logiciels) et Struts (...). Dans ce projet, je voudrais affecter à la classe de base de mes actions Struts certaines propriétés utilisables par ses sous-classes. En fait, il y aurait au moins deux propriétés : le SessionFactory d'Hibernate, et un objet local utilisé pour générer des meta-informations sur les objets. Mais je ne trouve pas la meilleure solution pour les câbler avec Spring. J'ai trouvé au moins deux voies, mais aucune ne me satisfait :


1. Câbler toutes les actions dans le fichier de configuration de Spring. Cela me semble une idée plutôt mauvaise, puisque notre application va utiliser des tonnes d'actions et, par conséquent, de répétitions de lignes.
2. Rendre mes actions Struts [BeanFactoryAware](http://www.springframework.org/docs/api/org/springframework/beans/factory/BeanFactoryAware.html). Mais à mon avis, ça va contre la philosophie de Spring.

 Je n'ai pas mentionné l'autowiring, puisqu'il va à l'encontre de bonnes pratiques que j'ai lues sur ONJava. Alors, quelles auitres solutions existent ?via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)