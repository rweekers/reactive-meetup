# Reactive Programming Workshop for JavaScript

## Setup

* First make sure you've got Node.js installed on your machine (version 6 or higher is required).
You can download the latest version from: [https://nodejs.org/en/download/](https://nodejs.org/en/download/).
To verify whether Node.js was installed correctly and which version you have running, execute the following command from a console:
```
node -v
```

* [Download](https://gitlab.com/craftsmen/reactive-meetup/repository/archive.zip?ref=master) or [clone](https://gitlab.com/craftsmen/reactive-meetup.git) the workshop files from [https://gitlab.com/craftsmen/reactive-meetup](https://gitlab.com/craftsmen/reactive-meetup)

* Once you have downloaded or cloned the workshop files open the `javascript` directory with your favorite IDE or editor.

* Additionally you need to install the NPM dependencies for the workshop.
To do so open a console and navigate to the `javascript` directory of the workshop.
From this directory execute the following command:
```
npm install
```

## Exercises

You can find the exercises in the `src/exercises` directory.
There are two subdirectories `one` and `two` for both parts of the workshop.
Within each of these directories you will find files labeled `exercise-xx.js` which can be executed with Node.js.
Every exercise file contains the necessary information and assignment to solve the exercise.
None of the exercises depend on other exercises, so if necessary you can skip exercises (although we do not recommend that).

To run an exercise execute the following command in a console:

```
node src/exercises/one/exercise-01.js
```

## Reference resources

A link to the online version of the presentation will be published [here](https://gitlab.com/craftsmen/reactive-meetup/blob/master/README.md).
You can use the presentation as reference material for workshop exercises.
Furthermore visit the ReactiveX documentation page for more detailed information about RxJS:

* [Overview of the ReactiveX operators](http://reactivex.io/documentation/operators.html)
* [API documentation](http://reactivex.io/rxjs/identifiers.html)

_Please note that for the workshop we are using RxJS 5._
_If you search for RxJS documentation online it is likely that you will end up with the documentation for RxJS 4._
_Although the concepts remain the same, some operators have been renamed or have a slightly different API._
_Hence always check if you were are looking at the right documentation if things don't appear to be working according to the docs ;)_ 

## Solutions

The solutions for the exercises are available as well.
These can be found in the `solutions` branch of this GIT repository.
Obviously try not to peek at the solutions before you have tried to solve the assignments yourself.
If you get stuck during the workshop, we will be there to help you :)