package p2p;


import Blockchain.Blockchain;
import Blockchain.Config;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

public class User {
    public static String id;
    public static String ipAddress;
    public static int portNo;
    public static Blockchain blockchain;
    public static String publicKey;
    public static Wallet wallet = new Wallet();
    public static int nonce = 0;
    static final int DEPTH = 20;
    public static ArrayList<Node> trashlist = new ArrayList<>();
    public static KademliaBucket kadBucket = new KademliaBucket();
    public static StayinAliveThread stayinAliveThread= new StayinAliveThread();

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

    public static void startPing() { User.stayinAliveThread.start(); }


    public static void trustness() {
        TreeSet<Contact> tempTree = KademliaBucket.getClonedTreeSet();
        for(Contact contact : tempTree) {
            Blockchain tempBlockchain = null;


        }
    }


    public static void verifyNodesIntegrity(Node node) {
        double integrity = -1;
        if(node.sumInteractions >= 1)
            integrity = node.successInteractions / node.sumInteractions;
        if(integrity < Config.min_reputation)
            User.TrashNodes(node);
    }

    public static void TrashNodes(Node node) {
        kadBucket.removeNode(node);
        addNodesToTrash(node);
    }

    public static void addNodesToTrash(Node node) {
        trashlist.add(node);
    }

    public static ArrayList<Node> getTrashlist() {
        return trashlist;
    }

    public static boolean isTrash(Node node) {
        return trashlist.contains(node);
    }

    private static class StayinAliveThread extends Thread {
        public StayinAliveThread() {}

        public void run() {
            while(true) {
                try{
                    TimeUnit.MILLISECONDS.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for(int i = 0;i < User.kadBucket.lastSeenNodes.size(); i++) {
                    if(!new ClientGRPC(User.kadBucket.lastSeenNodes.get(i).ipAddress, User.kadBucket.lastSeenNodes.get(i).portNo).ping()){
                        User.kadBucket.lastSeenNodes.remove(i);
                        i--;
                    }
                }
            }
        }
    }
}
