package edu.hcu.triage;

import java.time.Instant;
import java.util.Objects;


/**
 * Immutable identity (id) + mutable clinical state (severity).
 * Arrival order must be trackable for stable tie-breaking.
 */
public class Patient {
    private final String id;        // e.g., "P001"
    private String name;
    private int age;
    private int severity;           // define scale (e.g., 1..5 or 1..10): higher = more urgent
    private final Instant arrival;  // registration time
    private final long arrivalSeq;  // monotonically increasing sequence for FIFO ties

    // TODO: constructor(s)
    public Patient(String id, String name, int age, int severity, Instant arrival, long arrivalSeq) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.severity = severity;
        this.arrival = Objects.requireNonNull(arrival);
        this.arrivalSeq = arrivalSeq;
    }


    // TODO: getters

    public String getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }
    public int getSeverity() { return severity; }
    public Instant getArrival() { return arrival; }
    public long getArrivalSeq() {
        return arrivalSeq;


        // TODO: setter(s) for fields allowed to change (e.g., name, age, severity) with validation

        public void setName (String name){
            if (name == null || name.isEmpty())
                throw new IllegalArgumentException("Name cannot be null or empty");
            this.name = name;

        }
        public void setAge ( int age){
            this.age = age;

        }

        public void setSeverity (int severity) {
            if (severity < 1 || severity > 5)
                throw new IllegalArgumentException("Severity must be between 1 and 5");
            this.severity = severity;
        }
        // TODO: equals/hashCode based on id only (justify in README)

        public int hashCode() {
            return Objects.hash(id);
        }


        // TODO: toString() concise
        public String toString() {
            return String.format("Patient{id=%s, name=%s, age=%d, severity=%d}", id, name, age, severity);
        }
    }
