type: post
status: published
title: Utiliser gmaven pour Groovy 1.8
tags: gmaven, groovy
date: Thu Aug 11 15:47:01 CEST 2011
~~~~~~
# Utiliser gmaven pour Groovy 1.8

Juste histoire d'??tre s??r de s'en souvenir plus tard, voici le p??t?? ?? ??crire dans son pom.xml pour pouvoir utiliser du Groovy 1.8 avec [GMaven](http://docs.codehaus.org/display/GMAVEN/Home) :  




[https://gist.github.com/1139693](https://gist.github.com/1139693)

Sans le exclude, vous aurez l'impression que ??a marche, jusqu'?? ce qu'il vous dise ?? peu pr??s 

java.lang.NoClassDefFoundError: org/apache/tools/ant/BuildException  

at org.codehaus.gmaven.runtime.v1_7.ScriptExecutorFeature$ScriptExecutorImpl.createAntBuilder(ScriptExecutorFeature.java:87)  

at org.codehaus.gmaven.runtime.v1_7.ScriptExecutorFeature$ScriptExecutorImpl.createMagicAttribute(ScriptExecutorFeature.java:105)  

at org.codehaus.gmaven.runtime.support.ScriptExecutorSupport.applyContext(ScriptExecutorSupport.java:108)  

at org.codehaus.gmaven.runtime.support.ScriptExecutorSupport.execute(ScriptExecutorSupport.java:69)  

at org.codehaus.gmaven.plugin.execute.ExecuteMojo.process(ExecuteMojo.java:239)  

at org.codehaus.gmaven.plugin.ComponentMojoSupport.doExecute(ComponentMojoSupport.java:60)  

at org.codehaus.gmaven.plugin.MojoSupport.execute(MojoSupport.java:69)  

at org.apache.maven.plugin.DefaultBuildPluginManager.executeMojo(DefaultBuildPluginManager.java:101)

Ce qui, il faut le reconna??tre, n'est pas vraiment une fa??on sympathique de vous dire qu'il vous manque la bonne version de groovy-all.