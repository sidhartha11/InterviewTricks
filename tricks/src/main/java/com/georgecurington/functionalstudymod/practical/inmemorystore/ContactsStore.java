/**
 * 
 */
package com.georgecurington.functionalstudymod.practical.inmemorystore;

import java.util.List;

/**
 * <pre><p><b>INTERVIEW TRICKS</b></p></pre>
 * <p>================================================</p>
 * <br>
 * @author George Curington
 * @version 1.0.0
 * @since Feb 4, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface ContactsStore {
 void command(String command, Contact data);
 List<Contact> find (String partialName);
 List<Contact> all();
}
