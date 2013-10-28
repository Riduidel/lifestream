type: post
status: published
title: logger declaration
tags: code, informatique, java
date: Thu Feb 10 19:07:00 CET 2005
~~~~~~
# logger declaration

  Parfois, les gens préfèrent écrire

<pre>private Log log = LogFactory.getLog(this.getClass());</pre>

ou

<pre>package com.company.app.package;    public class MyClass {     private static Log log = LogFactory.getLog("com.company.app.package.MyClass"); }</pre>

plutôt que

<pre>public class MyClass {     private static Log log = LogFactory.getLog(MyClass.class); }</pre>

Bizarre, non ?via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)