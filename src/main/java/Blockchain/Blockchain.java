package Blockchain;

import p2p.Wallet;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    public static List<Block> chain = new ArrayList<>();  // TODO <Block> ??
    public List<Transaction> waitingTransactions = new ArrayList<>();  // TODO <> ??

    //Constructor
    public Blockchain(List<Block> chain, List<Transaction> waitingTransactions) {
        this.chain = chain;
        this.waitingTransactions = waitingTransactions;
    }

    // TODO call Wallet file
    public Blockchain(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        genesisBlock(wallet);
    }

    public Blockchain() {
        this.chain = new ArrayList<>();
        this.waitingTransactions = new ArrayList<>();
    }

    // TODO call Wallet file
    public Transaction addTransaction(int amount, Wallet send, Wallet received) throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        Transaction newt = new Transaction(amount, send.getPublicKey(), received.getPublicKey());
        newt.signTransaction(send);
        waitingTransactions.add(newt);
        return newt;
    }

    // TODO call Wallet file
    public void genesisBlock(Wallet wallet) throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        Wallet temp = new Wallet();
        this.addTransaction(100, temp, wallet);

    }

    //Add waiting new pending transactions to the waiting transactions list
    public void addToWaitingTransactions(List<Transaction> pendingTransactions){
        int temp=0;
        while(temp< pendingTransactions.size()){
            Transaction actualTransaction = pendingTransactions.get(temp);
            waitingTransactions.add(new Transaction(actualTransaction.hashBlock, actualTransaction.sourceName, actualTransaction.destinationName, actualTransaction.timestamp, actualTransaction.sum, actualTransaction.signature));
            temp++;
        }
    }

    //Get the waiting transactions
    public List<Transaction> getFromWaitingTransactions() {
        return waitingTransactions;
    }

    //? blocks to the chain
    public void defineChain(List<Block> oldChain) {
        for(int i=0; i<oldChain.size(); i++) {
            Block actualBlock = oldChain.get(i);
            List<Transaction> newTransactionList = new ArrayList<>();
            for(Transaction oldTransaction : actualBlock.getTransaction()) {
                newTransactionList.add(new Transaction(oldTransaction.hashBlock, oldTransaction.sourceName, oldTransaction.destinationName, oldTransaction.timestamp, oldTransaction.sum, oldTransaction.signature));
            }
            chain.add(new Block(actualBlock.hashId, actualBlock.hashBlock, actualBlock.previousHash, actualBlock.transactions, actualBlock.nonce, actualBlock.timestamp, actualBlock.publicKey));
        }
    }

    //Get the chain
    public static List<Block> getChain(){return chain;}

    //Add blocks to the chain
    public boolean addBlock(Block newBlock) {

        if (!newBlock.verify()) {
            System.out.println("Error. The block couldn't be added because the hashes don't match.");
            return false;
        }

        int transactionSize = newBlock.transactions.size();
        System.out.println(transactionSize);
        for (int i = 0; i < transactionSize; i++) {
            System.out.println(waitingTransactions.size());
            if (waitingTransactions.size() == 0) {
                return false;
            }
            else if (!waitingTransactions.get(i).equals(newBlock.transactions.get(i))) {
                System.out.println("Block add FAILED. The Transactions in the block didn't match the pending ones.");
                return false;
            }
        }

        waitingTransactions.subList(0, transactionSize).clear();
        newBlock.previousHash = this.getLastBlock().hashBlock;

        chain.add(newBlock);
        System.out.println("Block added SUCCESSFULLY.");
        return true;
    }
    // TODO isValid
    /*public boolean isValid() {
        Block previous, actual;
        String hashPrefix = new String(new char[Config.difficulty]).replace('\0','0');

        int i=1;
        while (i< chain.size()) {
            actual = chain.get(i);
            previous = chain.get(i-1);

            if(!actual.hashBlock.equals(actual.calculateHash()))
                return false;

            if(!actual.previousHash.equals(previous.hashBlock))
                return false;

            if(!actual.hashBlock.substring(0,Config.difficulty).equals(hashPrefix))
                return false;

            int k=0;
            while(k < actual.transactions.size()) {
                Transaction actualTransaction = actual.transactions.get(k);
                if(!actualTransaction.hashBlock.equals(actualTransaction.calculateHash())){
                    return false;
                }
                k++;
            }

            i++;
        }
        return true;
    }
*/

    public Block getLastBlock() {
        return chain.get(chain.size() - 1);
    }
}
