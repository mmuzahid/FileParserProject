package com.freewire.parser;

import java.io.File;

import com.freewire.parser.output.FileParserResult;
import com.freewire.parser.strategy.FileParseStrategy;

public interface FileParser extends
		GenericParser<File, FileParserResult, FileParseStrategy> {
}
