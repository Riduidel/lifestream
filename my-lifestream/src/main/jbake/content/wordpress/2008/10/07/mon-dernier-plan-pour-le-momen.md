type: post
status: published
title: mon dernier plan (pour le moment)
tags: 
date: Tue Oct 07 13:44:00 CEST 2008
~~~~~~
# mon dernier plan (pour le moment)

Vous le savez, j'ai toujours sous le coude un plan pour dominer le monde ...

Euh, non, pardon, pour pouvoir faire du blogounage de qualité.

En ce moment, mon plan, c'est diviser pour mieux régner.

J'ai donc un paquet de blog (dont celui-ci) sur des plateformes adaptées au contenu que j'y publie ([DZone](http://snippets.dzone.com/user/Riduidel), [Goodreads](http://www.goodreads.com/review/list/1156136), [LinuxFr](http://linuxfr.org/~Riduidel/), ....) Et au début, j'étais content de cette idée. Sauf que, en particulier pour goodreads, j'aime disposer d'archives de qualité, et sur le long terme (par exemple, j'ai, [planqué chez google groups](http://groups.google.fr/group/fr.rec.arts.sf/search?group=fr.rec.arts.sf&q=nicolas+delsaux+avis&qt_g=Rechercher+dans+ce+groupe), tous mes avis sur des bouquins depuis à peu près l'an 2000).

Donc j'avais trouvé l'idée de [lifestream](http://www.miaouw.net/articles/2007/09/differences-entre-lifestream-lifelog-et-lifecast/) intéressante, en particulier [sweetcron](http://code.google.com/p/sweetcron/), puisque je peux l'installer sur mon site chez free. Sauf que même chez free, ça n'est pas un de **mes** ordinateurs (même si je leur fais plutôt confiance, depuis le temps, pour conserver mes octets).

Et surtout, surtout, [Rui Carmo](http://the.taoofmac.com/space/Data%20For%20The%20Ages) l'a dit (enfin, je l'ai interprété comme ça), la forme optimale de sauvegarde sur le long terme est celle qui sera lisible facilement dans dix ans. Et pour moi, ça, ça veut dire des fichiers plats dans un FS quelconque.

Donc, j'ai commencé à écrire une extension pour [webgen](http://webgen.rubyforge.org/) à laquelle je donne à manger un fichier OPML avec tous mes flux, et qui me crée des fichiers que webgen utilise ensuite pour créer ce fameux lifelog.

Fameux, non ?

Bon, pour l'instant, je crée juste des .page dans un coin, que webgen processe ensuite, mais c'est prometteur.

Sauf que certaines API ruby sont moisies. Par exemple, [Ruby::RSS](http://www.rubyrss.com/) ne parse que le RSS le plus strict. Et donc [le flux de goodreads](http://snippets.dzone.com/posts/show/6210) qui est absolument magnifique donne un résultat minable. Je pensais naïvement trouver une solution facile pour accéder quand même aux données. Las, rien n'a marché.

J'ai donc repris l'un des meilleurs gems ruby : [hpricot](http://code.whytheluckystiff.net/hpricot/), qui après cinq minutes de recherche, dispose [d'extensions spéciales pour le rss](http://choonkeat.svnrepository.com/svn/rails-plugins/misc/hpricot/rss.rb) qui rendent le parsing beaucoup plus agréable, car flexible. Et dès ce soir, j'injecterais les données de goodreads (c'est-à-dire mes vieux avis dans une nouvelle forme) dans mon lifelog !

Bon, après, faudra encore gérer les tags, la génération d'un flux RSS de qualité, delicious, tout ça. Mais ça me fait moins peur grâce ) hpricot (et aussi grâce à l'auteur de webgen, qui est un mec épatant).