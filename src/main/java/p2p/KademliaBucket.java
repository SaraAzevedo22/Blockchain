package p2p;

import java.util.TreeSet;

public class KademliaBucket {
    int depth;
    TreeSet<Contact> contacts; //Contacts stored in routing table
    TreeSet<Contact> backupContacts;
    final int BUCKET_SIZE = 20;


    public KademliaBucket(int depth) {
        this.depth = depth;
        contacts = new TreeSet<>();
        backupContacts = new TreeSet<>();
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
