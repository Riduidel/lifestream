type: post
status: published
title: J'ai eu chaud !
tags: dns323, mavie, ssh
date: Tue May 26 20:59:34 CEST 2009
~~~~~~
# J'ai eu chaud !

Aujourd'hui, j'ai fait une sacr??ment belle connerie.J'ai voulu activer l'authentification par cl?? publique/cl?? priv??e sur le DNS323. Et, b??tement, j'ai suivi [un tutoriel](http://bfg100k.blogspot.com/2007/10/getting-ssh-working-on-dns-323.html) sur un site que je sais ??tre de mauvaise qualit??. Du coup, quand j'ai reboot?? mon NAS, je n'y avais plus acc??s en SSH, ni en telnet (je l'avais r??activ??, mais l'impl??mentation pr??sente dans fonz_fun_plug semble ... comment dire ... pas vraiment compl??te).Gros flip, quoi, parce que je devais aller ??diter ?? la main, et en tant que root, le sshd_config, auquel je n'avais pas acc??s en FTP ou en samba (rparce que c'est un fichier sur lequel seul root a des droits).Heureusement, le DNS323, avant fonz_fun_plug, c'est surtout le concept du [fun_plug](http://wiki.dns323.info/howto:fun_plug).Et, parmi les fun_plug, il y a celui qui fournit [un telnet qui marche](http://wiki.dns323.info/howto:telnet) !Donc, pouf, je l'installe, pouf je reboote, et blam je rends sshd_config accessible en ??criture. Et comme ??a, tranquillement, j'ai pu, via Samba, ??diter ce fichier, enlever les options pourries, et remettre fonz_fun_plug en route avec un SSH qui marchait enfin.J'ai quand m??me cru, l'espace d'un instant, que j'??tais foutu et que j'allais devoir r??installer fonz_fun_plug, bittorrent, et tout.