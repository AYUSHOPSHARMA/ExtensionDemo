package com.extension.demo.springboot.extension.launcher;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.NoSuchFileException;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class ExtensionLauncher {

	public static void loadJarFromExternalLocation(final String pathToJar) throws IOException {

		final JarFile jarFile = new JarFile(pathToJar);
		final Enumeration<JarEntry> jarEntryEnum = jarFile.entries();

		final URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };

		ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
		ClassLoader urlClassLoader = URLClassLoader.newInstance(urls, parentClassLoader);
		Thread.currentThread().setContextClassLoader(urlClassLoader);
		System.out.println(
				"   #####  THREAD CLASS LOADER ####### " + Thread.currentThread().getContextClassLoader().getParent());
		while (jarEntryEnum.hasMoreElements()) {
			final JarEntry jarEntry = jarEntryEnum.nextElement();
			if (jarEntry.getName().endsWith(".class") == true) {
				String jarEntryName = jarEntry.getName();
				int endIndex = jarEntryName.lastIndexOf(".class");
				String className = jarEntryName.substring(0, endIndex).replace('/', '.');
				try {
					urlClassLoader.loadClass(className);
				} catch (final ClassNotFoundException ex) {
				}
			}
		}
		jarFile.close();
	}
	
	public static void loadJarWithResourcesFromExternalLocation(final String pathToJar) throws IOException {
		JarFile jarFile = null;
		try {
			jarFile = new JarFile(pathToJar);
		}catch (NoSuchFileException e) {
			e.printStackTrace();
		}
		if(jarFile==null) {
			System.out.println("##################### No Extension Found ########### ");
			return;
		}
		
		final Enumeration<JarEntry> jarEntryEnum = jarFile.entries();

		final URL[] urls = { new URL("jar:file:" + pathToJar + "!/") };

		ClassLoader parentClassLoader = Thread.currentThread().getContextClassLoader();
		ClassLoader urlClassLoader = URLClassLoader.newInstance(urls, parentClassLoader);
		Thread.currentThread().setContextClassLoader(urlClassLoader);
		System.out.println(
				"   #####  THREAD CLASS LOADER ####### " + Thread.currentThread().getContextClassLoader().getParent());
		while (jarEntryEnum.hasMoreElements()) {
			final JarEntry jarEntry = jarEntryEnum.nextElement();
			System.out.println("JAr Entry ---- >"+jarEntry);
			 if (jarEntry.getName().endsWith(".class") || jarEntry.getName().endsWith(".properties") || jarEntry.getName().endsWith(".xml")) {
				String jarEntryName = jarEntry.getName();
				System.out.println("Loading --------------- >"+jarEntryName);
				int endIndex = jarEntryName.lastIndexOf(".class");
				if (endIndex > 0) {
					String className = jarEntryName.substring(0, endIndex).replace('/', '.');
					try {
						urlClassLoader.loadClass(className);
					} catch (final ClassNotFoundException ex) {
					}
				} else {
					//String resourceName = jarEntryName.substring(0, endIndex).replace('/', '.');
					System.out.println("Loading Resource --------------- >"+jarEntryName);
					urlClassLoader.getResourceAsStream(jarEntryName);
				}
			}
		 } 
		jarFile.close();
	}

}
