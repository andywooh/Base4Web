/**
 * @author wuuuxjia
 *
 */
package com.andywooh.web.controller;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("demo")
public class DemoControler {
	
	@RequestMapping(value = "/say-hello", method = RequestMethod.POST)
	@ResponseBody
	public String sayHello(String userName) throws UnsupportedEncodingException {
		String tmp = "Hello " + new String(userName.getBytes( "UTF-8"), "UTF-8");
		return new String(tmp.getBytes( "UTF-8"),  "UTF-8");
		
	}
	
	@RequestMapping(value = "/to-other-page", method = RequestMethod.GET)
	public String toOtherPage(){
		return "other";
	}	
}