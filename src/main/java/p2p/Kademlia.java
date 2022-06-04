package p2p;

import java.math.BigInteger;

public class Kademlia {
    public static boolean pingNode(Node node) {
        return new ClientGRPC(node.ipAddress, node.portNo).ping();
    }

    // Distance between 2 nodes using xor distance
    public static String xorDist(String node1, String node2) {
        String finalXor = "";
        node1 = stringToBitString(node1);
        node2 = stringToBitString(node2);

        int maxLength = Math.max(node1.length(),node2.length());

        if(node1.length() != node2.length()) {
            if(node1.length() > node2.length()) {
                node2 = addPadding(node1.length() - node2.length()) + node2;
            }
            else node1 = addPadding(node2.length() - node1.length()) + node1;
        }

        for(int i = maxLength - 1; i >= 0; i--) {
            if(node1.charAt(i) != node2.charAt(i)) finalXor = "1" + finalXor;
            else finalXor = "0" + finalXor;
        }


        return finalXor;

    }

    public static String stringToBitString(String str) {
        return new BigInteger(str,16).toString(2);
    }

    public static String addPadding(int n) {
        return "0".repeat(Math.max(0,n));
    }

}
