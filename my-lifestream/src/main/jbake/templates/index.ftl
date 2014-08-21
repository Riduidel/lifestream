<#include "header.ftl">

	<#include "menu.ftl">

	<div class="page-header">
		<h1>Blog</h1>
	</div>
	<div class="container-fluid">
	<#list posts as post>
  		<#if (post.status == "published")>
  			<div class="no-relatives">
			<@display_post content=post/>
			</div>
  		</#if>
  	</#list>
	</div>
	<hr />

	<p>Older posts are available in the <a href="/${config.archive_file}">archive</a>.</p>

<#include "footer.ftl">