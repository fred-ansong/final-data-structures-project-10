// Record of a patient case that has already been treated

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
    // Constructor initializing class fields
    public TreatedCase(Patient patient, Instant start, Instant end, Outcome outcome, String notes) {
        this.patient = patient;
        this.start = start;
        this.end = end;
        this.outcome = outcome;
        this.notes = notes;
    }

    // Getters for each of the class fields
    public Patient getPatient() { return patient; }
    public Instant getStart() { return Start; }
    public Instant getEnd() { return end; }
    public Outcome getOutcome() { return outcome; }
    public String getNotes() { return notes; }

    // toString method which determines what is printed when an instance of this class is printed
    @Override
    public String toString() {
        return "TreatedCase:" +
                "patient=" + patient +
                ", start=" + start +
                ", end=" + end +
                ", outcome=" + outcome +
                ", notes='" + notes + '\'';
    }
}
