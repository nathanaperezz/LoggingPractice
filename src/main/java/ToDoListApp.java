//Nathan Perez
//To do list that allows adding, deleting, and printing - with logging

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


import java.util.ArrayList;
import java.util.Scanner;


public class ToDoListApp {

    static Scanner scan = new Scanner(System.in);

    static final Logger logger = (Logger) LogManager.getLogger("myLogger");

    public static void main(String[] args) {

        logger.info("creating to do list");

        ArrayList<String> toDo = new ArrayList<>();

        int userOption = 0;
        while(userOption != -1) {

            System.out.println("\nEnter a number to perform a function to your to do list.");
            System.out.println("1  - Add new item");
            System.out.println("2  - Delete an item");
            System.out.println("3  - Print the list");
            System.out.println("-1 - Quit");

            userOption = scan.nextInt();

            if (userOption == 1) {
                logger.debug("running UserInputAddItem");
                UserInputAddItem(toDo);
                logger.info("item added");
            }
            else if (userOption == 2) {
                logger.debug("running UserInputDeleteItem");
                UserInputDeleteItem(toDo);
                logger.info("item deleted");
            }
            else if (userOption == 3) {
                logger.debug("running PrintToDoList");
                PrintToDoList(toDo);
                logger.info("list printed");
            }
            else if (userOption != -1) {
                logger.warn("invalid user input");
                System.out.println("Error please type a valid option");
            }
        }
        logger.info("exiting to do list");
    }

    public static void UserInputAddItem (ArrayList<String> toDo) {

        String newItem;

        System.out.print("New item: ");
        scan.nextLine();
        newItem = scan.nextLine();

        AddItem(newItem, toDo);
        System.out.println("\n" + newItem + " has been added to your to do list!");
    }

    public static void AddItem (String item, ArrayList<String> toDo) {
        toDo.add(item);
    }


    public static void UserInputDeleteItem (ArrayList<String> toDo) {

        int place;

        System.out.println("Please enter the number of the item you would like to delete: ");
        place = scan.nextInt();

        if(DeleteItem((place - 1), toDo)) {
            System.out.println("Item " + place + " has been deleted from your list.");
        }
        else System.out.println("Item " + place + " could not be deleted.");
    }

    //returns true if item was successfully deleted, else false
    public static boolean DeleteItem (int placeInArray, ArrayList<String> toDo) {
        try {
            toDo.remove(placeInArray);
            return true;
        }
        catch (Exception e){
            logger.error("no item at " + placeInArray + ".");
            return false;
        }
    }

    public static void PrintToDoList (ArrayList<String> toDo) {
        if(toDo.isEmpty()) {
            logger.warn("printing empty list");
        }

        System.out.println("To do: ");

        for(int i = 0; i < toDo.size(); i++) {
            System.out.println(i+1 + ". " + toDo.get(i));
        }
    }
}

