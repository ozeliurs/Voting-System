package shared;

import java.util.*;

public interface Vote {
    List<Candidate> getCandidates();

    void setCandidates(List<Candidate> candidates);
}