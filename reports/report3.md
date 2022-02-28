# Report 3

## How We Test

Currently we have implemented unit testing on our specifically-scoped (
program-assistive) classes: The Student class, the Course class, and the Parsing
class. Each class contains helper methods to make our code more readable, assist
with data manipulation and formatting, increase consistency, and make testing
more reliable and efficient. In the Student class, all of the methods return
information about the students through it’s CSV file. After various points on
parsing and data confirmation, the respective student information is then added
to an array list of Student objects. Similarly, the Course class’s methods do
the same thing; it returns information about each course after extensive data
manipulation, parsing, and formatting, and is then added into an ArrayList of
Course objects. The Parsing class runs all of the methods from the two previous
classes and has two methods of its own. These methods help parse each CSV file
and return the data from the files.

To test each class, we used JUnit 5 to create a new test class for each of our
classes. For the Student and Course test class, there is a test method for every
existing method in the original classes, as per the principles of Unit Testing.
The test methods check a handful of things: it checks that the correct columns
of the CSV file are being parsed, the correct data type is used, such as String
or Integer, and lastly, it checks that specific information is not empty or
undefined. For the Parsing test class, the test methods check each exception
that is included in the try-catch statement, such as, `FileNotFoundException`
, `NumberFormatException`, and `IOException`.

Additionally, we are implementing Integration Testing for the overall
functionality, performance, security, and user experience consistency in our
application. Our integration testing will be implemented once our final rounds
of unit testing are completed on the algorithmic portion of this program. The
algorithm development has been slightly test-driven as small manipulations and
fine-tuning are performed on the algorithm with the return of information from
each round of unit testing.

## Findings

The current findings of our testing have been both expected as well as
intuitive; the expected results have been through a large amount of our
objective Unit Testing on object and base-functionality classes. The Student and
Course objects are required to correctly parse and assign data, and the methods
are tested to ensure that the proper data can both be stored and retrieved
properly from both a Student and a Course object. In contrast, when testing the
Parser class and its ability to parse a randomized CSV file consistently, we
were able to spot and correct erroneous cases; one such case would be when the
randomized data created an empty cell. Our parser did not have the functionality
to handle this, and without testing we may not have considered how our program
would react to nonexistent data (rather than existing data that is incorrect).

An additional interesting finding to come out of testing is the test-driven
tuning of the schedule-matching algorithm in the application; since refactoring
our algorithm to follow methods of maximum bipartite matching practices, testing
has been a crucial factor to the consistent development of the algorithm. The
more strongly tested through development, the less breaking test cases that pop
up suddenly at the end. Through testing the algorithm has been continuously
improved to expect variable types of Students and Courses being passed to it (
with more variability handling expected).

## Prototyping

So far, we have created prototypes for the main algorithm through various flow
charts and practice models, multiple models and test-implementations of the
parsing system, and both low-fidelity and high-fidelity wireframes for the UI.
Changing from our previous design, our new algorithm will be using maximum
bipartite matching. The large refactor in both approach, implementation, and
requirements of our algorithmic model required changes in current prototyping
and multiple test implementations of the base concepts of the algorithm and our
pseudo-coded alterations. These documents, as records of our teams development
process and maturation of mental mapping, will be included in the final
documentation bundle and submittal.

## Summary

As for what we’ve done so far, one of the most critical actions we’ve taken is
reapproaching our algorithm’s design. After our midterm presentation, we
realized the idea behind our algorithm had become bloated and difficult to
realize, and that this was still an acceptable time to change our methodology.
We eventually came to the conclusion that we should focus on graph theory and
matching algorithms. Once reviewing the principle of maximum bipartite graph
algorithms and the necessary logical alterations required to achieve our end
goal, we were able to remodel our algorithm through flow chart and visual
prototyping, pseudo-coded prototyping on top of the base algorithm, and then
building the actual algorithm used for our application.

The user interface has also been updated. The GUI currently has two input
buttons incorporated into the design, one for inputting the student csv file and
the other for inputting the classes csv file. We are aiming to have as much user
feedback as possible, so after each file is selected, the file name is displayed
on the main screen.

One of the first things that we worked on was measuring a scale of time to
operate on. Initially, our plan was to use military time, but we struggled with
getting java to use it. In order to simplify this process, we removed the
leading and following zeroes, effectively translating military time into a list
of time-based integers. Another problem that we’ve been working on is the
inconsistency of the information in the CSV files, with information that isn’t
complete.

In addition, the project has been reorganized to support execution and
professional compilation using Maven. The project has been restructured into a
more comprehensible tree, with the respective functionalities sorted into
packaged directories. This has allowed more clear communication between layers
in our application as we connect multiple endpoints that different team members
have been working on to each other. This also includes the creation of a test
directory to include our finalized tests in.

