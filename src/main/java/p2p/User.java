package p2p;


import Blockchain.Blockchain;
import Blockchain.Config;

public class User {
    public static String id;
    public String ipAddress;
    public int portNo;
    public KademliaRoutingTable kadRT;
    public Blockchain blockchain;
    public static String publicKey;
    public static Wallet wallet = new Wallet();
    public int nonce = 0;

    public User() {
    }

    // TODO ADD LEDGER
    public void iniateUser(int portNo, String ip) {
        this.portNo = portNo;
        this.ipAddress = ip;
        this.publicKey = this.wallet.getPublicKey();
        this.blockchain = new Blockchain();
        this.id = getGuidId();


    }

    private String getGuidId() {
        String prefix = new String(new char[Config.difficulty]).replace('\0','0');
        while(!id.substring(0, Config.difficulty).equals(prefix)) {
            nonce++;
            id = calculateHashGuid(this.ipAddress, this.portNo, this.publicKey, this.nonce);
        }
        return id;
    }


    public String calculateHashGuid(String ip,int portNo, String publicKey, int nonce) {
        return Config.calculateSHA256(ip + portNo + publicKey + nonce); //Apply SHA256 to User ID
    }


}
