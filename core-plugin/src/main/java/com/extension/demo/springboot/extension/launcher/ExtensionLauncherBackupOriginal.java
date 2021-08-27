package com.extension.demo.springboot.extension.launcher;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;


public class ExtensionLauncherBackupOriginal {

	public static void loadJar(final String pathToJar) throws IOException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {

		// Class name to Class object mapping.
		final Map<String, Class<?>> classMap = new HashMap<String, Class<?>>();

		final JarFile jarFile = new JarFile(pathToJar);
		final Enumeration<JarEntry> jarEntryEnum = jarFile.entries();

		final URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };
		
		ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
		System.out.println("   Previous #####  THREAD CLASS LOADER ####### "+parentClassLoader);
		// Create a class loader using the URL as the codebase
		// Use previous as parent class loader to maintain current visibility
		ClassLoader urlClassLoader = URLClassLoader.newInstance(urls, parentClassLoader);
		Thread.currentThread().setContextClassLoader(urlClassLoader);
		
		System.out.println("   #####  THREAD CLASS LOADER ####### "+Thread.currentThread().getContextClassLoader().getParent());
		//final URLClassLoader urlClassLoader = URLClassLoader.newInstance(urls);
		
		while (jarEntryEnum.hasMoreElements()) {

			final JarEntry jarEntry = jarEntryEnum.nextElement();

			if (/*
				 * jarEntry.getName().startsWith("org/springframework/boot") &&
				 */ jarEntry.getName().endsWith(".class") == true) {

				String jarEntryName = jarEntry.getName();
				int endIndex = jarEntryName.lastIndexOf(".class");
                
				String className = jarEntryName.substring(0, endIndex).replace('/', '.');

				try {

					final Class<?> loadedClass = urlClassLoader.loadClass(className);

					classMap.put(loadedClass.getName(), loadedClass);
				} catch (final ClassNotFoundException ex) {

				}
			}
		}
		System.out.println("############################ CLASS MAP  ################################");
		//System.out.println(classMap);
		 File file = new File("C:\\dev\\Core-Extension\\extension\\a.txt");
		 BufferedWriter bf = null;
		  
	        try {
	  
	            // create new BufferedWriter for the output file
	            bf = new BufferedWriter(new FileWriter(file));
	  
	            // iterate map entries
	            for (Entry<String, Class<?>> entry :
	            	classMap.entrySet()) {
	  
	                // put key and value separated by a colon
	                bf.write(entry.getKey() + ":"
	                         + entry.getValue());
	  
	                // new line
	                bf.newLine();
	            }
	  
	            bf.flush();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
	        finally {
	  
	            try {
	  
	                // always close the writer
	                bf.close();
	            }
	            catch (Exception e) {
	            }
	        }
	  
		
		
		final Class<?> jarFileArchiveClass = classMap.get("org.springframework.boot.loader.archive.JarFileArchive");

		final Constructor<?> jarFileArchiveConstructor = 
			jarFileArchiveClass.getConstructor(File.class);

		final Object jarFileArchive = 
				jarFileArchiveConstructor.newInstance(new File(pathToJar));

		final Class<?> archiveClass = 	classMap.get("org.springframework.boot.loader.archive.Archive");
		
		final Class<?> mainClass = 	classMap.get("org.springframework.boot.loader.JarLauncher");
		
		// Create JarLauncher object using JarLauncher(Archive) constructor. 
		final Constructor<?> jarLauncherConstructor = mainClass.getDeclaredConstructor(archiveClass);

		jarLauncherConstructor.setAccessible(true);
		final Object jarLauncher = jarLauncherConstructor.newInstance(jarFileArchive);
		
		
		// Invoke JarLauncher#launch(String[]) method.
		final Class<?> launcherClass = 	classMap.get("org.springframework.boot.loader.Launcher");

		/*
		 * final Method launchMethod = launcherClass.getDeclaredMethod("launch",
		 * String[].class); launchMethod.setAccessible(true);
		 * 
		 * launchMethod.invoke(jarLauncher, new Object[]{new String[0]});
		 */
		
		jarFile.close();
	}
}
