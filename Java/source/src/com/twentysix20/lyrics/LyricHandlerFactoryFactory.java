package com.twentysix20.lyrics;

import java.util.HashSet;
import java.util.Set;

public class LyricHandlerFactoryFactory {
	Set<LyricHandlerFactory> parsers = new HashSet<LyricHandlerFactory>();

	public LyricHandlerFactory getHandlerFactory(String url) {
		for (LyricHandlerFactory parser : parsers)
			if (parser.matches(url)) {
				parser.verify();
				return parser;
			}
		return null;
	}

	public boolean hasHandlerFactory(String url) {
		for (LyricHandlerFactory parser : parsers)
			if (parser.matches(url))
				return true;
		return false;
	}

	public void addParserFactory(LyricHandlerFactory parser) {
		parsers.add(parser);
	}

}
