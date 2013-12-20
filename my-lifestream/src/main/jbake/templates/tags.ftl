<#assign path="../"/>

<#include "header.ftl">

	<#include "menu.ftl">

	<div class="page-header">
		<h1>Tag: ${tag}</h1>
	</div>

	<div class="tag_posts">
	<!--<ul>-->
		<#list tag_posts as post>
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

		<li class="post-filter-unselected">${post.date?string("dd")} - <@link_to_post post=post path=path/><br/>
			<#if (post.tags)??>
			<@tag_list tagList=post.tags depth="" excludedTag=tag/>
			</#if>
		</li>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	</div>

<#include "footer.ftl">