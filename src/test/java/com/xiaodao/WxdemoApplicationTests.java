package com.xiaodao;

import me.chanjar.weixin.mp.api.WxMpService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxdemoApplicationTests {

	@Autowired
	WxMpService wxMpService;

	@Test
	public void contextLoads()  throws  Exception{

		String shortUrl = wxMpService.shortUrl("https://www.baidu.cn");
		System.out.println(shortUrl);
	}


}
