package com.xiaodao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.xiaodao.template.service.TemplateMessageSend;
import com.xiaodao.template.domain.TemplateBase;
import com.xiaodao.template.domain.TemplateMetaData;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxdemoApplicationTests {

	@Autowired
	WxMpService wxMpService;
	@Autowired
	TemplateMessageSend templateMessageSend;

	@Test
	public void contextLoads()  throws  Exception{

		String shortUrl = wxMpService.shortUrl("https://www.baidu.cn");
		System.out.println(shortUrl);
	}

	@Test
	public void testTemplate() {
		// WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
		// 		.toUser("oiFO803DJu3Qqk5jafFFxOYOChKs")
		// 		.templateId("Xn4Vm_fCWlY7f43-OUOWehAGnq4wa8WxPsWRuNDhpOo")
		// 		.url("www.baidu.com")
		// 		.build();
        //
		// templateMessage.getData().add(new WxMpTemplateData("AA", "haha", "FFFFFF"));
		// templateMessage.getData().add(new WxMpTemplateData("BB", "BIBI", "CCC"));
        //
		// try {
		// 	String message = new ObjectMapper().writeValueAsString(templateMessage);
		// 	System.out.println(message);
		// 	wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
		// } catch (WxErrorException e1) {
		// 	e1.printStackTrace();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }
		// TemplateBase templateBase = new TemplateBase();
		// templateBase.setToUser("oiFO803DJu3Qqk5jafFFxOYOChKs");
		// templateBase.setTemplateId("Xn4Vm_fCWlY7f43-OUOWehAGnq4wa8WxPsWRuNDhpOo");
		// templateBase.setUrl("www.baidu.com");
		// templateBase.setMiniProgram(null);
        //
		// List<Map<String, TemplateMetaData>> data = Lists.newArrayList();
		// templateBase.setData(data);
		String message = "{\"touser\":\"oiFO803DJu3Qqk5jafFFxOYOChKs\",\"template_id\":\"Xn4Vm_fCWlY7f43-OUOWehAGnq4wa8WxPsWRuNDhpOo\",\"url\":\"www.baidu.com\",\"miniprogram\":null,\"data\":{\"AA\":{\"value\":\"aaaa\",\"color\":\"#173177\"},\"BB\":{\"value\":\"bbbb\",\"color\":\"#173177\"}}}";
		try {
			TemplateBase base = new ObjectMapper().readValue(message, TemplateBase.class);
			WxMpTemplateMessage wxMpTemplateMessage = base.toWxMpTemplateMessage();
			wxMpService.getTemplateMsgService().sendTemplateMsg(wxMpTemplateMessage);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WxErrorException e) {
			e.printStackTrace();
		}
// try {
		// 	String message = new ObjectMapper().writeValueAsString(templateMessage);
		// 	System.out.println(message);
		//
		// } catch (WxErrorException e1) {
		// 	e1.printStackTrace();
		// } catch (Exception e) {
		// 	e.printStackTrace();
		// }

	}

	@Test
	public void test() throws JsonProcessingException {
		TemplateMetaData data1 = new TemplateMetaData("aaaa");
		TemplateMetaData data2 = new TemplateMetaData("bbbb");
		Map map = Maps.newHashMap();
		map.put("AA", data1);
		map.put("BB", data2);

		TemplateBase templateBase = new TemplateBase();
		templateBase.setToUser("oiFO803DJu3Qqk5jafFFxOYOChKs");
		templateBase.setTemplateId("Xn4Vm_fCWlY7f43-OUOWehAGnq4wa8WxPsWRuNDhpOo");
		templateBase.setUrl("www.baidu.com");
		templateBase.setMiniProgram(null);
		templateBase.setData(map);
		String string = new ObjectMapper().writeValueAsString(templateBase);
		System.out.println(string);
	}


}
