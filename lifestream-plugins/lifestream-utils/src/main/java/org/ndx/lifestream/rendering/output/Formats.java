package org.ndx.lifestream.rendering.output;

import java.util.Optional;

import org.ndx.lifestream.rendering.path.PathNavigator;

public enum Formats {
	MARKDOWN("md") {
		@Override
		public String link(PathNavigator fromPath, PathNavigator toPath, String text) {
			return MarkdownUtils.link(fromPath, toPath, text, suffix);
		}
	},
	ASCIIDOC("adoc") {
		@Override
		public String link(PathNavigator fromPath, PathNavigator toPath, String text) {
			return AsciidocUtils.link(fromPath, toPath, text, "html");
		}
	};

	public final String suffix;
	public final String extension;

	Formats(String string) {
		this.suffix= string;
		this.extension = String.format(".%s", string);
	}

	public static Optional<Formats> forExtension(String extension2) {
		for(Formats f : values()) {
			if(f.extension.equals(extension2)) {
				return Optional.of(f);
			}
		}
		return Optional.empty();
	}

	public static Optional<Formats> forFile(String filename) {
		return forExtension(filename.substring(filename.lastIndexOf('.')));
	}

	public abstract String link(PathNavigator fromPath, PathNavigator toPath, String text);
}
