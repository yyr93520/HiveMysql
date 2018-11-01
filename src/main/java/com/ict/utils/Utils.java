package com.ict.utils;

import java.io.IOException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public final class Utils {

	public static String base64Encoder(String str) {

		BASE64Encoder encode = new BASE64Encoder();
		return encode.encode(str.getBytes());

	}

	public static String base64Decoder(String str) {

		BASE64Decoder decode = new BASE64Decoder();
		String result = "";

		byte[] b;
		try {
			b = decode.decodeBuffer(str);
			result = new String(b);

		} catch (IOException e) {
			e.printStackTrace();
		}

		return result;
	}
}
