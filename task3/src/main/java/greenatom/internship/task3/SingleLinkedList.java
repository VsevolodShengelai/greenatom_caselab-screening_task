package greenatom.internship.task3;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList<T> {

    ListItem<T> head;
    ListItem<T> tail;

    private static class ListItem<T>{
        T data;
        ListItem<T> next;
    }

    public boolean isEmpty(){
        return head == null;
    }

    // Метод бобаления элемента списа в конец списка
    public void addToEnd(T item){
        ListItem<T> newItem =  new ListItem<>();
        newItem.data = item;
        if (isEmpty()){
            head = newItem;
            tail = newItem;
        }
        else {
            tail.next = newItem;
            tail = newItem;
        }
    }

    //Метод инвертации односвязного списка
    public void reverse(){
        if (!isEmpty() && head.next != null){
            tail = head;
            ListItem<T> current = head.next;
            head.next = null;
            while (current!=null){
                ListItem<T> next = current.next;
                current.next = head;
                head = current;
                current = next;
            }
        }
    }

    // Метод для получения всех элементов в виде списка
    public List<T> getAllItems() {
        List<T> items = new ArrayList<>();
        ListItem<T> current = head;
        while (current != null) {
            items.add(current.data);
            current = current.next;
        }
        return items;
    }
}
