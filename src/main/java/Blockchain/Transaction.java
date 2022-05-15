package Blockchain;
import java.util.Date;
import java.util.Objects;

public class Transaction {

    public String sourceName;
    public String destinationName;
    public Long sum;
    public long timestamp;
    public String hashBlock;

    //Constructor to create Transaction
    public Transaction(String hashBlock, String sourceName, String destinationName, long timestamp, Long sum) {
        this.hashBlock = hashBlock;
        this.sourceName = sourceName;
        this.destinationName = destinationName;
        this.timestamp = timestamp;
        this.sum = this.sum;
        transactionIsValid();
    }

    public void transactionIsValid(){
        this.timestamp = new Date().getTime();
        this.hashBlock = calculateHash();
    }

    public String calculateHash(){
        return Config.calculateSHA256(this.sourceName + this.destinationName + this.timestamp + this.sum);
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
