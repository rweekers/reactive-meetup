package nl.craftsmen.workshops.reactivemeetup.exercises;

import nl.craftsmen.workshops.reactivemeetup.util.ExampleStreams;
import rx.Observable;

public class Exercise07 {

    public static void main(String[] args) throws InterruptedException {
        Observable<String> word$ = ExampleStreams.word$();

        word$
                .scan((sum, item) -> sum + " " + item)
                .subscribe(System.out::println);

        Thread.sleep(6000);
    }
}