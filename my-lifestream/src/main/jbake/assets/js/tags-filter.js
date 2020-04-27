
function toggleHighlightedTagDisplay(tagId, active) {
	var tagAHref = document.getElementById(tagId)
	var selected = (tagAHref.getAttribute("data-selected")=="true")
	Array.prototype.forEach.call(document.getElementsByClassName(tagId), 
			element => {
				var span = element.firstElementChild;
				["label-info", "label-default", "label-danger", "label-success"].forEach(
						label => span.classList.remove(label)
						)
			})
	var newLabelType = selected ?
			(active ? "label-danger" : "label-info") :
				(active ? "label-success" : "label-default")
	Array.prototype.forEach.call(document.getElementsByClassName(tagId), 
			element => {
				var span = element.firstElementChild
				span.classList.add(newLabelType)
			})
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
	toggleHighlightedTagDisplay(tagId, true)
	// Now come the hard part: hide links having no selected tags (well, unless there is absolutly no selected tag)
	var linked_tags = document.getElementsByClassName("reference_tag")
	var selected_tags = Array.prototype.filter.call(linked_tags, element => element.getAttribute("data-selected")=="true")
		.map(element => element.getAttribute("data-safe-tag"))
	refreshVisiblePosts(selected_tags)
	refreshUrl(selected_tags)
}
function refreshUrl(selected_tags) {
	var params = new URLSearchParams()
	selected_tags.map(t => encodeURI(t))
		.forEach(t => params.append("tag", t))
	document.getElementById("autolink").setAttribute("href", document.location.href + "?" + params.toString())
}

function refreshVisiblePosts(selected_tags) {
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

// At document loaded, we search all distinct tags and generate for each one the "reference" tag
function reconstituteTags() {
	var all_linked_tags = {}
	var linked_tags = document.getElementById("linked_tags")
	Array.prototype.forEach.call(document.getElementsByClassName("linked_tag"), element => {
		var linked_tag_name = element.getAttribute("data-safe-tag")
		if(!all_linked_tags.hasOwnProperty(linked_tag_name)) {
			var reference = element.cloneNode(true)
			reference.setAttribute("id", "linked_tag_"+linked_tag_name)
			reference.setAttribute("data-selected", false)
			reference.classList.add("reference_tag")
			all_linked_tags[linked_tag_name]=reference
		}
	})
	// Now all elements have been added, sort them and add them to document
	Object.keys(all_linked_tags).sort().forEach(tag => linked_tags.appendChild(all_linked_tags[tag]))
}

function loadLinkedTags() {
	var params = new URLSearchParams(document.location.search)
	params.getAll("tag").map(t => "linked_tag_"+t)
		.forEach(t => toggleTag(t))
}

function onLoad() {
	reconstituteTags();
	loadLinkedTags()
}

onLoad()