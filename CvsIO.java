package edu.hcu.triage;

import java.io.*;
import java.nio.file.*;
import java.time.Instant;
import java.util.*;

/** Minimal CSV import/export using only java.nio + java.io. */
public final class CsvIO {
    // TODO: loadPatients(Path csv, PatientRegistry reg)
    //  - Expect header id,name,age,severity
    //  - Trim fields; skip blank lines; validate
    public static void loadPatients(Path csv, PatientRegistry registry) {
        if (csv == null || registry == null) {
            throw new IllegalArgumentException("csv or registry cannot be null"); //checking for null inputs
        }

        try (BufferedReader br = Files.newBufferedReader(csv)) {

            String line = br.readLine(); //reads first line of file
            if (line == null) {
                System.out.println("CSV file is empty.");
                return; //exits if file is empty
            }

            int lineNumber = 1; //to track line number

            while (true) {
                line = br.readLine();
                if (line == null) {
                    break; //loops to read whole file til null line(end)
                }

                lineNumber++;

                line = line.trim();
                if (line.isEmpty()) {
                    continue; //skip blank lines
                }

                String[] parts = line.split(","); //split lines where ,
                if (parts.length != 4) { //if not 4 , to split, sends error, but continues
                    System.out.println("Warning: Skipping malformed line " + lineNumber);
                    continue;
                }

                String id = parts[0].trim();
                String name = parts[1].trim();
                String ageText = parts[2].trim();
                String sevText = parts[3].trim(); //assigns each datapoint to a variable and removes spaces

                try {
                    int age = Integer.parseInt(ageText);
                    int severity = Integer.parseInt(sevText); //age and severity numbers instead of strings

                    // Create the patient in the registry
                    registry.registerNew(id, name, age, severity); //adds patient to registry

                } catch (Exception e) {
                    System.out.println("Warning: Skipping bad data on line " + lineNumber); //if there is a mistake in the file, notifies user instead of killing code
                }
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage()); //same as above, but if error in reading file
        } 
    }
    // TODO: exportLog(Path csv, List<TreatedCase> cases)
    //  - Write header and rows (ISO-8601 timestamps)
    public static void exportLog(Path csv, List<TreatedCase> cases) {
        if (csv == null || cases == null) {
            throw new IllegalArgumentException("csv path or case list is null"); //validates input
        }

        try (BufferedWriter bw = Files.newBufferedWriter(csv)) { //opens file

            bw.write("id,name,age,initialSeverity,arrivalIso,treatStartIso,treatEndIso,outcome,notes");
            bw.newLine();

            for (TreatedCase tc : cases) {
                Patient p = tc.getPatient(); //loops over treated to get patient data

                String row =
                        p.getId() + "," +
                        p.getName() + "," +
                        p.getAge() + "," +
                        p.getSeverity() + "," +
                        p.getArrival().toString() + "," +
                        tc.getStart().toString() + "," +
                        tc.getEnd().toString() + "," +
                        tc.getOutcome().name() + "," +
                        escapeNotes(tc.getNotes()); //gets data and converts to string, changes commas to ;

                bw.write(row);
                bw.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error writing log CSV: " + e.getMessage()); //error message
        }
    }

    private static String escapeNotes(String notes) {
        if (notes == null) {
            return "";
        }
        return notes.replace(",", ";"); //method to stop export log from breaking bc commas
    }
}
