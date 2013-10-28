type: post
status: published
title: Franchement, Eclipse, ?? installer, c'est gal??re 
tags: ide, java, plugins
date: Thu Jun 25 12:47:15 CEST 2009
~~~~~~
# Franchement, Eclipse, ?? installer, c'est gal??re 

Ce matin, Eclipse 3.5 est sorti, et je me suis dit que j'allais l'installer. Seulement, j'utilise aussi maven et subversion. Ert d'un seul coup, ??a devient moins dr??le ?? faire marcher !
1. La premi??re fois, j'ai install?? [subversive ](http://www.eclipse.org/subversive/)avant ses connecteurs. Du coup, ??a ne marchait pas.
2. La deuxi??me fois, ??a marchait presque bien, sauf l'int??gration maven/svn (pour que quand je r??cup??re un projet, il ait automatiquement la bonne nature et la bonne gestion des d??pendances. Et pour corriger ??a, j'ai d?? passer aux builds stables de [m2eclipse ](http://m2eclipse.codehaus.org/)et, en bonus, essayer d'installer l'int??gration subversion (mais rien ne pr??cise dans le site de m2eclipse que cette int??gration est r??serv??e ?? subclipse)
3. Et depuis, je n'arrive plus ?? installer correctement le connecteur SVN pour Subclipse ...  
