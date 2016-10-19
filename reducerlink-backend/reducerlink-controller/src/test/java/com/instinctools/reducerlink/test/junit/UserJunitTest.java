package com.instinctools.reducerlink.test.junit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.Assert;
import com.instinctools.reducerlink.JUnitTestRunner;
import com.instinctools.reducerlink.service.LinkService;
import org.junit.Test;

public class UserJunitTest extends JUnitTestRunner {
    private static final Logger logger = LoggerFactory.getLogger(UserJunitTest.class);

//    @Autowired
//    private LinkService linkService;

    //@Rollback(false)
    @Test
    public void test(){
        logger.info("UserJunitTest:Test Start.");
        //Assert.notNull(linkService.increaseNumberLinkVisits((long)2));

        logger.info("UserJunitTest:Test finish.");
    }
}
