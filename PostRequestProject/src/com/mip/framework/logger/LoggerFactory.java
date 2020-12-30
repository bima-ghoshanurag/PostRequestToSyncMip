package com.mip.framework.logger;

import org.slf4j.Logger;

/**
 * This class <code>LoggerFactory</code> provides the point of access to the
 * logging layer.
 * 
 * The typical usage in getting a <code>MIPLogger</code> instance to start
 * logging is as shown below :
 * 
 * <p>
 * <blockquote>
 * 
 * <pre>
 * private static MIPLogger logger = LoggerFactory.getInstance().getLogger(
 * 		MyClass.class);
 * </pre>
 * 
 * or
 * 
 * <pre>
 * private static MIPLogger logger = LoggerFactory.getInstance().getLogger(
 * 		&quot;MyJSP&quot;);
 * </pre>
 * 
 * <p>
 * It can then use all the methods defined under <code>Logger</code> interface
 * appropriately.
 * 
 * <pre>
 *   For Ex :
 *     if (logger.isDebugEnabled())
 *        logger.debug(&quot;My debug message&quot;);
 * 
 * </pre>
 * 
 * </blockquote>
 * 
 * @author Anurag Ghosh
 * @version 1.0, 24/04/2018
 * 
 * @see Logger
 * @see MIPLogger
 * 
 */

public final class LoggerFactory {

	private static LoggerFactory theInstance = new LoggerFactory();

	/**
	 * Returns the instance of this class
	 * 
	 * @return an instance of <code>LoggerFactory</code>
	 */
	public static LoggerFactory getInstance() {
		return theInstance;
	}

	/**
	 * Gets the instance of logger which is associated with the given class.
	 * 
	 * @param clss
	 *            a <code>Class</code> object interested in using the logger.
	 * 
	 * @return an implementation of <code>Logger</code>
	 * 
	 */
	@SuppressWarnings("rawtypes")
	public MIPLogger getLogger(Class clss) {
		MIPLogger log = new MIPLogger(clss);
		return log;
	}

	/**
	 * Gets the instance of logger which is associated with the given class
	 * name.
	 * 
	 * @param name
	 *            name of a class interested in using the logger.
	 * 
	 * @return an implementation of <code>Logger</code>
	 * 
	 */
	public MIPLogger getLogger(String name) {
		MIPLogger log = new MIPLogger(name);
		return log;
	}

}