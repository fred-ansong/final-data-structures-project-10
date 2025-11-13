package edu.hcu.triage;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


/**
 * O(1)-ish lookup of patients by id.
 */
public class PatientRegistry {
    private final Map<String, Patient> byId = new HashMap<>();
    private long nextArrivalSeq = 0L;

    // TODO: registerNew(id, name, age, severity): create Patient with arrivalSeq = nextArrivalSeq++
    // TODO: updateExisting(id, name?, age?, severity?): partial updates
    // TODO: get(id): Optional<Patient>
    // TODO: contains(id)
    // TODO: size()
    // TODO: Defensive validation (non-null, age bounds, severity bounds)
}

