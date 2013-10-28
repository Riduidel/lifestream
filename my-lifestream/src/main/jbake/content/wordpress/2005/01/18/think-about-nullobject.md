type: post
status: published
title: Think about NullObject
tags: code, informatique, java
date: Tue Jan 18 17:43:00 CET 2005
~~~~~~
# Think about NullObject

  Quand on parle de [exception handling](http://jroller.com/comments/pmckinstry/Weblog/exception_handling1), les problèmes de NullPointerException ne sont jamais loin. Dans ce cas, le pattern NullObject est vraiment utile (bien que je ne m'en sois jamais servi). Avec du code bien organisé, le [Java Null Proxy](http://c2.com/cgi/wiki?JavaNullProxy) est un très bon choix. Autrement, associer à chaque classe une sous-classe singleton null-object peut aider, et pas seulement en supprimant tous les tests de nullité.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)