package client;

import shared.Ballot;
import shared.Candidate;

import java.io.Serializable;
import java.util.*;

public class BallotImpl extends HashMap<Candidate, Integer> implements Ballot, Serializable {
    void addCandidates(List<Candidate> candidates) {
        for (Candidate candidate : candidates) {
            this.put(candidate, 0);
        }
    }
}
