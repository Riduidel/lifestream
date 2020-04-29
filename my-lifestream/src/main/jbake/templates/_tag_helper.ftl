<#macro safe_tag tag>${tag?trim?replace("-", "_")?replace("@", "_at_")}</#macro>

<#macro tag_classes linked_tag><#compress>linked_tag_<@safe_tag linked_tag/>
<#if linked_tag?contains("année")>
linked_tag__année
<#elseif linked_tag?contains("mois")>
linked_tag__mois
</#if>
</#compress></#macro>