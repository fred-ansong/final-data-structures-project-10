package edu.hcu.triage;

import java.util.*;

/**
 * Thin wrapper around PriorityQueue to enforce the triage policy and provide extra utilities.
 */
public class TriageQueue {
    private final PriorityQueue<Patient> pq = new PriorityQueue<>(new TriageOrder());

    // TODO: enqueue(Patient p)
    // TODO: enqueueById(PatientRegistry reg, String id) -> look up patient then enqueue
    // TODO: peekNext(): Optional<Patient>
    // TODO: dequeueNext(): Optional<Patient>
    // TODO: size()
    // TODO: snapshotOrder(): List<Patient> in triage order WITHOUT mutating the queue
    //       Hint: iterate via toArray/copy or temporary PQ clone
    // TODO: clear()
}
