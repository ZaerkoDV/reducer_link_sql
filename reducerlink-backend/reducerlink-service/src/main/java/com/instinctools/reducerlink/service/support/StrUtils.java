package com.instinctools.reducerlink.service.support;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public final class StrUtils {
    public static final String ENCODING_UTF8 = "UTF-8";

    private StrUtils() {
    }

    public static String urlEncode(String text) {
        return urlEncode(text, ENCODING_UTF8);
    }

    public static String urlEncode(String text, String encoding) {
        try {
            return URLEncoder.encode(text, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String urlDecode(String text) {
        return urlDecode(text, ENCODING_UTF8);
    }

    public static String urlDecode(String text, String encoding) {
        try {
            return URLDecoder.decode(text, encoding);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String md5(String value) {
        return hash("MD5", value, null);
    }

    public static String md5(String value, String salt) {
        return hash("MD5", value, salt);
    }

    public static String sha1(String value) {
        return hash("SHA1", value, null);
    }

    public static String sha1(String value, String salt) {
        return hash("SHA1", value, salt);
    }

    public static String hash(String algorithm, String value, String salt) {
        try {
            MessageDigest digester = MessageDigest.getInstance(algorithm);
            digester.update(value.getBytes(ENCODING_UTF8));
            byte[] a = (salt == null ? digester.digest() : digester.digest(salt.getBytes(ENCODING_UTF8)));
            int len = a.length;
            StringBuilder sb = new StringBuilder(len << 1);

            for (int i = 0; i < len; i++) {
                sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
                sb.append(Character.forDigit(a[i] & 0x0f, 16));
            }

            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
