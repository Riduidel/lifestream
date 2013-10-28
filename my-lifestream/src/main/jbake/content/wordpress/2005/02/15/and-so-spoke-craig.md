type: post
status: published
title: And so spoke Craig
tags: code, informatique, java
date: Tue Feb 15 19:47:00 CET 2005
~~~~~~
# And so spoke Craig

  En continuant mes explorations des problèmes de multithreading dans Struts, j'ai trouvé [cette page de wiki](http://wiki.apache.org/struts/StrutsWhyOnlyOneInstanceOfActionClass "StrutsWhyOnlyOneInstanceOfActionClass") discutant un point proche du multithreading (et une solution possible de ce problème) : remplacer le modèle à instance unique par un modèle à une instance par requête. Et ainsi parle Craig :

> Changing this now would be a major backwards incompatibility issue, so it will not be done in any Struts 1.x release. We can look at other approaches in a 2.x time frame.

Il y a quelque chose dans cette phrase que je ne comprend pas clairement. Supposons que dans le RequestProcessor on change le code de

<pre>// Return any existing Action instance of this class              instance = (Action) actions.get(className);              if (instance != null) {                  if (log.isTraceEnabled()) {                      log.trace("  Returning existing Action instance");                  }                  return (instance);              }              // Create and return a new Action instance              if (log.isTraceEnabled()) {                  log.trace("  Creating new Action instance");              }              try {                  instance = (Action) RequestUtils.applicationInstance(className);</pre>

pour le plus simple

<pre>try {                  instance = (Action) RequestUtils.applicationInstance(className);                  // TODO Maybe we should propagate this exception instead of returning                  // null.              } catch (Exception e) {</pre>

Quelle serait la perte ? Comme dit sur le Wiki "quel est le coût d'une création d'objet ?", déclaration à laquelle je pourrais ajouter "quand on ne sait même pas à quel point la logique peut être lourde ?".

 via [Nicolas Delsaux's posterous import script](http://riduidel.posterous.com/quest-ce-que-cest-que-ce-flux-qui-clignote)