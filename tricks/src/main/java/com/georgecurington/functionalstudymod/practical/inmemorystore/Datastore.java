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
 * @since Jan 27, 2018
 * @see https://github.com/sidhartha11/InterviewTricks
 * @see https://github.com/sidhartha11/InterviewTricks/blob/master/LICENSE
 */
public interface Datastore {
public boolean command(Operation op, String nationalId , String name );
public List<Contacts> find (String s);
public List<Contacts> all();
}
