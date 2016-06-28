package org.vicykie.myapp.util;

import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.vicykie.myapp.entities.authority.UserInfo;

import java.security.NoSuchAlgorithmException;


/**
 * 密码加密工具。无解密
 *
 * @author 李朝衡
 */
public class PasswordUtils {
    private static void md5() {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        // false 表示：生成32位的Hex版, 这也是encodeHashAsBase64的, Acegi 默认配置; true
        // 表示：生成24位的Base64版
        md5.setEncodeHashAsBase64(false);
        String pwd = md5.encodePassword("1234", null);
        System.out.println("MD5: " + pwd + " len=" + pwd.length());
    }

    private static void sha_256() throws NoSuchAlgorithmException {
        ShaPasswordEncoder sha = new ShaPasswordEncoder(256);
        sha.setEncodeHashAsBase64(true);
        String pwd = sha.encodePassword("1234", null);
        System.out.println("哈希算法 256: " + pwd + " len=" + pwd.length());
    }

    private static void sha_SHA_256() {
        ShaPasswordEncoder sha = new ShaPasswordEncoder();
        sha.setEncodeHashAsBase64(false);
        String pwd = sha.encodePassword("1234", null);
        System.out.println("哈希算法 SHA-256: " + pwd + " len=" + pwd.length());
    }

    /**
     * 使用以下方式加密
     */
    private static void md5_SystemWideSaltSource() {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);

        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        String pwd = md5.encodePassword("123123", "927778976427");
        System.out.println("MD5 SystemWideSaltSource: " + pwd + " len="
                + pwd.length());
    }

    /**
     * 使用以下方式加密
     */
    public static String md5_SystemWideSaltSource_encode(UserInfo user) {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);
        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        return md5.encodePassword(user.getPassword(), user.getSalt());
    }

    public static String encodeUserPassword(String password, String salt) {
        Md5PasswordEncoder md5 = new Md5PasswordEncoder();
        md5.setEncodeHashAsBase64(false);
        // 使用动态加密盐的只需要在注册用户的时候将第二个参数换成用户名即可
        return md5.encodePassword(password, salt);//
    }


    public static void main(String[] args) throws NoSuchAlgorithmException {
        String or = "123456";
        String salt = "123";
        System.out.println(encodeUserPassword(or, salt));//7c34bb7c545506787cc1bbd6987d5dac
    }
}
