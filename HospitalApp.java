package edu.hcu.triage;

import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

/** Text-based UI. Keep it simple and robust. */
public class HospitalApp {

    private final PatientRegistry registry = new PatientRegistry();
    private final TriageQueue triage = new TriageQueue();
    private final TreatmentLog log = new TreatmentLog();
    private final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        new HospitalApp().run();
    }

    private void run() {
        // TODO: load seed CSV if provided via args[0]
        // TODO: main loop: printMenu(); switch on choice; handle invalid input
        // Required actions:
        //   1) Register patient
        //   2) Update patient
        //   3) Enqueue for triage (by id)
        //   4) Peek next
        //   5) Admit/treat next (simulate start/end times; capture outcome + notes; append to log)
        //   6) Print triage order (non-destructive)
        //   7) Find patient by id
        //   8) Show treatment log (choose order)
        //   9) Performance demo (calls SampleWorkloads with sizes)
        //  10) Export log to CSV
        //   0) Exit
        while true{
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

            int option = scanner.nextInt();
            switch (choice) {
                    
    }

    // TODO: helper methods per menu action
    // TODO: input parsing with validation; avoid crashing on bad input
}
