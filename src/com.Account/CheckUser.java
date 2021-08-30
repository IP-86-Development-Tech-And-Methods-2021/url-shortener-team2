
package com.Account;
import java.io.IOException;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@SuppressWarnings("serial")
public class CheckUser extends HttpServlet {

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");

		//md5对密码加密
		Md5 md5=new Md5();

		//判断类型是登陆还是注册
		String Sql="";
		String action_type = request.getParameter("actiontype");
		if(action_type.equals("login")){
			 Sql = "SELECT user_id,username,password,phone FROM user "
			 		+ "where username='" + userName+ "' AND password='" + md5.MD5(password) + "' ";
		}else if(action_type.equals("register")){
			 Sql = "INSERT into user (username,password,phone) values ('"+ userName+"' ,'"+md5.MD5(password) +"',' ')";
		}

		Connection connection = null;
		PreparedStatement preSta = null;
		ResultSet rs = null;

		// 建立连接,DriverManager是JDBC的管理层
		try {
			//使用连接池的方式连接数据库
			ConnectionPool.setUrl(ConnectionPool.getUrl());
			ConnectionPool.setUser(ConnectionPool.getUser());//DBTools.getDatabaseUserName()
			ConnectionPool.setPassword(ConnectionPool.getPassword());

			connection = ConnectionPool.getConnection();
			preSta = connection.prepareStatement(Sql);

			UserInfo info = null;

			if(action_type.equals("login")) {
				rs = preSta.executeQuery();
				while(rs.next()){
					info = new UserInfo();
					info.setuser_id(rs.getInt("user_id"));
					info.setUserName(rs.getString("username"));
					info.setPassword(rs.getString("password"));
					info.setPhone(rs.getString("phone"));
				}
				if(info != null){
					session.setAttribute("password", password);
					session.setAttribute("info", info);
					String login_suc = "success.jsp";
					response.sendRedirect(login_suc);
				}
				else{
					String login_fail = "login.jsp";
					session.setAttribute("errorMsg", "用户名或密码错误，登陆失败！");
					response.sendRedirect(login_fail);
				}
			}
			else if(action_type.equals("register")){
 			   int count= preSta.executeUpdate();
 			   if(count>0){
					session.setAttribute("registerMsg", "注册成功！");
				}
				else{
					session.setAttribute("registerMsg", "注册失败，请重试！");
				}
 				response.sendRedirect("register.jsp");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 关闭连接
		finally {
			if(connection!=null){
				try{
					connection.close();
				}catch(Exception e){}
			}
			if(preSta!=null){
				try{
					preSta.close();
				}catch(Exception e){}
			}
			if(rs!=null){
				try{
					rs.close();
				}catch(Exception e){}
			}
		}
	}
}
