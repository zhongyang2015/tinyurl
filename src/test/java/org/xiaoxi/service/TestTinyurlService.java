package org.xiaoxi.service;

import junit.framework.TestCase;
import junit.framework.TestResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xiaoxi.dto.Url;
import org.xiaoxi.service.impl.TinyurlServiceImpl;

/**
 * Created by YanYang on 2016/6/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml",
                        "classpath:spring/spring-service.xml"})
public class TestTinyurlService extends TestCase{
    private static final Logger LOGGER = LoggerFactory.getLogger(TestTinyurlService.class);

    @Autowired
    private TinyurlServiceInterface tinyurlService;

    @Test
    public void testTransferToShort_url() throws Exception {
        String long_url = "www.baidu.com/12345678901000123";
        long startTime = System.currentTimeMillis();
        Url url = tinyurlService.transferToShort_url(long_url);
        long endTime = System.currentTimeMillis();
        System.out.println(url);
        System.out.println(endTime - startTime);
    }

    @Test
    public void testTransferToLong_url() throws Exception {

        Url url = tinyurlService.transferToLong_url("1E");

        long startTime = System.currentTimeMillis();
        int n = 100;
        for (int i = 1; i < n; i++) {
            new Thread() {
                public void run() {
                    for (int i = 1; i < n; i++) {
                        tinyurlService.transferToLong_url("1E");
                    }
                }
            }.start();
        }
        long endTime = System.currentTimeMillis();

        System.out.println(url);
        System.out.println(endTime - startTime + "  " + (endTime - startTime) / (double)(n * n));
    }
}
