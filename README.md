lifestream
==========

a Maven project generating markdown files for each of my entries on various web services (currently planned ones are wordpress, Goodreads, and maybe StackExchange sites)

Project is to be split into ùany maven artifacts. The following structure is currently envisioned

 * lifestream-plugins (root for all groovy plugins for grabbing data from external sites and converting it into the markdown files to be used by site generator)
   * utils (obvious utility project containing the templating)
   * wordpress
   * goodreads
   * stackexchange
 * my-lifestream (configuration to generate my lifestream