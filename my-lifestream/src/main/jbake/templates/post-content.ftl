<div class="row-fluid">
<div class="page-header col-md-10-offset-2">
<#if (post.title)??>
	<a href="${content.rootpath}${post.uri}"><h1><#escape x as x?xml>${post.title}</#escape></h1></a>
<#else></#if>
</div>
</div>

<div class="row-fluid ${post.style}">
<div class="col-md-2">
<p><em>${post.date?string("dd MMMM yyyy")}</em></p>

<p class="tags">
<#list post.tags?sort as tag>
<a class="tag" href="${content.rootpath}${config.tag_path}/${tag}.html">${tag}</a>
</#list>
</p>

<a class="source" href="${post.source}">source</a>
</div>

<div class="content col-md-10">${post.body}</div>

</div>
