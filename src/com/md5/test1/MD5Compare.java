package com.md5.test1;

import com.common.test.ObjectUtil;
import com.jaxb.test1.Customer;
import com.jaxb.test1.JAXBExample;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by applec on 15/4/30.
 */
public class MD5Compare {
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException, IllegalAccessException {

		Customer customer = JAXBExample.getSampleCustomer();

		byte[] b = ObjectUtil.serialize(customer);

		MessageDigest messageDigest = MessageDigest.getInstance("MD5");
		messageDigest.update(b);
		byte[] resultByteArray = messageDigest.digest();
		long objSize = ObjectSizeCalculator.sizeOf(customer);
		System.out.println(objSize);
		String objMD5Str = byteArrayToHex(resultByteArray);
		System.out.println(objMD5Str);

		String path = "/Users/applec/Documents/IdeaProjects/zhengze_test/md5compare.txt";
		FileWriter writer = new FileWriter(path, true);
		writer.write(objSize + objMD5Str + "\n");
		writer.close();

	}
	public static String byteArrayToHex(byte[] byteArray) {

		// 首先初始化一个字符数组，用来存放每个16进制字符

		char[] hexDigits = {'0','1','2','3','4','5','6','7','8','9', 'A','B','C','D','E','F' };



		// new一个字符数组，这个就是用来组成结果字符串的（解释一下：一个byte是八位二进制，也就是2位十六进制字符（2的8次方等于16的2次方））

		char[] resultCharArray =new char[byteArray.length * 2];



		// 遍历字节数组，通过位运算（位运算效率高），转换成字符放到字符数组中去

		int index = 0;

		for (byte b : byteArray) {

			resultCharArray[index++] = hexDigits[b>>> 4 & 0xf];

			resultCharArray[index++] = hexDigits[b& 0xf];

		}



		// 字符数组组合成字符串返回

		return new String(resultCharArray);

	}
}
