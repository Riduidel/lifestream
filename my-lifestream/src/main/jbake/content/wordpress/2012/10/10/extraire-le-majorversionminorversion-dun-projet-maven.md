type: post
status: published
title: Extraire le majorVersion/minorVersion d'un projet maven
tags: groovy, maven, version
date: Wed Oct 10 17:32:21 CEST 2012
~~~~~~
# Extraire le majorVersion/minorVersion d'un projet maven

Pour un projet sur lequel je bossais, j'avais besoin de récupérer les composants du numéro de version de mon projet maven sous forme numérique (et pas chaîne de caractère). J'ai bien essayé d'utiliser le properties-maven-plugin, mais ça marchait pas (j'ai même demandé à [StackOverflow](http://stackoverflow.com/q/12799669/15619)). Mais aucune solution ne correspondait à mon problème. J'ai donc dégainé le [gmaven-plugin](http://docs.codehaus.org/display/GMAVEN/Home) et ... voilà !

[sourcecode language="xml"]
   <plugin>
    <groupId>org.codehaus.gmaven</groupId>
    <artifactId>gmaven-plugin</artifactId>
    <executions>
     <execution>
      <id>Compute portbase and admin port for Jenkins domain</id>
      <phase>validate</phase>
      <goals>
       <goal>execute</goal>
      </goals>
      <configuration>
       <classpath>
        <element>
         <groupId>org.apache.maven</groupId>
         <artifactId>maven-artifact</artifactId>
         <version>3.0.3</version>
        </element>
       </classpath>
       <providerSelection>${gmaven.provider.version}</providerSelection>
       <!-- small script used to update some properties according to version -->
       <source>
        <![CDATA[
import org.apache.maven.artifact.versioning.*

/* et voila, c'est tout ! */
def parsed = new DefaultArtifactVersion(project.version)

          ]]>
       </source>
      </configuration>
     </execution>
    </executions>
   </plugin>
[/sourcecode]

Oui, bon, j'aurais préféré mettre ça dans un gist, mais apparement Wordpress.com ne fournit pas ça.