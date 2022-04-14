package Blockchain;
import java.lang.String;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class Block extends Config{
    public String hashId;
    public String hashBlock;
    public String previousHash;
    // TODO Verify - List<Transaction> was changed to List<String>
    public List<String> transactions;
    public int nonce = 0;
    public long timestamp;
    public static int difficulty = 5;

    //Constructor to create the Block
    public Block(String hashId, String previousHash, List<String> transactions) {
        this.hashId = hashId;
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
        MerkleTree merkleTree = new MerkleTree(transactions);
        merkleTree.merkle_tree();
        // TODO Get MerkleTreeRoot??
        return calculateSHA256(this.hashId + this.previousHash + this.timestamp + this.nonce);
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
/*
    //Getters and Setters
    public String getPreviousHash() {
        return previousHash;
    }

    public void setPreviousHash(String previousHash) {
        this.previousHash = previousHash;
    }

    public List<String> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<String> transactions) {
        this.transactions = transactions;
    }*/

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
