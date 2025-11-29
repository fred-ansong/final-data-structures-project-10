package edu.hcu.triage;

import java.util.HashMap;
import java.util.Map;
import java.util.Op


/**
 * O(1)-ish lookup of patients by id.
 */
public class PatientRegistry {
    private final Map<String, Patient> byId = new HashMap<>();
    private long nextArrivalSeq = 0L;

    public Patient registerNew(String id, String name, int age, int severity) {  // Registers a new patient
        validateId(id);
        validateName(name);
        validateAge(age);  // age cant be greater than 100
        validateSeverity(severity);

        Patient p = new Patient(id, name, age, severity, nextArrivalSeq++);
        byId.put(id, p);
        return p;
    }
-----------------------------------------------------------------------------------------------------------------------------------------------------
    public Optional<Patient> get(String id, String name, int age, int severity) {   // updates an existing patient with partial updates
        Patient existing = byId.get(id);
        if (existing == null) return Optional.empty();

        if (name!= null){
            validateName(name);
            existing.setName(name);
        }
        if (age != null){
            validateAge(age);
            existing.setAge(age);

        }
        if (severity == null) {
            validateSeverity(severity);
            existing.setSeverity(severity);
        }
        return Optional.of(existing);


    }

    public Optional<Patient> get(String id) {
        return Optional.ofNullable(byId.get(id));
    }


    public boolean contains(String id) {
        return byId.containsKey(id);
    }

    public int size() {
        return byId.size();
    }


------------------------------------------------------------------------------------------------------------------------------------------------
    // This segment of the code basically just checks if the values entered are acceptable to tyhe system and it send an error message if its not//
    private void validateId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id is null or blank");  //throw new IllegalArgumentException , means its just going to stop everytrhing and report an error if any is found, like if i was to enter a blank id for ID//
        }
    }

    private void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name is null or blank");

        }
    }

    private void validateAge(int age) {
        if (age < 0||age > 100) {
            throw new IllegalArgumentException("age is null or blank");
        }
    }
    private void validateSeverity(int severity) {
        if (severity < 0 || severity > 10) {
            throw new IllegalArgumentException("severity is null or blank");
        }
    }








}

