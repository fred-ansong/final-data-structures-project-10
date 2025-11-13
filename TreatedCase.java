package edu.hcu.triage;

import java.time.Instant;


public class TreatedCase {
    public enum Outcome { STABLE, OBSERVE, TRANSFER }

    private final Patient patient;
    private final Instant start;
    private final Instant end;
    private final Outcome outcome;
    private final String notes;

    // TODO: constructor, getters, toString()
}
