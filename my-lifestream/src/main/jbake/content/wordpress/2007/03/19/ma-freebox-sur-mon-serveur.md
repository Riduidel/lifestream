type: post
status: published
title: Ma freebox sur mon serveur
tags: informatique, os, ubuntu
date: Mon Mar 19 14:25:00 CET 2007
~~~~~~
# Ma freebox sur mon serveur

l y a quelques temps, j’ai reçu une nouvelle freebox. Je me suis donc empressé d’activer le support FTP pour pouvoir y uploader quelques films. Ce qui m’intéressait aussi, c’était d’avoir un accès transparent à cette freebox depuis mon serveur pour plusieurs raisons (autrement dit plusieurs logiciels) :
* mlDonkey, oui, c’est mal, mais bon, c’est bien quand même. 
* WebDAV, pour pouvoir m’envoyer des fichiers depuis ailleurs (par exemple, chez mon frère).  Du coup, j’ai installé le formidable [curlftpfs](http://curlftpfs.sourceforge.net/), qui m’a quand même obligé à rajouter pas mal de swap (car curlftpfs, avant d’uploader le fichier, le place dans le swap). Le seul truc qui foire, c’est mon WebDAV qui, étrangement, ne me montre pas le dossier représentant ma freebox. Dommage, mais je suis sûr que je vais réussir à le faire ... un jour :-) via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)