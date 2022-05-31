package p2p;

public class KademliaNode {
    Node node;
    KademliaRoutingTable routingTable;
    String storage;

    public KademliaNode(Node node, KademliaRoutingTable routingTable, String storage) {
        this.node = node;
        this.routingTable = routingTable;
        this.storage = storage;
    }


    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public KademliaRoutingTable getRoutingTable() {
        return routingTable;
    }

    public void setRoutingTable(KademliaRoutingTable routingTable) {
        this.routingTable = routingTable;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }
}
