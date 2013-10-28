type: post
status: published
title: du 
tags: dns323
date: Thu Dec 17 11:31:51 CET 2009
~~~~~~
# du 

Juste pour que je m'en souvienne pour plus tard  
La commande pratique pour conna??tre l'utilisation du DNS-323, c'est [du](http://www.busybox.net/downloads/BusyBox.html#du)  
Et sa d??clinaison ?? utiliser sur "/", c'est  


  
root@dlink-6A4EB1:/# du -d 1 -c -h /mnt/HD_a2 /mnt/HD_b2  


28.0k /mnt/HD_a2/.systemfile  
 426.3M /mnt/HD_a2/nicolas  
3.8M /mnt/HD_a2/veronique  
13.5G /mnt/HD_a2/commun  
193.5M /mnt/HD_a2/ffp  
29.8M /mnt/HD_a2/ipkg  
132.0k /mnt/HD_a2/.transmission-daemon  
940.0k /mnt/HD_a2/lnx_bin  
14.1G /mnt/HD_a2  


20.0k /mnt/HD_b2/.systemfile  
122.9G /mnt/HD_b2/multimedia  
12.0k /mnt/HD_b2/usb  
48.0k /mnt/HD_b2/.bootstrap  
 122.9G /mnt/HD_b2  


137.0G total  


Et quand je vois ??a, je me dis que je peux encore mettre bien des choses l??-dedans !