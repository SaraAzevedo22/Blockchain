package p2p;

import java.util.TreeSet;

public class KademliaBucket {
    int depth;
    TreeSet<Contact> contacts; //Contacts stored in routing table
    TreeSet<Contact> lastSeenContacts;

    final int BUCKET_SIZE = 20;


    public KademliaBucket(int depth) {
        this.depth = depth;
    }

     public void insertContact(Contact contact) {
        if(!(this.contacts.contains(contact))) {
            if(this.contacts.size() >= BUCKET_SIZE) {
                for(Contact temp : this.contacts) {

                }
            }
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

    public Contact getContact(Node no) {
        for(Contact contact : this.contacts) {
            if(contact.getNode().equals(no)) return contact;
        }
        return null;
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
