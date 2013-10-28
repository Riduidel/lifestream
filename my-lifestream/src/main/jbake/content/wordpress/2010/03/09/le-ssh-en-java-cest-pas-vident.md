type: post
status: published
title: Le ssh en Java, c'est pas ??vident 
tags: java, maven, ssh
date: Tue Mar 09 17:20:46 CET 2010
~~~~~~
# Le ssh en Java, c'est pas ??vident 

Supposons que vous fassiez du Java (??a arrive ?? bien des gens).Supposons m??me que vous souhaitiez vous connecter ?? un serveur en [ssh](http://fr.wikipedia.org/wiki/Secure_Shell) (??a aussi, ??a peut arriver).Supposons enfin que, pour des raisons qui n'appartiennent qu'?? vous, vous deviez r??ellement tout faire dans du code Java.Et bien vous allez gal??rer.Parce que des librairies SSH gratuites en Java, il n'y en a pas des tonnes.En fait, j'en ai vu principalement deux :
* [Jsch](http://www.jcraft.com/jsch/)
* [j2ssh](http://sourceforge.net/projects/sshtools/)

Dans les deux cas, il faut d'abord fouiller le werb ?? la recherche de sources de documentation, parce que les sites sont totallement d??munis. Il faut aussi trouver des artefacts maven, ce qui est un peu plus [facile](http://www.mvnbrowser.com/artifacts-search.html?page=1&search=j2ssh) (mvnbrowser, chaque jour je b??nis ton nom). Mais il ne faut pas oublier de prendre une version de commons-logging, sans laquelle j2ssh ne marche pas (et on f??licite le mauvais d??veloppeur qui a cr???? un demi-artefact maven).Bon, vous vous en doutez, j'ai choisi j2ssh, essentiellement ?? cause de [ce blog](http://www.spindriftpages.net/blog/dave/2007/11/27/sshtools-j2ssh-java-sshsftp-library/comment-page-2/) qui compare les ??critures, et montre clairement ?? quel point j2ssh semble plus lisible.

Bien, maintenant, ?? titre d'exercice, supposons que vous souhaitez vous connecter en SSH, donc ??via [une simple authentification par mot de passe](http://serverfault.com/questions/120365/enable-passwordauthentication-on-opensuse-10) (je sais, l'??change cl?? publique/cl?? priv??e est plus s??curis??, mais dans mon cas il s'agit d'une machine virtuelle h??berg??e sur mon serveur de d??veloppement et non visible de l'ext??rieur).

Et bien pour ??a, voici les diff??rentes ??tapes n??cessaires.

D'abord, il faut cr??er une connexion, comme ??a :



[https://gist.github.com/326749](https://gist.github.com/326749)

Une fois la connexion obtenue, on va pouvoir lui passer des commandes, ce qui implique cette petite m??thode



[https://gist.github.com/326751](https://gist.github.com/326751)

Et avec cette petite m??thode, vous pouvez "facilement" demander ?? votre serveur de vous ex??cuter du code



[https://gist.github.com/326754](https://gist.github.com/326754)

Et ??a, ??a marchera.Il m'a quand m??me fallu quelques jours pour arriver ?? ce r??sultat, certes ??l??gant, mais suant ?? configurer.