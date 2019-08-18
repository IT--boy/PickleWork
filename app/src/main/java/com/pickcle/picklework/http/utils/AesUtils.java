package com.pickcle.picklework.http.utils;



import org.apache.commons.binary.Base64;

import java.nio.charset.Charset;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * 数据加解密
 *
 * @author panwb
 *
 */
public class AesUtils {

    public static boolean initialized = false;

    private static final String AES = "AES";
    private static final String ENCODE = "UTF-8";

    private static final String IV = "12345678abcdefgh";
    private static final int KEY_SIZE = 16;

    public static final String KEY = "JKTX-HMP00000001";

    public static final String CIPHER_ALGORITHM="AES/CBC/PKCS5Padding";

    public static final String PK7_ALGORITHM="AES/ECB/PKCS7Padding";

    public static String encryptBase64(String content, String password) throws Exception {
        byte keyPtr[] = new byte[KEY_SIZE];
        byte passPtr[] = password.getBytes(ENCODE);
        for (int i = 0; i < KEY_SIZE; i++) {
            if (i < passPtr.length){
                keyPtr[i] = passPtr[i];
            }
            else {
                keyPtr[i] = 0;
            }
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyPtr, AES);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, new IvParameterSpec(IV.getBytes(ENCODE)));
        byte[] result = cipher.doFinal(content.getBytes(ENCODE));
        return Base64.encodeBase64String(result);
    }

    public static String decryptBase64(String content, String password) throws Exception {
        byte keyPtr[] = new byte[KEY_SIZE];
        byte passPtr[] = password.getBytes(ENCODE);
        for (int i = 0; i < KEY_SIZE; i++) {
            if (i < passPtr.length){
                keyPtr[i] = passPtr[i];
            }
            else {
                keyPtr[i] = 0;
            }
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyPtr, AES);
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec,new IvParameterSpec(IV.getBytes(ENCODE)));
        byte[] base64 = Base64.decodeBase64(content.getBytes(ENCODE));
        byte[] result = cipher.doFinal(base64);
        return new String(result, Charset.forName(ENCODE)); // 加密
    }

    /*public static String encryptPK7Base64(String content, String password) throws Exception {

        initialize();

        byte keyPtr[] = new byte[KEY_SIZE];
        byte passPtr[] = password.getBytes(ENCODE);
        for (int i = 0; i < KEY_SIZE; i++) {
            if (i < passPtr.length){
                keyPtr[i] = passPtr[i];
            }
            else {
                keyPtr[i] = 0;
            }
        }
        SecretKeySpec secretKeySpec = new SecretKeySpec(keyPtr, AES);
        Cipher cipher = Cipher.getInstance(PK7_ALGORITHM, "BC");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] result = cipher.doFinal(content.getBytes(ENCODE));
        return Base64.encodeBase64String(result);
    }

    public static String decryptPK7Base64(String content, String password) throws Exception{
        initialize();
        byte keyPtr[] = new byte[KEY_SIZE];
        byte passPtr[] = password.getBytes(ENCODE);
        for (int i = 0; i < KEY_SIZE; i++) {
            if (i < passPtr.length){
                keyPtr[i] = passPtr[i];
            }
            else {
                keyPtr[i] = 0;
            }
        }

        SecretKeySpec secretKeySpec = new SecretKeySpec(keyPtr, AES);
        Cipher cipher = Cipher.getInstance(PK7_ALGORITHM, "BC");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] base64 = Base64.decodeBase64(content.getBytes(ENCODE));
        byte[] result = cipher.doFinal(base64);
        return new String(result, Charset.forName(ENCODE)); // 加密
    }

    public static void initialize(){
        if (initialized)
            return;
        Security.addProvider(new BouncyCastleProvider());
        initialized = true;
    }*/
}
