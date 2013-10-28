type: post
status: published
title: Un backup qui marche
tags: informatique, os, ubuntu
date: Sun Jul 23 08:51:40 CEST 2006
~~~~~~
# Un backup qui marche

Le backup et le blog, c'est une [longue](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2005/une-panade-pas-croyable/)[histoire](http://nicolas-delsaux.is-a-geek.net/wordpress/index.php/archives/2006/backup-des-fichiers-du-blog/) tr??s compliqu??e.Je croyais m'en tier avec un simple plugin Wordpress. Finallement, j'utilise une m??thode plus radicale : j'ai install?? sur ma machine un petit soft de copie via FTP : [sitecopy](http://www.delafond.org/traducmanfr/man/man1/sitecopy.1.html "http://www.delafond.org/traducmanfr/man/man1/sitecopy.1.html"). Je comptais initiallement utiliser [FUSE](http://fuse.sourceforge.net/ "FUSE: Filesystem in Userspace") et [curlftpfs](http://curlftpfs.sourceforge.net/ "CurlFtpFS - A FTP filesystem based in cURL and FUSE"). Seulement, ce dernier a une d??pendance sur une version de curl non disponible dans Ubuntu Dapper Drake. Donc, je me suis rabattu sur sitecopy, qui est plus simple, qui marche exactement comme je lke souhaite, et qui peut facilement s'int??grer dans mon crontab (au passage, modifier ce crontab avec [GNOME Schedule](http://gnome-schedule.sourceforge.net/ "Gnome-schedule"), c'est tr??s facile).via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)