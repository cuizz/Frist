package com.example.frist;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class GetSign {
	
	private static String cartOrder;
	/**
	 * 这里边 是 md5 两次加密的 方法
	 * 
	 * @param str
	 *            这个 是 c
	 * @param strr
	 *            这个 是 a
	 */
	public static String getCartInfo(String str, String strr,String cartTime) {
		md5sCart(strr + str + cartTime + "sunrock");
		md5sCart(cartOrder);
		return cartOrder;
	}
	
	public static void md5sCart(String plainText) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			cartOrder = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
}
