package server;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class VoteImpl implements shared.Vote {

    private Date start;
    private Date end;
    private List<shared.Candidate> candidates;

    public VoteImpl() {
        candidates = new ArrayList<>();
    }

    @Override
    public List<shared.Candidate> getCandidates() {
        return candidates;
    }

    @Override
    public void setCandidates(List<shared.Candidate> candidates) {
        this.candidates = candidates;
    }

    public void importCandidates(String filename) throws FileNotFoundException {
        candidates = new ArrayList<>();

        // Reads a simple csv file and imports the candidates
        BufferedReader br = new BufferedReader(new FileReader(filename));

        br.lines().forEach(line -> {
            String[] parts = line.split(",");
            candidates.add(new server.Candidate(Integer.parseInt(parts[0]), parts[1], parts[2]));
        });
    }

    public Date getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = parseDate(start);
        if (this.end != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = parseDate(end);
        if (this.start != null && this.start.after(this.end)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    private static Date parseDate(String date) {
        String[] dateParts = date.split("/");
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dateParts[0]));
        cal.set(Calendar.MONTH, Integer.parseInt(dateParts[1]));
        cal.set(Calendar.YEAR, Integer.parseInt(dateParts[2]));
        return cal.getTime();
    }
}
