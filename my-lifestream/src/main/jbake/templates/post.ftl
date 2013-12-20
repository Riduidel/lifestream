<#include "header.ftl">

	<#include "menu.ftl">

	<div class="container-fluid post">
	<@display_post content=content depth=content.depth>
		<#if (content.bigImage)??>
		<div class="image">
			<img src="${content.bigImage}"/>
		</div>
		</#if>
	</@display_post>
	</div>
	<hr>

<#include "footer.ftl">