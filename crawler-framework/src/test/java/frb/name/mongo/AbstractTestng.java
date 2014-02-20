/*
 * Create Author  : renbin.fang
 * Create Date    : Jan 17, 2014
 * File Name      : AbstractTest.java
 */

package frb.name.mongo;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;

/**
 * Abstract testng
 * <p>
 * 
 * @author : renbin.fang
 * @date : Jan 17, 2014
 */
@ContextConfiguration(locations = {"classpath*:spring/common/applicationContext-*.xml", "classpath:spring/common/applicationContext-*.xml"})
public abstract class AbstractTestng extends AbstractTestNGSpringContextTests {

}
