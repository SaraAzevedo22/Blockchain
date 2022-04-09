package Blockchain;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Block {

    public String hashBlock;
    public int previousHash;
    public List<Transaction> transactions;
    public int nonce = 0;
    public long timestamp;
    public static int difficulty = 5;

    //Constructor to create the Block
    public Block(int previousHash, List<Transaction> transactions) {
        this.hashBlock = calculateHash();
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.nonce = nonce;
        this.timestamp = new Date().getTime();

        //Object[] content = {Arrays.hashCode(transactions), previousHash};
        //this.blockHash = Arrays.hashCode(content);
    }

    //Calculate actual hash
    public String calculateHash() {
        //TODO Call merkle tree
        return "";
    }

    // TODO Change the difficulty
    //Mining the block
    public int mineBlock(int difficulty) {
        String prefix = new String(new char[difficulty]).replace('\0','0');
        while(!hashBlock.substring(0, difficulty).equals(prefix)) {
            nonce++;
            hashBlock = calculateHash();
        }
        System.out.println("Nonce that Solves proof of work:" + nonce);
        System.out.println("Hash Calculated: " + this.hashBlock);
        return nonce;
    }

    //Getters and Setters
    public int getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(int previousHash) {
        this.previousHash = previousHash;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    //Equals and Hash Code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Block block = (Block) o;
        return previousHash == block.previousHash && Objects.equals(transactions, block.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(previousHash, transactions);
    }
}
