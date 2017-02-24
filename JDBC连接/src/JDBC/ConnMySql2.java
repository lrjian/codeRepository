package JDBC;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnMySql2 {
	public ConnMySql2() {
	}
	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.jdbc.Driver");
		String user = "lrj";
		String password = "aa123";
		try(
				Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test",
						"root","123456"); //使用了自动资源回收机制
				PreparedStatement stmt = conn.prepareStatement("select count(1) from user_table where "
						+ "username="+"'"+user+"'"+" and password = " + "'"+password+"'");
				ResultSet rs = stmt.executeQuery();
				)
		{
			while(rs.next())
			{
				if(rs.getString(1).equals("1")){
					System.out.println("验证通过");
				}else{
					System.out.println("验证不通过");
				}
			}			
		}
	}
}
