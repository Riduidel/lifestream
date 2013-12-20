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

<#macro link_to_post post path="">
	<a href="${path}${fix_uri(post.uri)}">${post.title}</a>
</#macro>

<#macro tag_list tagList depth="" excludedTag="">
	<div class="tags">
		<ul>
		<#list tagList as tag>
			<#if (tag!=excludedTag)>
			<li class="tag">
				<a href="${fix_uri(depth)}${tag?trim}.html">${tag?trim}</a>
				<span class="add-filter">
					<span class="icon-plus-sign"></span>
				</span>
				<span class="remove-filter">
					<span class="icon-minus-sign"></span>
				</span>
			</li>
			</#if>
		</#list>
		</ul>
	</div>
</#macro>

<#macro display_post content depth="">
  <div class="row-fluid">
    <div class="span10 offset2">
  		<a href="${fix_uri(content.uri)}"><h1>${content.title}</h1></a>
	</div>
  </div>
  <div class="row-fluid <#if (content.style)??>${content.style}</#if>">
    <div class="span2">
    	<#nested/>
		<div class="date"><em>${content.date?string("dd MMMM yyyy")}</em></div>
		<#if (content.tags)??>
			<@tag_list tagList=content.tags depth=depth+"tags/"/>
		</#if>
		<#if (content.source)??><a href="${content.source}" class="source">source</a></#if>
    </div>
    <div class="span10">
		${content.body}
	</div>
  </div>
</#macro>
