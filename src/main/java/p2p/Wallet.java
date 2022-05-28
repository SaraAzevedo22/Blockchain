package p2p;

import Blockchain.Config;

import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

public class Wallet {
    private String privateKey;
    private String publicKey;
    private String id;

    public Wallet() {
        generateKeyPair();
    }

    private void generateKeyPair() {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(1024);
            KeyPair kp = kpg.generateKeyPair();
            PrivateKey privKey = kp.getPrivate();
            PublicKey pubKey = kp.getPublic();
            byte[] encodedPrivateKey = privKey.getEncoded();
            byte[] encodedPublicKey = pubKey.getEncoded();
            //this.privateKey= new String(Base64.encode(encodedPrivateKey));
            //this.publicKey= new String(Base64.encode(encodedPublicKey));

        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
        }
    }

    public byte[][] signMessage(String message) throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        Signature signature = Signature.getInstance("RSA");

        byte[] data = Base64.getDecoder().decode((privateKey.getBytes()));
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        KeyFactory fact = KeyFactory.getInstance("RSA");
        signature.initSign(fact.generatePrivate(spec));

        byte[] messageBytes = Config.calculateSHA256(publicKey.concat(String.valueOf(message))).getBytes();
        signature.update(messageBytes);

        byte[] digitalSignature = signature.sign();
        
        return new byte[][]{messageBytes,digitalSignature};
    }


}
