package p2p;
import io.grpc.ServerBuilder;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ServerGrpc{

    private io.grpc.Server server;

    public void start() throws IOException {
        server = ServerBuilder.forPort(50051).build();
        server.start();

        Runtime.getRuntime().addShutdownHook(new Thread(()-> {
            try {
                ClientGRPC.this.stop();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }

    private void stop() throws InterruptedException {
        if(server != null)
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);

    }

}
