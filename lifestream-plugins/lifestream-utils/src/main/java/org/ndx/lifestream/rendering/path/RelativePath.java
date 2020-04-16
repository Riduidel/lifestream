package org.ndx.lifestream.rendering.path;

import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.ndx.lifestream.rendering.output.UnableToresolveOutputFileException;

public class RelativePath implements PathNavigator {
	private final List<String> realPath;

	public RelativePath(List<String> realPath) {
		super();
		this.realPath = realPath;
	}

	@Override
	public FileObject toRealFile(FileObject output) {
		FileObject resultFile = output;
		try {
			for (String pathElement : realPath) {
				resultFile = resultFile.resolveFile(pathElement);
			}
			return resultFile;
		} catch (FileSystemException e) {
			throw new UnableToresolveOutputFileException("unable to create output file below " + output.getName().getPath() + " for "
							+ realPath.stream().collect(Collectors.joining("/")));
		}
	}

	@Override
	public String toURI() {
		return realPath.stream().collect(Collectors.joining("/"));
	}

	@Override
	public String depth() {
		StringBuilder sOut = new StringBuilder();
		for (int i = 0; i < realPath.size()-1; i++) {
			sOut.append("../");
		}
		return sOut.toString();
	}

	@Override
	public List<String> toPathList() {
		return realPath;
	}

}
