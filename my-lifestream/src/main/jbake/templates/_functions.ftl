<#function fix_uri uri>
	<#assign text = uri>
	<#assign text = text?replace("%5C", "/")>
	<#assign text = text?replace("\\", "/")>
	<#if text?starts_with("/")>
		<#assign text = text?substring(1)>
		<#return text>
	<#else>
		<#return text>
	</#if>
</#function>
