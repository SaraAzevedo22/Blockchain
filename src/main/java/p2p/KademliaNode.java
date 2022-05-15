package p2p;

public class KademliaNode {
    String guid;
    String ipAddress;
    int portNo;
    // Routing Table
    //Storage

    public KademliaNode(String id, String ipAddress, int portNo) {
        this.guid = id;
        this.ipAddress = ipAddress;
        this.portNo = portNo;
    }

}
