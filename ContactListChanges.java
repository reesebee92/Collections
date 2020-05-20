
/**
 * ContactListChanges
 * 
 * This class will prompt a user to enter the destination of
 * a contact list stored on their computer and read in the file.
 * The user will then be able to make changes to the file and
 * store the output in a new file. 
 * 
 * @author SDantzler
 * */
import java.util.*;
import java.io.*;

public class ContactListChanges {

   public static void main(String[] args) throws IOException {

      Scanner fileSrc = new Scanner(System.in);
      System.out.println("Please enter the file path for the contact list:");
      String filename = fileSrc.nextLine();
      // try with resources
      try (BufferedReader input = new BufferedReader(new FileReader(
            "/Users/sdantzle/Documents/Personal Reports/School/Intro to "
            + "Java/Homework/Homework11/"
                  + filename));
            PrintWriter output = new PrintWriter(
                  new BufferedWriter(new FileWriter(
                        "/Users/sdantzle/Documents/Personal Reports/School/"
                        + "Intro to Java/Homework/Homework11/Modified File.txt",
                        true)))) {

         // Create a tree map
         TreeMap<String, String> contactListUpdate = new TreeMap<String, String>(
               new LastNameSort());

         // read in the stored file
         readFile(input, contactListUpdate);

         // Prompt the user to make changes
         Scanner changes = new Scanner(System.in);
         Scanner update = new Scanner(System.in);

         // switch enumerations
         final int ADD = 1;
         final int REMOVE = 2;

         display(output, contactListUpdate);

         System.out
               .println("Would you like to make changes? Type [yes] or [no]");
         String makeChange = changes.next();

         Changes: while (makeChange.equalsIgnoreCase("yes")) {

            int updates;
            String name;
            String info;

            System.out.print(
                  "Here are the following options:\n Press [1] to add an entry, "
                        + "Press[2] to remove an entry, or Press[3] to display final changes");
            updates = update.nextInt();

            switch (updates) {
               case ADD:
                  System.out.println("Enter the new contact's first name:");
                  name = update.next();
                  System.out.println("Enter the new contact's last name:");
                  name = name + " " + update.next();
                  System.out
                        .println("Enter the new contact's phone number: ");
                  info = update.next();
                  System.out
                        .println("Enter the new contact's email address: ");
                  info = info + ", " + update.next();
                  contactListUpdate.put(name, info);
                  break;
               case REMOVE:
                  System.out.println("Enter the contact's first name:");
                  name = update.next();
                  System.out.println("Enter the contact's last name:");
                  name = name + " " + update.next();
                  contactListUpdate.remove(name);
                  break;
               default:
                  break;
            }// end switch

            // display the list after every updated change
            display(output, contactListUpdate);

            // Prompt the user for additional changes
            System.out.println(
                  "Would you like to make additional changes? Type [yes] or [no]");

            if (changes.next().equalsIgnoreCase("yes")) {
               makeChange = "yes";
            } else {
               break Changes;
            } // end if else
         } // end while loop

         // Close all of the Scanners
         changes.close();
         update.close();

      } catch (IOException ioe) {
         System.out.println("I/O Error: " + ioe);
      } // end try/catch
   }// end main method

   /**
    * readFile method
    * 
    * This method returns a TreeMap of the contact list read in from the file
    * prompted by the user.
    * 
    * @param output, contactListUpdate
    * @return contactListUpdate
    */
   public static TreeMap<String, String> readFile(BufferedReader input,
         TreeMap<String, String> contactListUpdate) {

      String line;
      String name = "";
      String info = "";
      int index;
      int space = 0;

      try {
         while ((line = input.readLine()) != null) {

            for (index = 0; index < 20; index++) {
               if ((line.charAt(index)) == ' ' && (space != 0)) {
                  continue;
               } else if (line.charAt(index) == ' ') {
                  name = name + " ";
                  space++;
               } else {
                  name = name + line.charAt(index);
               }
            } // end for loop

            space = 0; // reset space integer

            for (index = 20; index < 55; index++) {
               if (line.charAt(index) == ' ' && space != 0) {
                  continue;
               } else if (line.charAt(index) == ' ') {
                  info = info + " ";
                  space++;
               } else {
                  info = info + line.charAt(index);
               }
            } // end for loop

            space = 0; // reset space integer

            contactListUpdate.put(name, info);

            name = ""; // reset name String
            info = ""; // reset info String
         } // end while loop
      } catch (IOException e) {
         System.out.println("IO error: " + e);
      } // end try/catch
      return contactListUpdate;
   }// end readFile method

   /**
    * display method
    * 
    * This method displays the TreeMap in a Map.Entry manner
    * 
    * @param output, contactListUpdate
    */
   public static void display(PrintWriter output,
         TreeMap<String, String> contactListUpdate) {
      // Get a set of entries
      Set<Map.Entry<String, String>> mapSet = contactListUpdate.entrySet();

      output.println();
      output.println("Here is the current contact list: ");

      // Display the elements
      for (Map.Entry<String, String> mapEntry : mapSet) {
         output.printf("%-20s", mapEntry.getKey());
         output.printf("%-35s", mapEntry.getValue());
         output.println();
      } // end for each loop
   }// end display method

}// end ContactListChanges class
