package net.multiplemonomials.densetsu.util;

/**
 * Basic logging wrapper class for JavaJapanese
 * @author Jamie
 *
 */
public class Log
{
	/**
	 * Set this from your code if you want JavaJapanese logging output.
	 */
	public static boolean enableJavaJapaneseLogging = false;
	
	/**
	 * Log an error.
	 * @param tag
	 * @param message
	 */
	public static void e(String tag, String message)
	{
		log("Error", tag, message);
	}
	
	/**
	 * Log a warning: something which should not happen under normal circumstances and probably is a bug, but does not cause anything to crash.
	 * @param tag
	 * @param message
	 */
	public static void w(String tag, String message)
	{
		log("Unusual", tag, message);
	}
	
	/**
	 * Log an info message: a semi-important message which the user should probably see, but does not indicate anything is broken.
	 */
	public static void v(String tag, String message)
	{
		log("Info", tag, message);
	}
	
	/**
	 * Log a debug message which is not important during normal operation, but is useful if you're trying to debug the robot.
	 * @param tag
	 * @param message
	 */
	public static void d(String tag, String message)
	{
		log("Debug", tag, message);
	}
	
	private static void log(String severity, String tag, String message)
	{
		if(enableJavaJapaneseLogging)
		{
			System.out.println(String.format("[JavaJapanese] [%s] [%s] %s", severity, tag, message));
		}
	}
}
