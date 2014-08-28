package org.ndx.lifestream.rendering.path;

import java.util.Collections;
import java.util.List;

import org.apache.commons.vfs2.FileObject;

public class ExternalPathNavigator implements PathNavigator {

	private ExternalLink link;

	public ExternalPathNavigator(ExternalLink external) {
		this.link = external;
	}

	@Override
	public FileObject toRealFile(FileObject output) throws ImpossibleOnRemoteFileException {
		throw new ImpossibleOnRemoteFileException(link+" is external");
	}

	@Override
	public String toURI() {
		return link.getCurrent();
	}

	@Override
	public String depth() {
		return "";
	}

	@Override
	public List<String> toPathList() {
		return Collections.emptyList();
	}

}
