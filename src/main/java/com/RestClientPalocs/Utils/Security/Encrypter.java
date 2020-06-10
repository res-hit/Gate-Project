package com.RestClientPalocs.Utils.Security;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encrypter {
    private static SecretKeySpec secretKeySpec;
    private static byte[] key;

    public static void setKey(String myKey){
        MessageDigest sha = null;
        try{
            key = myKey.getBytes();
            sha = MessageDigest.getInstance("SHA-1");//TODO CHANGE WITH A BETTER ALGORITHM
            key = sha.digest(key);
            key = Arrays.copyOf(key,16);
            secretKeySpec = new SecretKeySpec(key,"AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    public static String encrypt(String strToEncrypt, String secret){
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE,secretKeySpec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (NoSuchPaddingException |
                NoSuchAlgorithmException |
                BadPaddingException |
                InvalidKeyException |
                UnsupportedEncodingException |
                IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String decrypt(String strToDecrypt, String secret){
        try{
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
            return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
        } catch (NoSuchPaddingException |
                NoSuchAlgorithmException |
                BadPaddingException |
                IllegalBlockSizeException |
                InvalidKeyException e) {
            e.printStackTrace();
            System.out.println("Error while decrypting : "+e.toString());
        }
        return null;
    }
    public static void main(String[] args){
        final String secret = "palocsKeyPalocsKey!!";
        String originalString = "Secret string";
        String encryptedString = Encrypter.encrypt(originalString,secret);
        String decryptedString = Encrypter.decrypt(encryptedString,secret);
        System.out.println(" ORIGINAL : " + originalString);
        System.out.println(" ENCRYPTED : " + encryptedString);
        System.out.println(" DECRYPTED : " + decryptedString);
    }
}
