type: post
status: published
title: J'arr??te mes tentatives de ssher !
tags: informatique, os, ubuntu
date: Thu Jun 02 16:11:00 CEST 2005
~~~~~~
# J'arr??te mes tentatives de ssher !

Après deux semaines de tests de fous, j'arrête. En fait, mon problème n'est pas de lancer un ssh, mais de réussir à faire passer mon ssh à travers du http. J'avais trouvé les outils pour le faire ([httptunnel](http://www.nocrew.org/software/httptunnel.html), ou [desproxy](http://desproxy.sourceforge.net/), par exemple), mais il faut croire que je ne suis pas encore assez linuxien pour réussir à m'en servir avec des proxies Microsoft. Donc j'abandonne ça et je remplace le tout par un accès au shell depuis le web.

Oui, je sais, c'est **excessivement dangereux**.

Mais bon, mon utilisateur www-data n'a pas tant de privilèges que ça. Bref, je vais utiliser un [shell](http://mgeisler.net/php-shell/)[PHP](http://phpterm.sourceforge.net/) qui devrait me permettre à terme de réussir à déplacer mon blog sur mon serveur perso. Au passage, un truc comme [visitors](http://www.hping.org/visitors/) pourrait se révéler assez marrant. [EDIT] Et puis, pour la gestion des fichiers, un truc comme [webDAV](http://www.serverwatch.com/tutorials/article.php/2176771), [NotFTP](http://sourceforge.net/projects/notftp/) ou [phpWebFTP](http://phpwebftp.sourceforge.net/) serait bien utile. Dans le même ordre d'idées, un proxy http me paraît bien pratique ... Et le choix est vaste : [CECID](http://cecid.sourceforge.net/), [PHPRedirector](http://sourceforge.net/projects/phpredirector/), [PHP-proxy](http://php-proxy.sourceforge.net/), [Philtron](http://philtron.sourceforge.net/), [GURL](http://gurl.sourceforge.net/)via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)