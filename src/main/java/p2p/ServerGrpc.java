package p2p;
import com.proto.test.TestServiceGrpc;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;

public class ServerGrpc{

    private io.grpc.Server server;
    private String ip;
    private int port;

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

}
