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
				<#list post.tags?sort as post_tag>
					<#if post_tag==tag>
					<#else>
					<a
						class="linked_tag linked_tag_<@safe_tag post_tag/>" 
						onclick="toggleTag('linked_tag_<@safe_tag post_tag/>');"
						onmouseover="toggleHighlightedTagDisplay('linked_tag_<@safe_tag post_tag/>', true);"
						onmouseout="toggleHighlightedTagDisplay('linked_tag_<@safe_tag post_tag/>', false);"
						href="#">
						<span class="label label-default">${post_tag}</span>
					</a>
					<#if all_linked_tags?seq_contains(post_tag)>
					<#else>
					<#assign all_linked_tags = all_linked_tags + [post_tag]>
					</#if>
					</#if>
				</#list>
			</span>
		</li>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	
	<div>
	<#list all_linked_tags?sort as linked_tag>
	<a class="reference_tag linked_tag linked_tag_<@safe_tag linked_tag/>" 
		id="linked_tag_<@safe_tag linked_tag/>"
		data-safe-tag="${linked_tag}"
		data-selected="false"
		onclick="toggleTag('linked_tag_<@safe_tag linked_tag/>');"
		onmouseover="toggleHighlightedTagDisplay('linked_tag_<@safe_tag linked_tag/>', true);"
		onmouseout="toggleHighlightedTagDisplay('linked_tag_<@safe_tag linked_tag/>', false);"
		href="#">
		<span class="label label-default">${linked_tag}</span>
	</a>
	</#list>
	</div>
	
<#include "footer.ftl">