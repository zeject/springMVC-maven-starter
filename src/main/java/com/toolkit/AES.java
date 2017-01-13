package com.toolkit;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import com.config.Constants;

public class AES {
	public static final String DEFAULT_CODING = "utf-8";

	/**
	 * 解密
	 * 
	 * @author lmiky
	 * @date 2014-2-25
	 * @param encrypted
	 * @param seed
	 * @return
	 * @throws Exception
	 */
	public static String decrypt(String encrypted) {
		byte[] clearbyte = null;
		try {
			byte[] keyb = Constants.AESKEY.getBytes(DEFAULT_CODING);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(keyb);
			SecretKeySpec skey = new SecretKeySpec(thedigest, "AES");
			Cipher dcipher = Cipher.getInstance("AES");
			dcipher.init(Cipher.DECRYPT_MODE, skey);

			clearbyte = dcipher.doFinal(toByte(encrypted));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new String(clearbyte);
	}

	/**
	 * 加密
	 * 
	 * @author lmiky
	 * @date 2014-2-25
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encrypt(String content) {
		byte[] cipherText = null;
		try {
			byte[] input = content.getBytes(DEFAULT_CODING);

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] thedigest = md.digest(Constants.AESKEY.getBytes(DEFAULT_CODING));
			SecretKeySpec skc = new SecretKeySpec(thedigest, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, skc);

			cipherText = new byte[cipher.getOutputSize(input.length)];
			int ctLength = cipher.update(input, 0, input.length, cipherText, 0);
			ctLength += cipher.doFinal(cipherText, ctLength);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return parseByte2HexStr(cipherText);
	}

	/**
	 * 字符串转字节数组
	 * 
	 * @author lmiky
	 * @date 2014-2-25
	 * @param hexString
	 * @return
	 */
	private static byte[] toByte(String hexString) {
		int len = hexString.length() / 2;
		byte[] result = new byte[len];
		for (int i = 0; i < len; i++) {
			result[i] = Integer.valueOf(hexString.substring(2 * i, 2 * i + 2), 16).byteValue();
		}
		return result;
	}

	/**
	 * 字节转16进制数组
	 * 
	 * @author lmiky
	 * @date 2014-2-25
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex);
		}
		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
		System.out.println(AES.encrypt("62284"));
	}
}
