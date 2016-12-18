/**
 * @author wuuuxjia
 *
 */
package com.andywooh.web.controller;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * this is master
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
	
	
	final String JNDINAME = "java:comp/env/jdbc/andywooh";
	@ResponseBody
	@RequestMapping(value = "/data-source", method = RequestMethod.GET,  produces = "text/html; charset=UTF-8")
	public String dataSource() {
		 Connection conn = null ;
		    PreparedStatement pstmt = null;
		    String sql = "select * from andywooh.user";
		    StringBuilder result = new StringBuilder();
		    try
		    {
		        // 初始化查找命名空间
		        Context ctx = new InitialContext() ;
		        // 找到DataSource
		        DataSource ds = (DataSource)ctx.lookup(JNDINAME) ;
		        conn = ds.getConnection() ;
		        pstmt =  conn.prepareStatement(sql);
		        ResultSet rs = pstmt.executeQuery();
		        int col = rs.getMetaData().getColumnCount();
		        while (rs.next()) {
		            for (int i = 1; i <= col; i++) {
		            	result.append(rs.getString(i) + "\t");
		                if ((i == 2) && (rs.getString(i).length() < 8)) {
		                	result.append("\t");
		                }
		             }
		            result.append("");
		        }
		    }
		    catch(Exception e)
		    {
		        System.out.println(e) ;
		    }
		    
		    return result.toString();
	}
}