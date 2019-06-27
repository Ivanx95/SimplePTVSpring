package one.main.tools;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;


/**
 * 
 * @author storm
 *
 */
public class FileClassLoader{

	public  <T> T getInstance(String path) throws ClassNotFoundException, MalformedURLException, InstantiationException, IllegalAccessException {
		
		File file = new File(path);
		URL url = file.toURI().toURL();

		URLClassLoader child = new URLClassLoader(
		        new URL[] {url},
		        this.getClass().getClassLoader()
		);
		
		Class classToLoad = Class.forName("one.main.components.CreditCardController", true, child);
		Object instance = classToLoad.newInstance();
		
		return (T) instance;
	}
	 
}
