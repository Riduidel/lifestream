<#macro safe_tag tag>${tag?trim?replace("-", "_")?replace("@", "_at_")}</#macro>

<#macro show_linked_tag linked_tag reference=false>
<span
	class="<#if reference>reference_tag </#if>linked_tag linked_tag_<@safe_tag linked_tag/>"
	<#if reference>
	id="linked_tag_<@safe_tag linked_tag/>"
	data-selected="false"
	</#if> 
	data-safe-tag="${linked_tag}"
	onclick="toggleTag('linked_tag_<@safe_tag linked_tag/>');"
	onmouseover="toggleHighlightedTagDisplay('linked_tag_<@safe_tag linked_tag/>', true);"
	onmouseout="toggleHighlightedTagDisplay('linked_tag_<@safe_tag linked_tag/>', false);"
	>
	<span class="label label-default">${linked_tag}</span>
	<a href="${linked_tag}.html" class="go_to_tag">🏠</a>
</span>
</#macro>

<#assign title=config.site_title + " >> tags >> " + tag>
<#include "header.ftl">

	<#include "menu.ftl">
	
	<div class="page-header">
		<h1><a id="autolink" href="#">🔗</a> Tag: ${tag}</h1>
	</div>
	
	<#assign all_linked_tags =  []>
	<ul id="linked_posts">
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
					<@show_linked_tag linked_tag=post_tag/>
					</#if>
				</#list>
			</span>
		</li>
		<#assign last_month = post.date?string("MMMM yyyy")>
		</#list>
	</ul>
	
	<div id="linked_tags">
	<#list all_linked_tags?sort as linked_tag>
					<@show_linked_tag linked_tag=linked_tag reference=true/>
	</#list>
	</div>
	
<#include "footer.ftl">