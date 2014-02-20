/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 23, 2014
 * File Name      : AbstractTestng.java
 */

package name.frb.crawler;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * AbstractTestng
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 23, 2014
 */
@ContextConfiguration(locations = {"classpath:spring/local/applicationContext-*.xml"})
public abstract class AbstractTestng extends AbstractTestNGSpringContextTests {

}
