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
    // TODO: exportLog(Path csv, List<TreatedCase> cases)
    //  - Write header and rows (ISO-8601 timestamps)
}
