<#if authors??>
## Authors
<#compress>
<#list authors as authorLink>
* ${authorLink}
</#list>
</#compress>
</#if>

<#if book.description??>
${book.description}
</#if>

<#if series??>
## Series
<#compress>
<#list series as serieLink>
* ${serieLink}
</#list>
</#compress>
</#if>

## Review

${book.review}