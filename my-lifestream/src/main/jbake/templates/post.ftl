<#assign title = config.site_title + " >> posts >> "+content.title>
<#assign post=content >
<!-- ${content.bigimage} -->
<#if content.bigimage??>
	<#assign image = content.bigimage>
</#if>

<#include "header.ftl">
	
	<#include "menu.ftl">

	<#include "post-content.ftl" >
	
<#include "footer.ftl">