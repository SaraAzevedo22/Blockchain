package p2p;
import com.proto.test.*;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ServerGrpc{

    private io.grpc.Server server;
    private String ip;
    private int port;
    PeerService ps = new PeerService();

    public ServerGrpc(String ip, int port) throws UnknownHostException {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(this.port).build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.err.println("*** Shutting down gRPC server ***");
            try {
                ServerGrpc.this.stop();

            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
            System.err.println("*** Server shuts down ***");
        }));
    }

    private void stop() throws InterruptedException {
        if(server != null)
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);

    }


    class PeerService extends  P2PGrpc.P2PImplBase {
        public void ping(Ping request, StreamObserver<PingResponse> responseObserver) {
            User.kadBucket.containsNode(new Node(request.getId(), request.getIpAddress(),request.getPortNo()));
            responseObserver.onNext(PingResponse.newBuilder().setResponseMessage(true).build());
            responseObserver.onCompleted();
        }
    }

    /*
    TODO gRPC is based around the idea of defining a service, specifying the methods that can be called remotely with their parameters and return types
    * On the server side, the server implements this interface and runs a gRPC server to handle client calls.
     */

}
