type: post
status: published
title: Comment on fait sudo dans un EJB ?
tags: 
date: Wed Jan 09 11:53:44 CET 2013
~~~~~~
# Comment on fait sudo dans un EJB ?

Question curieuse, que mon chef m'a posé pas longtemps après que j'explique à @bmathus que

https://twitter.com/riduidel/status/258176811693907968

Ou, dans une réponse plus longue, que JAAS était une bonne idée :

> 
Cela dit, plusieurs choses sont à retenir
  - l'authentification/autorisation est gérée dans toutes nos couches
 de manière homogène ET en utilisant toute la plomberie
 JavaEE/Glassfish. C'est-à-dire que, en dehors des développeurs de la
 partie royaume, la plupart des développeurs ne voient de la sécurité
 que les annotations @RolesAllowed/@RunAs. C'est incroyablement
 pratique au quotidien de ne pas avoir à se palucher d'envoi
 d'exception ou quoi que ce soit d'autre.
  - l'appli web et les web services utilisent une BASIC AUTH HTTP qui
 passe elle aussi par ce royaume. Donc on est sûr de l'utilisateur d'un
 bout à 'lautre de l'application. Et, bonus, c'est pas moi qui ait
 écrit le code de sécurité mais (j'espère) des mecs bien plus
 paranoïaques que moi (pas dur, vu que j'oublie parfois de fermer ma
 bagnole/ma maison à clé).
  - l'API à implémenter est raisonnablement simple - du moins dans
 Glassfish : dans l'ensemble, il faut juste être capable, étant donné
 un login/password, de retourner une liste de noms de rôles
 d'autorisation JAAS. Et, comme notre base est accédée via un
 connecteur JCA, ce connecteur est disponible via JNDI (mouaip, pas une
 goutte de CDI là-dedans) et donc dans le royaume, ce qui est
 indispensable. Autrement dit, Glassfish gère bien mieux que moi
 l'ordre de démarrage du bazar.

 Dans les inconvénients, j'aurais tendance à dire que
  - la doc peut être spartiate (en particulier ce qui concerne les
 différentes choses à faire pour que le serveur d'application utilise
 correctement le domaine)
  - les logs sont soit trop peu nombreux soit trop verbeux (pas assez
 nombreux si tu les enlève, mais trop verbeux dès que tu les active,
 parce que chaque méthode d'EJB nécessite une authentification).


Hélas, je me suis retrouvé à poser la question fatidique : [How can I change user principal during an EJB/MDB invocation?](http://stackoverflow.com/q/12935965/15619)

Et je vous encourage vivement à aller voir la réponse que j'ai moi-même écrite pour cette question, puisqu'elle montre précisément comment, tout en n'étant pas un élément de la spec JavaEE, il est possible d'implémenter "proprement" ce sudo.