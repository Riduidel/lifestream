type: post
status: published
title: Réinstaller fonz_fun_plug
tags: admin, dns323
date: Mon Mar 25 20:41:14 CET 2013
~~~~~~
# Réinstaller fonz_fun_plug

Une petite checklist des étapes à suivre pour réinstaller correctement [fonz_fun_plug](http://dns323.kood.org/howto:ffp) et tout ce qui va avec sur le DNS-323.

1. Pour l'installation de fonz_fun_plug, suivre le [guide de cette page](http://bernaerts.dyndns.org/appliance/231-dns323-ffp7-install-funplug7)
2. Copier depuis `/mnt/HD_a2/config` le fichier sites dans `/ffp/etc/funpkg/sites`
3. Installer ensuite toutes les libs de minidlna, puis [minidlna](http://sourceforge.net/projects/minidlna/). Faire un lien de la config de référence (dans /mnt/HD_a2/config) vers /ffp/etc/minidlna.conf. Lancer immédiatement minidlna pour qu'il scanne tous les dossiers de média.
4. Installer ensuite ligntthpd avec php et copier la config de référence vers `/ffp/etc/lighttpd.conf`. Bien vérifier que lighttpd se lance correctement.
5. Utiliser [ces versions de ](http://forum.dsmg600.info/viewtopic.php?pid=19267#p19267) pour que l'extension GD de php fonctionne (essentiel pour selfoss ou ownCloud)
Après, il faut encore trouver comment configurer mldonkey ... (soupir) et ça, c'est vraiment pas gagné.