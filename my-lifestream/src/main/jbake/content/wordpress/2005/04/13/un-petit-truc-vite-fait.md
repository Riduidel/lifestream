type: post
status: published
title: Un petit truc vite fait
tags: code, informatique, ruby
date: Wed Apr 13 14:14:44 CEST 2005
~~~~~~
# Un petit truc vite fait

En d??veloppant {Boo.km.arcs}, je me suis retrouv?? un nombre stup??fiant de fois face ?? une doc incorrecte, car un peu plus ancienne que le code. Et bien s??r, quelle est la premi??re chose qui change ? Les m??thodes publiques. Donc maintenant, quand je tombe sur un objet d'une classe que je n'ai pas cr????, j'ai souvent tendance ?? v??rifier de quelles m??thodes il dispose. Ca se fait (comme tout le reste en Ruby) tr??s simplement :[code]puts myObject.methods.sort.inspect[/code]via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)