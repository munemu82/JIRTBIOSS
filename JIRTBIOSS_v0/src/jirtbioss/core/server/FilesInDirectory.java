package jirtbioss.core.server;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;

public class FilesInDirectory extends SimpleFileVisitor<Path> {

	public ArrayList<Path> foundPaths = new ArrayList<>();
	private PathMatcher matcher;
	public FilesInDirectory(String pattern){
		matcher = FileSystems.getDefault().getPathMatcher("glob"+pattern);
	}


	@Override
	public FileVisitResult visitFile(Path file, BasicFileAttributes attrs)
			throws IOException {
		Path name = file.getFileName();
		System.out.println("Examining the file: "+name);
		if(matcher.matches(name)){
			foundPaths.add(file);
		}
		return FileVisitResult.CONTINUE;
	}

	
}
