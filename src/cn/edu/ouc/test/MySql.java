package cn.edu.ouc.test;

import java.sql.*;

public class MySql {
	static final String SSL = "?characterEncoding=utf8&useSSL=true";
	// JDBC 驱动名及数据库 URL
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/oceandataevaluatetest" + SSL;

	// 数据库的用户名与密码，需要根据自己的设置

	static final String USER = "root";
	// static final String PASS = "1234";
	static final String PASS = "111111";

	/*
	 * 2018-01-28 Liu_q 输入选择的（地域，年份）String型， 输出相关指标数组double[151]
	 */
	public static double[] selectValue(String location, String year) {
		Connection conn = null;
		Statement stmt = null;
		double[] outputs = new double[151];
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			// System.out.println(" 实例化Statement对...");
			stmt = conn.createStatement();
			String sql;
			String sql1 = "SELECT fourth_index_data.`value` FROM fourth_index_data WHERE fourth_index_data.`year` = ";
			String sql2 = " AND fourth_index_data.location = '";
			String sql3 = "'";
			sql = sql1 + year + sql2 + location + sql3;
			ResultSet rs = stmt.executeQuery(sql);

			int count = 0;
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				double value = rs.getDouble("value");
				outputs[count] = value;
				count++;
				// 输出数据
				// System.out.println("！！！！！！！！！！...");
				// System.out.print("\n");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!value");
		return outputs;
	}

	/*
	 * 2018-01-28 Liu_q 输出指标秩和比权重数组double[151]
	 * 
	 */
	public static double[] selectWeight() {
		Connection conn = null;
		Statement stmt = null;
		double[] outputs = new double[151];
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			// System.out.println(" 实例化Statement对...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT fourth_rank_weight.weight_value FROM fourth_rank_weight";
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				double value = rs.getDouble("weight_value");
				outputs[count] = value;
				count++;
				// 输出数据

				// System.out.print(value);
				// System.out.print("\n");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!weight");
		return outputs;
	}

	/*
	 * 2018-03-08 Liu_q 输出地点名称数组String[]
	 * 
	 */
	public static String[] selectLoction() {
		Connection conn = null;
		Statement stmt = null;
		String[] outputs = new String[3];
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			// System.out.println(" 实例化Statement对...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT DISTINCT fourth_index_data.location FROM fourth_index_data";
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				String value = rs.getString("location");
				outputs[count] = value;
				count++;
				// 输出数据

				// System.out.print(value);
				// System.out.print("\n");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!location");
		return outputs;
	}

	/*
	 * 2018-03-08 Liu_q 输入location 输出year数组double[]
	 * 
	 */
	public static String[] selectYear(String location) {
		Connection conn = null;
		Statement stmt = null;
		String[] outputs = new String[3];
		try {
			// 注册 JDBC 驱动
			Class.forName(JDBC_DRIVER);

			// 打开链接
			System.out.println("连接数据库...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// 执行查询
			// System.out.println(" 实例化Statement对...");
			stmt = conn.createStatement();
			String sql;

			// String sql2="SELECT DISTINCT fourth_index_data.year FROM
			// fourth_index_data WHERE fourth_index_data.location = 'A'";
			String sql1 = "SELECT DISTINCT fourth_index_data.year FROM fourth_index_data WHERE fourth_index_data.location = '";
			String sql3 = "'";

			sql = sql1 + location + sql3;
			ResultSet rs = stmt.executeQuery(sql);
			int count = 0;
			// 展开结果集数据库
			while (rs.next()) {
				// 通过字段检索
				String value = rs.getString("year");
				outputs[count] = value;
				count++;
				// 输出数据

				// System.out.print(value);
				// System.out.print("\n");
			}
			// 完成后关闭
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// 处理 JDBC 错误
			se.printStackTrace();
		} catch (Exception e) {
			// 处理 Class.forName 错误
			e.printStackTrace();
		} finally {
			// 关闭资源
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // 什么都不做
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		System.out.println("Goodbye!year");
		return outputs;
	}

	/*
	 * 数组转置
	 */
	public static double[][] tfunction(double[][] test) {
		int m = test.length;
		int n = test[0].length;
		double t[][] = new double[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				t[i][j] = test[j][i];
			}
		}
		return t;
	}
}
