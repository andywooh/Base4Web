/**
 * @author wuuuxjia
 *
 */
package com.andywooh.web.controller;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * this is springsecurity branch
 * @author wuuuxjia
 *
 */
@Controller
@RequestMapping(value = "demo")
public class DemoControler {
	
	@Autowired
	@Qualifier("details")
	private Properties properties;
	
	@RequestMapping(value = "/say-hello", method = RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String sayHello(String userName, HttpServletResponse res) throws UnsupportedEncodingException {
		
		return "Hello " + userName;
	}
	
	/**
	 * This content was added in home
	 * @return
	 */
	@RequestMapping(value = "/to-other-page", method = RequestMethod.GET)
	public String toOtherPage() {
		return "other";
	}
}