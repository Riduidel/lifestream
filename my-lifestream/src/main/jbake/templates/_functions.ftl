<#function fix_uri uri>
	<#assign text = uri>
	<#assign text = text?replace("%5C", "/")>
	<#assign text = text?replace("%20", "")>
	<#assign text = text?replace("\\", "/")>
	<#if text?starts_with("/")>
		<#assign text = text?substring(1)>
		<#return text>
	<#else>
		<#return text>
	</#if>
</#function>

<#macro link_to_post post path=".">
	<a href="${path}${fix_uri(post.uri)}">${post.title}</a>
</#macro>


<#macro display_post content depth="">
  <div class="row-fluid">
    <div class="span10 offset2">
  		<a href="${fix_uri(content.uri)}"><h1>${content.title}</h1></a>
	</div>
  </div>
  <div class="row-fluid <#if (content.style)??>${content.style}</#if>">
    <div class="span2">
		<div class="date"><em>${content.date?string("dd MMMM yyyy")}</em></div>
		<div class="tags">
			<ul>
			<#list content.tags as tag>
				<li><a href="${fix_uri(depth)}tags/${tag?trim}.html">${tag?trim}</a></li>
			</#list>
			</ul>
		</div>
		<#if (content.source)??><a href="${content.source}" class="source">source</a></#if>
    </div>
    <div class="span10">
		${content.body}
	</div>
  </div>
</#macro>
