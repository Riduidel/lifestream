<#include "header.ftl">

	<#include "menu.ftl">

	<div class="container-fluid">
	<@display_post content=content depth=content.depth>
		<#if (content.bigImage)??>
		<img src="${content.bigImage}"/>
		</#if>
	</@display_post>
	</div>
	<hr>

<#include "footer.ftl">