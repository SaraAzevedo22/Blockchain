package Testing;

import Blockchain.Blockchain;
import p2p.Wallet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;

public class MainTest {
    static Wallet walletSend;
    static Wallet walletReceived;
    static Blockchain blockchainTest;

    public static void create() throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        walletSend = new Wallet();
        walletReceived = new Wallet();
        blockchainTest = new Blockchain(walletSend);
        blockchainTest.addTransaction(1, walletSend, walletReceived);
        System.out.println(blockchainTest);
    }
}
