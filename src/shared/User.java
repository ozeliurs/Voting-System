package shared;

import java.util.*;

/**
 * 
 */
public interface User {
    /**
     * 
     */
    String studentId = "";

    /**
     * 
     */
    String passwordHash = "";

    /**
     * @param candidates
     */
    public void vote(Map<Candidate, Integer> candidates);

    /**
     * @param studentId 
     * @param passwordHash
     */
    public void checkCredentials(String studentId, String passwordHash);

}