package p2p;

import java.math.BigInteger;

public class Kademlia {
    public static boolean pingNode(Node node) {
        return new ClientGRPC(node.ipAddress, node.portNo).ping();
    }

    // Distance between 2 nodes using ht
    public static String xorDist(String node1, String node2) {
        String finalXor = "";
        node1 = stringToBitString(node1);
        node2 = stringToBitString(node2);

        if(node1.length() != node2.length()) {
            if(node1.length() > node2.length()) {

            }
            else
        }


        return finalXor;

    }

    public static String stringToBitString(String str) {
        return new BigInteger(str,16).toString(2);
    }

}
