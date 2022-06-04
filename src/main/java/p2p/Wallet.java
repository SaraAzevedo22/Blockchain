package p2p;

import Blockchain.Block;
import Blockchain.Blockchain;
import Blockchain.Config;
import Blockchain.Transaction;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Hashtable;


public class Wallet {
    private String privateKey;
    private String publicKey;
    private String id;
    int amountTransfered = 0;
    Hashtable<String, Integer> users = new Hashtable<>();


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
            this.privateKey = Base64.getEncoder().encodeToString(encodedPrivateKey);
            this.publicKey = Base64.getEncoder().encodeToString(encodedPublicKey);

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

    public boolean verifySignature(String pubKeyReceived, byte[] messageBytes, byte[] receivedSignature) throws NoSuchAlgorithmException, InvalidKeySpecException, InvalidKeyException, SignatureException {
        Signature signature = Signature.getInstance("RSA");

        byte[] data = pubKeyReceived.getBytes();
        KeyFactory fact = KeyFactory.getInstance("RSA");
        X509EncodedKeySpec spec = new X509EncodedKeySpec(data);
        PublicKey publicKeyReceived = fact.generatePublic(spec);

        signature.initVerify(publicKeyReceived);
        signature.update(messageBytes);

        boolean isCorrect = signature.verify(receivedSignature);

        return isCorrect;
    }

    private String getPrivateKey() {
        return privateKey;
    }

    public String getPublicKey() {
        return publicKey;
    }

    /*public void Ledger() {
        users.put(User.publicKey, amountTransfered);
    }*/

    public double returnBalance() {
        return users.get(this.publicKey);
    }

    public void printBalance() {
        System.out.print("Your account balance is: ");
        System.out.print(returnBalance());
    }

    public void reloadLedger() {
        users.clear();
        users.put(User.publicKey, amountTransfered);
        for(int i=0; i < Blockchain.chain.size(); i++){
            Block block = Blockchain.chain.get(i);
            upgradeLedger(block);
        }
    }

    public void upgradeLedger(Block block){
        for(int i=0; i < block.transactions.size(); i++) {
            Transaction transaction = block.transactions.get(i);
            int amountSent, amountReceived;

            if(users.get(transaction.sourceName) != null) {
                amountSent = users.get(transaction.sourceName);
            }
            else {
                amountSent = amountTransfered;
            }
            if(users.get(transaction.destinationName) != null) {
                amountReceived = users.get(transaction.destinationName);
            }
            else {
                amountReceived = amountTransfered;
            }
            users.put(transaction.sourceName, (amountSent - transaction.sum));
            users.put(transaction.destinationName, (amountReceived - transaction.sum));
        }
       /* if(users.get(block.publicKey) != null) {
            users.put(block.publicKey, users.get(block.publicKey)+1);
        } else {
            users.put(block.publicKey, amountTransfered + Config.MINING_REWARD);
        }*/
    }
}
