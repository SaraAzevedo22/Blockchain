package Blockchain;
import p2p.Wallet;

import java.util.Date;
import java.util.Objects;
import java.security.SignatureException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;


public class Transaction {
    public String sourceName;
    public String destinationName;
    public int sum;
    public long timestamp;
    public String hashBlock;
    public byte[] signature;


    //Constructor to add income transaction
    public Transaction(String hashBlock, String sourceName, String destinationName, long timestamp, int sum, byte[] signature) {
        this.hashBlock = hashBlock;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.timestamp = timestamp;
        this.sum = sum;
        this.signature = signature;
        transactionIsValid();
    }

    //Constructor to create Transaction
    public Transaction(int sum, String sourceName, String destinationName) {
        this.sum = sum;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        transactionIsValid();
    }

    public String calculateHash(){
        return Settings.calculateSHA256(this.sourceName + this.destinationName + this.timestamp + this.sum);
    }

    public void transactionIsValid(){
        this.hashBlock = calculateHash();
        this.timestamp = new Date().getTime();
    }

    public boolean signTransaction(Wallet sign) throws NoSuchAlgorithmException, SignatureException, InvalidKeySpecException, InvalidKeyException {
        if(!this.hashBlock.equals(this.calculateHash())) {
            System.out.println("Error: the transaction was tampered!");
            return false;
        }
        this.signature = sign.signMessage(hashBlock)[1];
        return true;
    }

    /*
        //Getters and Setters
        public String getSourceName() {
            return sourceName;
        }
        public void setSourceName(String sourceName) {
            this.sourceName = sourceName;
        }
        public String getDestinationName() {
            return destinationName;
        }
        public void setDestinationName(String destinationName) {
            this.destinationName = destinationName;
        }
        public Long getSum() {
            return sum;
        }
        public void setSum(Long sum) {
            this.sum = sum;
        }
    */

    //Equals and Hash Code
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(sourceName, that.sourceName) && Objects.equals(destinationName, that.destinationName) && Objects.equals(sum, that.sum);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceName, destinationName, sum);
    }
}