package shared;

import java.io.Serializable;
import java.util.Map;

public interface Ballot extends Map<Candidate, Integer>, Serializable {}
