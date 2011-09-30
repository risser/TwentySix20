package com.twentysix20.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

public class FileUtil {

    private FileUtil() {}

    public static String getFullPathFileName(Class<?> c, String name) throws FileNotFoundException {
        if (StringUtil.isEmpty(name)) throw new IllegalArgumentException("Can't use a blank or null file name.");
        if (c == null) throw new IllegalArgumentException("Can't use a null class.");
        
        URL resource = c.getResource(name);

        if (resource == null) throw new FileNotFoundException("Couldn't find path "+name+" from class "+c.getName()+".");
        
        String fileName = resource.getFile();
        if (fileName == null) throw new FileNotFoundException("Couldn't find path "+name+" from class "+c.getName()+".");
        
        return fileName.replaceAll("%20"," ");
    }
    
    static public StringBuffer readFile(String name) throws IOException {
        return readFile(FileUtil.class, name);
    }
    
    static public StringBuffer readFile(Object o, String name) throws IOException {
        return readFile(o.getClass(), name);
    }
    
    static public StringBuffer readFile(Class<?> clazz, String name) throws IOException {
        String fullName = FileUtil.getFullPathFileName(clazz, name);
        BufferedReader in = new BufferedReader(new FileReader(fullName));
        StringBuffer buff = new StringBuffer();
        String line;
        while ((line = in.readLine()) != null) {
            buff.append(line).append("\n");
        }
        return buff;
    }

	static public String readFileToString(String filename) throws FileNotFoundException, IOException {
		// not recommended unless the filename is fully qualified.
		return readFileToString(FileUtil.class, filename, null);
	}

	static public String readFileToString(Class<?> rootClass, String filename) throws FileNotFoundException, IOException {
		return readFileToString(rootClass, filename, null);
	}

	static public String readFileToString(String filename, String[][] replacements) throws FileNotFoundException, IOException {
		return readFileToString(FileUtil.class, filename, replacements);
	}

	static public String readFileToString(Class<?> rootClass, String filename, String[][] replacements) throws FileNotFoundException, IOException {
		String fullName = findFullyQualifiedFilename(rootClass, filename);
		BufferedReader in = new BufferedReader(new FileReader(new File(fullName)));
		StringBuffer buddy = new StringBuffer();
		String line = "";
		if ((line = in.readLine()) != null)
			buddy.append(line);
		while ((line = in.readLine()) != null)
			buddy.append("\n").append(line);
		String pal = buddy.toString();

		if (replacements != null) {
			for (int i = 0; i < replacements.length; i++) {
				pal = pal.replaceAll(replacements[i][0], replacements[i][1]);
			}
		}
		return pal;
	}

	static public String findFullyQualifiedFilename(Class<?> rootClass, String filename) throws FileNotFoundException {
		URL url = rootClass.getResource(filename);
		if (url == null) throw new FileNotFoundException(filename);
		String fullName = url.toString().substring(6); // strip "file:/" from beginning
		fullName = fullName.replaceAll("%20"," ");
		return fullName;
	}

	static public Set<File> findFilesInSubdirectories(File directory, FileFilter filter) {
		Set<File> files = new HashSet<File>();
		for (File file : directory.listFiles()) {
			if (file.isDirectory())
				files.addAll(findFilesInSubdirectories(file, filter));
			else if (filter.accept(file))
				files.add(file);
		}
		return files;
	}

	static public Set<File> findFilesInSubdirectories(Set<File> directories, FileFilter filter) {
		Set<File> files = new HashSet<File>();
		for (File directory : directories)
			files.addAll(findFilesInSubdirectories(directory, filter));

		return files;
	}
}
