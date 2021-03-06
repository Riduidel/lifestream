<#include "_tag_helper.ftl">
<#macro nothing>
<span class="col-md-2">
</span>
</#macro>
<#macro title_renderer post content>
<div class="col-md-10">
<#if (post.title)??>
	<a href="${content.rootpath}${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
<#else></#if>
</div>
</#macro>
<#macro header_renderer post content config>
<div class="col-md-2">
	<p><em>${post.date?string("dd MMMM yyyy")}</em></p>
	
	<p class="tags">
	<#if post.tags??>
	<#list post.tags?sort as tag>
	<a class="tag <@tag_classes tag/>" href="${content.rootpath}${config.tag_path}/${tag}.html">
		<span class="label label-default">${tag}</span>
	</a>
	</#list>
	</p>
	</#if>
	
	<#if post.source??>
	<a class="source" href="${post.source}">source</a>
	</#if>
</div>
</#macro>

<#macro content_renderer post>
<div class="content col-md-10">
<#if image??>
<#if image?length gt 0>
<img class="special-image pull-left img-responsive" src="${image}"/>
</#if>
</#if>
<div class="content-text">
${post.body}
</div>
</div>
</#macro>

<div class="row ${post.style} clearfix">
<#if post.style=="shaarli">
<@header_renderer post content config/>
<@title_renderer post content/>
<@content_renderer post/>
<#else>
<@nothing/>
<@title_renderer post content/>
<@header_renderer post content config/>
<@content_renderer post/>
</#if>
</div>
