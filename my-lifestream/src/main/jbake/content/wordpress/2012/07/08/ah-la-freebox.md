type: post
status: published
title: Ah la freebox !
tags: dns323, freebox, upnp
date: Sun Jul 08 18:29:05 CEST 2012
~~~~~~
# Ah la freebox !

La semaine derni??re, free a d??ploy?? une [mise ?? jour](http://www.freenews.fr/spip.php?article12282) de la freebox HD apportant cens??ment quelques mises ?? jour pour la partie HDMI (notament le HDMI CEC tr??s pratique pour se d??barasser ?? moiti?? d'une t??l??commande).  
H??las, dans le lot de modifications, il y avait aussi ... [la disparition de l'UPnP](http://bugs.freeplayer.org/task/10289) !Chouette, non ? NON.Parce que tous mes films sont stock??s sur le DNS-323 et acc??d??s en UPnP. Du coup, quand l'UPnP ne marche plus, ce sont les enfants qui viennent me dire qu'il y a un bug ... Pas cool du tout.Heureusement, le linuxien en moi a plus d'un tour dans son sac !Le tour, en l'occurence, c'est [l'excellent](http://riduidel.posterous.com/la-bonne-nouvelle-du-jour) lftp !Donc pif, je lance lftp. paf, je tape "mirror -R videos /Disque dur/Videos"Et pof : la freebox explose (plus exactement, je perds la mise en pause du direct et mes enregistrements du week-end) parce qu'il y a trop de films ?? synchroniser et que du coup il ne reste que 4 Ko sur le disque dur de la freebox.Alors, gentils d??veloppeurs de free, quand est-ce que vous corrigez ce fichu bug ?