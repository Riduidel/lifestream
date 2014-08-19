type=post
status=published
title=${input.title}
<#if input.tags??>
tags= <#list input.tags as t>${t}<#if t_has_next>,</#if></#list>
<#else>
tags=
</#if>	
date=${writeDate}
depth=${depth}
uri=${uri}
style=${input.style}
<#if input.source??>
source=${input.source}
</#if>
~~~~~~
${input.text}