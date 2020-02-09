package cn.edu.ouc.util;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 提供获取SqlSessionFactory对象或管理Sesison的操作
 * 
 * @author 高杨
 * @date 2017年11月28日 上午10:15:20
 */
public class MybatisUtil {

	private static SqlSessionFactory ssf;

	static {
		String resource = "mybatis-config.xml";
		Reader reader;
		try {
			reader = Resources.getResourceAsReader(resource);
			ssf = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取SqlSessionFactory
	 * 
	 * @author 高杨
	 * @date 2017年11月28日 上午10:16:04
	 * @return
	 */
	public static SqlSessionFactory getSqlSessionFactory() {

		return ssf;
	}

	/**
	 * 获取SqlSession
	 * 
	 * @author 高杨
	 * @date 2017年11月28日 上午10:26:53
	 * @return
	 */
	public static SqlSession getSqlSession() {
		return getSqlSessionFactory().openSession();
	}

	/**
	 * 获取SqlSession[自动提交]
	 * @author 高杨
	 * @date 2017年11月29日 下午9:05:50
	 * @param isAutoCommit
	 * @return
	 */
	public static SqlSession getSqlSession(boolean isAutoCommit) {
		return getSqlSessionFactory().openSession(isAutoCommit);
	}

	/**
	 * 关闭SqlSession
	 * 
	 * @author 高杨
	 * @date 2017年11月28日 上午10:16:22
	 * @param session
	 */
	public static void closeSession(SqlSession session) {

		if (null != session) {
			session.close();
		}
	}
}