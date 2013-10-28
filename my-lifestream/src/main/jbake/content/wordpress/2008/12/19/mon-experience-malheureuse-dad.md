type: post
status: published
title: Mon expérience malheureuse d'admin ...
tags: 
date: Fri Dec 19 12:37:24 CET 2008
~~~~~~
# Mon expérience malheureuse d'admin ...

Ca faisait un petit moment que je me disais que je devais vous le raconter ...

Il y a bien des années, dans la boîte où je bossais à l'époque, l'admin en chef a décidé de se séparer de tout un tas de brouettes, dont un serveur Dell Poweredge 1300, bi-processable, avec des disques durs SCSI, le controlleur qui va bien, l'alim de fou qui tourne avec, et tout le bazar.

Au même moment, j'avais déja compris que free, question blog-hosting, c'était pas vraiment le Pérou.

Donc, j'ai abandonné [mon blog hébergé](http://nicolas.delsaux.free.fr/wordpress) (cherchez pas, comme je le raconte dans [ma première note](http://riduidel.posterous.com/une-nouvelle-forme-de-blog-1), ça ne marche plus depuis longtemps) pour un truc en local.

Je me suis installé un [Ubuntu](http://www.ubuntu-fr.org/) sur ce magnifique serveur, un Apache, j'ai récupéré mes archives wordpress, et zou, j'avais un blog en local.

Hélas, comme je ne suis pas un admin dans l'âme, j'ai vite rajouté des trucs : BitTorrent, une connexion [XDMCP](http://fr.wikipedia.org/wiki/XDMCP) pour utiliser Gnome depuis mon iBook, et bénéficier de la beauté de synaptic, et surtout, surtout, un serveur [WebDAV](http://fr.wikipedia.org/wiki/WebDAV) pour accéder à mes fichiers depuis mon portable (ça, ça va) et depuis mon boulot.

Parce que WebDAV, c'est trop une tuerie : je peux voir mes fichiers depuis mon boulot sans ouvrir aucun port (ce que les admins de mon boulot de l'époque - et ceux de mon boulot de maintenant - auraient dans un bel ensemble refusé). Sauf que bien configurer ça, c'est un boulot d'admin.

Du coup, comme je ne suis pas admin, j'ai ouvert "un peu trop l'accés" et mon site web s'est mis à ralentir pour des raisons bizarres (surtout quand on ne regarde jamais les logs d'accès Apache).

Un jour, je l'ai fait, j'ai regardé ces logs.

Et j'ai constaté avec stupeur que des milliers de requétes étaient faites sur un gros zip plein de MP3. Tellement même, que ça paralysait la bande passante d'upload de chez moi. D'ailleurs, si vous tapez encore maintenant dans google [http://nicolas-delsaux.is-a-geek.net/webdav](http://nicolas-delsaux.is-a-geek.net/webdav), vous tomberez sur des résultats pas forcément marrants).

Tellement, même, que les pirates des quatres coins du monde s'échangeaient l'adresse de ce fichier.

N'écoutant que mon courage, quand j'ai vu ça, j'ai pris une mesure radicale : j'ai débranché la prise réseau du serveur !

Et depuis, je ne l'ai jamais rebranché (sauf pour exporter à nouveau le dump SQL de la base de mon Wordpress).

D'ailleurs, le serveur a même refusé de redémarrer, à cause d'un ventilateur qui s"était usé à force de n'étre pas vraiment droit.

J'y ai gagné quoi ?

Oh, quelques watts sur ma facture

Beaucoup plus de silence dans ma cave (vous n'imaginez pas à quel point ce genre de serveur d'entreprise peut étre bruyant).

Et pas mal de respect pour ces ingrats d'admins qui passent leur temps à me dire **NON** mais ne laissent pas des failles béantes dans leurs serveurs.