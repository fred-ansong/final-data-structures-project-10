package edu.hcu.triage;

import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

/** Text-based UI. Keep it simple and robust. */
public class HospitalApp {

    private final PatientRegistry registry = new PatientRegistry();  // stores all patients
    private final TriageQueue triage = new TriageQueue();  // stores waiting patients in priority order
    private final TreatmentLog log = new TreatmentLog();   // stores all treated cases
    private final Scanner in = new Scanner(System.in);   // stores user input from keyboard

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new HospitalApp().run();   // creates the UI app and starts the menu loop
    }

    private void run() {

        // TODO: load seed CSV if provided via args[0]
        //try catch blocks to protect from crashing
        try {
            String[] launchArgs = edu.hcu.triage.AppArgs.args;
            if (launchArgs.length > 0) {
                Path seedPath = Path.of(launchArgs[0]);  // converts string to filesystem path
                registry.loadFromCsv(seedPath);   // professor provided method
                System.out.println("Seed patients loaded from: " + seedPath);
            }
        } catch (Exception e) {
            System.out.println("No valid seed CSV provided. Continuing without loading.");
        }

        // TODO: main loop: printMenu(); switch on choice; handle invalid input
        while (true) {        // (syntax fix: added parentheses around true)
            printMenu();

            int option = readIntSafe("Enter option: ");

            switch (option) {        // (syntax fix: changed choice -> option)
                case 1 -> handleRegisterPatient();        // 1) Register
                case 2 -> handleUpdatePatient();          // 2) Update
                case 3 -> handleEnqueue();                // 3) Enqueue
                case 4 -> handlePeekNext();               // 4) Peek
                case 5 -> handleTreatNext();              // 5) Admit/treat
                case 6 -> handlePrintTriageOrder();       // 6) Print queue
                case 7 -> handleFindById();               // 7) Find patient
                case 8 -> handleShowLog();                // 8) Show log
                case 9 -> runPerformanceDemo();           // 9) Performance demo
                case 10 -> handleExportCsv();             // 10) Export CSV
                case 0 -> {
                    System.out.println("Exiting system...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    // TODO: helper methods per menu action

    private void handleRegisterPatient() {
        System.out.println("=== Register New Patient ===");
        int id = readIntSafe("ID: ");
        String name = readStringSafe("Name: ");
        int age = readIntSafe("Age: ");
        int severity = readIntSafe("Severity (1–5): ");

        registry.registerNew(id, name, age, severity);
        System.out.println("Patient registered.");
    }

    private void handleUpdatePatient() {
        System.out.println("=== Update Existing Patient ===");
        int id = readIntSafe("ID: ");

        String name = readStringSafe("New name: ");
        int age = readIntSafe("New age: ");
        int sev = readIntSafe("New severity (1–5): ");

        registry.updateExisting(id, name, age, sev);
        System.out.println("Patient updated.");
    }

    private void handleEnqueue() {
        System.out.println("=== Enqueue Patient for Triage ===");
        int id = readIntSafe("ID: ");

        triage.enqueueById(registry, id);
        System.out.println("Patient enqueued.");
    }

    private void handlePeekNext() {
        Optional<Patient> next = triage.peekNext();
        if (next.isEmpty()) System.out.println("Queue empty.");
        else System.out.println("Next patient: " + next.get());
    }

    private void handleTreatNext() {
        System.out.println("=== Treating Next Patient ===");

        Optional<Patient> removed = triage.dequeueNext();
        if (removed.isEmpty()) {
            System.out.println("Queue empty.");
            return;
        }

        Patient p = removed.get();
        Instant start = Instant.now();
        String notes = readStringSafe("Enter treatment notes: ");

        // simulate short delay
        try { Thread.sleep(300); } catch (InterruptedException ignored) {}

        Instant end = Instant.now();

        TreatedCase tc = new TreatedCase(p, start, end, Outcome.STABLE, notes);
        log.append(tc);

        System.out.println("Patient treated and logged.");
    }

    private void handlePrintTriageOrder() {
        System.out.println("=== Triage Queue Snapshot ===");
        List<Patient> order = triage.snapshotOrder();
        order.forEach(System.out::println);
    }

    private void handleFindById() {
        int id = readIntSafe("Enter ID: ");
        Optional<Patient> p = registry.get(id);

        if (p.isEmpty()) System.out.println("No such patient.");
        else System.out.println(p.get());
    }

    private void handleShowLog() {
        System.out.println("1) Oldest first");
        System.out.println("2) Newest first");

        int choice = readIntSafe("Choose order: ");

        List<TreatedCase> entries =
                (choice == 2 ? log.asListNewestFirst() : log.asListOldestFirst());

        for (TreatedCase tc : entries) System.out.println(tc);
    }

    private void handleExportCsv() {
        String pathStr = readStringSafe("Enter CSV output path: ");
        Path out = Path.of(pathStr);

        log.exportToCsv(out);
        System.out.println("Log exported to: " + out);
    }

    // Performance demo provided by professor
    private void runPerformanceDemo() {
        System.out.println("Running performance demo...");
        SampleWorkloads.enqueueRandomPatients(triage, 1000, 42L);
        SampleWorkloads.dequeMany(triage, 500);
        SampleWorkloads.runRatioWorkload(triage, 10, 10, 5, 42L);
        System.out.println("Performance demo completed.");
    }

    // TODO: input parsing with validation; avoid crashing

    private int readIntSafe(String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(in.nextLine().trim());
            } catch (Exception e) {
                System.out.println("Invalid number — try again.");
            }
        }
    }

    private String readStringSafe(String prompt) {
        System.out.print(prompt);
        return in.nextLine().trim();
    }

    private void printMenu() {
        System.out.println("\n------Actions------");
        System.out.println("1) Register patient");
        System.out.println("2) Update patient");
        System.out.println("3) Enqueue for triage (by id)");
        System.out.println("4) Peek next");
        System.out.println("5) Admit/treat next");
        System.out.println("6) Print triage order");
        System.out.println("7) Find patient by id");
        System.out.println("8) Show treatment log");
        System.out.println("9) Performance demo");
        System.out.println("10) Export log to CSV");
        System.out.println("0) Exit");
    }
}
