package p2p;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerGrpc{

    private io.grpc.Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(50051).build();
        server.start();
    }

    private void stop() throws InterruptedException {

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

}
