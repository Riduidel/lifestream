type: post
status: published
title: Chargement d\'une signature
tags: code, informatique, ruby
date: Tue Apr 05 12:15:00 CEST 2005
~~~~~~
# Chargement d\'une signature

Plongeons maintenant dans le chargement de l'objet signature. A la base, c'est relativement facile

<pre>def initialize(_sigText, _config)                 @signatureText = _sigText                 @config = _config                 # table associant ?? des mots leur poids                 @keywords = {}                 createSignatureMetaData         end</pre>

Ce qui est plus complexe, c'est la création des métadonnées, c'est-à-dire par exemple les mots-clés. Notons que, dans la méthode précédente, _config est un objet Config, utilisé en tant que réservoir de données (celles lues d'abord du fichier de configuration). On notera également que @keywords est un [Hash](http://www.rubycentral.com/book/ref_c_hash.html) associant à un mot (String) un poids (Integer), de manière à scorer cette signature. Il faut maintenant créer ces mots-clés :

 



[https://gist.github.com/266047](https://gist.github.com/266047)

 C'est assez limpide, non ? On extrait les métadonnées, puis, à partir de celles-ci, on peuple les mots-clés avant de faire de même, mais avec un poids différent, pour le texte de la signature. splitSentence et populateKeywords sont des méthodes franchement triviales, laissées aux bons soins des lecteurs exercés.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)