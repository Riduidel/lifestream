type: post
status: published
title: Struts seems mediocre
tags: code, informatique, java
date: Tue Feb 15 19:28:00 CET 2005
~~~~~~
# Struts seems mediocre

  Je me pose actuellement des question sur la gestion multithread de Struts (qui en fait n'existe pas). Donc, comme tout développeur, j'ai googlé "multithreading in Struts" et d'autres requêtes. Malheureusement, les résultats commencent presque avecdes pages comme [Struts semble médiocre](http://www.javapractices.com/Topic193.cjp). je trouve cette critique de Struts très intéressante, et pas seulement parce que je n'aime personnellement pas ce non-framework. En effet, les arguments correspondent fortement aux miens :**No policies for translating request parameters into common java objects.**

Est-ce pourquoi mes beans métier contiennent des setters utilisant des String ?**ActionForms are a massive parallel structure to the "real" model.**

Encore une fois, puisque je n'utilise personnellement rien d'autre que les DynaActionForm, je peux considérer que j'ai trouvé un contournement utile, qui déplace toute la validation dans le Javascript ou dans les classes métier.**Standard HTML forms are not used.**

Dans mon cas, le tag Struts form n'est pas utilisé. Il n'y a que des formulaires HTML standards. Bien sûr, on doit coder notre validation Javascript spécifique. Mais c'est nettement plus simple, et plus facile à "introspecter" en utilisant du Javascript spécifique. En fait la seule partie délicate a été le Javascript modifiant la taille d'une propriété mappée.**Actions must be thread-safe.**

Je lutte encore là-dessus, et j'apprécierais toute aide ;-)**Struts is an unimpressive implementation of the Command pattern.**

Et personne ne parle de la DispatchAction et de ses mystères.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)