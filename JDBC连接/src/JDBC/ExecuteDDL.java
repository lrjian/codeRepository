package JDBC;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ExecuteDDL {
	private String driver;
	private String url;
	private String user;
	private String pass;
	public void initParam(String paramFile) throws FileNotFoundException, IOException
	{
		//使用Properties类来加载属性文件
		Properties props = new Properties();
		props.load(new FileInputStream(paramFile));
		driver = props.getProperty("jdbc.driverClassName");
		url = props.getProperty("jdbc.url");
		user = props.getProperty("jdbc.username");
		pass = props.getProperty("jdbc.password");
	}
	public void createTable(String sql) throws SQLException, ClassNotFoundException
	{
		//加载驱动
		Class.forName(driver);
		try(
			//获取数据库连接
			Connection conn = DriverManager.getConnection(url,user,pass);
			//使用Connection来创建一个Statement对象
			Statement stmt = conn.createStatement())
		{
			//执行DDL语句，创建数据表
			stmt.executeUpdate(sql);
		}
	}
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException, SQLException {
		ExecuteDDL ed = new ExecuteDDL();
		ed.initParam("jdbc.properties");
		Map map = new HashMap<String,Object>();
		ed.createTable("create table jdbc_test "
				+ "(jdbc_id int auto_increment primary key,"
				+ "jdbc_name varchar(255),"
				+ "jdbc_desc text);");
		System.out.println("----建表成功----");
	}
	
}
