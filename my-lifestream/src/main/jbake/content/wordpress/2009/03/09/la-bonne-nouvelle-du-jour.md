type: post
status: published
title: La bonne nouvelle du jour 
tags: dns323, freebox, ftp, linux
date: Mon Mar 09 21:27:14 CET 2009
~~~~~~
# La bonne nouvelle du jour 

Je cherchais d??s??sp??r??ment comment faire pour synchroniser ma freebox et mon disque dur r??seau, et, d'une mani??re indirecte, j'ai trouv??.  


La r??ponse courte tient en un soft : [lftp](http://fr.wikipedia.org/wiki/Lftp).

La r??ponse longue est ... plus longue??<img alt="338" height="12" src="http://getfile6.posterous.com/getfile/files.posterous.com/riduidel/BGIt0Fv6jA9pSznMeYz3jFWSmODQGdm8qppEFFOY7IgH2AqfwHI6MQHfbXTQ/338.gif" width="12"></img>

J'ai donc d?? commencer par chercher si je pouvais trouver un package pour fonz_fun_plug, mais je n'ai pas r??ussi ?? mettre la main sur le package (le site o?? il est disponible semble mort).

Du coup, je suis pass?? ?? la m??thode B (trouv??e gr??ce au super forum d??di??) :
1. Installer [optware](http://wiki.dns323.info/howto:optware) (ce qui n'est pas trivial)
2. Taper ipkg install lftp (??a, par contre, ??a rappelle beaucoup trop ubuntu pour que ce soit mal).

Et boum, ??a marche.

Du coup, demain, je vais voir si je peux faire des synchronisations en suivant des liens symboliques, ce qui serait, je ne vous le cache pas, totallement cosmique.

Apr??s, la seule chose encore plus dingue ?? faire, c'est de demander ?? ce pauvre petit disque dur de faire du transcodage vid??o pour optimiser l'espace sur la freebox. Oui, je sais, c'est dingue.

Mais devinez quoi ? Il est en plus un peu plus facile de bouger tout le bazar install?? sur le disque dur vers une cl?? USB une fois optware install?? ... Enfin, faut voir, quoi.