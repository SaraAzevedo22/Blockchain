package p2p;

import java.util.Arrays;

public class KademliaRoutingTable {
    Node currentNode;
    KademliaBucket[] kbuckets;
    final int ID_LENGTH = 160;

    public KademliaRoutingTable(Node currentNode) {
        this.currentNode = currentNode;
        this.initialize();
    }

    public void initialize() {
        this.kbuckets = new KademliaBucket[ID_LENGTH];
        for(int i = 0;i < ID_LENGTH; i++) kbuckets[i] = new KademliaBucket(i);
    }


    public Node getCurrentNode() {
        return currentNode;
    }

    public void setCurrentNode(Node currentNode) {
        this.currentNode = currentNode;
    }

    public KademliaBucket[] getKbuckets() {
        return kbuckets;
    }

    public void setKbuckets(KademliaBucket[] kbuckets) {
        this.kbuckets = kbuckets;
    }

    @Override
    public String toString() {
        return "KademliaRoutingTable{" +
                "currentNode=" + currentNode +
                ", kbuckets=" + Arrays.toString(kbuckets) +
                '}';
    }
}
