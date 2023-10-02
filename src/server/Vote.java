package server;

import shared.Candidate;

import java.util.Date;
import java.util.List;

public class Vote implements shared.Vote{

    private Date start;
    private Date end;



    public Vote(Date start, Date end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public Candidate getVoteMaterial() {
        return null;
    }

    @Override
    public void addCandidates(List<Candidate> candidates) {

    }
}
