package Blockchain;
import java.util.ArrayList;
import java.util.List;

public class MerkleTree extends Config{
    String root; //Merkle Tree root
    List<Transaction> transactionsList; //List of Transactions

    public MerkleTree(List<Transaction> transactionsList) {
        this.transactionsList = transactionsList;
        root = "";
    }

    public void merkle_tree() {
        List<String> tempTransactionsList = new ArrayList<>();

        for(int i = 0;i < this.transactionsList.size(); i++) {
            Transaction t = this.transactionsList.get(i);
            tempTransactionsList.add(t.calculateHash());
        }

        List<String> newTransactionsList = getTransactionsList(tempTransactionsList);
        while(newTransactionsList.size() != 1) {
            newTransactionsList =getTransactionsList(newTransactionsList);
        }

        this.root = newTransactionsList.get(0);
    }

    private List<String> getTransactionsList(List<String> tempTransactionsList) {
        List<String> finalList = new ArrayList<>();
        int i = 0;
        while(i < tempTransactionsList.size()) {
            String left = tempTransactionsList.get(i);
            i++;

            String right = null;
            if(i != tempTransactionsList.size()) {
                right = tempTransactionsList.get(i);
            }
            String finalHash = left + right;
            String finalHashedString = calculateSHA256(finalHash);
            finalList.add(finalHashedString);
            i++;
        }
        return finalList;
    }

    public String getRoot(){
        return this.root;
    }
}