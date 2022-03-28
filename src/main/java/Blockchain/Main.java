package Blockchain;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args){

        List<Block> blockchain = new ArrayList<>();

        Transaction transaction1 = new Transaction("Peter", "Sam", 15000L);
        Transaction transaction2 = new Transaction("Sam", "Ryan", 10500L);
        Transaction transaction3 = new Transaction("Sam", "Ryan", 1000L);


        Block firstBlock = new Block(0, Arrays.asList(transaction1, transaction2));
        System.out.println(firstBlock.hashCode());
        blockchain.add(firstBlock);

        Block secondBlock = new Block(firstBlock.hashCode(), Arrays.asList(transaction2, transaction3));
        System.out.println(secondBlock.hashCode());
        blockchain.add(secondBlock);

        System.out.println(blockchain);
    }
}
