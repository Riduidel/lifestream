type: post
status: published
title: Lecture d\'un fichier de configuration
tags: code, informatique, rndsig, ruby
date: Wed Mar 30 12:21:00 CEST 2005
~~~~~~
# Lecture d\'un fichier de configuration

Comme {!RndSig} n'est qu'un clone de {RndSig}, il doit commencer, tout comme son illustre cousin, par lire un fichier de configuration pour charger un certain nombre d'informations. J'ai donc choisi de commencer par cette tâche mon implémentation. Avant toute chose, jetons un oeil à un fichier de configuration typique :

<pre>EDITEUR C:Program Filesnotepad2Notepad2.exeMULTIPLE DELAI 50 RCHQUOTE FORCERCRLF TOUCHE control-shift-F11 TOUCHEMAN control-alt-F11 AUTOCLE POIDS 10 POIDSAUTO 2 FCHDEF mysigs IGNORE les,des,mes,tes,ses,leur,leurs,notre,notres,votre,votres,you,your,yours,his IGNORE sur,sous,moi,toi,elle,pas,suis,sommes,etes,avons,avez,ont,pour,avec,une,mon IGNORE ton,son,dans,est,sont,her,they,their,are,were,and,for,with,under,over,out,off IGNORE from,our,your,well,html,the PRESEP  PRE Nicolas Delsaux</pre>

Ca a l'air facile, mais en fait non : un certain nombre de ces lignes de configuration sont des déclarations de flags (RCHQUOTE, FORCERCRLF, AUTOCLE), d'autres sont des entiers (DELAI, POIDS, POIDSAUTO), d'autres des chaÃ®nes de caractère (EDITEUR, TOUCHE), et d'autres enfin des parties de chaînes (IGNORE, PRE).  Pour arriver à ce résultat en Ruby, je me suis donc défini un objet Config, contenant un attribut pour chaque élément de la configuration. Seulement, comment le charger ? Simplement en lisant le premier mot de chaque ligne, puis en lisant la valeur associée à cette clé dans un [Hash](http://www.rubycentral.com/book/ref_c_hash.html). Et enfin, en exécutant le morceau de code qu'est cette valeur. Plus clairement, voyons un exemple (pas trop simple) : POIDS.



[https://gist.github.com/266034](https://gist.github.com/266034)

On remarquera la ruse qui consiste à associer, dans le initialize (pour que @weight soit bien associé à l'instance courante d'objet Config), la clé WEIGHT à un objet [Proc](http://www.rubycentral.com/ref/ref_c_proc.html) permettant de définir un bout de code exécutable. J'expliquerais le loadSignatures la prochaine fois. En attendant, la version courante de la classe Config est [disponible ici](wp-content/ruby/!rndsig/config.rb)

 via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)