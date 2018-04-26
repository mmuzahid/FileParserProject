package com.freewire.get;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.freewire.parser.FileParser;
import com.freewire.parser.output.FileParserResult;
import com.freewire.parser.strategy.FileParseStrategy;

public class LanguageFinder {

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

	public static void main(String[] args) throws IOException {
		String projectPath = args.length > 0 ? args[0]
				: "F:/GIT_REPO/test_project";

		File projectDir = new File(projectPath);
		if (!projectDir.isDirectory()) {
			System.err.printf("Project path '%s' is not a Directory!",
					projectPath);
		} else {
			System.out.println("Start processing...");
			FileParser parser = new DirectoryParser();
			FileParseStrategy strategy = new FileParseStrategy();
			FileParserResult result = parser.parse(projectDir, strategy);
			System.out.println(result);
			System.out.println("End processing.");

		}
	}

}
