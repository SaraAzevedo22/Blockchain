package p2p;

import com.proto.test.*;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ServerGRPC{

    private int port;
    private String ip;
    private io.grpc.Server server;
    PeerService ps = new PeerService();

    public ServerGRPC(String ip, int port) throws UnknownHostException {
        this.ip = ip;
        this.port = port;
    }

    public void start() throws IOException {
        server = ServerBuilder.forPort(this.port).build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            System.err.println("*** Shutting down gRPC server ***");
            try {
                ServerGRPC.this.stop();

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

    public static KNodes KBucketForGRPC(ArrayList<Node> nodeList){
        ArrayList<NodeforKNodes> nodeListGrpc = new ArrayList<>();
        for (Node node : nodeList)
            nodeListGrpc.add(NodeForGRPC(node));
        return KNodes.newBuilder().addAllKbucket(nodeListGrpc).build();
    }

    public static NodeforKNodes NodeForGRPC(Node node){
        return NodeforKNodes.newBuilder().setId(node.guid).setIpAddress(node.ipAddress).setPortNo(node.portNo).build();
    }


    class PeerService extends  P2PGrpc.P2PImplBase {
        public void ping(Ping request, StreamObserver<PingResponse> responseObserver) {
            User.kadBucket.doesNodeExist(new Node(request.getId(), request.getIpAddress(),request.getPortNo()), request.getProof(), request.getPublicKey());
            responseObserver.onNext(PingResponse.newBuilder().setResponseMessage(true).build());
            responseObserver.onCompleted();
        }
        public void findNode(FindNode request, StreamObserver<KNodes> responseObserver) {
            if(User.kadBucket.doesNodeExist(new Node(request.getId(), request.getIpAddress(), request.getPortNo()), request.getProof(), request.getPublicKey())) {
                responseObserver.onNext(KBucketForGRPC(User.kadBucket.getNeighbours(User.kadBucket.lastSeenNodes, request.getTarget())));
            }
            responseObserver.onCompleted();
        }

    }

}
