package com.ontariotechu.sofe3980U;

import java.io.*;
import org.joda.time.LocalTime;

import net.sf.javaml.core.Dataset;
import net.sf.javaml.tools.data.FileHandler;

/**
 * Hello world!
 *
 */
public class App {
	/**
	 * Main program: The entry point of the program. The local time will be printed
	 * first,
	 * Then it will create two binary variables, add them and print the result.
	 *
	 * @param args: not used
	 */
	public static void main(String[] args) {
		LocalTime currentTime = new LocalTime();
		System.out.println("The current local time is: " + currentTime);

		
        /* Load a dataset */        
		try {
            // Now you can use the FileHandler with the temporary file
            Dataset data = FileHandler.loadDataset(new File("src/main/resources/iris.data"), 4, ",");
            System.out.println("Data loaded successfully - " + data.size() + " instances");
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
