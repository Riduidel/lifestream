<#if books??>
## Books
<#compress>
<#list books as bookLink>
* ${bookLink}
</#list>
</#compress>
</#if>

<#if serie.description??>
## Description
${serie.description}
</#if>