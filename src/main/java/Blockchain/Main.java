package Blockchain;

import p2p.Kademlia;
import p2p.ServerGRPC;
import p2p.User;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void menu() {
        int i=1;
        System.out.println("Menu\n" +
                (i++) + "Personal Information\n" +
                (i++) + "Mining\n" +
                "Choose an option.\n");
    }

    public static void main(String[] args) throws IOException {
        int port = 12765;

        if(Settings.bootstrapNode.equals("")) {
            port = 8080;
        }
        User userTest = new User();
        String ip = "localhost";
        //System.out.println("Debug");
        userTest.iniateUser(port,ip);
        //System.out.println("Debug2");

        ServerGRPC server = new ServerGRPC("localhost", port);
        server.start();

        userTest.startPing();
        Kademlia.findNode(User.id);

        Scanner in = new Scanner(System.in);
        while(true) {
            menu();
            int n = in.nextInt();
            in.nextLine();
            switch (n) {
                case 1:
                    System.out.println("ID: " + User.id);
                    System.out.println("IP: " + User.ipAddress + " Port: " + User.portNo);
                    System.out.println("PublicKey: " + User.publicKey);
                    break;
                case 2:
                    User.startMining();
                    break;
                default:
                    System.out.println("That option does not exist.");
                    break;
            }
        }




    }

}