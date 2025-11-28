/* This file is basically:
    - generating fake patients
    - loading them into the priority queue
    - running performance tests (such as 10,000 enqueues + 5,000 dequeues)
    - allowing the production of results using a fixed random seed
 */

// tells java which folder this file belongs to
package edu.hcu.triage;

// used for timestamps when we create a patient
import java.time.Instant;

//generates random values for ages, names, severities etc
import java.util.Random;

/** Deterministic workload generator for performance runs. */
public final class SampleWorkloads {
    // TODO: method to enqueue N random patients with fixed Random(seed)
    // Generates N random patients using a fixed seed so results repeat every run.
    // Then adds them into the triage queue.
    public static void enqueueRandomPatients(TriageQueue queue, int n, long seed) {

        // create the random generator
        Random rng = new Random(seed); // passing seed as argument so results repeat

        // generating fake patients using for loop (new patient created per iteration)
        for (int i = 0; i < n; i++) {
            String id = "P" + i;  // created patient ids such as : P0, P1, P2
            String name = "Patient" + i;  // Creates a fake placeholder name
            int age =  rng.nextInt(90) + 10;  // creates a random age between 10 and 99
            int severity = rng.nextInt(11);  // creates a random severity on a scale of 0-10
            Instant arrival = Instant.now(); // uses real current system time for when this patient arrived
            long seq = i; // used for tie-breaking - if two patients have the same severity, lower seq = treated first

            Patient p = new Patient(id, name, age, severity, arrival, seq); // calls the patient constructor and creates a patient object

            queue.enqueue(p);  // adds the new patient into hospital priority queue
        }

        //
    }

    // TODO: method to perform K dequeues (with checks for empty queue)
    // attempts to remove K patients from the queue
    // stops early if the queue becomes empty
    public static void dequeMany(TriageQueue queue, int k) {

        // Removes one patient per loop iteration
        for (int i = 0; i < k; i++) {

            // Stops early if queue is empty
            if (queue.size() == 0)
                return;

            // Removes the patient with the highest priority
            queue.dequeueNext();
        }
    }
    // TODO: knobs: severity distribution (uniform vs. skewed), ratios of enqueue/dequeue
    /* Simulating a real hospital where:
        - We create N patients where 80% of patients have low severity (0 - 4)
        - and 20% have high severity (5 - 10)  **(We believe this is a realistic scenario)
     */
    public static void enqueueSkewedSeverity(TriageQueue queue, int n, long seed) {

        // Seeded RNG for deterministic behaviour (creates a random value and assigns it to rng)
        Random rng = new Random(seed);

        // Generate N skewed-severity patients
        for (int i = 0; i < n; i++) {

            // fields containing patient identity info
            String id = "S" + i;
            String name = "SkewedPatient" + i;
            int age = rng.nextInt(90) + 10;

            // Initializing the timestamp
            Instant arrival = Instant.now();

            // Initializing sequence number
            long seq = i;

            // initialising severity field
            int severity;


            /* Randomly assigning severity while ensuring that
                - 80% of cases have a low severity (0-4)
                - 20% of cases have a high severity (5-10)
             */
            if (rng.nextDouble() < 0.8) {
                severity = rng.nextInt(5); // sets a severity in the range of 0 - 4
            } else {
                severity = 5 + rng.nextInt(6); // sets a severity in the range of 5 - 10
            }

            // Creating and enqueueing the patient
            Patient p = new Patient(id, name, age, severity, arrival, seq);
            // adding created patient to queue
            queue.enqueue(p);
        }
    }

    /* Method for enqueue/dequeue ratio workload
       This simulates a repeating pattern such as:
            - enqueue X patients
            - dequeue Y patients
       repeated for a certain number of cycles.

       Purpose:
            - lets us model realistic hospital flows
            - allows instructor to test performance under load
            - useful for priority queue stress tests
     */
    public static void runRatioWorkload(
            TriageQueue queue, // hospital queues being tested
            int cycles,        // how many times this simulation runs
            int enqueueCount,  // how many patients to enqueue per cycle
            int dequeueCount,  // how many patients to dequeue per cycle
            long seed          // makes randomness repeatable
    )
    {

        // seeded RNG so that the workload is deterministic on every run
        Random rng = new Random(seed);

        // used for tie-breaking so ordering is consistent (lower seq val is treated first)
        long globalSeq = 0;

        // repeating the enqueue/dequeue pattern for the given number of cycles
        for (int c = 0; c < cycles; c++) {


            // ENQUEUE PHASE  (enqueueCount patients)
            for (int i = 0; i < enqueueCount; i++) {

                // generating a fake patient
                String id = "R" + globalSeq;
                String name = "RatioPatient" + globalSeq;
                int age = rng.nextInt(90) + 10;    // random age (10–99)
                int severity = rng.nextInt(11);    // random severity (0–10)
                Instant arrival = Instant.now();   // timestamp

                // unique increasing sequence number
                Patient p = new Patient(id, name, age, severity, arrival, globalSeq);

                // add patient into queue
                queue.enqueue(p);

                // increment global sequence for next patient
                globalSeq++;
            }


            // DEQUEUE PHASE  (dequeueCount patients)
            for (int d = 0; d < dequeueCount; d++) {

                // stop early if queue is empty
                if (queue.size() == 0)
                    break;

                // remove the highest priority patient
                queue.dequeueNext();
            }
        }
    }



}
