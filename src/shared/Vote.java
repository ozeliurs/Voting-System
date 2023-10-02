package shared;

import java.util.*;

/**
 * 
 */
public interface Vote {
    /**
     * 
     */
    Date start = null;

    /**
     * 
     */
    Date end = null;

    /**
     * @return
     */
    public Candidate getVoteMaterial();

    /**
     * @param candidates Candidate[0...*]
     */
    public void addCandidates(List<Candidate> candidates);

}