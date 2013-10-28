type: post
status: published
title: Comment j'ai install?? mon ffp sur une cl?? USB 
tags: dns323, m??moire, mavie, usb
date: Mon Mar 02 21:07:22 CET 2009
~~~~~~
# Comment j'ai install?? mon ffp sur une cl?? USB 

En bonus de tout, ce message va me servir de m??moire de mon installation du driver usb-storage et du d??placement de fonz_fun_plug sur cl?? USB.  


Mes sources seront
* [Mounting external disks to the DNS-323 USB port for fun and profit](http://www.horto.ca/?p=27)
* [Moving ffp to a USB stick](http://bfg100k.blogspot.com/2008/06/moving-ffp-to-usb-stick.html) Ah, non pardon, il faut plut??t voir [Upgrading ffp 0.5 and moving it to USB... again](http://bfg100k.blogspot.com/2008/11/upgrading-ffp-05-and-moving-it-to-usb.html)

Avec ??a, normalement, je devrais m'en sortir ...

1) T??l??chargement et installation du driver USB

Je t??l??charge donc le module [usb-storage.ko](http://www.domaigne.com/download/dns-323/usb-storage.ko). (c'est l'url que tout le monde utilise, hein)

Je l'installe sur mon disque 2 (qui n'est pas en RAID). Donc :

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt# cd /mnt/HD_b2</span>

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2# mkdir .bootstrap</span>

<span style="background-color:#cccccc;">root@dlink-6A4EB1:</span><span style="background-color:#cccccc;">/mnt/HD_b2</span><span style="background-color:#cccccc;"># cd .bootstrap</span>

<span style="background-color:#cccccc;">root@dlink-6A4EB1:</span><span style="background-color:#cccccc;">/mnt/HD_b2/</span><span style="background-color:#cccccc;">.bootstrap# wget <a href="http://www.domaigne.com/download/d" target="_blank">http://www.domaigne.com/download/d</a></span><span style="background-color:#cccccc;">ns-323/usb-storage.ko</span><span style="background-color:#cccccc;"><br></br></span>

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/.bootstrap# ls</span>

<span style="background-color:#cccccc;">usb-storage.ko</span>

Voil?? ! usb-storage est t??l??charg??.  


Reste ?? l'activer.

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/.bootstrap# insmod usb-storage.ko</span>

Notez bien que cette commande ne supportera pas le red??marrage. Mais ?? mon avis, pour la remise en route automatique, on verra plus loin ...

D'une mani??re typique, je teste l'installation

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/.bootstrap# dmesg</span>

<span style="background-color:#cccccc;">Initializing USB Mass Storage driver...</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">scsi2 : SCSI emulation for USB Mass Storage devices</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: device found at 5</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: waiting for device to settle before scanning</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">scsi3 : SCSI emulation for USB Mass Storage devices</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: device found at 6</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: waiting for device to settle before scanning</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usbcore: registered new driver usb-storage</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">USB Mass Storage support registered.</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">?? Vendor: Kingmax   Model: USB2.0 FlashDisk  Rev: 1.00</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">?? Type:   Direct-Access                      ANSI SCSI revision: 02</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">?? Vendor: IC35L060  Model: AVV207-0          Rev:  0 0</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">?? Type:   Direct-Access                      ANSI SCSI revision: 00</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">SCSI device sdc: 7737344 512-byte hdwr sectors (3962 MB)</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: Write Protect is off</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: Mode Sense: 23 00 00 00</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: assuming drive cache: write through</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">SCSI device sdc: 7737344 512-byte hdwr sectors (3962 MB)</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: Write Protect is off</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: Mode Sense: 23 00 00 00</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdc: assuming drive cache: write through</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">??sdc: sdc1</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">Attached scsi removable disk sdc at scsi2, channel 0, id 0, lun 0</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">Attached scsi generic sg2 at scsi2, channel 0, id 0, lun 0,  type 0</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">SCSI device sdd: 66055248 512-byte hdwr sectors (33820 MB)</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdd: assuming drive cache: write through</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: device scan complete</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">SCSI device sdd: 66055248 512-byte hdwr sectors (33820 MB)</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">sdd: assuming drive cache: write through</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">??sdd: sdd1</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">Attached scsi disk sdd at scsi3, channel 0, id 0, lun 0</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">Attached scsi generic sg3 at scsi3, channel 0, id 0, lun 0,  type 0</span><span style="background-color:#cccccc;"><br></br></span><span style="background-color:#cccccc;">usb-storage: device scan complete</span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;">L??, je vois ??a, j'ai envie de dire "**Bouyah !**" Ce gros pat?? de logs signifie simplement que le module a ??t?? correctement install??, et a trouv?? plusieurs ??l??ments (normal, j'ai branch?? sur mon hub une cl?? USB Kingmax et un disque dur externe).</span></span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Maintenant, il faut monter tout ce petit monde (et id??alement le monter d'une mani??re visible ?? travers tous les protocoles).</span></span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Pour ??a, la m??thode la plus simple est de monter tout l'USB ?? un endroit pratique ... comme par exemple ce m??me disque HD_b2.</span></span>

<span style="background-color:#ffffff;">Mais qui est la cl?? USB, et qui est le disque dur ?</span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Facile :</span></span>

<span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2# ls /proc/scsi/usb-storage/</span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">2  3</span></span></span><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><p></p></span></span><span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Maintenant, d??couvrons-en un peu plus ...</span></span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><br></br></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;">root@dlink-6A4EB1:/mnt/HD_b2# cat /proc/scsi/usb-storage/2<br></br>??  Host **scsi2**: usb-storage<br></br>??      Vendor: Kingmax<br></br>??     Product: USB2.0 FlashDisk<br></br>Serial Number: 777090109FFFFF0000000399<br></br>??    Protocol: Transparent SCSI<br></br>??   Transport: Bulk<br></br>??      Quirks:<p></p></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Bon, ben voil?? ma cl?? Kingmax</span></span>

<span style="background-color:#cccccc;"><span style="background-color:#ffffff;">Et mon disque dur est donc ...</span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;">root@dlink-6A4EB1:/mnt/HD_b2# cat /proc/scsi/usb-storage/3<br></br>??  Host **scsi3**: usb-storage<br></br>??      Vendor: Cypress Semiconductor<br></br>??     Product: USB2.0 Storage Device<br></br>Serial Number: DEF109AE572C<br></br>??    Protocol: Transparent SCSI<br></br>??   Transport: Bulk<br></br>??      Quirks:<p></p></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">R??sumons-nous.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">La cl?? USB est donc scsi2 et le disque dur scsi3.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Un dernier truc ... avant de monter la cl??, il faut bien v??rifier qu'elle est en FAT32 (sinon ca foire, bien s??r).</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">root@dlink-6A4EB1:/mnt/HD_b2# fdisk -l<p>Disk /dev/sda: 500.1 GB, 500107862016 bytes<br></br>255 heads, 63 sectors/track, 60801 cylinders<br></br>Units = cylinders of 16065 * 512 = 8225280 bytes</p><p>??  Device Boot      Start         End      Blocks  Id System<br></br>/dev/sda1               1          66      530113+ 82 Linux swap<br></br>/dev/sda2             131       30547   244324552+ 83 Linux<br></br>/dev/sda3           30548       60801   243015255  83 Linux<br></br>/dev/sda4              67         130      514080  83 Linux</p><p>Partition table entries are not in disk order</p><p>Disk /dev/sdb: 500.1 GB, 500107862016 bytes<br></br>255 heads, 63 sectors/track, 60801 cylinders<br></br>Units = cylinders of 16065 * 512 = 8225280 bytes</p><p>??  Device Boot      Start         End      Blocks  Id System<br></br>/dev/sdb1               1          66      530113+ 82 Linux swap<br></br>/dev/sdb2             131       30547   244324552+ 83 Linux<br></br>/dev/sdb3           30548       60801   243015255  83 Linux<br></br>/dev/sdb4              67         130      514080  83 Linux</p><p>Partition table entries are not in disk order</p><p>Disk /dev/sdc: 3961 MB, 3961520128 bytes<br></br>124 heads, 60 sectors/track, 1039 cylinders<br></br>Units = cylinders of 7440 * 512 = 3809280 bytes</p><p>??  Device Boot      Start         End      Blocks  Id System<br></br>/dev/**sdc1**               2        1040     3862192   b Win95 FAT32</p><p>Disk /dev/sdd: 33.8 GB, 33820286976 bytes<br></br>255 heads, 63 sectors/track, 4111 cylinders<br></br>Units = cylinders of 16065 * 512 = 8225280 bytes</p><p>??  Device Boot      Start         End      Blocks  Id System<br></br>/dev/**sdd1**               1        4112    33027592+  b Win95 FAT32</p></span></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><p>Vous voyez, tout va bien ;-)<br></br></p></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">On peut aussi constater que le device scsi2 est pr??sent?? sur /dev/sdc1 (??a, c'est la cl?? USB) et que le device scsi3 est pr??sent?? sur /dev/sdd1 (et ??a, c'est le disque dur).</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Ca m'aidera pour la suite, je crois.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Pour en revenir ?? ce que j'ai d??ja, je cr??e mon r??pertoire usb</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2# mkdir usb</span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2# cd usb/</span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# ls</span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mkdir kingmax</span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mkdir hdd</span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# ls</span><span style="background-color:#cccccc;"><br></br></span></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;"><br></br></span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Et je monte mes devices dedans.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">La cl?? USB :</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mount -t vfat -o umask=0 /dev/sdc1 /mnt/HD_b2/usb/kingmax/</span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><br></br></span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Puis le disque dur :</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mount -t vfat -o umask=0 /dev/sdd1 /mnt/HD_b2/usb/hdd/</span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><br></br></span></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Et quelques ls nous confirment la superbe v??rit?? : c'est mont?? ! (je ne les fais pas ici, car ils ne servent ?? rien, mais vous voyez l'id??e)</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Et maintenant, la partie r??ellement touchy.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">2) D??placement de fonz_fun_plug sur la cl?? USB</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">L??, c'est un peu plus risqu?? ...</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Avant tout ... Reformater la cl?? en ext2fs</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mke2fs /dev/sdc1</span></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><br></br></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Et bing ! Ma cl?? de 4 Go est pass??e en ext2fs.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Maintenant, je la remonte au m??me endroit</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb# mount /dev/sdc1 /mnt/HD_b2/usb/kingmax/</span></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><br></br></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Et je copie les fichiers de ffp sur la cl??.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><span style="background-color:#cccccc;">root@dlink-6A4EB1:/mnt/HD_b2/usb/kingmax# cp -a /mnt/HD_a2/ffp/ .</span></span></span></span></span><span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;"><br></br></span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">C'est maintenant qu'arrive la partie vraiment chaude ...</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Laissez-moi vous expliquer ...</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">fonz_fun_plug fonctionne gr??ce au fun_plug, un syst??me int??gr?? au DNS323 qui ex??cute un script sur le premier volume au d??marrage. Donc, il va falloir remplacer ce script par un script d??marrant fonz_fun_plug depuis la cl?? USB. ce script, il en existe plusieurs versions. J'ai choisi (pour l'instant) celle-ci.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Bon, pour l'instant, je suspend un peu le truc parce que ??a marche pas parfaitement, et que je dois faire ?? manger ;-) A tout de suite !</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Allez, retournons-y !</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Donc, finallement, je me dis que le plus simple, c'est encore de faire un petit fun_plug qui se contente de lancer celui de la cl?? USB.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Bon, en fait, non.</span></span></span></span>

<span style="background-color:#cccccc;"><span style="background-color:#cccccc;"><span style="background-color:#ffffff;"><span style="background-color:#ffffff;">Je vais refaire ??a une autre fois, parce que m??me si il n'y a en fait aucun risque (gr??ce aux copies simples de fichiers), faut vraiment que j'y r??fl??chisse prudement ...</span></span></span></span>