package Blockchain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){

        /*
        List<Transaction> tempTxList = new ArrayList();
        long i = 2345;
        tempTxList.add("My","name", "is", i, i);
        //tempTxList.add();
        //tempTxList.add();
        //tempTxList.add("Nikhil");
        //tempTxList.add("sara");

        MerkleTree merkleTrees = new MerkleTree(tempTxList);
        merkleTrees.merkle_tree();
        System.out.println("root : " + merkleTrees.getRoot());
*/


        // b4ad42740f74141d5dc4472608c9f8aebff7fcb7694da2edbf4241f292ba5549 sara

        List<Block> blockchain = new ArrayList<>();


        long a = 2345678;
        List<Transaction> trans = new ArrayList<>();
        Transaction transaction1 = new Transaction("Peter", "Sam", "ola", 1234, a);
        System.out.println(transaction1);

        Transaction transaction2 = new Transaction("Sam", "Ryan", "ola", 1234, a);
        Transaction transaction3 = new Transaction("Sam", "Ryan", "ola", 1234, a);
        trans.add(transaction1);
        trans.add(transaction2);
        trans.add(transaction3);

        System.out.println(trans);


        Block firstBlock = new Block("0", "a12345", trans);
        System.out.println(firstBlock.hashCode());
        blockchain.add(firstBlock);

        Block secondBlock = new Block("1", firstBlock.previousHash, trans);
        System.out.println(secondBlock.hashCode());
        blockchain.add(secondBlock);

        System.out.println(blockchain);

    }

}