type: post
status: published
title: Un plus joli shaarli ...
tags: php javascript shaarli
date: Mon Jun 17 13:38:35 CEST 2013
~~~~~~
# Un plus joli shaarli ...

Bon,c e week-end, j'ai eu envie de coder.

Ca m'arrive de temps en temps (généralement quand je fais plus de maintenance que de nouveau dév). Là, la lubie du moment, c'est [shaarli](http://sebsauvage.net/wiki/doku.php?id=php:shaarli). Peut-être parce que c'est du code bien de chez nous, élevé en liberté. Peut-être aussi parce que ce delicious-like en PHP (et en un seul fichier index.php) est très simple à hacker, et que ça me change sacrément de la purée de JavaEE que je mange tous les jours - mais dont je ne me lasse curieusement pas tant que ça.

Toujours est-il que, la semaine dernière, je désespérais du style pas bandant que sebsauvage fournit par défaut. Il ne faut pas le prendre comme une critiquead hominem, hein. shaarli est riche de fonctionnalités, bien fichu, mais souffre juste, je trouve, d'un look un peu ... tristounet. Surtout quand je comapre le style par défaut avec ce que certains ([ArthurHoaro](http://links.hoa.ro/) par exemple) en font. J'avais donc entré [un item GitHub](https://github.com/sebsauvage/Shaarli/issues/93) en espérant que quelqu'un s'en emparerait. Et puis ça me titillait trop, surtout après [ce commentaire](https://github.com/nodiscc/shaarli-themes) indiquant qu'il existait un repository GitHub listant des thèmes shaarli "tout prêts".

J'ai donc sorti mon meilleur Notepad++ et, hier après-midi, devant un reportage racontant [l'histoire des amphores lors de l'expansion romaine](http://boutique.arte.tv/route_des_amphores_mare_nostrum) (si vous le regardez, vous pourrez ensuite faire des parallèles étonnants avec l'oil democracy américaine), suivi d'un autre sur le port disparu d'Istambul (moins bien que le premier, il faut le reconnaître), j'ai modifié le panneau de configuration de shaarli pour y ajouter ça

[](http://riduidel.files.wordpress.com/2013/06/mes-liens-opera_2013-06-17_10-46-13.png)

Bon, évidement, c'est pas super, mais je ne suis pas plus designer que sebsauvage :-)

Au niveu des sombres détails techniques, 'lessentiel du boulot est fait par du Javascript qui va se connecter à un repository de CSS pour Shaarli (en l'occurence celui de nodiscc), et le PHP se contente de copier le fichier user.css dans le dossier inc. Evidement il écrase sommairement celui déja présent.

Et en bonus, avec ça, et un peu de travail, tout le monde pourra avoir un shaarli sympathique ... Enfin, une fois que sebsauvage aura accepté ma pull request (raison principale pour alquelle j'écris ce message, d'ailleurs).