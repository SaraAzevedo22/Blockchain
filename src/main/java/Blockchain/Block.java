package Blockchain;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Block {

    public int hashBlock;
    public int previousHash;
    public List<Transaction> transactions;
    public int nonce = 0;
    public long timestamp;

    //Constructor
    public Block(int previousHash, List<Transaction> transactions) {
        this.previousHash = previousHash;
        this.transactions = transactions;
        this.nonce = nonce;
        this.timestamp = new Date().getTime();

        //Object[] content = {Arrays.hashCode(transactions), previousHash};
        //this.blockHash = Arrays.hashCode(content);
    }

    //Calculate actual hash
    public int calculateHash() {
        //TODO Call merkle tree
        return 0;
    }

    // TODO mudar o x (prefix)
    //Mining the block
    public String mineBlock(int x) {
        String prefix = new String(new char[x]).replace('\0','0');
        while(!hashBlock.substring(0,x).equals(hashBlock)) {
            nonce++;
            hashBlock = calculateHash();
        }
        return hashBlock;
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
