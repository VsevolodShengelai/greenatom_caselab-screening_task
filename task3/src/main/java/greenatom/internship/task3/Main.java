package greenatom.internship.task3;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        SingleLinkedList<Integer> testSingleLinkedList = new SingleLinkedList<>();

        for (int i = 0; i < 10; i++) {
            testSingleLinkedList.addToEnd(i);
        }

        List<Integer> allItems = testSingleLinkedList.getAllItems();
        for (Integer item : allItems) {
            System.out.println(item);
        }

        testSingleLinkedList.reverse();

        System.out.println("------");

        allItems = testSingleLinkedList.getAllItems();
        for (Integer item : allItems) {
            System.out.println(item);
        }


    }
}
