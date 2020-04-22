function toggleSelectedStatus(selected) {
	return function(element) {
		var span = element.firstElementChild
		if(selected) {
			span.classList.add("label-success")
			span.classList.remove("label-default")
		} else {
			span.classList.add("label-default")
			span.classList.remove("label-success")
		}
	}
}

/**
 * In our document, there is one tag which has this tagId as id, and some elements which have 
 * this tag id as class. On click, we get the data-selected attribute of the element which
 * has this id, toggle it.
 * If now selected, we apply the label-selected label to each label in element which have this class name
 * If unselected, well, we remove that label.
 * (more to come later)
 * 
 * @param tagId
 * @returns
 */
function toggleTag(tagId) {
	var tagAHref = document.getElementById(tagId)
	var previouslySelected = (tagAHref.getAttribute("data-selected")=="true")
	var selected = !previouslySelected
	tagAHref.setAttribute("data-selected", selected.toString())
	// Highlight matching tags
	Array.prototype.forEach.call(document.getElementsByClassName(tagId), toggleSelectedStatus(selected))
	// Now come the hard part: hide links having no selected tags (well, unless there is absolutly no selected tag)
	refreshVisiblePosts()
}

function refreshVisiblePosts() {
	var linked_tags = document.getElementsByClassName("reference_tag")
	var selected_tags = Array.prototype.filter.call(linked_tags, element => element.getAttribute("data-selected")=="true")
		.map(element => element.getAttribute("data-safe-tag"))
	var posts = document.getElementsByClassName("linked_post")
	if(selected_tags.length<=0) {
		// Display all posts
		Array.prototype.forEach.call(posts, element => {
			element.classList.remove("hidden");
			element.classList.add("visible");
		})
	} else {
		Array.prototype.forEach.call(posts, showPostIfMatching(selected_tags))
	}
}

function showPostIfMatching(tags) {
	return function(element) {
		var post_tags = JSON.parse(element.getAttribute("data-tags").split("'").join('"'))
		var selected = tags.map(t => post_tags.indexOf(t)>=0)
			.reduce(((all_matching, tag_matching) => all_matching && tag_matching), true)
		if(selected) {
			element.classList.remove("hidden");
			element.classList.add("visible");
		} else {
			element.classList.add("hidden");
			element.classList.remove("visible");
		}
	}
}