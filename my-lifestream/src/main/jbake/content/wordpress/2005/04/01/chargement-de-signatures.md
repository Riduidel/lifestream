type: post
status: published
title: Chargement de signatures
tags: code, informatique, rndsig, ruby
date: Fri Apr 01 18:46:00 CEST 2005
~~~~~~
# Chargement de signatures

J'ai parlé la dernière fois du fichier de configuration et de sa lecture (pour les curieux, j'en ai mis un exemple [ici](http://nicolas.delsaux.free.fr/wordpress/wp-content/ruby/!rndsig/rndsig.ini)). Mais je n'avais pas exmpliqué le chargement des signatures (le fameux `loadSignatures`). Donc allons-y. Il s'agit encore une fois de lire un fichier dont on connaÃ®t le format. Une signature se présente sous la forme suivante :

<pre>-- (Conan) Conan "Conan, qu'y a-t-il de mieux dans la vie ?" Conan "Ecraser ses ennemis, les voir mourir devant soi et entendre les lamentations de leurs femmes."</pre>

D'abord, un signe `-- ` pour commencer (l'espace fait partie du symbole). Eventuellement, un nom (entre parenthèses), un certain nombre de mots clés, dont le poids dans la signature correspond au `WEIGHT` défini dans le fichier de configuration. Puis, à la ligne suivante, et jusqu'à la prochaine ligne vide, le texte de la signature. Ca donne une méthode `loadSignatures` assez simple :



[https://gist.github.com/266027](https://gist.github.com/266027)

Bon, en fait, il y aune ruse. J'ai légèrement étendu le code de {RndSig} pour gérer plusieurs fichiers de signatures. Cette méthode se contente donc de charger chaque fichier de signature, en appelant la suivante, un poil plus complexe :



[https://gist.github.com/266029](https://gist.github.com/266029)

Pas bien compliqué, somme toute : `sigText` récupère chaque ligne de la signature. Si la ligne est vide (`lineLength==0`), on crée une nouvelle signature qu'on place dans la liste qui va bien. Sinon, on ajoute le texte à `sigText`. Bien sûr, `-- ` impose le remplacement de `sigText` par line. Rien de bien complexe, quoi. Sauf qu'on construit au vol une signature. Mais ça, j'en reparlerai plus tard. Les curieux pourront consulter le fichier signature.rb, au même en,droit que le reste.

 via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)