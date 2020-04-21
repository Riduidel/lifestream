<#include "header.ftl">
	
	<#include "menu.ftl">
	
	<#assign currentPageInt=currentPageNumber?number>
	<#assign posts_per_page=config.index_posts_per_page?number>
	<#assign pagination_size=5>
	<#assign pagination_min=[1, currentPageInt-pagination_size]?max>
	<#assign pagination_max=[(posts?size/posts_per_page)?ceiling, currentPageInt+pagination_size]?min>
	<#assign posts_pages=pagination_min..pagination_max>
	<#assign post_first_index=(currentPageInt-1)*posts_per_page>
	<#assign post_last_index=[currentPageInt*posts_per_page-1, posts?size-1]?min>
	<#assign posts_indices=post_first_index..post_last_index>
	<#if currentPageNumber==1>
    <#assign path="">
	<#else>
    <#assign path="../">
    </#if>
	
	<!--
	current page = ${currentPageInt}
	posts per page = ${posts_per_page}
	pages indices (firs) = ${posts_pages?first}
	posts for all pages = ${(posts?size/posts_per_page)?ceiling}
	pages indices (last) = ${posts_pages?last}
	posts indices (first) = ${posts_indices?first}
	posts indices (last) = ${posts_indices?last}
	-->

	<div class="page-header">
		<h1>Blog</h1>
	</div>
	<#list posts_indices as post_index>
	<!--including post ${post_index}-->
		<#assign post=posts[post_index]>
  		<#if (post.status == "published")>
			<#include "post-content.ftl" >
  		</#if>
  	</#list>
  	
  	<#if config.index_paginate>
		<nav aria-label="Page navigation">
		  <ul class="pagination">
		  	<#macro link_to path page_index="#" aria_label="#">
		    	<#if page_index?number == 1>
		    	<a href="${path}index.html" aria-label="${aria_label}">
		    		<#nested>
		    	</a>
		    	<#else>
		    	<a href="${path}${page_index}/index.html">
		    		<#nested>
		    	</a>
		    	</#if>
		  	</#macro>
		  
		    <#if currentPageInt gt 1>
		    <li>
			    <@link_to path "${currentPageInt-1}" "Previous">
			        <span aria-hidden="true">&laquo;</span>
			    </@link_to>
		    </li>
		    <#else>
		    <li class="disabled">
		    	<a href="#" aria-label="Previous">
			        <span aria-hidden="true">&laquo;</span>
		    	</a>
		    </li>
		    </#if>
		    </li>
		    <#list posts_pages as page_index>
		    	<#if page_index==currentPageNumber><li class="disabled"><#else><li></#if>
			    <@link_to path page_index page_index>${page_index}</@link_to>
		    	</li>
		    </#list>
		    <#if currentPageInt lt numberOfPages-1>
		    <li>
			    <@link_to path "${currentPageInt+1}" "Next">
			        <span aria-hidden="true">&raquo;</span>
			    </@link_to>
		    </li>
		    <#else>
		    <li class="disabled">
		    	<a href="#" aria-label="Next">
			        <span aria-hidden="true">&raquo;</span>
		    	</a>
		    </li>
		    </#if>
		  </ul>
		</nav>
  	<#else>
	<p>Older posts are available in the <a href="${content.rootpath}${config.archive_file}">archive</a>.</p>
	</#if>

  	  	

<#include "footer.ftl">