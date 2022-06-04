package p2p;


import Blockchain.Blockchain;
import Blockchain.Config;

import java.util.TreeSet;

public class User {
    public static String id;
    public static String ipAddress;
    public static int portNo;
    public static Blockchain blockchain;
    public static String publicKey;
    public static Wallet wallet = new Wallet();
    public static int nonce = 0;
    static final int DEPTH = 20;
    public static KademliaBucket kadBucket = new KademliaBucket(DEPTH);

    public User() {
    }

    // TODO ADD LEDGER
    public void iniateUser(int portNo, String ip) {
        this.portNo = portNo;
        this.ipAddress = ip;
        this.publicKey = this.wallet.getPublicKey();
        this.blockchain = new Blockchain();
        this.id = getId();
        this.nonce = 0;


    }

    private String getId() {
        String prefix = new String(new char[Config.difficulty]).replace('\0','0');
        while(!id.substring(0, Config.difficulty).equals(prefix)) {
            nonce++;
            id = calculateHashId(this.ipAddress, this.portNo, this.publicKey, this.nonce);
        }
        return id;
    }


    public String calculateHashId(String ip,int portNo, String publicKey, int nonce) {
        return Config.calculateSHA256(ip + portNo + publicKey + nonce); //Apply SHA256 to User ID
    }

    public static void trustness() {
        TreeSet<Contact> tempTree = KademliaBucket.getClonedTreeSet();
        for(Contact contact : tempTree) {
            Blockchain tempBlockchain = null;


        }
    }


}
