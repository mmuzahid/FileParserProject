package com.freewire.parser.output;

import java.io.File;
import java.util.Date;
import java.util.Set;

public class FileParserResult {
	private File srcFile;
	private boolean parseCompleted;
	private Date startTime;
	private Date endTime;
	private Set<Keyword> keywords;

	public FileParserResult() {
		
	}
	
	public File getSrcFile() {
		return srcFile;
	}

	public void setSrcFile(File srcFile) {
		this.srcFile = srcFile;
	}

	public boolean isParseCompleted() {
		return parseCompleted;
	}

	public void setParseCompleted(boolean parseCompleted) {
		this.parseCompleted = parseCompleted;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Set<Keyword> getKeywords() {
		return keywords;
	}

	public void setKeywords(Set<Keyword> keywords) {
		this.keywords = keywords;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (Keyword keyword : getKeywords()) {
			sb.append(keyword);
			sb.append("\n");
		}
		return sb.toString();
	}
}
