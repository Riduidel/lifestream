type: post
status: published
title: Int??grer du Spring remoting HTTP dans une application web
tags: code, informatique, java
date: Tue Oct 24 12:58:00 CEST 2006
~~~~~~
# Int??grer du Spring remoting HTTP dans une application web

Bon, c'est plus une astuce qu'autre chose, mais comme la doc concernant le [Spring remoting en HTTP](http://www.springframework.org/docs/reference/remoting.html#d0e23436 "Chapter&nbsp;17.&nbsp;Remoting and web services using Spring") est loin d'être clair, voici quelques éclaircissements.

D'abord, ces éclaircissements concernent Spring 1.2. j'imagine qu'avec l'implémentation de HttpRequestHandlker, ça doit marcher mieux.

Au cas où vous ne l'auriez pas remarqué, un [HttpInvokerServiceExporter](http://static.springframework.org/spring/docs/1.2.8/api/org/springframework/remoting/httpinvoker/HttpInvokerServiceExporter.html "HttpInvokerServiceExporter (Spring Framework)") est un objet implémentant l'interface [Controller](http://static.springframework.org/spring/docs/1.2.8/api/org/springframework/web/servlet/mvc/Controller.html "Controller (Spring Framework)"). A la lecture de sa javadoc, on pourrait penser qu'il s'agit d'une servlet modifiée, mais ça n'est pas du tout le cas. le Controller est l'équivalent, d'après ce que je comprend, de l'Action Struts. pour être utilisable, il doit être déclaré dans le contexte Spring d'une [DispatcherServlet](http://static.springframework.org/spring/docs/1.2.8/api/org/springframework/web/servlet/DispatcherServlet.html "DispatcherServlet (Spring Framework)"). Mais pour ça, il faut évidement avoir rajouté une DispatcherServlet dans le web.xml de cette manière :



[https://gist.github.com/266110](https://gist.github.com/266110)

Une fois cette servlet créée, il faut, dans le contexte de cette servlet, ajouter le service de remoting comme ça :



[https://gist.github.com/266111](https://gist.github.com/266111)

Et là, ça marche,;; on peut se créer un client en Java qui accèdera à cette resource distante.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)