import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class lab7 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 1. Create a new ArrayList and print the collection by position
        System.out.println("\n----- 1. Create and Print ArrayList -----");
        ArrayList<String> stringList = new ArrayList<>();
        stringList.add("Apple");
        stringList.add("Banana");
        stringList.add("Orange");
        stringList.add("Grapes");

        System.out.println("ArrayList elements by position:");
        for (int i = 0; i < stringList.size(); i++) {
            System.out.println("Index " + i + ": " + stringList.get(i));
        }

        // 2. Add some elements (strings)
        System.out.println("\n----- 2. Add Elements -----");
        System.out.print("Enter a string to add to the list: ");
        String newElement = scanner.nextLine();
        stringList.add(newElement);
        System.out.println("ArrayList after adding \"" + newElement + "\": " + stringList);

        // 3. Search an element in an ArrayList
        System.out.println("\n----- 3. Search Element -----");
        System.out.print("Enter a string to search in the list: ");
        String searchElement = scanner.nextLine();
        int index = stringList.indexOf(searchElement);
        if (index != -1) {
            System.out.println("Element \"" + searchElement + "\" found at index: " + index);
        } else {
            System.out.println("Element \"" + searchElement + "\" not found in the list.");
        }

        // 4. Reverse elements in an ArrayList
        System.out.println("\n----- 4. Reverse Elements -----");
        System.out.println("Original ArrayList: " + stringList);
        Collections.reverse(stringList);
        System.out.println("Reversed ArrayList: " + stringList);

        // 5. Test if an ArrayList is empty or not
        System.out.println("\n----- 5. Test if ArrayList is Empty -----");
        System.out.println("Is the ArrayList empty? " + stringList.isEmpty());

        // 6. Join two ArrayLists
        System.out.println("\n----- 6. Join Two ArrayLists -----");
        ArrayList<String> anotherList = new ArrayList<>();
        anotherList.add("Mango");
        anotherList.add("Kiwi");
        System.out.println("First ArrayList: " + stringList);
        System.out.println("Second ArrayList: " + anotherList);

        stringList.addAll(anotherList); // Join the lists
        System.out.println("ArrayList after joining: " + stringList);

        //Demonstrate creating an array list with initial capacity
        System.out.println("\n----- 7. ArrayList with initial capacity -----");
        ArrayList<Integer> numbersList = new ArrayList<>(10);  //initial capacity of 10
        numbersList.add(100);
        numbersList.add(200);
        numbersList.add(300);
        System.out.println("Numbers ArrayList: " + numbersList);

        //Demonstrate using the subList method
        System.out.println("\n----- 8. ArrayList subList -----");
        List<String> subList = stringList.subList(2, 5); //from index 2 to 4 (exclusive of 5)
        System.out.println("Sublist from index 2 to 4: " + subList);

        //Demonstrate clearing an arrayList
        System.out.println("\n----- 9. Clearing an ArrayList -----");
        System.out.println("Original ArrayList: " + stringList);
        stringList.clear();
        System.out.println("ArrayList after clearing: " + stringList);
        System.out.println("Is the ArrayList empty after clearing? " + stringList.isEmpty());

        scanner.close();
    }
}

