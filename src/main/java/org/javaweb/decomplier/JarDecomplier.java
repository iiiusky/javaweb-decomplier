package org.javaweb.decomplier;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author yz
 */
public class JarDecomplier {

	public static void main(String[] args) {
		String fileName       = "java-decompiler.jar";
		File   decompilerFile = new File(new File(System.getProperty("java.class.path")).getParentFile(), fileName);

		if (!decompilerFile.exists()) {
			decompilerFile = new File(System.getProperty("user"), fileName);
		}

		if (decompilerFile.exists()) {
			try {
				String         str         = "org.jetbrains.java.decompiler.main.decompiler.ConsoleDecompiler";
				URLClassLoader classLoader = new URLClassLoader(new URL[]{decompilerFile.toURL()});
				Class          clazz       = classLoader.loadClass(str);
				Method         method      = clazz.getMethod("main", String[].class);

				System.out.println("Github: https://github.com/JetBrains/intellij-community/tree/master/plugins/java-decompiler/engine");
				method.invoke(null, new Object[]{args});
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("文件:" + decompilerFile.getAbsolutePath() + "不存在!");
		}
	}

}
