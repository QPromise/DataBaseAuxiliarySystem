package cn.edu.ouc.test;

import java.util.ArrayList;
import java.util.List;

public class GyTest {
	public static void main(String[] args) {

		List<Double> x = new ArrayList<Double>();
		x.add(1.0);
		x.add(3.0);
		x.add(2.0);
		x.add(4.0);
		x.add(5.0);
		
		System.out.println(standardDiviation(x));
 		
	}

	public static double standardDiviation(List<Double> x) {
		int m = x.size();
		double sum = 0;
		for (int i = 0; i < m; i++) {// 求和
			sum +=x.get(i);
		}
		double dAve = sum / m;// 求平均值
		double dVar = 0;
		for (int i = 0; i < m; i++) {// 求方差
			dVar += (x.get(i) - dAve) * (x.get(i) - dAve);
		}
		return Math.sqrt(dVar / m);
	}
}
