<div style="${post.style}">

<#if path??>
<#else>
<#assign path="">
</#if>
<div class="page-header">
<#if (post.title)??>
	<a href="${path}${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
<#else></#if>

<p><em>${post.date?string("dd MMMM yyyy")}</em></p>

<p class="tags">
<#list post.tags?sort as tag>
<#list tags?filter(t -> t.name==tag) as matching>
<a class="tag" href="${path}${content.rootpath}${matching.uri}">${matching.name}</a></#list>
</#list>
</p>
</div>

<div class="content">${post.body}</div>

<hr />

</div>
