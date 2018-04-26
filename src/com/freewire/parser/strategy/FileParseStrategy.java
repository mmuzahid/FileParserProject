package com.freewire.parser.strategy;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.freewire.parser.ParseStrategy;
import com.freewire.parser.output.FileParserResult;
import com.freewire.parser.output.Keyword;

public class FileParseStrategy implements ParseStrategy<File, FileParserResult> {
	@Override
	public FileParserResult process(File file) {
		FileParserResult result = new FileParserResult();

		Map<Keyword, Integer> keywordMap = new HashMap<Keyword, Integer>();
		updateKeywordsForFile(keywordMap, file);
		for (Keyword keyword : keywordMap.keySet()) {
			keyword.setCount(keywordMap.get(keyword));			
		}
		result.setKeywords(keywordMap.keySet());

		return result;
	}

	private static Map<String, String[]> langMatchesAnyPart = new HashMap<String, String[]>();
	private static Map<String, String[]> langMatchesExt = new HashMap<String, String[]>();
	static {
		langMatchesAnyPart.put("htm", new String[] { "HTML" });
		langMatchesAnyPart.put("html", new String[] { "HTML" });
		langMatchesAnyPart.put("xhtml", new String[] { "HTML" });
		langMatchesAnyPart.put("css", new String[] { "CSS" });
		langMatchesAnyPart.put("js", new String[] { "JavaScript" });
		langMatchesAnyPart.put("pom", new String[] { "Maven" });
		langMatchesAnyPart.put("gitignore", new String[] { "Git" });

		langMatchesExt.put("xml", new String[] { "XML" });
		langMatchesExt.put("java", new String[] { "Java" });
		langMatchesExt.put("class", new String[] { "Java" });
		langMatchesExt.put("cs", new String[] { "C#" });
		langMatchesExt.put("py", new String[] { "Python" });
		langMatchesExt.put("php", new String[] { "PHP" });
	}

	private static void updateKeywordsByFileExt(Map<Keyword, Integer> keywordMap, String ext) {
		String[] langsForExt = langMatchesExt.get(ext);
		if (langsForExt != null) {

			for (String lang : langsForExt) {
				Keyword keyword = new Keyword(lang);

				if (keywordMap.containsKey(keyword)) {
					keywordMap.put(keyword, keywordMap.get(keyword) + 1);
				} else {
					keywordMap.put(keyword, 1);
				}
			}
		}
	}

	private static void updateKeywordsByFileName(Map<Keyword, Integer> keywordMap, String fileName) {
		for (String namePart : fileName.split("\\.")) {
			String[] langsForKeyword = langMatchesAnyPart.get(namePart);
			if (langsForKeyword != null) {
				for (String lang : langsForKeyword) {
					Keyword keyword = new Keyword(lang);
					if (keywordMap.containsKey(keyword)) {
						keywordMap.put(keyword, keywordMap.get(keyword) + 1);
					} else {
						keywordMap.put(keyword, 1);
					}
				}
			}
		}
	}

	private void updateKeywordsForFile(Map<Keyword, Integer> keywordMap,
			File file) {
		File[] childFiles = file.listFiles();
		if (childFiles != null) {
			for (File child : childFiles) {
				updateKeywordsForFile(keywordMap, child);
			}
		}
		updateKeywordsByFileName(keywordMap, file.getName());
		String fileExt = file.getName().substring(file.getName().lastIndexOf('.') + 1);
		updateKeywordsByFileExt(keywordMap, fileExt);
	}

}
