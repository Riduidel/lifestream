type: post
status: published
title: Connexion ?? Blogmarks
tags: bookmarcs, code, informatique, ruby
date: Fri Apr 22 10:11:00 CEST 2005
~~~~~~
# Connexion ?? Blogmarks

Bon, j'ai encore un ou deux problèmes avec les signets de mon disque dur (notamment IE qui n'en fait qu'à sa tête). Donc, pour changer, je commence la connexion en écriture à {BlogMarks} (parce que la lecture, ça fait longtemps que c'est fait). Donc, d'après la doc de [l'API Atom associé](http://www.blogmarks.net/docs/?doc=format), je dois envoyer un document XML après m'être authentifié (ça, c'est pas gagné). Naturellement, générer le document XML est de la rigolade avec la syntaxe HEREDOC. Mais que mettre dedans. Voici par exemple un document XML généré à partir de l'un de mes delicieux :



[https://gist.github.com/266049](https://gist.github.com/266049)

Comme vous le voyez, le titre et le lien sont bien gérés. C'est pour d'autres éléments que je me pose des questions :les ?
* L'ID utilisé envoyé à BlogMarks présente quel genre d'intérêt ?
* Les différents tags (dans dc:subject) sont-ils réellement envoyés à ce format ? Et que faire si l'un d'entre eux n'existe pas encore ?

Bref, pas mal de questions auxquelles, j'espère, Stéphane pourra répondre ...via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)