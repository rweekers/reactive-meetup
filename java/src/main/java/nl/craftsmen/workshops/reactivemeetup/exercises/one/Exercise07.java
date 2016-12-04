package nl.craftsmen.workshops.reactivemeetup.exercises.one;

import static nl.craftsmen.workshops.reactivemeetup.util.Utils.*;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise07 {

    public static void main(String[] args) {
    	// The word$ stream will emit a sequence words. Each word is just a string.
        Observable<String> word$ = ExampleStreams.word$();

        // ASSIGNMENT: Concatenate all the words from the word$ stream to form a sentence. Store the final and intermediate
        // results (the partial sentences) in the sentence$ stream.
        
        Observable<String> sentence$ = unknown(); // ???
        
        // If implemented correctly you should now know what "jirble" means.
        
        // Performance in this example is obviously not a problem. In the real world you may encounter a similar scenario in
        // which performance might be critical. In such cases string concatenation is usually performed using a StringBuilder.
        //
        // BONUS ASSIGNMENT: Modify the definition of the sentence$ to use a StringBuilder instead of the string concatenation
        // operator (+).
        //
        // HINT: you will need a different version (overloaded method) of the same operator you used above.
        
        sentence$.subscribe(System.out::println);

        waitForStreamToComplete(sentence$);
    }
}
