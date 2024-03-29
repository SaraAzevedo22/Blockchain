package p2p;

import Blockchain.Blockchain;
import Blockchain.Settings;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.concurrent.TimeUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {
    public static String id;
    public static String ipAddress;
    public static String publicKey;
    public static Blockchain blockchain;
    public static int portNo;
    public static int proof = 0;
    public static Wallet wallet = new Wallet();
    public static ArrayList<Node> trashList = new ArrayList<>();
    public static KademliaBucket kadBucket = new KademliaBucket();
    public static StayinAliveThread stayinAliveThread = new StayinAliveThread();
    public static MiningBlockThread miningBlockThread = new MiningBlockThread();

    public User() { }

    public void iniateUser(int portNo, String ip) {
        this.portNo = portNo;
        this.ipAddress = ip;
        this.publicKey = this.wallet.getPublicKey();
        this.blockchain = new Blockchain();
        this.proof = 0;
        this.id = getId();

        try {
            if(Settings.bootstrapNode.equals("")) User.blockchain = new Blockchain(User.wallet);
            else User.blockchain = new Blockchain();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        if(Settings.bootstrapNode.equals("")) {
            Settings.bootstrapNode = User.id;
        } else {
            Node tempNode = new Node(Settings.bootstrapNode, ip,8080);
            User.kadBucket.addNodeToList(tempNode);
            System.out.println("Node = " + tempNode);
            Kademlia.pingNode(tempNode);
            System.out.println("debug ping");
        }
    }

    private String getId() {
        String prefix = new String(new char[Settings.difficulty]).replace('\0','0');
         do {
            proof++;
            id = calculateHashId(this.ipAddress, this.portNo, this.publicKey, this.proof);
        } while(!id.substring(0, Settings.difficulty).equals(prefix));
        return id;
    }

    public String calculateHashId(String ip,int portNo, String publicKey, int proof) {
        return Settings.calculateSHA256(ip + portNo + publicKey + proof); //Apply SHA256 to User ID
    }

    public static void startPing() {
        User.stayinAliveThread.start();
    }

    public static void startMining() {
        User.miningBlockThread.start();
    }

    public static void Trust() {
        List<Node> bucket = KademliaBucket.getClonedList();
        for (Node node : bucket) {
            Blockchain blockchain = null;
            if (blockchain != null) {
                if (blockchain.verifyBlockchain())
                    User.kadBucket.getNode(String.valueOf(node.guid)).incrementInteractions();
                else {
                    User.kadBucket.getNode(String.valueOf(node.guid)).incrementUnsuccessfulInteractions();
                    verifyNodesIntegrity(kadBucket.getNode(String.valueOf(node.guid)));
                }
            }
        }
    }


    public static void verifyNodesIntegrity(Node node) {
        double integrity = -1;
        if(node.sumInteractions >= 1)
            integrity = node.successInteractions / node.sumInteractions;
        if(integrity < Settings.min_reputation)
            User.TrashNodes(node);
    }

    public static void TrashNodes(Node node) {
        kadBucket.removeNodeFromList(node);
        addNodesToTrash(node);
    }

    public static void addNodesToTrash(Node node) {
        trashList.add(node);
    }

    public static boolean isTrash(Node node) {
        return trashList.contains(node);
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
                User.Trust();

                for(int i = 0;i < User.kadBucket.lastSeenNodes.size(); i++) {
                    if(!new ClientGRPC(User.kadBucket.lastSeenNodes.get(i).ipAddress, User.kadBucket.lastSeenNodes.get(i).portNo).ping()){
                        User.kadBucket.lastSeenNodes.remove(i);
                        i--;
                    }
                }
            }
        }
    }

    private static class MiningBlockThread extends Thread {
        public MiningBlockThread() {}

        public void run() {
            while(true) {
                try {
                    TimeUnit.MICROSECONDS.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                if(User.blockchain.getFromWaitingTransactions().size() >= 1) {
                    String hashBlock = Settings.calculateSHA256(new Date().getTime() + "");
                    User.blockchain.mineWaitingTransactions(User.wallet);
                    User.wallet.upgradeLedger(User.blockchain.getLastBlock());
                }
            }
        }
    }
}