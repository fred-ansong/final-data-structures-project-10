package edu.hcu.triage;

import java.util.Comparator;


/**
 * Comparator for PriorityQueue: higher severity first; among equals, smaller arrivalSeq first.
 */
public final class TriageOrder implements Comparator<Patient> {
    // TODO: implement compare(...) WITHOUT reversing FIFO among ties
    // NOTE: Ensure the comparator is CONSISTENT WITH EQUALS expectations for PriorityQueue usage.

    // Method to compare severity
    @Override
    public int compare(Patient a, Patient b) {

        // Comparing severity to ensure higher severity comes first:
        if (a.getSeverity() != b.getSeverity()) {
            return Integer.compare(b.getSeverity(), a.getSeverity());
            // since queues are in ascending order, we flip b with a to ensure higher severity gets treated first
        }

        // if severity is the same however, we compare arrivalseq (arrival order) and the lowest goes first
        return Long.compare(a.getArrivalSeq(), b.getArrivalSeq());
    }
}
