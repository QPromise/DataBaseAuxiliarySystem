package cn.edu.ouc.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.math.*;

import org.apache.commons.math3.stat.ranking.NaNStrategy;
import org.apache.commons.math3.stat.ranking.NaturalRanking;
import org.apache.commons.math3.stat.ranking.TiesStrategy;
import org.apache.commons.math3.stat.descriptive.rank.*;
import org.apache.commons.math3.stat.inference.*;
import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.regression.MultipleLinearRegression;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.stat.regression.RegressionResults;
import org.apache.commons.math3.stat.regression.OLSMultipleLinearRegression;

public class Evaluation_Method {

	/*
	 * 2018-03-07 liu_Q 显示matlab脚本中 Kendall一致性检验结果
	 * 函数要求输入格式为（评价方法数量>=2，评价样本个数>=2，评价排序总和）
	 * matlab函数要求输入格式为（评价方法数量，评价样本个数，卡方分布表，评价排序总和）
	 * 卡方分布表，自由度为n-1,置信水平0.95；n=评价样本个数，卡方分布表的相关值以数组的形式存储在函数中
	 * 输出一个数值，若为1，则证明排序结果合理；若不为1，则选择n-1个方法再次检验，直到成功。
	 * 
	 */

	private static int Kendall_java(int methodSum, int sampleSum, double[] Array) {
		int outputs = 0;
		// double chiNum=0;//卡方分布表的对应值.
		// 卡方分布表，[n]对应sampleSum-1需要的值
		double chiNumList[] = { 0, 3.84, 5.99, 7.81, 9.49, 11.07, 12.59, 14.07, 15.51, 16.92, 18.31, 19.68, 21.03,
				22.36, 23.68, 25, 26.3, 27.59, 28.87, 30.14, 31.41 };

		double[] mataArray = Array;
		double[] V1 = new double[sampleSum];
		double V = 0;
		double W = 0;
		double T = 0;
		for (int i = 0; i < sampleSum; i++) {
			V1[i] = mataArray[i] * mataArray[i];
		}
		for (int i = 0; i < sampleSum; i++) {
			V += V1[i];
		}
		W = (12 * V - 3 * (methodSum * methodSum) * sampleSum * ((sampleSum + 1) * (sampleSum + 1)))
				/ ((methodSum * methodSum) * ((sampleSum) * (sampleSum) * (sampleSum) - sampleSum));
		T = methodSum * (sampleSum - 1) * W;
		// result=method.kendall(1,methodSum,sampleSum,chiNumList[sampleSum-1],inputs);
		if (T > chiNumList[sampleSum - 1]) {
			outputs = 1;
		}
		System.out.println("kendall  结果	" + outputs);

		return outputs;

	}

	/*
	 * 2018-01-23 liu_Q 显示matlab脚本中 评价方法2———熵值法结果
	 * 评价方法2要求输入的为同一省份不同年份的指标值，而且指标值不能有0值（否则matlab显示结果为NaN） 输入 一个
	 * double[n][m]数组，n为指标条目，m为年份 输出一个double[1][m],结果分别是每一年份的评价值
	 * 
	 */

	private static double[] Method1(double[][] Array) {

		double[] outputs = new double[Array[0].length - 1];// 存放结果数组
		/*
		 * for(int j = 0;j<3;j++){ for(int i=0;i<Array[0].length-1;i++){
		 * outputs[j]=+Array[j][i]*Array[3][i]; } } System.out.println("方法一 结果"
		 * ); for(int i =0 ;i<outputs.length;i++){ System.out.print(outputs[i]+
		 * "	"); } System.out.println();
		 */
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}

		// 一级指标权重5个
		double weight1[] = { 0.2, 0.15, 0.31, 0.07, 0.27 };

		// 二级指标权重17个
		double weight2[] = { 0.009785794, 0.029843899, 0.044997955, 0.05214456, 0.099988828, 0.065587185, 0.036185083,
				0.052601387, 0.065688786, 0.017759363, 0.091242168, 0.105173465, 0.065510279, 0.003162303, 0.08933296,
				0.067112493, 0.1039 };

		// 三级指标权重51个
		double weight3[] = { 0.017151843, 0.01326187, 0.012254475, 0.014238057, 0.015336342, 0.023069483, 0.004670118,
				0.001487023, 0.01788532, 0.033924827, 0.036435224, 0.010313517, 0.006251713, 0.034041355, 0.009277332,
				0.025187419, 0.037708609, 0.025932317, 0.03394486, 0.000387165, 0.005343386, 0.031931477, 0.016776476,
				0.034722547, 0.02866142, 0.02680562, 0.013498367, 0.006475355, 0.00606889, 0.007453536, 0.01647561,
				0.033383053, 0.019119749, 0.031821456, 0.017970023, 0.017836821, 0.017576866, 0.016076543, 0.035162783,
				0.000217774, 0.011598821, 0.001917337, 0.027034038, 0.02535415, 0.038336523, 0.021554256, 0.015602867,
				0.007752752, 0.02438284, 0.028601149, 0.0417 };

		// 四级指标权重151个

		double weight4[] = { 0.012520429, 0.005962736, 0.004378347, 0.000102425, 0.002189087, 0.010931803, 0.00849289,
				0.004254032, 0.000348078, 0.004691847, 0.009819276, 0.003942362, 0.002392146, 0.00548053, 0.011448888,
				0.008249206, 0.00072867, 0.005334026, 0.003985874, 0.00201062, 0.00408102, 4.30E-05, 0.005773797,
				0.008928629, 0.010862452, 0.009977158, 0.002146833, 0.007286969, 0.006931237, 0.01213549, 0.005833172,
				0.000591391, 0.012732502, 0.000177704, 0.00728671, 0.012332755, 0.011861637, 0.008157464, 0.009239004,
				0.012396054, 0.005967511, 0.002332068, 0.008141301, 0.006842618, 0.004811308, 0.010207688, 0.010933256,
				0.004202749, 0.00786693, 0.010319998, 0.000443419, 0.011312103, 0.009010465, 0.010584428, 0.012113601,
				0.005123051, 0.006230148, 0.00116237, 0.012211391, 0.008955934, 0.008461627, 0.013145983, 0.00659396,
				0.005676681, 0.010945025, 0.001540388, 0.007902633, 0.002920164, 0.005262535, 0.003975211, 0.00836278,
				0.01247378, 7.75E-05, 0.010779105, 0.009119686, 0.012948999, 0.000920563, 0.000593403, 0.009767389,
				0.010812406, 0.003443096, 0.009292968, 0.003913479, 0.001086241, 0.011555064, 0.001022362, 0.009780993,
				0.001650926, 0.005857011, 0.007820777, 0.008399794, 0.0070321, 0.002755058, 0.00889321, 0.003193192,
				0.009510988, 0.005279525, 0.005201275, 0.00543183, 0.001976617, 0.011592674, 0.000287176, 0.012194591,
				0.008350549, 0.000883288, 0.008351278, 0.009949422, 0.010159549, 0.010666076, 0.007172007, 0.00640311,
				0.005824094, 0.008269781, 0.001026738, 0.00718163, 0.002760081, 0.003563982, 0.003850195, 0.010853312,
				0.005431776, 0.006955814, 0.012146746, 0.00500459, 0.008433464, 0.002293353, 0.010372562, 0.00482633,
				0.010255634, 0.004516331, 0.010219123, 0.001951385, 0.001956798, 0.008435481, 0.001232524, 0.012260007,
				0.006403793, 0.002154836, 0.003647715, 0.001728853, 0.000306505, 0.003053125, 0.011157413, 0.009587901,
				0.009169879, 0.005057003, 0.006333921, 0.012765442, 0.010342676, 0.005408515, 0.01263722, 0.0044 };
		for (int num = 0; num < Array_del[0].length; num++) {
			double score_final = 0;// 综合指标结果
			double score1[] = new double[5];
			double score2[] = new double[17];
			double score3[] = new double[51];

			// 三级指数计算
			for (int i = 0; i < 3; i++) {
				score3[0] += Array_del[i][num] * weight4[i];
			}
			for (int i = 3; i < 6; i++) {
				score3[1] += Array_del[i][num] * weight4[i];
			}
			for (int i = 6; i < 9; i++) {
				score3[2] += Array_del[i][num] * weight4[i];
			}
			for (int i = 9; i < 12; i++) {
				score3[3] += Array_del[i][num] * weight4[i];
			}
			for (int i = 12; i < 15; i++) {
				score3[4] += Array_del[i][num] * weight4[i];
			}
			for (int i = 15; i < 18; i++) {
				score3[5] += Array_del[i][num] * weight4[i];
			}
			for (int i = 18; i < 25; i++) {
				score3[6] += Array_del[i][num] * weight4[i];
			}
			for (int i = 25; i < 30; i++) {
				score3[7] += Array_del[i][num] * weight4[i];
			}
			for (int i = 30; i < 33; i++) {
				score3[8] += Array_del[i][num] * weight4[i];
			}
			for (int i = 33; i < 37; i++) {
				score3[9] += Array_del[i][num] * weight4[i];
			}
			for (int i = 37; i < 38; i++) {
				score3[10] += Array_del[i][num] * weight4[i];
			}
			for (int i = 38; i < 41; i++) {
				score3[11] += Array_del[i][num] * weight4[i];
			}
			for (int i = 41; i < 43; i++) {
				score3[12] += Array_del[i][num] * weight4[i];
			}
			for (int i = 43; i < 47; i++) {
				score3[13] += Array_del[i][num] * weight4[i];
			}
			for (int i = 47; i < 54; i++) {
				score3[14] += Array_del[i][num] * weight4[i];
			}
			for (int i = 54; i < 59; i++) {
				score3[15] += Array_del[i][num] * weight4[i];
			}
			for (int i = 59; i < 63; i++) {
				score3[16] += Array_del[i][num] * weight4[i];
			}
			for (int i = 63; i < 65; i++) {
				score3[17] += Array_del[i][num] * weight4[i];
			}
			for (int i = 65; i < 69; i++) {
				score3[18] += Array_del[i][num] * weight4[i];
			}
			for (int i = 69; i < 72; i++) {
				score3[19] += Array_del[i][num] * weight4[i];
			}
			for (int i = 72; i < 76; i++) {
				score3[20] += Array_del[i][num] * weight4[i];
			}
			for (int i = 76; i < 81; i++) {
				score3[21] += Array_del[i][num] * weight4[i];
			}
			for (int i = 81; i < 85; i++) {
				score3[22] += Array_del[i][num] * weight4[i];
			}
			for (int i = 85; i < 87; i++) {
				score3[23] += Array_del[i][num] * weight4[i];
			}
			for (int i = 87; i < 90; i++) {
				score3[24] += Array_del[i][num] * weight4[i];
			}
			for (int i = 90; i < 93; i++) {
				score3[25] += Array_del[i][num] * weight4[i];
			}
			for (int i = 93; i < 96; i++) {
				score3[26] += Array_del[i][num] * weight4[i];
			}
			for (int i = 96; i < 98; i++) {
				score3[27] += Array_del[i][num] * weight4[i];
			}
			for (int i = 98; i < 100; i++) {
				score3[28] += Array_del[i][num] * weight4[i];
			}
			for (int i = 100; i < 103; i++) {
				score3[29] += Array_del[i][num] * weight4[i];
			}
			for (int i = 103; i < 105; i++) {
				score3[30] += Array_del[i][num] * weight4[i];
			}
			for (int i = 105; i < 107; i++) {
				score3[31] += Array_del[i][num] * weight4[i];
			}
			for (int i = 107; i < 110; i++) {
				score3[32] += Array_del[i][num] * weight4[i];
			}
			for (int i = 110; i < 112; i++) {
				score3[33] += Array_del[i][num] * weight4[i];
			}
			for (int i = 112; i < 114; i++) {
				score3[34] += Array_del[i][num] * weight4[i];
			}
			for (int i = 114; i < 117; i++) {
				score3[35] += Array_del[i][num] * weight4[i];
			}
			for (int i = 117; i < 120; i++) {
				score3[36] += Array_del[i][num] * weight4[i];
			}
			for (int i = 120; i < 122; i++) {
				score3[37] += Array_del[i][num] * weight4[i];
			}
			for (int i = 122; i < 124; i++) {
				score3[38] += Array_del[i][num] * weight4[i];
			}
			for (int i = 124; i < 127; i++) {
				score3[39] += Array_del[i][num] * weight4[i];
			}
			for (int i = 127; i < 130; i++) {
				score3[40] += Array_del[i][num] * weight4[i];
			}
			for (int i = 130; i < 132; i++) {
				score3[41] += Array_del[i][num] * weight4[i];
			}
			for (int i = 132; i < 134; i++) {
				score3[42] += Array_del[i][num] * weight4[i];
			}
			for (int i = 134; i < 137; i++) {
				score3[43] += Array_del[i][num] * weight4[i];
			}
			for (int i = 137; i < 139; i++) {
				score3[44] += Array_del[i][num] * weight4[i];
			}
			for (int i = 139; i < 141; i++) {
				score3[45] += Array_del[i][num] * weight4[i];
			}
			for (int i = 141; i < 142; i++) {
				score3[46] += Array_del[i][num] * weight4[i];
			}
			for (int i = 142; i < 145; i++) {
				score3[47] += Array_del[i][num] * weight4[i];
			}
			for (int i = 145; i < 147; i++) {
				score3[48] += Array_del[i][num] * weight4[i];
			}
			for (int i = 147; i < 149; i++) {
				score3[49] += Array_del[i][num] * weight4[i];
			}
			for (int i = 149; i < 151; i++) {
				score3[50] += Array_del[i][num] * weight4[i];
			}
			// 二级指数计算
			int i = 0;
			for (; i < 4; i++) {
				score2[0] += score3[i] * weight3[i];
			}
			for (; i < 6; i++) {
				score2[1] += score3[i] * weight3[i];
			}
			for (; i < 10; i++) {
				score2[2] += score3[i] * weight3[i];
			}
			for (; i < 13; i++) {
				score2[3] += score3[i] * weight3[i];
			}
			for (; i < 17; i++) {
				score2[4] += score3[i] * weight3[i];
			}
			for (; i < 20; i++) {
				score2[5] += score3[i] * weight3[i];
			}
			for (; i < 24; i++) {
				score2[6] += score3[i] * weight3[i];
			}
			for (; i < 27; i++) {
				score2[7] += score3[i] * weight3[i];
			}
			for (; i < 30; i++) {
				score2[8] += score3[i] * weight3[i];
			}
			for (; i < 32; i++) {
				score2[9] += score3[i] * weight3[i];
			}
			for (; i < 36; i++) {
				score2[10] += score3[i] * weight3[i];
			}
			for (; i < 39; i++) {
				score2[11] += score3[i] * weight3[i];
			}
			for (; i < 41; i++) {
				score2[12] += score3[i] * weight3[i];
			}
			for (; i < 43; i++) {
				score2[13] += score3[i] * weight3[i];
			}
			for (; i < 47; i++) {
				score2[14] += score3[i] * weight3[i];
			}
			for (; i < 49; i++) {
				score2[15] += score3[i] * weight3[i];
			}
			for (; i < 51; i++) {
				score2[16] += score3[i] * weight3[i];
			}
			// 计算一级指数
			i = 0;
			for (; i < 4; i++) {
				score1[0] += score2[i] * weight2[i];
			}
			for (; i < 8; i++) {
				score1[1] += score2[i] * weight2[i];
			}
			for (; i < 12; i++) {
				score1[2] += score2[i] * weight2[i];
			}
			for (; i < 14; i++) {
				score1[3] += score2[i] * weight2[i];
			}
			for (; i < 17; i++) {
				score1[4] += score2[i] * weight2[i];
			}
			// 计算综合指数
			i = 0;
			for (; i < 5; i++) {
				score_final += score1[i] * weight1[i];
			}
			outputs[num] = score_final;
		}
		System.out.println("方法1 结果");
		for (int i = 0; i < locSum; i++) {
			System.out.print(outputs[i] + "	");
		}
		System.out.println();
		return outputs;
	}

	/*
	 * 2018-03-05 liu_Q 显示matlab脚本中 评价方法2———熵值法结果
	 * 评价方法2要求输入的为同一省份不同年份的指标值，而且指标值不能有0值（否则matlab显示结果为NaN） 输入 一个
	 * double[n][m]数组，n为指标条目，m为年份 输出一个double[1][m],结果分别是每一年份的评价值
	 * 
	 */
	private static double[] Method2_java(double[][] Array) {

		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}
		// System.out.println(indexSum);
		// System.out.println(locSum);

		double[][] mataArray = tfunction(Array_del);// 矩阵转置
		/*
		 * for(int i = 0;i<locSum;i++){ for(int j=0;j<indexSum;j++){
		 * System.out.print(mataArray[i][j]+"	"); } System.out.println(); }
		 */
		// int yearSum=mataArray.length;//行数
		// int methodSum=mataArray[0].length;//列数
		// int yearSum=indexSum;//行数
		// int methodSum=locSum;//列数
		double[][] x_norm = new double[locSum][indexSum];// 计算数据标准化用
		double[][] x_norm_ln = new double[locSum][indexSum];// 计算指标信息熵
		double[][] score = new double[locSum][indexSum];
		double[][] score1 = new double[indexSum][locSum];
		double[] sum_x = new double[indexSum];
		double[] sum_x_ln = new double[indexSum];
		double[] E = new double[indexSum];
		double[] D = new double[indexSum];
		double sum_D = 0;
		double[] weight = new double[indexSum];

		double[] outputs = new double[locSum];// 存放结果数组
		// 求列和
		for (int j = 0; j < indexSum; j++) {
			double temp = 0;
			for (int i = 0; i < locSum; i++) {
				temp += mataArray[i][j];
			}
			sum_x[j] = temp;
			// System.out.println(temp);
		}

		// 均值法数据标准化处理
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				x_norm[i][j] = mataArray[i][j] / sum_x[j];
				// System.out.print(x_norm[i][j]);
			}
			// System.out.println();
		}
		// 求log
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				x_norm_ln[i][j] = x_norm[i][j] * Math.log(x_norm[i][j]);
				// System.out.print(x_norm_ln[i][j]);
			}
			// System.out.println();
		}
		// 求列和
		for (int j = 0; j < indexSum; j++) {
			double temp = 0;
			for (int i = 0; i < locSum; i++) {
				temp += x_norm_ln[i][j];
			}
			sum_x_ln[j] = temp;
			// System.out.println(temp);
		}
		double k = 1 / Math.log(locSum);
		k = -k;
		// System.out.println(k);

		for (int i = 0; i < indexSum; i++) {
			E[i] = k * sum_x_ln[i];
		}

		for (int i = 0; i < indexSum; i++) {
			D[i] = 1 - E[i];
		}
		// 计算指标权重
		for (int i = 0; i < indexSum; i++) {
			sum_D += D[i];
		}
		for (int i = 0; i < indexSum; i++) {
			weight[i] = D[i] / sum_D;
		}

		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				score[i][j] = x_norm[i][j] * weight[j];
			}
		}
		score1 = tfunction(score);// 矩阵转置
		// 求列和
		System.out.println("方法二结果：");
		for (int j = 0; j < locSum; j++) {
			double temp = 0;
			for (int i = 0; i < indexSum; i++) {
				temp += score1[i][j];
			}
			outputs[j] = temp;
			System.out.print(temp + "		");

		}
		System.out.println();
		return outputs;
	}
	/*
	 * 2018-03-06 liu_Q 显示matlab脚本中 评价方法3——灰色关联法结果 评价方法3要求输入的为同一省份不同年份的指标值 输入 一个
	 * double[n][m]数组，n为指标条目，m为年份 输出一个double[1][m],结果分别是每一年份的评价值
	 * 
	 */

	private static double[] Method3_java(double[][] Array) {
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}
		// System.out.println(indexSum);
		// System.out.println(locSum);

		double[][] x_norm = new double[locSum][indexSum];// 计算数据标准化用
		double[][] delta_x_norm = new double[locSum][indexSum];// 差分序列
		double[][] denominator = new double[locSum][indexSum];//
		double[][] Numertor = new double[locSum][indexSum];//
		double[][] Gama = new double[locSum][indexSum];//
		double[][] Gama_tf = new double[indexSum][locSum];// gama转置
		// double [][] Gama_tf = new double[indexSum][locSum];//gama转置
		double[] x_max = new double[indexSum];// 提取最优点
		double[] sum_x = new double[indexSum];// 计算数据标准化用
		double[] sum_gama = new double[locSum];// 计算数据标准化用
		double[] series_min = new double[locSum];
		double[] series_max = new double[locSum];
		double[] E_Gama = new double[locSum];//
		double min_min = 0;
		double max_max = 0;

		double[][] mataArray = tfunction(Array_del);// 矩阵转置
		// 求列和
		for (int j = 0; j < indexSum; j++) {
			double temp = 0;
			for (int i = 0; i < locSum; i++) {
				temp += mataArray[i][j];
			}
			sum_x[j] = temp;
			// System.out.println(temp);
		}

		// 均值法数据标准化处理
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				x_norm[i][j] = mataArray[i][j] / sum_x[j];
				// System.out.print("!!!"+(j+1)+"!!!"+x_norm[i][j]);
			}
			// System.out.println();
		}
		// 提取最优点，构造单个序列,列遍历，求每一列最大值
		for (int i = 0; i < indexSum; i++) {
			double maxtemp = 0;
			maxtemp = x_norm[0][i];
			for (int j = 1; j < locSum; j++) {
				// maxtemp=x_norm[0][i];
				maxtemp = Math.max(maxtemp, x_norm[j][i]);
			}
			x_max[i] = maxtemp;
			// System.out.println("!!!"+(i+1)+"!!!"+maxtemp);

		}
		// 构建差分序列
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				delta_x_norm[i][j] = Math.abs(x_norm[i][j] - x_max[j]);
				// System.out.print(delta_x_norm[i][j]);
			}
			// System.out.println();
		}
		// 求两级最小差和最大差
		for (int i = 0; i < locSum; i++) {
			double min = 0;
			min = delta_x_norm[i][0];
			for (int j = 1; j < indexSum; j++) {
				min = Math.min(min, delta_x_norm[i][j]);
			}
			series_min[i] = min;
			// System.out.println(min);
		}
		for (int i = 0; i < locSum; i++) {
			double max = 0;
			max = delta_x_norm[i][0];
			for (int j = 1; j < indexSum; j++) {

				max = Math.max(max, delta_x_norm[i][j]);
			}
			series_max[i] = max;
			// System.out.println(max);
		}
		max_max = series_max[0];
		for (int j = 1; j < locSum; j++) {
			max_max = Math.max(max_max, series_max[j]);
		}
		min_min = series_min[0];
		for (int j = 1; j < locSum; j++) {
			min_min = Math.min(min_min, series_min[j]);
		}
		// System.out.println(max_max);
		// System.out.println(min_min);

		// 求解参考列与比较列对应的相对关联度
		double k = 0.5;
		double numertor = min_min + k * max_max;
		// System.out.println(k);
		// System.out.println(numertor);
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				denominator[i][j] = delta_x_norm[i][j] + k * max_max;
				// System.out.print("!!!!!!"+denominator[i][j]);
			}
			// System.out.println();
		}
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				Numertor[i][j] = numertor;
				// System.out.print("!!!!!!"+Numertor[i][j]);
			}
			// System.out.println();
		}
		for (int i = 0; i < locSum; i++) {
			for (int j = 0; j < indexSum; j++) {
				Gama[i][j] = Numertor[i][j] / denominator[i][j];
				// System.out.print("!!!!!!"+Gama[i][j]);
			}
			// System.out.println();
		}
		Gama_tf = tfunction(Gama);
		// 列平均值
		for (int j = 0; j < locSum; j++) {
			double temp = 0;
			for (int i = 0; i < indexSum; i++) {
				temp += Gama_tf[i][j];
			}
			sum_gama[j] = temp;
			// System.out.println(temp);
		}
		System.out.println("方法三结果：");
		for (int i = 0; i < locSum; i++) {
			E_Gama[i] = sum_gama[i] / indexSum;
			System.out.print(E_Gama[i] + "	");
		}
		double[] outputs = E_Gama;// 存放结果数组
		System.out.println();
		return outputs;
	}

	/*
	 * 2018-03-06 liu_Q 显示matlab脚本中 评价方法4——全排列多边形图示法结果 评价方法4要求输入的为同一省份不同年份的指标值
	 * 输入 一个 double[n][m]数组，n为指标条目，m为年份 输出一个double[1][m],结果分别是每一年份的评价值
	 * 
	 */
	private static double[] Method4_java(double[][] Array) {
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}
		// System.out.println(indexSum);
		// System.out.println(locSum);

		double[][] mataArray = Array_del;
		double[][] mataArray_tf = tfunction(Array_del);

		double[][] bzh = new double[indexSum][locSum];// 计算数据标准化用

		double Array_min[] = new double[indexSum];// 最小值
		double Array_max[] = new double[indexSum];// 最大值
		double Array_sum[] = new double[indexSum];// 和值
		double Array_mean[] = new double[indexSum];// 临界值
		double s[] = new double[locSum];// 综合指数值

		// 求列最大值
		for (int i = 0; i < indexSum; i++) {
			double max = 0;
			max = mataArray_tf[0][i];
			for (int j = 1; j < locSum; j++) {

				max = Math.max(max, mataArray_tf[j][i]);
			}
			Array_max[i] = max;
			// System.out.println(max);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		// 求列最小值
		for (int i = 0; i < indexSum; i++) {
			double min = 0;
			min = mataArray_tf[0][i];
			for (int j = 1; j < locSum; j++) {

				min = Math.min(min, mataArray_tf[j][i]);
			}
			Array_min[i] = min;
			// System.out.println(min);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		// 求平均值
		for (int j = 0; j < indexSum; j++) {
			double temp = 0;
			for (int i = 0; i < locSum; i++) {
				temp += mataArray_tf[i][j];
			}
			Array_sum[j] = temp;
			// System.out.println(temp);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < indexSum; i++) {
			Array_mean[i] = Array_sum[i] / locSum;
			// System.out.println(Array_mean[i]);
		}
		// 数据标准化
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				bzh[i][j] = ((Array_max[i] - Array_min[i]) * (mataArray[i][j] - Array_mean[i]))
						/ ((Array_min[i] + Array_max[i] - 2 * Array_mean[i]) * mataArray[i][j]
								+ Array_min[i] * Array_mean[i] + Array_max[i] * Array_mean[i]
								- 2 * Array_max[i] * Array_min[i]);
				// System.out.print(bzh[i][j]+" ");
			}
			// System.out.println();
		}
		System.out.println("方法四结果：");
		// 计算综合指数
		for (int i = 0; i < locSum; i++) {
			double sum = 0;
			for (int j = 0; j < indexSum; j++) {
				for (int k = 0; k < indexSum; k++) {
					if (j != k) {
						double pro = bzh[j][i] * bzh[k][i];
						sum += pro;
					}
				}
			}
			s[i] = sum / (2 * indexSum * (indexSum - 1));
			System.out.print(s[i] + "	  ");
		}

		double[] outputs = s;// 存放结果数组
		System.out.println();

		return outputs;
	}

	/*
	 * 2018-03-06 liu_Q 显示matlab脚本中 评价方法5——TOPSIS法结果 评价方法5要求输入的为同一省份不同年份的指标值 输入
	 * 一个 double[n][m]数组，n为指标条目，m为年份 输出一个double[1][m],结果分别是每一年份的评价值
	 * 
	 */
	private static double[] Method5_java(double[][] Array) {
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}

		double[][] mataArray = Array_del;
		double[][] mataArray_tf = tfunction(mataArray);

		int m = locSum;
		int n = indexSum;
		double[][] Z = new double[m][n];// 理想解集
		double[][] B1 = new double[m][n];
		double[][] B2 = new double[m][n];

		double[] x2_sum = new double[n];// 列平方和
		double Z1[] = new double[n];// 理想解
		double Z2[] = new double[n];// 负理想解
		double[] D1 = new double[m];// 可行解到理想解的距离
		double[] D2 = new double[m];// 可行解到负理想解的距离
		double[] d1_sum = new double[m];
		double[] d2_sum = new double[m];
		double[] C = new double[m];// 结果
		// 求列平方和
		for (int i = 0; i < n; i++) {
			double temp = 0;
			for (int j = 0; j < m; j++) {
				temp += mataArray_tf[j][i] * mataArray_tf[j][i];
			}
			x2_sum[i] = temp;
			// System.out.println(temp);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		// 求理想解集
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				Z[i][j] = mataArray_tf[i][j] / Math.sqrt(x2_sum[j]);
				// System.out.print(Z[i][j]+" ");
			}
			// System.out.println();
		}

		// 求列最大值 ，理想解
		for (int i = 0; i < n; i++) {
			double max = 0;
			max = Z[0][i];
			for (int j = 1; j < m; j++) {

				max = Math.max(max, Z[j][i]);
			}
			Z1[i] = max;
			// System.out.println(max);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		// 求列最小值，负理想解
		for (int i = 0; i < n; i++) {
			double min = 0;
			min = Z[0][i];
			for (int j = 1; j < m; j++) {

				min = Math.min(min, Z[j][i]);
			}
			Z2[i] = min;
			// System.out.println(min);
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < m; i++) {
			B1[i] = Z1;
			B2[i] = Z2;
		}
		for (int i = 0; i < m; i++) {
			double temp = 0;
			for (int j = 0; j < n; j++) {
				temp += (Z[i][j] - B1[i][j]) * (Z[i][j] - B1[i][j]);
			}
			d1_sum[i] = temp;
			// System.out.println(temp+" ");
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < m; i++) {
			double temp = 0;
			for (int j = 0; j < n; j++) {
				temp += (Z[i][j] - B2[i][j]) * (Z[i][j] - B2[i][j]);
			}
			d2_sum[i] = temp;
			// System.out.println(temp+" ");
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < m; i++) {
			D1[i] = Math.sqrt(d1_sum[i]);
			// System.out.print(D1[i]+" ");
		}
		// System.out.println("!!!!!!!!!!!!!!!!!!!!!");
		for (int i = 0; i < m; i++) {
			D2[i] = Math.sqrt(d2_sum[i]);
			// System.out.print(D2[i]+" ");
		}

		System.out.println("方法五的结果：");
		for (int i = 0; i < m; i++) {
			C[i] = D2[i] / (D1[i] + D2[i]);
			System.out.print(C[i] + "		");
		}
		double[] outputs = C;// 存放结果数组
		System.out.println();
		return outputs;
	}

	/*
	 * 2018-03-07 liu_Q 显示matlab脚本中 评价方法6——秩和比法结果 评价方法6要求输入的为同一省份不同年份的指标值 输入 一个
	 * double[n][m]数组，n为指标条目，m-1为年份，m列为第四指标对应权重，所以m>=3
	 * 输出一个double[1][m],结果分别是每一省份的评价值
	 * 
	 */
	private static double[] Method6_java(double[][] Array) {
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数

		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}

		// double [][] mataArray=Array_del;
		// double [][] mataArray_tf=tfunction(Array_del);//等于matlab中的a
		double[][] ra_tf = (double[][]) Array_del.clone();

		int n = locSum;
		int m = indexSum;
		double[][] W = new double[n][m];
		double RSR[][] = new double[n][1];
		double WRSR[][] = new double[n][1];
		double X[][] = new double[n][2];
		double weight[] = new double[m];
		double p[] = new double[n];
		double probit[] = new double[n];
		double WRSR_tf[] = new double[n];
		double WRSRfit[] = new double[n];
		NaturalRanking rank = new NaturalRanking();// 秩和排序用
		NormalDistribution dis = new NormalDistribution(0, 1);// 正态分布生成，期望为0，标准差为1
		SimpleRegression reg = new SimpleRegression();
		// OLSMultipleLinearRegression OLS=new OLSMultipleLinearRegression();

		for (int j = 0; j < indexSum; j++) {
			weight[j] = Array[j][locSum];// 等于matlab中的w
			// System.out.print(weight[j]+" ");
		}
		for (int i = 0; i < indexSum; i++) {
			double temp[] = new double[locSum];
			temp = rank.rank(ra_tf[i]);
			ra_tf[i] = temp;
		}

		double[][] ra = tfunction(ra_tf);

		for (int i = 0; i < n; i++) {
			double temp = 0;
			for (int j = 0; j < m; j++) {
				temp += ra[i][j];
			}
			RSR[i][0] = temp / m / n;
			// System.out.println(RSR[i][0]);
		}
		for (int i = 0; i < n; i++) {
			W[i] = weight;
		}
		for (int i = 0; i < n; i++) {
			double temp = 0;
			for (int j = 0; j < m; j++) {
				temp += ra[i][j];
			}
			WRSR[i][0] = temp / n;
			// System.out.println(WRSR[i][0]);
		}
		// 计算累计频率
		for (int i = 0; i < n; i++) {
			double di = (double) i;
			double dn = (double) n;
			p[i] = (di + 1) / dn;
			p[p.length - 1] = 1 - 1 / (4 * dn);
		}

		// 计算正态分布分位数
		for (int i = 0; i < n; i++) {

			probit[i] = dis.inverseCumulativeProbability(p[i]) + 5;
			// System.out.println(probit[i]);
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < X[0].length; j++) {
				X[i][j] = 1;
			}
		}
		for (int i = 0; i < n; i++) {
			X[i][1] = probit[i];
		}
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < X[0].length; j++) {
				// System.out.print(X[i][j]+" ");
			}
			// System.out.println();
		}
		// 一元线性规划
		for (int i = 0; i < n; i++) {
			WRSR_tf[i] = WRSR[i][0];
			// System.out.println(WRSR_tf[i]);
		}
		// double [][]X_tf=tfunction(X);
		double ab[] = null;// double ab1=0;
		// reg.addObservation(X[1], WRSR_tf[1]);
		// reg.addObservations(X, WRSR_tf);
		// reg.addObservation(X[0], WRSR_tf[0]);
		// reg.addObservation(X[1], WRSR_tf[1]);
		// reg.addObservation(X[2], WRSR_tf[2]);
		// reg.addData(X[0][1], WRSR_tf[0]);
		// reg.addData(X[1][1], WRSR_tf[1]);
		// reg.addData(X[2][1], WRSR_tf[2]);
		// matlab中regress函数要求首列应保留一列1，但是math包中不需要，所以X[][]可以使用probit[]替代
		for (int i = 0; i < n; i++) {
			reg.addData(probit[i], WRSR_tf[i]);
		}
		RegressionResults regr = reg.regress();
		// ab1=reg.getSlope();//斜率
		// System.out.println(ab1);
		ab = regr.getParameterEstimates();
		/*
		 * for(int i=0;i<n;i++){ for(int j=0;j<X_n[0].length;j++){ X_n[i][j]=0;
		 * } } for(int i=0;i<n;i++){ for(int j=0;j<X[0].length;j++){
		 * X_n[i][j]=X[i][j]; } } OLS.newSampleData(WRSR_tf,X);
		 * ab3=OLS.estimateRegressionParameters(); for(int
		 * i=0;i<ab3.length;i++){ System.out.println(ab3[i]); }
		 */
		System.out.println("方法六的结果：");
		for (int i = 0; i < n; i++) {
			WRSRfit[i] = ab[0] + ab[1] * probit[i];
			System.out.print(WRSRfit[i] + "	");
		}
		System.out.println();
		double[] outputs = WRSRfit;// 存放结果数组
		return outputs;
	}

	/*
	 * 数组转置
	 */
	private static double[][] tfunction(double[][] test) {
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

	/*
	 * 2018-03-07 LIU_q 数组排序输出原数组对应值的排序结果 rank
	 */
	public static double[] rank(double[] Array) {
		double[] outputs = null;
		double[] a = Array;
		double[] b = (double[]) a.clone();
		NaturalRanking rank = new NaturalRanking(NaNStrategy.MAXIMAL, TiesStrategy.MINIMUM);
		b = rank.rank(Array);
		outputs = b;
		return outputs;
	}

	/*
	 * 2018-01-27 Liu_Q 利用hashmap做选择一致性检验样本的功能
	 * 输入同一年份不同省份的指标Array[m][n],m为省份,n指标m-1为省份，第m列为第四指标对应权重，所以m>=3
	 * 输出数组格式为[m][n],[m][1---n]为评价方法得到的排序结果，[0--m][n]为同一年不同省份。
	 * 其中[m][0]为评价方法表示，默认为方法1到方法6
	 */
	private static double[][] MapSort(double[][] Array, String[] locArray, String year) {
		int kendallFlag = 0;
		int indexSum = Array.length;// 行数，即指标数
		int locSum = Array[0].length - 1;// 列数，即地域数
		double outputs[][] = null;

		double Sum[] = new double[locSum];// 排序结果和
		double Array_del[][] = new double[indexSum][locSum];// 去除最后一列权重值
		for (int i = 0; i < indexSum; i++) {
			for (int j = 0; j < locSum; j++) {
				Array_del[i][j] = Array[i][j];
				// System.out.print(Array_del[i][j]+" ");
			}
			// System.out.println();
		}

		// 方法过程结果
		double[] method1 = null;
		double[] method2 = null;
		double[] method3 = null;
		double[] method4 = null;
		double[] method5 = null;
		double[] method6 = null;

		// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!！！！！！！！！！！！！！！！！
		Map<String, double[]> map = new HashMap<String, double[]>();
		method1 = Sort(Method1(Array));
		method2 = Sort(Method2_java(Array));
		method3 = Sort(Method3_java(Array));
		method4 = Sort(Method4_java(Array));
		method5 = Sort(Method5_java(Array));
		method6 = Sort(Method6_before(locArray, year));
		map.put("1.0", method1);
		map.put("2.0", method2);
		map.put("3.0", method3);
		map.put("4.0", method4);
		map.put("5.0", method5);
		map.put("6.0", method6);

		String key[] = { "1.0", "2.0", "3.0", "4.0", "5.0", "6.0" };
		double sortSum[][] = new double[6][locSum];// 排序结果二维
		for (int i = 0; i < 6; i++) {
			sortSum[i] = map.get(key[i]);
		}
		Sum = sortSumMap(sortSum);
		kendallFlag = Kendall_java(6, locSum, Sum);
		// 枚举组合
		double zuhe2[][] = { { 1, 2 }, { 1, 3 }, { 1, 4 }, { 1, 5 }, { 1, 6 }, { 2, 3 }, { 2, 4 }, { 2, 5 }, { 2, 6 },
				{ 3, 4 }, { 3, 5 }, { 3, 6 }, { 4, 5 }, { 4, 6 }, { 5, 6 } };
		double zuhe3[][] = { { 1, 2, 3 }, { 1, 2, 4 }, { 1, 2, 5 }, { 1, 2, 6 }, { 1, 3, 4 }, { 1, 3, 5 }, { 1, 3, 6 },
				{ 1, 4, 5 }, { 1, 4, 6 }, { 1, 5, 6 }, { 2, 3, 4 }, { 2, 3, 5 }, { 2, 3, 6 }, { 2, 4, 5 }, { 2, 4, 6 },
				{ 2, 5, 6 }, { 3, 4, 5 }, { 3, 4, 6 }, { 3, 5, 6 }, { 4, 5, 6 } };
		double zuhe4[][] = { { 1, 2, 3, 4 }, { 1, 2, 3, 5 }, { 1, 2, 3, 6 }, { 1, 2, 4, 5 }, { 1, 2, 4, 6 },
				{ 1, 2, 5, 6 }, { 1, 3, 4, 5 }, { 1, 3, 4, 6 }, { 1, 3, 5, 6 }, { 1, 4, 5, 6 }, { 2, 3, 4, 5 },
				{ 2, 3, 4, 6 }, { 2, 3, 5, 6 }, { 2, 4, 5, 6 }, { 3, 4, 5, 6 } };
		double zuhe5[][] = { { 1, 2, 3, 4, 5 }, { 1, 2, 3, 4, 6 }, { 1, 2, 3, 5, 6 }, { 1, 2, 4, 5, 6 },
				{ 1, 3, 4, 5, 6 }, { 2, 3, 4, 5, 6 } };

		// 5-2种评价过程
		if (kendallFlag != 1) {
			double[] name5 = new double[5];// 存放方法编号
			double output5[][] = new double[5][locSum + 1];// 5种评价方法的输出
			double sortSum5[][] = new double[5][locSum];// 评价结果
			for (int i = 0; i < zuhe5.length; i++) {
				name5 = zuhe5[i];
				for (int j = 0; j < 5; j++) {
					String str = "" + name5[j];
					sortSum5[j] = map.get(str);// 对应结果数组加入组合结果
				}
				Sum = sortSumMap(sortSum5);
				kendallFlag = Kendall_java(5, locSum, Sum);
				if (kendallFlag == 1) {
					for (int i1 = 0; i1 < 5; i1++) {
						output5[i1][0] = name5[i1];
						for (int j = 1; j < locSum + 1; j++) {
							output5[i1][j] = sortSum5[i1][j - 1];
						}
					}
					outputs = output5;
					break;
				}
			}
			if (kendallFlag != 1) {
				// 4种评价过程
				double[] name4 = new double[4];// 存放方法编号
				double output4[][] = new double[4][locSum + 1];// 4种评价方法的输出
				double sortSum4[][] = new double[4][locSum];// 评价结果
				for (int i = 0; i < zuhe4.length; i++) {
					name4 = zuhe4[i];
					for (int j = 0; j < 4; j++) {
						String str = "" + name4[j];
						sortSum4[j] = map.get(str);// 对应结果数组加入组合结果
					}
					Sum = sortSumMap(sortSum4);
					kendallFlag = Kendall_java(4, locSum, Sum);
					if (kendallFlag == 1) {
						for (int i1 = 0; i1 < 4; i1++) {
							output4[i1][0] = name4[i1];
							for (int j = 1; j < locSum + 1; j++) {
								output4[i1][j] = sortSum4[i1][j - 1];
							}
						}
						outputs = output4;
						break;
					}
				}
			}

			if (kendallFlag != 1) {
				// 3种评价过程
				double[] name3 = new double[3];// 存放方法编号
				double output3[][] = new double[3][locSum + 1];// 3种评价方法的输出
				double sortSum3[][] = new double[3][locSum];// 评价结果
				for (int i = 0; i < zuhe3.length; i++) {
					name3 = zuhe3[i];
					for (int j = 0; j < 3; j++) {
						String str = "" + name3[j];
						sortSum3[j] = map.get(str);// 对应结果数组加入组合结果
					}
					Sum = sortSumMap(sortSum3);
					kendallFlag = Kendall_java(3, locSum, Sum);
					if (kendallFlag == 1) {
						for (int i1 = 0; i1 < 3; i1++) {
							output3[i1][0] = name3[i1];
							for (int j = 1; j < locSum + 1; j++) {
								output3[i1][j] = sortSum3[i1][j - 1];
							}
						}
						outputs = output3;
						break;
					}
				}
			}
			if (kendallFlag != 1) {
				// 2种评价过程
				double[] name2 = new double[2];// 存放方法编号
				double output2[][] = new double[2][locSum + 1];// 2种评价方法的输出
				double sortSum2[][] = new double[2][locSum];// 评价结果
				for (int i = 0; i < zuhe2.length; i++) {
					name2 = zuhe2[i];
					for (int j = 0; j < 2; j++) {
						String str = "" + name2[j];
						sortSum2[j] = map.get(str);// 对应结果数组加入组合结果
					}
					Sum = sortSumMap(sortSum2);
					kendallFlag = Kendall_java(2, locSum, Sum);
					if (kendallFlag == 1) {
						for (int i1 = 0; i1 < 2; i1++) {
							output2[i1][0] = name2[i1];
							for (int j = 1; j < locSum + 1; j++) {
								output2[i1][j] = sortSum2[i1][j - 1];
							}
						}
						outputs = output2;
						break;
					}
				}
			}

		} else {
			double output6[][] = new double[6][locSum + 1];// 6种评价方法都可用
			for (int i = 0; i < 6; i++) {
				output6[i][0] = i + 1;
				for (int j = 1; j < locSum + 1; j++) {
					output6[i][j] = sortSum[i][j - 1];
				}
			}
			outputs = output6;
		}

		// ！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！

		return outputs;
	}

	private static double[] sortSumMap(double[][] Array) {
		// double [][]outputs=null;
		int methodSum = Array.length;// 行数，即评价方法数
		int sampleSum = Array[0].length;// 列数，即评价样本数
		double[] outputs = new double[sampleSum];
		// outputs=null;

		// 计算从第1列开始每一列的和，放在输出数组
		for (int i = 0; i < sampleSum; i++) {
			double temp = 0;
			for (int j = 0; j < methodSum; j++) {
				temp += Array[j][i];
			}
			outputs[i] = temp;
		}
		System.out.print("排序和为:");
		for (int i = 0; i < outputs.length; i++) {
			System.out.print("	" + outputs[i]);
		}
		System.out.println();
		return outputs;

	}

	/*
	 * 2018-01-26 LIU_q 数组排序输出原数组对应值的排序结果
	 * 
	 */
	private static double[] Sort(double[] Array) {
		double[] outputs = null;
		double[] a = Array;
		double[] b = a.clone();
		for (int x = 1, i = 0; x <= a.length; x++, i++) {
			b[i] = x;
		}
		double t, t1 = 0;// 中间量
		for (int j = 0; j < a.length; j++) {
			for (int i = 0; i < a.length - 1 - j; i++)
				if (a[i] < a[i + 1]) {
					t = a[i];
					a[i] = a[i + 1];
					a[i + 1] = t;

					t1 = b[i];
					b[i] = b[i + 1];
					b[i + 1] = t1;
				}
		}

		outputs = b;
		return outputs;
	}

	// 计算从第2列开始每一列的和，放在输出数组
	public static double[] sortSum(double[][] Array) {
		// double [][]outputs=null;
		int methodSum = Array.length;// 行数，即评价方法数
		int sampleSum = Array[0].length - 1;// 列数-1，即评价样本数
		double[] outputs = new double[sampleSum];
		// outputs=null;

		// 计算从第2列开始每一列的和，放在输出数组
		for (int i = 1; i <= sampleSum; i++) {
			double temp = 0;
			for (int j = 0; j < methodSum; j++) {
				temp += Array[j][i];
			}
			outputs[i - 1] = temp;
		}
		System.out.print("排序和为:");
		for (int i = 0; i < outputs.length; i++) {
			System.out.print("	" + outputs[i]);
		}
		System.out.println();
		return outputs;

	}

	/*
	 * 2018-03-09 liu_Q 提取数据库相应数据，为评价方法提供数据
	 * 
	 * 输入 一个 String[]数组和一个String字符，数组内为需要评价的地域（有顺序），字符为评价的年份
	 * 输出一个double[][],结果分别是对应省份和年份的相关数据
	 * 
	 */
	private static double[][] select_Sql(String[] locArray, String year) {
		int locSum = locArray.length;
		MySql mysql = new MySql();// 数据库链接，库名和用户名密码需要更改！！！！
		double[][] outArray = new double[locSum + 1][151];// 固定选择3个省份得数据，最后一列为权重
		double[][] Array = new double[151][locSum + 1];// 转置之后得数组，输入评价过程用
		for (int i = 0; i < locSum; i++) {
			outArray[i] = mysql.selectValue(locArray[i], year);// 省份，年份，得到数据
		}
		outArray[locSum] = mysql.selectWeight();// 得到权重数据

		Array = tfunction(outArray);// 矩阵转置

		double[][] outputs = Array;
		return outputs;

	}

	/*
	 * 2018-03-09 liu_Q 为评价方法主函数
	 * 
	 * 输入 一个 String[]数组和一个String字符，数组内为需要评价的地域（有顺序），字符为评价的年份
	 * 输出一个double[][],结果分别是对应省份的6种评价方法得到的排名 其中第一列为评价方法编号
	 */
	public static double[][] evaluation_Method(String[] locArray, String year) {

		double[][] Array = select_Sql(locArray, year);// 查询数据库得到相应值
		double[][] out1 = MapSort(Array, locArray, year);

		double[][] outputs = out1;
		return outputs;
	}

	/*
	 * 2018-03-09 liu_Q 为评价方法6前期函数
	 * 
	 * 输入 一个 String[]数组和一个String字符，数组内为需要评价的地域（有顺序），字符为评价的年份
	 * 输出一个double[][],结果分别是对应省份的评价方法6得到的评价值
	 * 
	 */
	private static double[] Method6_before(String[] locArray, String year) {
		MySql mysql = new MySql();// 数据库链接
		// double[][]Array = new double[locArray.length+1][151];
		// //存method6_java所需数组
		double[] outputs = new double[locArray.length];// 返回值

		String[] loc = mysql.selectLoction();// 地域
		String[] year_Array = mysql.selectYear(loc[0]);// 年份

		// 计算method6的值，同一省份不同年份的值输入method6_java
		double[][][] loc_sum_Array = new double[loc.length][year_Array.length + 1][151];// 存放不同地域不同年份的数据,二维最后一列为weight
		for (int j = 0; j < loc.length; j++) {
			for (int i = 0; i < year_Array.length; i++) {
				loc_sum_Array[j][i] = mysql.selectValue(loc[j], year_Array[i]);// 省份，年份，得到数据
			}
			loc_sum_Array[j][year_Array.length] = mysql.selectWeight();// 得到权重数据
		}
		/*
		 * for(int j=0;j<loc.length;j++){ for(int i=0;i<year_Array.length;i++){
		 * for(int k=0;k<151;k++){ System.out.print(loc_sum_Array[j][i][k]+
		 * "	"); } System.out.println(); } System.out.println(); }
		 */
		double[][] method6 = new double[loc.length][year_Array.length];// 存放不同地域、年份方法六计算结果
		for (int i = 0; i < loc.length; i++) {
			method6[i] = Method6_java(tfunction(loc_sum_Array[i]));
		}
		double[][] methodValue = tfunction(method6);

		// 找对应loc和year的指标值，按照locArray的顺序放入返回值数组
		for (int z = 0; z < locArray.length; z++) {
			int i_flag = 0;
			int j_flag = 0;
			for (int i = 0; i < loc.length; i++) {
				if (locArray[z] == loc[i]) {
					i_flag = i;
				}
			}
			for (int i = 0; i < year_Array.length; i++) {
				if (year == year_Array[i]) {
					j_flag = i;
				}
			}
			outputs[z] = methodValue[i_flag][j_flag];
		}
		return outputs;
	}
}
