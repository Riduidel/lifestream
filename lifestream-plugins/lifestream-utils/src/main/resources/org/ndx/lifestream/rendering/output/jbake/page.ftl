<#compress>
type=post
status=published
title=${input.title}
<#if input.tags??>
tags=<#list input.tags as t>${t}<#if t_has_next>,</#if></#list>
<#else>
tags=
</#if>	
date=${writeDate}
depth=${depth}
uri=${uri}
<#if input.additionalHeaders??>
<#assign headers=input.additionalHeaders>
<#list headers?keys as key>
<#if key??>
<#if headers[key]??>
${key}=${headers[key]}
</#if>
</#if>
</#list>
</#if>
</#compress>

~~~~~~
${input.text}