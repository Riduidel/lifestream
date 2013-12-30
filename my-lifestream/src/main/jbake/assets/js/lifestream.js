$('.add-filter span').click(function(){
   // add tag will consist in
    // 1 - finding the parent <span class="tag">
    var tagSpan = $(this).closest("span.tag")[0];
	var filteringTagText = $(tagSpan).data("tag");
    // 2- cloning that element into document#filtered-tags
    var cloned = $(tagSpan).clone();
    var filteringTags = $("#filtering-tags");
    filteringTags.append(cloned);
    // 3 - mark all tag span as filtering to hide them
    markTagAsFiltering(filteringTagText);
    // 4 - refilter document to show only matching posts
    reloadFilters();
});

function markTagAsFiltering(filteringTag) {
    $("div.tags li.tag").each(function() {
    	if($(this).data("tag")==filteringTag) {
    		$(this).toggleClass("filtering");
    	}
    });
}

$('#filtering-tags').on('click', '.remove-filter span', function(event){
    var tagSpan = $(this).closest("span.tag")[0];
    $(tagSpan).remove();
	var filteringTagText = $(tagSpan).data("tag");
    // 3 - mark all tag span as filtering to show them
    markTagAsFiltering(filteringTagText);
    // 4 - refilter document to show only matching posts
    reloadFilters();
});

function reloadFilters() {
	var requiredTags = $("#filtering-tags span.tag").map(function() {
		return $(this).data("tag");
	}).get();
	$('li.post-link').each(function(unusedIndex, examinedLink) {
		var linkTags = $(this).data('tags');
		var matching = true;
		$.each(requiredTags, function(arrayIndex, tagName) {
			var searched = "|"+tagName+"|";
			matching = (matching && linkTags.indexOf(searched)>=0);
		});
		if(matching) {
			if($(this).hasClass('not_matching')) {
				$(this).removeClass('not_matching');
			}
		} else {
			if(!$(this).hasClass('not_matching')) {
				$(this).addClass('not_matching');
			}
		}
	});
}