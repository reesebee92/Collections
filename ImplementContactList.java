
/**
 * ImplementContactList
 * 
 * This class will create a contact list using the Tree Map Class.
 * The contact list will be sorted by last name and saved as a file 
 * located on my computer.  
 * 
 * @author SDantzler
 * */

import java.util.*;
import java.io.*;

public class ImplementContactList {

   public static void main(String[] args) throws IOException {

      // try with resources
      try (PrintWriter output = new PrintWriter(
            new BufferedWriter(new FileWriter(
                  "/Users/sdantzle/Documents/Personal Reports/School/Intro to "
                  + "Java/Homework/Homework11/Original File.txt")))) {

         // Create a tree map
         TreeMap<String, String> contactList = new TreeMap<String, String>(
               new LastNameSort());

         // put elements in the Map
         contactList.put("Tammy Hall", "404-555-4318, t_hall@yahoo.com");
         contactList.put("Edith Hall", "676-555-2177, ehall1@jhu.edu");
         contactList.put("Melanie Baker", "202-555-0158, mbaker@gmail.com");
         contactList.put("Caroline Smith", "770-555-6017, ccsmith@gmail.com");

         // Get a set of entries
         Set<Map.Entry<String, String>> mapSet = contactList.entrySet();

         // Display the elements
         for (Map.Entry<String, String> mapEntry : mapSet) {
            output.printf("%-20s", mapEntry.getKey());
            output.printf("%-35s", mapEntry.getValue());
            output.println();
         } // end for each loop
      } catch (IOException ioe) {
         System.out.println("I/O Error: " + ioe);
      } // end try/catch
   }// end main method

}// end ImplementContactList class
