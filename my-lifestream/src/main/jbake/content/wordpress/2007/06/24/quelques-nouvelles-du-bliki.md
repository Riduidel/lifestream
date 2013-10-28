type: post
status: published
title: Quelques nouvelles du bliki
tags: code, informatique, lebliki, ruby
date: Sun Jun 24 14:25:16 CEST 2007
~~~~~~
# Quelques nouvelles du bliki

En ce moment, ça foire un peu. 
####Un bug tout pourri pour les données bibliographiques
J'ai un bug un peu galère sur le feu avec mon superbe AmazonAsin. Un bug d'autant plus pénible que ma version de Rails ne contient pas de breakpointer (me semble-t-il) et que le seul résultat que j'obtiens est de ne pas réussir à permettre la création des données bibliographiques. Et ça, ça m'énerve. 


####Skinnable 

#####Ma mauvaise humeur Pour passer le temps, j'ai donc essayé un peu d'utiliser le plugin skinnable. Et j'ai encore déchanté. Mais là, je vais l'expliquer en détail, puisque l'auteur est français et, j'imagine, en attente de tout feedback. Donc, le plugin est, d'aprés l'auteur, un one-shot. Personnellement, je n'apprécie pas trop ce genre d'idée. J'apprécie encore moins le fait que tous les styles soient téléchargés dans le plugin pour qu'un seul soit installable à la fois dans mon application (puisqu'ils sont utilisés pour générer à chaque fois le méme fichier application.rhtml). Mais heureusement, j'ai des idées. 


#####Quelques suggestions La première, et à mon avis la plus essentielle, c'est de laisser les styles sur un serveur, et de ne télécharger que celui que l'utilisateur souhaite utiliser. Ca a plusieurs avantages. D'abord, ce repository de plugin peut étre étendu à un trés grand nombre de styles sans altérer la taille du plugin téléchargé. Ensuite, la distribution d'un nouveau style serait facilité, puisque l'auteur aurait simplement à déposer son style dans ce repository. Corollaire de cette première proposition, je pense qu'il faudrait que toutes les données de style soient installées non pas dans les répertoires standard de Rails, mais dans des sous-répertoires reprenant le nom du style. Exactement comme dans Wordpress, en fait. Le lien avec le reste de l'application se ferait, dans le layout standard, par un `render :partial` bien positionné. Ainsi, un utilisateur pourrait installer plusieurs thèmes, un switch entre ces thèmes (qui pourrait méme faire partie du plugin), le tout sans pourrir les fichiers de son dossier de layout. Pour suivre les idées de base de Rails, pourquoi ne pas définir dans ces différents styles des inclusions de partial bien nommés, pour faciliter encore plus la modification de style ? Bon, en plus, quand j'ai essayé de modifier le thème fractal broccoli, je me suis retrouvé avec des erreurs de compilation à la noix, que je conservais méme quand le fichier était complétement vide. Sans doute parce que j'avais déplacé le fichier dans un sous-dossier de layouts. Bref, c'était le bazar, et j'ai dû faire un rollback subversion pour m'en tirer <i class="footnote">(Heureusement d'ailleurs que j'ai la bonne habitude (parfois un peu pénible au bureau pour mes collégues) de faire des commits trés réguliers pour n'avoir qu'un minimum de modifications en local. )</i>

####Heureusement, j'ai un beau calendrier Ben oui, il y a quand méme des plugins qui font bien leur boulot <i class="footnote">(En fait, je dis pas ça pour skinnable, qui est pas mal du tout, mais encore en beta, plus parce que c'est un peu agaçant d'installer un plugin, d'essayer de le faire marcher, pour se rendre compte au final que le truc ne fait vraiment qu'un job minuscule (comme par exemple acts_as_blog (supprimé), acts_as_threaded (supprimé), ...))</i>. Et, par exemple, calendariffic est ... terrifique. Les étapes d'installation sont bien documentées, le boulot est bien fait, et le résultat final déchire (enfin, je trouve). 


####Conclusion J'ai quand méme encore du boulot, moi. Je dois corriger mon bug, intégrer la modification du created_at, récupérer des pages depuis des flux RSS, des dossiers IMAP ou NNTP, et pour ça, je dois utiliser au mieux acts_as_configurable (qui est pas mal non plus, hein) et trouver une solution pour lancer des tâches périodiques dans mon application Rails. via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)