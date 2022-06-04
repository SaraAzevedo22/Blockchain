package p2p;

import com.proto.test.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import java.util.concurrent.TimeUnit;

public class ClientGRPC {
    private String ip = "localhost";
    public P2PGrpc.P2PBlockingStub blockingStub;
    private int port = 50051;
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
        Ping request = Ping.newBuilder()
                .setId(User.id)
                .setIpAddress(User.ipAddress)
                .setPortNo(User.portNo)
                .setNonce(User.nonce)
                .setPublicKey(User.publicKey)
                .build();
        PingResponse response = blockingStub.ping(request);
        if(response != null) return true;
        else return false;
    }

    /*
    public TreeSet<Contact> findNode(String target) {
        KNodes foundNodes = blockingStub.findNode(FindNode.newBuilder().setId(User.id).setIpAddress(User.ipAddress).setPortNo(User.portNo).setNonce(User.nonce).setTarget(target).build());

    } */




    // On the client side, the client has a stub (referred to as just a client in some languages) that provides the same methods as the server.


}
