package lhweb.asia.LHTomCat.utils;


import lhweb.asia.LHTomCat.constant.LHTomCatConstant;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * @author :罗汉
 * @date : 2024/1/29
 */
public class DataUtils {

    /**
     * MD5加密密码
     *
     * @param password 密码
     * @return {@link String}
     */
    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update((LHTomCatConstant.SALT +password).getBytes());
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 验证密码
     *
     * @param inputPassword     输入密码
     * @param encryptedPassword 加密密码
     * @return boolean
     */
    public static boolean verifyPassword(String inputPassword, String encryptedPassword) {
        // 对用户输入的密码进行MD5加密
        String inputEncryptedPassword = encryptPassword(inputPassword);
        // 比对加密后的密码是否匹配
        return inputEncryptedPassword.equals(encryptedPassword);
    }

    /**
     * 解读url编码转中文
     *
     * @param encodedString 编码字符串
     * @return {@link String}
     */
    public static String decodeChinese(String encodedString) {
        return URLDecoder.decode(encodedString, StandardCharsets.UTF_8);
    }
    // 生成随机验证码
    public static String generateRandomCode() {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 4; i++) {
            sb.append(random.nextInt(10)); // 生成0到9的随机数字
        }
        return sb.toString();
    }

}
