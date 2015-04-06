<span class="twitter_status">

	<span class="author">
	
		<a href="http://twitter.com/${status.user.getScreenName()}" class="screenName"><img src="${status.user.getMiniProfileImageURL()}" alt="profile of ${status.user.getDescription()}"/>${status.user.getScreenName()}</a>
		<span class="name">${status.user.getName()}</span>
		
	</span>
	
	<a href="https://twitter.com/${status.user.getScreenName()}/status/${status.getId()}" class="date">${status.getCreatedAt()?datetime}</a>

	<span class="content">
	
	<span class="text">${status.getText()}</span>
	
	<span class="medias">
	<#list status.getExtendedMediaEntities() as media>
		<span class="media media-${media.getType()}">
		<#switch media.getType()>
			<#case "photo">
			<img src="${media.getMediaURL()}" alt="${media.getId()}"/>
			<#break>
			<#default>
			Media at <a href="${media.getMediaURL()}">${media.getMediaURL()}</a>
		</#switch>
		</span>
	</#list>
	</span>
	
	</span>
	
	
	<span class="twitter_status_end"/>
</span>