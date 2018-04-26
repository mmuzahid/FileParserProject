package com.freewire.get;

import java.io.File;

import com.freewire.parser.FileParser;
import com.freewire.parser.output.FileParserResult;
import com.freewire.parser.strategy.FileParseStrategy;

public class DirectoryParser implements FileParser {

	@Override
	public FileParserResult parse(File file, FileParseStrategy strategy) {
		return strategy.process(file);
	}

}
