<#include "header.ftl">

	<#include "menu.ftl">

    <div id="main">
    	<ul class="posts">
                <header>
                    <h1>Tags</h1>
                </header>
                <#list tags?sort_by("name") as tag>
                	<h2><a href="${content.rootpath}${tag.uri}">${tag.name}</a> [${tag.tagged_posts?size}]</h2>

				</#list>
            </ul>
    </div>

<#include "footer.ftl"> 