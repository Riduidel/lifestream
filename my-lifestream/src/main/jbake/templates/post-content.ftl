<div style="${post.style}">

<div class="page-header">
<#if (post.title)??>
	<a href="${content.rootpath}${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
<#else></#if>

<p><em>${post.date?string("dd MMMM yyyy")}</em></p>

<p class="tags">
<#list post.tags?sort as tag>
<a class="tag" href="${content.rootpath}${config.tag_path}/${tag}.html">${tag}</a>
</#list>
</p>
</div>

<div class="content">${post.body}</div>

<hr />

</div>
