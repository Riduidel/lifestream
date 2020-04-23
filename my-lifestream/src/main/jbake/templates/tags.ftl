<#include "header.ftl">

	<#include "menu.ftl">
	
	<#macro safe_tag tag>${tag?trim?replace("-", "_")?replace("@", "_at_")}</#macro>
	
	<div class="page-header">
		<h1>Tag: ${tag}</h1>
	</div>
	
	<#assign all_linked_tags =  []>
	<ul>
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
		
		<li class="linked_post"
			data-tags="[${post.tags?map(t -> "'"+t?trim+"'")?join(", ")}]"
			>${post.date?string("dd")} - <a href="${content.rootpath}${post.uri}">${post.title}</a>
			<span class="post_tags">
				<#list post.tags?sort as tag>
					<a
						class="linked_tag linked_tag_<@safe_tag tag/>" 
						onclick="toggleTag('linked_tag_<@safe_tag tag/>');"
						href="#">
						<span class="label label-default">${tag}</span>
					</a>
					<#if all_linked_tags?seq_contains(tag)>
					<#else>
					<#assign all_linked_tags = all_linked_tags + [tag]>
					</#if>
				</#list>
			</span>
		</li>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	
	<div>
	<#list all_linked_tags as tag>
	<a class="reference_tag linked_tag linked_tag_<@safe_tag tag/>" 
		id="linked_tag_<@safe_tag tag/>"
		data-safe-tag="${tag}"
		data-selected="false"
		onclick="toggleTag('linked_tag_<@safe_tag tag/>');"
		href="#">
		<span class="label label-default">${tag}</span>
	</a>
	</#list>
	</div>
	
<#include "footer.ftl">