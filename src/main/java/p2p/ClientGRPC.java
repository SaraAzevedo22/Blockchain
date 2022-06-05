package p2p;

import Blockchain.Blockchain;
import com.proto.test.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ClientGRPC {
    private String ip = "localhost";
    public P2PGrpc.P2PBlockingStub blockingStub;
    private int port;
    private ManagedChannel channel;

    public ClientGRPC(String ip, int port) {
        this(ManagedChannelBuilder.forAddress(ip, port)
                .usePlaintext()
                .build());
        this.ip = ip;
        this.port = port;
    }

    ClientGRPC(ManagedChannel channel) {
        this.channel = channel;
        blockingStub = P2PGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(4, TimeUnit.SECONDS);
    }

    public boolean ping() {
        System.out.println("MEKIE MERMAO");
        Ping request = Ping.newBuilder()
                .setId(User.id)
                .setIpAddress(User.ipAddress)
                .setPortNo(User.portNo)
                .setProof(User.proof)
                .setPublicKey(User.publicKey)
                .build();
        System.out.println("AQUIIIIIIIIIIIIIII");
        System.out.println("request = " + request);
        PingResponse response = blockingStub.ping(request);
        System.out.println("BOAAAAAAAAAAAAAAAAAAAS");
        if(response != null) return true;
        else return false;
    }

    public ArrayList<Node> findNode(String target) {
        try {
            try {

                KNodes foundNodes = blockingStub.findNode(FindNode.newBuilder().setId(User.id).setIpAddress(User.ipAddress).setPortNo(User.portNo).setProof(User.proof).setPublicKey(User.publicKey).setTarget(target).build());
                ArrayList<Node> nodeList = GRPCForKBucket(foundNodes);

                return nodeList;
            } catch (StatusRuntimeException e) {

                return null;
            }
        } finally {
            try {
                ClientGRPC.this.shutdown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static ArrayList<Node> GRPCForKBucket(KNodes kbucket){
        ArrayList<NodeforKNodes> nodeList = new ArrayList<>(kbucket.getKbucketList());
        ArrayList<Node> a = new ArrayList<>();
        for (NodeforKNodes node : nodeList)
            a.add(GRPCForNode(node));

        return a;
    }

    public static Node GRPCForNode(NodeforKNodes node){
        return new Node(node.getId(),node.getIpAddress(),node.getPortNo());
    }
}