<?xml version="1.0"?>
<rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
  <channel>
    <title>Lifestream</title>
    <link>${config.site_host}</link>
    <atom:link href="${config.site_host}/feed.xml" rel="self" type="application/rss+xml" />
    <description>Lifestream de Nicolas Delsaux</description>
    <language>fr-FR</language>
    <pubDate>${published_date?string("EEE, d MMM yyyy HH:mm:ss Z")}</pubDate>
    <lastBuildDate>${published_date?string("EEE, d MMM yyyy HH:mm:ss Z")}</lastBuildDate>

    <#list published_posts as post>
    <#if post_index<100>
    <item>
      <#if post.title??> <title>${post.title}</title></#if>
      <#if post.uri??><link>${config.site_host}${post.uri}</link></#if>
      <#if post.date??><pubDate>${post.date?string("EEE, d MMM yyyy HH:mm:ss Z")}</pubDate></#if>
      <#if post.uri??><guid isPermaLink="false">${post.uri}</guid></#if>
      <#if post.body??>
      	<description>
      		<#escape x as x?xhtml>
			${post.body}
			</#escape>
		</description>
		</#if>
    </item>
    </#if>
    </#list>

  </channel>
</rss>
