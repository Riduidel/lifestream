<#assign title = config.site_title + " >> posts >> "+content.title>
<#include "header.ftl">
	
	<#include "menu.ftl">

	<#assign post=content >
	<#include "post-content.ftl" >
	
<#include "footer.ftl">