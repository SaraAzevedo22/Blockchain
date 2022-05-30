package p2p;

import java.util.Objects;

import static java.lang.System.currentTimeMillis;

public class Contact {
    Node node;
    long lastSeen;
    int unavblCount; //Number of times the node is unavailable


    public Contact(Node node) {
        this.node = node;
        this.lastSeen = currentTimeMillis()/1000L;
    }

    public Node getNode() {
        return this.node;
    }

    public long getLastSeen() {
        return lastSeen;
    }

    public void setContactSeen() {
        this.lastSeen = currentTimeMillis()/1000L;
    }

    public int getUnavblCount() {
        return unavblCount;
    }

    public void setUnavblCountToZero() {
        this.unavblCount = 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return node.equals(contact.node);
    }

    @Override
    public int hashCode() {
        return Objects.hash(node);
    }

}
