package p2p;

import java.util.HashSet;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Set;

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
            if(node1.length() > node2.length()) { node2 = addPadding(node1.length() - node2.length()) + node2; }
            else { node1 = addPadding(node2.length() - node1.length()) + node1; }
        }

        int i=maxLength-1;
        while(i >= 0) {
            if(node1.charAt(i) != node2.charAt(i)) { finalXor = "1" + finalXor; }
            else { finalXor = "0" + finalXor; }
            i--;
        }

        return finalXor;
    }

    public static String stringToBitString(String str) {
        return new BigInteger(str,16).toString(2);
    }

    public static String addPadding(int n) {
        String str = "0".repeat(Math.max(0,n));
        return str;
    }

    public static Node findNode(String target) {
        ArrayList<Node> nodesFound = new ArrayList<>();
        ArrayList<Node> closestNodes = User.kadBucket.getNClosestNodes(target, User.kadBucket.lastSeenNodes);
        ArrayList<Node> recentSeen = null;

        while(true) {
            for(Node user: closestNodes) {
                ArrayList<Node> temp = new ClientGRPC(user.ipAddress, user.portNo).findNode(target);
                nodesFound.addAll(temp);
            }

            removeDuplicated(nodesFound);

            for(Node node: nodesFound) {
                if(node.guid.equals(target)) {
                    nodesFound.remove(node);
                    break;
                }
            }

            removeDuplicated(nodesFound);
            User.kadBucket.doesNodeExistInList(nodesFound);

            if(recentSeen == null) recentSeen = new ArrayList<>();
            else if(recentSeen.containsAll(nodesFound)) return null;

            recentSeen.clear();
            recentSeen.addAll(nodesFound);

            closestNodes = User.kadBucket.getNeighbours(nodesFound, target);
            nodesFound.clear();
        }
    }

    private static void removeDuplicated(ArrayList<Node> users) {
        Set<Node> user = new HashSet<>(users);
        user.clear();
        user.addAll(users);
    }
}
