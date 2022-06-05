package p2p;

import java.util.Objects;

public class Node {
    int portNo;
    String ipAddress;
    String guid;
    int successInteractions;
    int sumInteractions;

    public Node(String guid, String ipAddress, int portNo) {
        this.guid = guid;
        this.ipAddress = ipAddress;
        this.portNo = portNo;
        this.successInteractions = 0;
        this.sumInteractions = 0;
    }

    public String getGuid() {
        return this.guid;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public int getPortNo() {
        return this.portNo;
    }

    public void incrementInteractions() {
        this.successInteractions++;
        this.sumInteractions++;
    }

    public void incrementUnsuccessfulInteractions() {
        this.sumInteractions++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return portNo == node.portNo && Objects.equals(guid, node.guid) && Objects.equals(ipAddress, node.ipAddress);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guid, ipAddress, portNo);
    }
}