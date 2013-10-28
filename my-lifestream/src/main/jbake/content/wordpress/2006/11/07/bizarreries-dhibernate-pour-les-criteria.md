type: post
status: published
title: Bizarreries d'Hibernate pour les Criteria
tags: code, informatique, java
date: Tue Nov 07 19:15:00 CET 2006
~~~~~~
# Bizarreries d'Hibernate pour les Criteria

Hibernate est vraiment un framework bizarre. En effet, il nous permet de faire des recherches en utilisant une API de [Criteria](http://www.hibernate.org/hib_docs/v3/api/org/hibernate/Criteria.html "Criteria (Hibernate API Documentation)") que j'aime beaucoup, à un détail pénible près. Supposons par exemple que vous ayez un objet utilisateur et une liste de groupes associés comme dans le cas suivant :

<pre>public class Utilisateur  implements java.io.Serializable {     private Set listGroupe = new HashSet();</pre>

Pour rechercher les utilisateurs du groupe d'id "20", vous allez écrire quelque chose comme

<pre>Criteria returned = Criteria.forClass(Utilisateur.class); Criteria listGroups = returned.createCriteria("listGroupe"); listGroups.add(Restrictions.eq("id", "20");</pre>

Sauf que, si vous utilisez à nouveau votre Criteria pour manipuler des objets Groupe, vous aurez droit à des exceptions de l'espace, car Hibernate n'accepte pas que vous crééiez d'autres Criteria portant sur la même association, c'est-à-dire qu'à partir du moment où vous écrivez une deuxième fois

<pre>Criteria listGroups = returned.createCriteria("listGroupe");</pre>

c'est foutu. Pour contourner ce bug à la noix, j'ai créé une sous-classe (de DetachedCriteria, car je l'utilise avec Spring et donc en mode détaché) qui me permet d'éviter ces recréations. Bien sûr, elle n'est pas parfaite, car ça n'est pas prévu dans Hibernate, mais bon, elle fait le boulot :



[https://gist.github.com/265964](https://gist.github.com/265964)

Bon, je m'en vais bug-reporter tout ça ...

 via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)