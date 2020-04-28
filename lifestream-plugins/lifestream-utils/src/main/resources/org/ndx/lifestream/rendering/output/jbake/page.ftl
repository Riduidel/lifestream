:jbake-type: post
:jbake-status: published
:jbake-title: ${input.title}
<#if input.tags??>
:jbake-tags: <#list input.tags as t>${t}<#if t_has_next>,</#if></#list>
<#else>
:jbake-tags: 
</#if>	
:jbake-date: ${writeDate}
:jbake-depth: ${depth}
:jbake-uri: ${uri}
<#if input.additionalHeaders??>
<#assign headers=input.additionalHeaders>
<#list headers?keys as key>
<#if key??>
<#if headers[key]??>
:jbake-${key}: ${headers[key]}
</#if>
</#if>
</#list>
</#if>

${input.text}