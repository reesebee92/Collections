
/**
 * LastNameSort
 * 
 * This class will allow a Tree Map to sort the files by last name
 * The code logic was provided by use from the java reading page 587 - 588
 * Java The Complete Reference Eleventh Edition 
 * 
 * @author Herbert Schildt
 * */

import java.util.*;

public class LastNameSort implements Comparator<String> {
   public int compare(String s1, String s2) {

      // String variables used for comparisons
      int str1, str2, sReturn;

      // Retrieve the index of beginning of last name
      str1 = s1.lastIndexOf(' ');
      str2 = s2.lastIndexOf(' ');

      // Return correct last name
      sReturn = s1.substring(str1).compareToIgnoreCase(s2.substring(str2));

      // if the last names match, check the entire name
      if (sReturn == 0) {
         return s1.compareToIgnoreCase(s2);
      } else {
         return sReturn;
      } // end if else

   }// end class LastNameSort

}// end class LastNameSort
