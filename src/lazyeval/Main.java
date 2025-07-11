package lazyeval;

import java.time.Duration;
import java.time.Instant;

public class Main {
    public static void main(String[] args) {
        var startTimeA = Instant.now();
        // the inefficient way of eval
        Lazy<Integer> lazyA = () -> 1;
        for (int i = 0; i < 10; i++) {
            var lazyB = lazyA;
            lazyA = () -> lazyB.eval() + lazyB.eval();
        }
        System.out.println("lazyA = " + lazyA.eval() + ", elapsed time in millis: " + Duration.between(startTimeA, Instant.now()).toNanos());

        // a more efficient way of doing this
        // is to memoize the computed expression
        // this runs in linear time vs. the exponential
        // non-memoized solution from above
        var startTimeB = Instant.now();
        Lazy<Integer> memA = () -> 1;
        for (int i = 0; i < 10; i++) {
            var memB = memA;
            memA = new Memoize<>(() -> memB.eval() + memB.eval());
        }

        System.out.println("memA = " + memA.eval() + ", elapsed time millis: " + Duration.between(startTimeB, Instant.now()).toNanos());
    }
}
