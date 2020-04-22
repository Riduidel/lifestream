<#include "header.ftl">

	<#include "menu.ftl">

    <div id="main">
        <header>
            <h1>Tags</h1>
        </header>
        <div class="tags">
        <#list tags?sort_by("name") as tag>
        	<#if tag.name?trim?length gt 0>
        	<span class="tag">
        		<a href="${content.rootpath}${tag.uri}">${tag.name}</a>
        		<span class="badge">${tag.tagged_posts?size}</span>
        	</span>
			</#if>
		</#list>
		</div>
    </div>

<#include "footer.ftl"> 