package com.example.demo.util;

import ch.qos.logback.core.encoder.ByteArrayUtil;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.AESDecrypter;
import com.nimbusds.jose.crypto.AESEncrypter;
import com.nimbusds.jose.crypto.RSAEncrypter;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPublicKey;
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

    // AES encryption decryption with JWE symmetric Key
    public static String encryptSymmetricKeyWithCertificate(String symmetricKey, String bankCertificate) throws Exception {
        RSAPublicKey publicKey = (RSAPublicKey)getPublicKey(bankCertificate);
        Payload pl = new Payload(symmetricKey);
        JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.RSA_OAEP_256, EncryptionMethod.A256GCM).build();
        JWEObject jweObject = new JWEObject(jweHeader, pl);
        jweObject.encrypt(new RSAEncrypter(publicKey));

        return jweObject.serialize();
    }

    public static String encryptWithJWE(String data, String key) throws Exception{
        try {
            byte[] aesKeyBytes = ByteArrayUtil.hexStringToByteArray(key);
            // Construct the AES key using SecretKeySpec
            SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

            // Create a JWE header with default encryption method (e.g., A256GCM)
            JWEHeader jweHeader = new JWEHeader.Builder(JWEAlgorithm.A256KW, EncryptionMethod.A256GCM).build();

            // Create a JWE object with the payload as the JSON data
            JWEObject jweObject = new JWEObject(jweHeader, new Payload(data));

            // Encrypt the payload using the AES key
            jweObject.encrypt(new AESEncrypter(aesKey));

            // Serialize the JWE object
            return jweObject.serialize();
        }
        catch (Exception ex){
            log.error("Error in encryption for IndusInd Eph", ex);
            throw new Exception("Error in encryption for IndusInd Eph");
        }
    }

    public static String decryptWithJWE(String data, String key) throws Exception {
        try{
            // converted the AES key from hexadecimal to byte array
            byte[] aesKeyBytes = ByteArrayUtil.hexStringToByteArray(key);

            // Construct the AES key using SecretKeySpec
            SecretKey aesKey = new SecretKeySpec(aesKeyBytes, "AES");

            // Create a JWE object
            JWEObject jweObject = JWEObject.parse(data);

            // Decrypt the payload using the AES key
            jweObject.decrypt(new AESDecrypter(aesKey));

            // Convert the decrypted payload to a JSON object
            byte[] plaintext = jweObject.getPayload().toBytes();
            JsonObject decryptedJson = JsonParser.parseString(new String(plaintext, StandardCharsets.UTF_8)).getAsJsonObject();

            return decryptedJson.toString();
        }
        catch (Exception ex){
            log.error("Error in decryption for IndusInd Eph", ex);
            throw new Exception("Error in decryption for IndusInd Eph");
        }
    }
}
