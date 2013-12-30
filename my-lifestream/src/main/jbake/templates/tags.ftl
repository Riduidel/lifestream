<#assign path="../"/>

<#include "header.ftl">

	<#include "menu.ftl">

	<div class="page-header">
		<h1 id="filtering-tags">Tag: <span class="tag" data-tag="${tag?trim}">${tag?trim}</span></h1>
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

		<@link_to_post_in_list post=post path=path excludedTag=tag/>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	</div>

<#include "footer.ftl">