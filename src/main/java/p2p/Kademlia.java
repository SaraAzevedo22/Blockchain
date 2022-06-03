package p2p;

public class Kademlia {
    public static boolean pingNode(Node node) {
        return new ClientGRPC(node.ipAddress, node.portNo).ping();
    }

}
