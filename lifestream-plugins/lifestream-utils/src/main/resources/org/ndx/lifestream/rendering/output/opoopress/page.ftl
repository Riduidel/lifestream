---
layout: post
title: ${input.title}
<#if input.tags??>
tags: <#list input.tags as t>${t}<#if t_has_next>,</#if></#list>
</#if>	
date: ${input.writeDate}
---
# ${input.title}

${input.text}