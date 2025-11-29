package edu.hcu.triage;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/** Append-only log of treated cases using LinkedList. */
public class TreatmentLog {
    private final LinkedList<TreatedCase> log = new LinkedList<>();

    // TODO: append(TreatedCase tc)
    public void append(TreadedCase tc) {
        if (tc == null) {
            throw new IllegalArgumentException("TreatedCase cannot be null");
        }
        log.addLast(tc);
    }

    // TODO: size()
    public int size() {
        return log.size();
    }
    // TODO: asListOldestFirst()
    public List <TreatedCase> asListOldestFirst() {
        return newArrayList<>(log);
    }


    // TODO: asListNewestFirst()
    public List<TreatedCase> asListNewestFirst() {
        List<TreatedCase> copy = new ArrayList<>(log);
        Collections.reverse(copy);
        return copy;
    }
}