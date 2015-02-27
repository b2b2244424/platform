/**
 * 
 */
package com.lml.platform.core.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 测试环境
 * 
 * @author lanmingle
 */
public class EnvironmentTest {

	/***
	 * slf4j logger
	 */
	private final Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		logger.debug("setUp");
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
		logger.debug("tearDown");
	}

	/***
	 * 测试输出日记
	 */
	@Test
	public void testLogger() {
		logger.debug("testLogger...");
	}

}
