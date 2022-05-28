package p2p;

import java.security.*;

public class Wallet {
    private String privKey;
    private String pubKey;
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


        } catch (NoSuchAlgorithmException e) {
            System.out.println("Exception thrown : " + e);
        }
    }


    
}
