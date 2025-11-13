package edu.hcu.triage;

import java.util.Comparator;


/**
 * Comparator for PriorityQueue: higher severity first; among equals, smaller arrivalSeq first.
 */
public final class TriageOrder implements Comparator<Patient> {
    // TODO: implement compare(...) WITHOUT reversing FIFO among ties
    // NOTE: Ensure the comparator is CONSISTENT WITH EQUALS expectations for PriorityQueue usage.
}
