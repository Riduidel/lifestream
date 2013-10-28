type: post
status: published
title: Subversion et Red Ha
tags: informatique, os, ubuntu
date: Tue Feb 28 16:11:39 CET 2006
~~~~~~
# Subversion et Red Ha

Je le mets dans la cat??gorie Ubuntu parce que je n'ai pas l'intention d'en cr??er une pour Red Hat, mais le probl??me concerne la Red Hat Enterprise Linux 4.Et quel est ce probl??me ?Facile : vous avez configur?? correctement votre Subversion et votre Apache, mais dans un navigateur, la seule chose affich??e est un angoissant Could not open the requested SVN filesystem.Mais que se passe-t-il ? C'est [SELinux](http://fr.wikipedia.org/wiki/SELinux), que Red Hat int??gre d'origine aux versions RHEL et Fedora, qui bloque l'acc??s d'Apache au r??pertoire de votre repository subversion. Pratique, non ?Enervant, oui !via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)