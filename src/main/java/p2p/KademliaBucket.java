package p2p;

import java.util.TreeSet;

public class KademliaBucket {
    int depth;
    TreeSet<Contact> contacts; //Contacts stored in routing table
    TreeSet<Contact> lastSeenContacts;


    public KademliaBucket(int depth) {
        this.depth = depth;
    }

     public void insertContact(Contact contact) {
        if(!(this.contacts.contains(contact))) {

        }
        /**
         * The contact is already in the bucket, so we update that we've seen it
         * We remove and re-add the contact to get the Sorted Set to update sort order
         */
        else {
            Node tempNo = contact.getNode();
            Contact tempC = removeFromContacts(tempNo);
            tempC.setContactSeen();
            this.contacts.add(tempC);
        }
    }

    public void removeContact(Contact contact) {

    }

    public Contact removeFromContacts(Node no) {
        for(Contact contact : this.contacts) {
            if(contact.getNode().equals(no)) {
                this.contacts.remove(contact);
                return contact;
            }
        }
    }



    public int getDepth() {
        return this.depth;
    }

}
