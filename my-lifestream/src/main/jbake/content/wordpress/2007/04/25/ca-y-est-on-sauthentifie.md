type: post
status: published
title: Ca y est, on s'authentifie
tags: code, informatique, lebliki, ruby
date: Wed Apr 25 17:25:00 CEST 2007
~~~~~~
# Ca y est, on s'authentifie

J'ai rajouté un peu de polish, car la version initiale était franchement rustique. Et maintenant, je passe directement à la recherche. Je veux pouvoir faire une recherche facile dans tous les posts grâce à acts_as_ferret. Bon, évidement, il faut installer ferret, mais j'ai l'impression que c'est un peu le must de la recherche en Ruby. Donc

gem install ferret

script/plugin install projects.jkraemer.net/acts_as_ferret/tags/stable/acts_as_ferret

Et on code !via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)