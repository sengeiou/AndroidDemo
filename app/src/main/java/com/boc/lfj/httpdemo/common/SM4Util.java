/**
 * �Գ��㷨���ʹ��demo
 * ��Ҫ�ӽ���
 */
package com.boc.lfj.httpdemo.common;


import com.kjhxtc.crypto.api.Symmetric.BocSymmetricCrypt;
import com.kjhxtc.crypto.api.Symmetric.SymDecryptOperator;
import com.kjhxtc.crypto.api.Symmetric.SymEncryptOperator;

import javax.crypto.IllegalBlockSizeException;

/**
 * @author taoqiang @create on 2016/10/31 14:09
 * @email tqdream@163.com
 */
public class SM4Util {
    public static int length = 16;

    /**
     * SMS4加密
     */
    public static byte[] encodeSMS4(String plainText , String key) {

        String keyFormat = keyFormat(key);

        BocSymmetricCrypt api = new BocSymmetricCrypt();
        SymEncryptOperator sm4opt = api.BOC_EncryptInit(BocSymmetricCrypt.ALG_SM4_ECB, BocSymmetricCrypt.PADDING_ZEROS, keyFormat.getBytes(), null);
        byte[] e_data = api.BOC_Encrypt(sm4opt, plainText.getBytes());

        return e_data;
    } /**
     * SMS4加密
     */
    public static byte[] encodeSMS42(byte[] plaintext, byte[] key) {
        BocSymmetricCrypt api = new BocSymmetricCrypt();

        SymEncryptOperator sm4opt = api.BOC_EncryptInit(BocSymmetricCrypt.ALG_SM4_ECB, BocSymmetricCrypt.PADDING_ZEROS, key, null);
        byte[] e_data = api.BOC_Encrypt(sm4opt, plaintext);

        return e_data;
    }

    /**
     * SMS4解密
     */
    public static byte[] decodeSMS4(byte[] ciphertext, byte[] key) {
        BocSymmetricCrypt api = new BocSymmetricCrypt();
        SymDecryptOperator sm4dec = api.BOC_DecryptInit(BocSymmetricCrypt.ALG_SM4_ECB, BocSymmetricCrypt.PADDING_ZEROS, key, null);
        byte[] d_data = null;
        try {
            d_data = api.BOC_Decrypt(sm4dec, ciphertext);
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return d_data;
    }

    private static String keyFormat(String key) {
        String keyFormat;
        if (key.length() < length) {
            keyFormat = key + String.format("%1$0" + (length - key.length()) + "d", 0);
        } else if (key.length() > 16) {
            keyFormat = key.substring(0, length);
        } else {
            return key;
        }
        return keyFormat;
    }
}
