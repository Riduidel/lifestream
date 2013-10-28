type: post
status: published
title: Tr??s bonne id??e
tags: informatique, web
date: Fri Apr 01 17:40:00 CEST 2005
~~~~~~
# Tr??s bonne id??e

Les sélecteurs CSS permettent de définir un style descendant. Donc <a>EricMeyer</a> en profite pour nous permettre, par le biais d'un style utilisateur, de modifier son site.

> Starting this morning, I modified meyerweb.com to have the  following on every page[1]:[html][/html]

> Why?  To allow anyone who wants to restyle my site to do so simply by  adding the appropriate rules to a user stylesheet.  By using  descendant selectors starting with the site ID, the styles will apply  only to pages on my site.  So let's say you don't like my font  choice.  Add the following to a user stylesheet:[css]#www-meyerweb-com {font : 1em Times, serif;}[/css] [css]#www-meyerweb-com * {font-family : Times, serif;}[/css]

N'étant pas à une expérience près, je vais sans doute essayer la même chose, en modifiant le body de mon blog pour lui donner l'id nicolas.delsaux.free.fr, ainsi, vous en ferez ce que vous voulez.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)