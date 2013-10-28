type: post
status: published
title: old Tomcat tips
tags: code, informatique, java
date: Tue Jan 25 17:59:00 CET 2005
~~~~~~
# old Tomcat tips

 J'ai toujours été énervé par la façon dont Tomcat se lance depuis Eclipse : la fenêtre de lancement apparaît en effet dans la console d'Eclipse, mais Tomcat la quitte très vite pour une console Windows mal fichue. Maintenant, grâce à [Choongyong](http://jroller.com/page/dunpanic/20040708#running_tomcat_in_the_same), je sais que je dois simplement remplacer dans le catalina.bat les lignes

if not "%OS%" == "Windows_NT" goto noTitle set _EXECJAVA=start "Tomcat" %_RUNJAVA% goto gotTitle :noTitle set _EXECJAVA=start %_RUNJAVA% :gotTitle

par

if not "%OS%" == "Windows_NT" goto noTitle set _EXECJAVA="Tomcat" %_RUNJAVA% goto gotTitle :noTitle set _EXECJAVA=%_RUNJAVA% :gotTitle

Notez que, sur le même blog, [Choongyong](http://jroller.com/page/dunpanic/20040715#calling_optional_ant_tasks_from) donne aussi un truc intéressant pour le redéploiement à chaud dans Tomcat depuis Eclipse.via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)