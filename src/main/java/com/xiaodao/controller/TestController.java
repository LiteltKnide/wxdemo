package com.xiaodao.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hujt49 on 2018/05/31.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    private Logger logger = LoggerFactory.getLogger(TestController.class);

    @ApiOperation(value = "测试接口", notes = "测试程序是否正常运行")
    @RequestMapping(value = "/{str}", method = RequestMethod.GET)
    public String test(@PathVariable("str") String string) {
        logger.info("test ..." + string);
        return "test success" + string;
    }
}
