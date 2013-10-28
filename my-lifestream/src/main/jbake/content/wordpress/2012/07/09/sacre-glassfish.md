type: post
status: published
title: Sacré Glassfish !
tags: java, javaee
date: Mon Jul 09 17:07:36 CEST 2012
~~~~~~
# Sacré Glassfish !

On avait récement un probléme sacrément tordu de client EJB qui n'arrivait pas à se connecter à l'application quand on passait par internet et/ou un NAT.Pas de bol pour nous, c'est un bug connu de Glassfish : [EJB remote deployed on GF 3.1 behind a NAT unaccessible via a simple Java app](http://java.net/jira/browse/GLASSFISH-17151)Coup de bol pour nous, contrairement à Blaise Gosselin (qui a proposé un patch), on a longuement lu le source pour en arriver à la conclusion qu'on peut contourner ce probléme de dingues en positionnant une variable d'environnement Java : com.sun.corba.ORBServerHost. Une fois qu'elle a été positionnée (et que le fichier hosts du serveur a permis la bonne résolution d'adresse), notre appel d'EJB à travers internet s'est mis à marcher !