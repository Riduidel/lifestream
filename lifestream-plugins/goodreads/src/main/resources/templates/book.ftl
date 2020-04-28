<#if book.description??>
++++
<div class="book-description">
${book.description}
</div>
++++
</#if>


<#if authors??><#if authors?has_content>
## Authors
<#compress>
<#list authors as authorLink>
* ${authorLink}
</#list>
</#compress>
</#if></#if>


<#if series??><#if series?has_content>
## Series
<#compress>
<#list series as serieLink>
* ${serieLink}
</#list>
</#compress>
</#if></#if>


## Review

++++
${book.review}
++++