type: post
status: published
title: Swing MVC ?
tags: code, informatique, java
date: Wed Feb 02 13:18:00 CET 2005
~~~~~~
# Swing MVC ?

  Sur son blog, [Dim](http://colebatch.com/blog/archives/2004/11/swing_mvc_frame.html) déclare :

> Why is it that there are no swing frameworks that have got to a mature stage ?

J'ai une réponse simple pour Dim : Swing EST le framework Swing MVC mature. Regarde un peu la Javadoc et tu y trouveras des choses comme le `TreeModel`, un squelette poour ton composant modèle, `DefaultTableCellRenderer` pour ta vue. Apparement, les gens tendent à ignorer le fait que la partie contrôleur de Swing est gérée par l'envoi d'événement, comme `NodeChangedEvent`. En fait, l'événement n'est pas le contrôleur. Non, le contrôleur est, conceptuellement, la manière dont de tels événements sont envoyés quand l'utilisateur modifie quelque chose dans l'application.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)