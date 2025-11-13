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
    // TODO: getters
    // TODO: setter(s) for fields allowed to change (e.g., name, age, severity) with validation
    // TODO: equals/hashCode based on id only (justify in README)
    // TODO: toString() concise
}
