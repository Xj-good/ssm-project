package com.atguigu.crowd.util;

import com.atguigu.crowd.constant.CrowdConstant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CrowdUtil {

	/**
	 * 对明文字符串进行md5加密
	 * @param source 传入的明文字符串
	 * @return	返回加密的结果
	 */
	public static String md5(String source){
		if (source == null || source.length() == 0){
			throw new RuntimeException(CrowdConstant.MESSAGE_STRING_INVALIDATE);
		}
		try {
			String algorithm = "md5";
			MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
			byte[] input = source.getBytes();
			byte[] output = messageDigest.digest(input);

			int signum = 1;
			BigInteger bigInteger = new BigInteger(signum,output);

			int radix = 16;
			String encoded = bigInteger.toString(radix).toUpperCase();
			return encoded;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 判断当前请求是否为Ajax请求
	 * @param request 请求对象
	 * @return
	 * 		true：当前请求是Ajax请求
	 * 		false：当前请求不是Ajax请求
	 */
	public static boolean judgeRequestType(HttpServletRequest request) {
		
		// 1.获取请求消息头
		String acceptHeader = request.getHeader("Accept");
		String xRequestHeader = request.getHeader("X-Requested-With");
		
		// 2.判断
		return (acceptHeader != null && acceptHeader.contains("application/json"))
				
				||
				
				(xRequestHeader != null && xRequestHeader.equals("XMLHttpRequest"));
	}

}
