package server;

import shared.Candidate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepository {
    List<Candidate> candidates;
    public CandidateRepository() {
        this.candidates = new ArrayList<>();
    }

    public CandidateRepository(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public static CandidateRepository fromFile(String file) throws FileNotFoundException {
        List<Candidate> candidates = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(file));

        br.lines().forEach(line -> {
            String[] parts = line.split(",");
            candidates.add(new CandidateImpl(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]));
        });

        return new CandidateRepository(candidates);
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }
}
