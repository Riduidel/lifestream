<#assign path=""/>

<#include "header.ftl">

	<#include "menu.ftl">

	<div class="page-header">
		<h1>Blog Archive</h1>
		<h2 id="filtering-tags"></h2>
	</div>

	<div class=""archive_posts">
	<!--<ul>-->
		<#list published_posts as post>
		<#if (last_month)??>
			<#if post.date?string("MMMM yyyy") != last_month>
				</ul>
				<h4>${post.date?string("MMMM yyyy")}</h4>
				<ul>
			</#if>
		<#else>
			<h4>${post.date?string("MMMM yyyy")}</h4>
			<ul>
		</#if>

		<@link_to_post_in_list post=post path=path/>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	</div>

<#include "footer.ftl">