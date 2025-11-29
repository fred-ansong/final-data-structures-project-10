package edu.hcu.triage;

import java.util.*;

/**
 * Thin wrapper around PriorityQueue to enforce the triage policy and provide extra utilities.
 */
public class TriageQueue {
    private final PriorityQueue<Patient> pq = new PriorityQueue<>(new TriageOrder());

    // TODO: enqueue(Patient p)
    public void enqueue(Patient p) {
        if (p == null) {
            throw new IllegalArgumentException("Patient cannot be null");
        }
        pq.add(p);
    }
    // TODO: enqueueById(PatientRegistry reg, String id) -> look up patient then enqueue
    public void enqueueById(PatientRegistry reg, String id) {
        if (reg == null || id == null) {
            throw new IllegalArgumentException("Registry or id cannot be null");
        }

        Optional<Patient> found = reg.get(id);
        if (found.isPresent()) {
            pq.add(found.get());
        }
    }
    // TODO: peekNext(): Optional<Patient>
    public Optional<Patient> peekNext() {
        Patient p = pq.peek();
        return Optional.ofNullable(p);
    }
    // TODO: dequeueNext(): Optional<Patient>
    public Optional<Patient> dequeueNext() {
        Patient p = pq.poll();
        return Optional.ofNullable(p);
    // TODO: size()
    public int size() {
        return pq.size();
    }
    // TODO: snapshotOrder(): List<Patient> in triage order WITHOUT mutating the queue
    //       Hint: iterate via toArray/copy or temporary PQ clone
    public List<Patient> snapshotOrder() {
        PriorityQueue<Patient> temp = new PriorityQueue<>(pq);
        List<Patient> result = new ArrayList<>();
        while (!temp.isEmpty()) {
            Patient next = temp.poll();
            result.add(next);
        }
        return result;
    }
    // TODO: clear()
        public void clear() {
        pq.clear();
    }
}
