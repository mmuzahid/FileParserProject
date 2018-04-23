package com.freewire.get;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class LanguageFinder {

	private static Map<String, String[]> langsForAnyPartMatch = new HashMap<String, String[]>();
	private static Map<String, String[]> langsForExtMatch = new HashMap<String, String[]>();
	static {
		langsForAnyPartMatch.put("htm", new String[] { "HTML" });
		langsForAnyPartMatch.put("html", new String[] { "HTML" });
		langsForAnyPartMatch.put("xhtml", new String[] { "HTML" });
		langsForAnyPartMatch.put("css", new String[] { "CSS" });
		langsForAnyPartMatch.put("js", new String[] { "JavaScript" });
		langsForAnyPartMatch.put("pom", new String[] { "Maven" });
		langsForAnyPartMatch.put("gitignore", new String[] { "Git" });

		langsForExtMatch.put("xml", new String[] { "XML" });
		langsForExtMatch.put("java", new String[] { "Java" });
		langsForExtMatch.put("class", new String[] { "Java" });
		langsForExtMatch.put("cs", new String[] { "C#" });
		langsForExtMatch.put("py", new String[] { "Python" });
		langsForExtMatch.put("php", new String[] { "PHP" });
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
			System.out.println(Arrays.toString(getLanguageTags(projectDir)
					.toArray(new String[] {})));
			System.out.println("End processing.");
		}
	}

	private static Set<String> getLanguageTags(File file) {
		Set<String> langs = new TreeSet<String>();
		if (!file.isDirectory()) {
			langs.addAll(getTagsForFileName(file.getName()));
			langs.addAll(getTagsForFileExt(file.getName().substring(
					file.getName().lastIndexOf('.') + 1)));
			return langs;
		}

		File[] childFiles = file.listFiles();
		for (File child : childFiles) {
			langs.addAll(getLanguageTags(child));
		}
		return langs;
	}

	private static Set<String> getTagsForFileExt(String ext) {
		Set<String> langs = new TreeSet<String>();
		String[] langsForExt = langsForExtMatch.get(ext);
		if (langsForExt != null) {
			langs.addAll(Arrays.asList(langsForExt));
		}
		return langs;
	}
	
	private static Set<String> getTagsForFileName(String fileName) {
		Set<String> langs = new TreeSet<String>();

		for (String namePart : fileName.split("\\.")) {
			String[] langsForKeyword = langsForAnyPartMatch.get(namePart);
			if (langsForKeyword != null) {
				langs.addAll(Arrays.asList(langsForKeyword));
			}
		}
		return langs;
	}

}
