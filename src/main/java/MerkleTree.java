import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
//import java.security;


public class MerkleTree {
    String root; //Merkle Tree root
    List<String> transactionsList; //List of Transactions

    public MerkleTree(List<String> transactionsList) {
        this.transactionsList = transactionsList;
        root = "";
    }

    public void merkle_tree() {
        List<String> tempTransactionsList = new ArrayList<String>();

        for(int i = 0;i < this.transactionsList.size(); i++) {
            tempTransactionsList.add(this.transactionsList.get(i));
        }

        List<String> newTransactionsList = getTransactionsList(tempTransactionsList);
        while(newTransactionsList.size() != 1) {
            newTransactionsList =getTransactionsList(newTransactionsList);
        }

        this.root = newTransactionsList.get(0);
    }

    private List<String> getTransactionsList(List<String> tempTransactionsList) {
        List<String> finalList = new ArrayList<String>();
        int i = 0;
        while(i < tempTransactionsList.size()) {
            String left = tempTransactionsList.get(i);
            i++;

            String right = "";
            if(i != tempTransactionsList.size()) {
                right = tempTransactionsList.get(i);
            }
            String finalHash = left + right;
            MessageDigest digest = null;
            try {
                digest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            byte[] stringHash = digest.digest(
                    finalHash.getBytes(StandardCharsets.UTF_8));
            finalList.add(String.valueOf(stringHash));
            i++;
        }
        return finalList;
    }
}