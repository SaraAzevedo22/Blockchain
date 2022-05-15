package Blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    public List<Block> chain = new ArrayList<>();  // TODO <Block> ??
    public List<Transaction> waitingTransactions = new ArrayList<>();  // TODO <> ??

    //Constructor
    public Blockchain(List<Block> chain, List<Transaction> waitingTransactions) {
        this.chain = chain;
        this.waitingTransactions = waitingTransactions;
    }

    //Add waiting new pending transactions to the waiting transactions list
    public void addToWaitingTransactions(List<Transaction> pendingTransactions){
        int temp=0;
        while(temp< pendingTransactions.size()){
            Transaction actualTransaction = pendingTransactions.get(temp);
            waitingTransactions.add(new Transaction(actualTransaction.hashBlock, actualTransaction.sourceName, actualTransaction.destinationName, actualTransaction.timestamp, actualTransaction.sum));
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
                newTransactionList.add(new Transaction(oldTransaction.hashBlock, oldTransaction.sourceName, oldTransaction.destinationName, oldTransaction.timestamp, oldTransaction.sum));
            }
            chain.add(new Block(actualBlock.hashId, actualBlock.previousHash, actualBlock.transactions));
        }
    }

    //Get the chain
    public List<Block> getChain(){return chain;}

    //Add blocks to the chain
    public  boolean addBlock(Block newBlock) {

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
        newBlock.previousHash = this.getLatestBlock().hashBlock;

        chain.add(newBlock);
        System.out.println("Block added SUCCESSFULLY.");
        return true;
    }


    public boolean isValid() {
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

    public void printChain() {
        System.out.println("Blockchain:");
        String verifyChain;
        if(this.isValid())
            verifyChain = "True";
        else
            verifyChain = "False";
        System.out.println("\nChain valid:" + verifyChain);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }
}
