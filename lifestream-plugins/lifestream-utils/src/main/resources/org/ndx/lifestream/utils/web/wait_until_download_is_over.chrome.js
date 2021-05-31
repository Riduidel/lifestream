var items = document.querySelector('downloads-manager').shadowRoot.getElementById('downloadsList').items;
return items.every(e => e.state === "COMPLETE")