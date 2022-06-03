package p2p;

import com.proto.test.Ping;
import com.proto.test.TestServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import com.proto.test.*;

import java.util.concurrent.TimeUnit;

public class ClientGRPC {
    private String ip = "localhost";
    public TestServiceGrpc.TestServiceBlockingStub blockingStub;
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
        blockingStub = TestServiceGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(4, TimeUnit.SECONDS);
    }

    /*
    public boolean ping() {
        try{
            Ping request = Ping.newBuilder()
                    .setId(User.id)
                    .setIp(User.ipAddress)
                    .setPort(User.portNo)
                    .setPubKey(User.publicKey)
                    .build();
            PingResponse response =
        }
    } */

    // On the client side, the client has a stub (referred to as just a client in some languages) that provides the same methods as the server.


}