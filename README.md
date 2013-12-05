lifestream
==========

a Maven project generating markdown files for each of my entries on various web services (currently planned ones are wordpress, Goodreads, and maybe StackExchange sites)

Project is to be split into ùany maven artifacts. The following structure is currently envisioned

 * lifestream-plugins (root for all groovy plugins for grabbing data from external sites and converting it into the markdown files to be used by site generator)
   * utils (obvious utility project containing the templating)
   * wordpress
   * goodreads
   * shaarli
   * stackexchange (TODO)
 * my-lifestream (configuration to generate my lifestream

As this project is currently still alpha-level, please don't go wild about the fact that artifacts are not yet released to maven central.
Which immediatly leads me to another thing : to try it, you ahve only one solution

1. Fork project on GitHub
1. Add to your very own settings.xml the settings required to build all projects (or at least all the ones you want). I don't need to document these here, as each missing setting will appear during compilation.
1. Once all is done, go to `my-lifestream` subfolder, then run mvn install with the following maven profiles set
   * `download-goodreads` will download all your Goodreads infos (provided you have one)
   * `download-wordpress` will download content of the wordpress blog you gave in config (only one is supported now)
   * `download-shaarli` will download all your Shaarli links (again, only one Shaarli instance is supported now)
   * `jbake` will generate a fugly site using all those markdown files.

So, if you want to get the whole package once you have installed/compiled the whole, the command line you should write is

    mvn install -Pdownload-goodreads -Pdownload-wordpress -Pdownload-shaarli -Pjbake

And, after a while, a site should appear in `my-lifestream/target/generatedSite`

Feel totally and utterly free to add any issue you noticed.
