type: post
status: published
title: La prochaine frontière ? l'ALM
tags: agile, github, processus
date: Thu Oct 24 20:58:43 CEST 2013
~~~~~~
# La prochaine frontière ? l'ALM

Dans mon message sur le [dépôt de bilan](http://riduidel.wordpress.com/2013/10/23/le-depot-de-bilan-ca-craint/), je parlais de


> Un truc tout con : pour faire de l’agile, utiliser cinq outils, c’est juste idiot. je vais préciser un peu : on utilise Scrumwise pour le backlog, Google Drive pour les specs, Mantis pour les bugs, Google Spreadsheet pour donner d ela visibilité aux bugfixes …
Je dois dire que ça m'a pas mal travaillé récement. Ca m'a d'autant plus travaillé qu'en raison des contraintes externes, on nous demande ces temps-ci beaucoup de reporting concernant les corrections de bugs (un secteur du développement sur lequel il faudrait aussi que j'écrive quelque chose). Un reporting qui, naturellement, prend la forme d'un tableau Google Drive (parce que quand ton seul outil est un marteau, tous les problèmes finissent par ressembler à des clous).

J'en parlais parce qu'une nouvelle sur LinuxFr m'a récement intrigué  :  [La forge libre Tuleap gagne le prix américain InfoWorld Bossie Award 2013](https://linuxfr.org/news/la-forge-libre-tuleap-gagne-le-prix-americain-infoworld-bossie-award-2013). Ca m'avait intrigué parce que, avec Sourceforge, Origo, gitHub, on peut dire que j'ai un peu vadrouillé du côté des forges, et que si certaines fonctionnalités sont sympathiques, ça ne va pas souvent bien loin ...

Enfin, pas loin, pas loin, je dois bien reconnaître que GitHub Issues est une sacrément bon sang de bonne idée. Et c'est sans doute à cause de cette bonne idée que je trouve Tuleap aussi bien : parce qu'il pousse cette diée de tracabilité du code bien plus loin. C'est expliqué dans leur doc : on peut lier une user story à un bug report, lui-même lié à un commit (et éventuellement fermé depuis le commit) et à un build du Jenkins embarqué. Et là, comme dans GitHub, il semble possible de fermer (ou tout au moins de référencer) un item dans le commit pour que tous soient liés.

Et à mon avis, c'est ça la prochaine frontière du développement.

Je m'explique.

Vous développiez comment il y a dix ans ?

Moi, je faisais du java dans mon Textpad avec un script Ant qui tentait de faire (mal) des tests. (Bon déja c'était bien j'utilisais Ant pour compiler).

Et puis JUnit est apparu pour mieux qualifier la qualité de mon code.

Et puis Hudson/Jenkins est apparu pour mieux s'assurer de la qualité de tout le code qui pouvait dépendre du mien ... et pour faire tout un tas d'autre trucs.

Mais, d'un point de vue traçabilité, il manquait quand même des choses : comment savoir d'où venait - fonctionnellement - telle ou telle ligne de code ?

Rien.

Aujourd'hui, avec Git, on peut un peu plus facilement retracer une ligne de code jusqu'à un commit et - dans GitHub uniquement - à un issue qui a pu être écrit à ce sujet. Mais il manque l'aspect fonctionnel : quel est le besoin utilisateur qui a mené à ce code ?

Et c'est ça à mona vis que peut amener Tuleap : aller de la spec au code en un coup, et d'une façon qui documente clairement le process.

Bon, il y a évidement d'autres avantages, mais celui-là me paraît structurant pour une entreprise.

Oh, j'allais oublier la cerise sur le gateau : il y a un connecteur Mylyn pour que le développeur aie connaissance d'autant d'informations que possible dans son Eclipse !

Autrement dit, j'ai hâte d'essayer cet outil.

 

 