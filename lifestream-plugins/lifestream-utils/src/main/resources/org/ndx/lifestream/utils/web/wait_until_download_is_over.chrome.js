var items = document.querySelector('downloads-manager').shadowRoot.getElementById('downloadsList').items;
if (items.every(e => e.state === "COMPLETE"))
	return items.map(e => e.fileUrl || e.file_url);