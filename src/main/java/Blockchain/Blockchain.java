package Blockchain;

import java.util.List;

public class Blockchain {
    public List<Block> chain;  // TODO <Block> ??
    public List<Transaction> waitingTransactions;  // TODO <> ??

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

    public Block getLatestBlock() {
        return chain.get(chain.size() - 1);
    }
}
