type: post
status: published
title: Fonctionnalités typiques d'un bliki (suite)
tags: code, informatique, lebliki, ruby
date: Tue May 15 15:25:00 CEST 2007
~~~~~~
# Fonctionnalités typiques d'un bliki (suite)

Grrr ... Hier soir, j’avais édité mon message Qu’est-ce qui différencie un bliki d’un wiki, mais à cause d’un réseau pas au top, ma modification a été mangée par le réseau. Donc je recommence ... Donc, je disais que Craowiki parlait également des blikis, avec une liste de fonctionnalité que je m’en vais de ce pas désosser.

> Publication directe vers l’internet sans connaissance du code ou du HTML

Merci à [RedCloth ](http://redcloth.org/)et [acts_as_blog](http://juixe.com/techknow/index.php/2006/07/05/acts-as-blog-plugin/), qui m’ont permis d’implémenter ça sans trop de boulot.

> Track Back, et capacité de pings

Pour ça, je crois que je préfère un peu la solution de l’ouvre-boîte, qui ne différencie pas les trackbacks des urls entrantes. Cependant, il faut bien sûr pouvoir administrer ça pour éviter le trackback spam.

> Gestion des catégories

Je vais essayer d’intégrer ça à ma gestion des tags, mais ça n’est pas forcément évident. Quoique [has_many_polymorphs](http://m.onkey.org/2007/8/14/excuse-me-wtf-is-polymorphs) explique comment le faire (voir en particulier self-referential tagging) (même si je ne trouve pas l’explication très limpide)

> Support des jeux de caractères internationaux

Bon, là, c’est plus du ressort de Ruby et Rails, mais effctivement, j’y pense.

> Notification de nouveau contenu via Messagerie Instantanée comme Jabber

Beuark ! Grâce à Rui Carmo, j’ai imaginé une solution plus intelligente permettant d’accepter du contenu venant au choix : d’un newsgroup usenet, d’un dossier IMAP, ou même d’un flux RSS. D’un autre côté, je ne vois pas en quoi ce serait complexe d’ajouter Jabber à cette liste ...

> Moteur plein-texte intégré

Déja fait, grâce à [ferret](http://www.davebalmain.com/trac)

> Respect des standards - XHTML et support CSS (Cascading Style Sheets)

Là aussi, j’essaye de faire de mon mieux. Mais, à mon avis, la meilleure solution serait encore de laisser bosser [HAML](http://haml-lang.com/docs.html).

> Serveur Web Intégré SQL Web

Là, je ne comprend pas le sens de la phrase.

> Multi-utilisateurs

Là aussi, c’est fait.

> Multi-blogs

Disons que, dans l’état actuel, cette fonctionnalité serait envisageable si je munissais la page d’un utilsiateur de son propre flux RSS (ce qui d’ailleurs serait une très bonne idée ... je vais creuser ça, tiens).

> Supports pour images, fichiers vectoriels SVG et pièces jointes

J’y réfléchis, mais c’est pas pour tout de suite.

> Support pour GeoURL

Si quelqu’un peut me dire ce que c’est, ça m’aiderait.

J’en profite pour dire que j’aimerais bien, à un moment donné, envoyer le bliki dans le pays merveilleux des microformats, parce que je pense que ça aurait un sacré look.  bq. Open Source  Déja fait  Voilà. Ca fait encore un peu plus de code à écrire, j’ai l’impression ... Mais ce que j’aimerais surtout, c’est l’avis de mes deux trois lecteurs ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)