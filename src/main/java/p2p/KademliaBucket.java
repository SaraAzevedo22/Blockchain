package p2p;

import Blockchain.Settings;
import java.util.ArrayList;
import java.util.TreeSet;

public class KademliaBucket {
    int depth;
    public static ArrayList<Node> lastSeenNodes = new ArrayList<>();
    public static TreeSet<Contact> contacts; //Contacts stored in routing table
    TreeSet<Contact> backupContacts;
    final int BUCKET_SIZE = 20;
    final int ALPHA = 4; //Number of nodes to announce


    public KademliaBucket(int depth) {
        this.depth = depth;
        contacts = new TreeSet<>();
        backupContacts = new TreeSet<>();
    }

    public KademliaBucket() {
        lastSeenNodes = new ArrayList<>();
    }

     public void insertContact(Contact contact) {
        // The contact is not in the tree
        if(!(this.contacts.contains(contact))) {
            // We have to see if the buck is full or not

            if (this.contacts.size() >= BUCKET_SIZE) {
                Contact useless = null;
                //We check if any contacts are unavailable
                for (Contact temp : this.contacts) {
                    // 1 means that the contact was unavailable at least one time
                    if (temp.getUnavblCount() >= 1) {
                        if (temp.getUnavblCount() > useless.getUnavblCount()) {
                            useless = temp;
                        }
                        else useless = temp;
                    }
                }
                if (useless != null) {
                    this.contacts.remove(useless);
                    this.contacts.add(contact);
                }
                else this.insertInBackupContacts(contact);
            }
            else this.contacts.add(contact);
        }
        /**
         * The contact is already in the bucket, so we update that we've seen it
         * We remove and re-add the contact to get the Sorted Set to update sort order
         */
        else {
            Node tempNo = contact.getNode();
            Contact tempC = removeFromContacts(tempNo);
            tempC.setUnavblCountToZero();
            tempC.setContactSeen();
            this.contacts.add(tempC);
        }
    }


    public void insertInBackupContacts(Contact contact) {
        if(this.backupContacts.size() > BUCKET_SIZE) {
            this.backupContacts.remove(this.backupContacts.last());
            this.backupContacts.add(contact);
        }

        else if(this.backupContacts.contains(contact)) {
            Contact temp = this.removeFromBackupContacts(contact.getNode());
            temp.setContactSeen();
            this.backupContacts.add(temp);
        }
        else this.backupContacts.add(contact);
    }

    public Contact removeFromBackupContacts(Node node) {
        for(Contact contact : this.backupContacts) {
            Node tempNo = contact.getNode();
            if(tempNo.equals(node)) {
                this.backupContacts.remove(contact);
                return contact;
            }
        }
        return null;
    }

    public Contact removeFromContacts(Node no) {
        for (Contact contact : this.contacts) {
            if (contact.getNode().equals(no)) {
                this.contacts.remove(contact);
                return contact;
            }
        }
        return null;
    }


    public static TreeSet<Contact> getClonedTreeSet() {
        return (TreeSet)contacts.clone();
    }

    public int getTreeSize() {
        return this.contacts.size();
    }

    /**
     * lastSeen Functions
     */

    public Node getOldestNode() {
        return lastSeenNodes.get(0);
    }

    public Node getNode(String idNode) {
        for(Node node: this.lastSeenNodes) {
            if(idNode.equals(node.guid)) return node;
        }
        return null;
    }

    public void sendNodeToEnd(Node node){
        lastSeenNodes.remove(node);
        lastSeenNodes.add(node);
    }

    public void removeNodeFromList(Node node) {
        lastSeenNodes.remove(node);
    }

    public ArrayList<Node> getLastSeenNodes() {
        return lastSeenNodes;
    }

    public void setLastSeenNodes(ArrayList<Node> lastSeenNodes) {
        this.lastSeenNodes = lastSeenNodes;
    }

    public void addNodeToList(Node node) {
        lastSeenNodes.add(node);
    }

    public boolean doesNodeExist(Node node, int proof, String publicKey) {
        if(node.guid.equals(User.id)) return true; //The node is verifying itself

        if(proof != -1) {
            if(!isNodeValid(node.guid,node.ipAddress,node.portNo,proof,publicKey)) return false;
        }

        if(lastSeenNodes.contains(node)) {
            sendNodeToEnd(node);
            return true;
        }

        if(BUCKET_SIZE > lastSeenNodes.size()) {
            addNodeToList(node);
        }
        else {
            if(!Kademlia.pingNode(getOldestNode())) {
                removeNodeFromList(getOldestNode());
                addNodeToList(node);
            }
            else sendNodeToEnd(getOldestNode());
        }
        return false;
    }


    public boolean isNodeValid(String id, String ipAddress, int portNo, int proof, String publicKey) {
        return Settings.calculateSHA256(ipAddress + portNo + proof + publicKey).equals(id);
    }

    public ArrayList<Node> getNeighbours(ArrayList<Node> nodesList, String string) {
        ArrayList<Node> finalList = (ArrayList<Node>)nodesList.clone();

        finalList.sort((node1,node2) -> {
            String xor1 = Kademlia.xorDist(node1.guid, string);
            String xor2 = Kademlia.xorDist(node2.guid, string);

            return compareDistance(xor1,xor2);
        });

        return finalList;
    }

    public static int compareDistance(String s1, String s2) {
        for(int i = 0; i < s1.length();i++) {
            if(s1.length() > s2.length()) return 1;
            else if(s1.length() < s2.length()) return -1;
        }
        return 0;
    }

    public ArrayList<Node> getNClosestNodes(String target, ArrayList<Node> nodes) {
        if(ALPHA > nodes.size()) return getNeighbours(nodes, target);
        else return (ArrayList<Node>) getNeighbours(nodes, target).subList(0,ALPHA -1);
    }

    public void doesNodeExistInList(ArrayList<Node> list) {
        int proof = -1;

        for(Node node: list) doesNodeExist(node,proof,"");
    }

    public static ArrayList<Node> getClonedList() {
        return (ArrayList)lastSeenNodes.clone();
    }

    /**
     * Getters and Setters
     */
    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public TreeSet<Contact> getContacts() {
        return contacts;
    }

    public void setContacts(TreeSet<Contact> contacts) {
        this.contacts = contacts;
    }

    public TreeSet<Contact> getBackupContacts() {
        return backupContacts;
    }

    public void setBackupContacts(TreeSet<Contact> backupContacts) {
        this.backupContacts = backupContacts;
    }

    public int getBUCKET_SIZE() {
        return BUCKET_SIZE;
    }

    @Override
    public String toString() {
        return "KademliaBucket{" +
                "depth=" + depth +
                ", contacts=" + contacts +
                ", backupContacts=" + backupContacts +
                ", BUCKET_SIZE=" + BUCKET_SIZE +
                '}';
    }

}
