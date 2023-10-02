package shared;

import java.util.*;

/**
 * 
 */
public interface Vote {
    /**
     * @return
     */
    public Candidate getVoteMaterial();

    /**
     * @param candidates Candidate[0...*]
     */
    public void addCandidates(List<Candidate> candidates);

}