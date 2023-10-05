package server;

import shared.Candidate;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CandidateRepository {
    List<Candidate> candidates;
    String filepath;
    public CandidateRepository(String filepath) throws IOException {
        this.filepath = filepath;
        candidates = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(filepath));

        br.lines().forEach(line -> {
            String[] parts = line.split(",");
            candidates.add(new CandidateImpl(Integer.parseInt(parts[0]), parts[1], parts[2], parts[3]));
        });

        br.close();
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void save() throws IOException {
        // Write to a csv file
        StringBuilder csv = new StringBuilder();
        candidates.forEach(candidate -> {
            csv.append(candidate.getId()).append(",");
            csv.append(candidate.getName()).append(",");
            csv.append(candidate.getSurname()).append(",");
            csv.append(candidate.getPitch()).append("\n");
        });

        // Write to file
        BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));
        writer.write(csv.toString());
        writer.close();
    }
}
