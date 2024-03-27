package com.example.demo.util;

import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

@Slf4j
public class EncryptionUtil {

    // RSA encryption decryption
    public static String rsaEncrypt(String data, String pubKey) throws Exception {
        PublicKey publicKey = getPublicKey(pubKey);
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
    }

    private static byte[] rsaDecrypt(String data, String pvtKey) {
        try {
            PrivateKey privateKey = getPrivateKey(pvtKey);
            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            byte[] d = Base64.getDecoder().decode(data);
            return cipher.doFinal(d);
        } catch (Exception ex) {
            log.error("Error in decryption", ex);
        }
        return new byte[0];
    }

    public static PublicKey getPublicKey(String pubKey) throws IOException, CertificateException {
        InputStream targetStream = new ByteArrayInputStream(Base64.getDecoder().decode(pubKey));
        CertificateFactory cf = CertificateFactory.getInstance("X509");
        X509Certificate crt = (X509Certificate) cf.generateCertificate(targetStream);
        return crt.getPublicKey();
    }

    public static PrivateKey getPrivateKey(String pvtKey) throws NoSuchAlgorithmException, InvalidKeySpecException {
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(pvtKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(keySpec);
    }
}
