package p2p;

public class KademliaRoutingTable {
    Node currentNode;
    KademliaBucket[] kbuckets;

    public KademliaRoutingTable(Node currentNode, KademliaBucket[] kbuckets) {
        this.currentNode = currentNode;
        this.kbuckets = kbuckets;
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
}
