# Report 2

## Program Plan / Design

In regards to our plan for the program, the best place to start would likely be
the language framework we’re using. The program will be built using an
Object-Oriented Programming framework, specifically Java, and will implement a
simple interactive UI using javax.swing. The program will use a minimalistic
pop-up UI to prompt the user to submit two CSV files that contain student and
schedule data, validate them, then algorithmically produce an optimal schedule
that satisfies a variety of rules set to ensure the best match between TA and
class. With that in mind, there are a variety of plausible concepts and methods
being considered to get the expected optimal result out of our program.

The currently favored method is based on semi-random selection, with hard
requirements (i.e. classes taken, applying for 392 or 492, etc.) limiting what
could be selected. A focus will be added on graduation priority to ensure that
students closer to graduation are more likely to be assigned to a course. The
process could also be scaled to be weighted based on student performance and
preference in order to allow for a better selection of applicable students.
Consequently, there would need to be a set of observable principles to ensure
that students are selected equally. There are several ways this could be
achieved, but the most likely would be an attempt to ensure that the weighting
wouldn’t be restrictive. This would allow people to end up in, theoretically,
any class they qualify for while increasing the probability that they would end
up in classes they are best suited to.

After this, the algorithm will likely be re-run, to provide multiple possible
outcomes for matching TAs to classes. That will either allow us to improve the
program, to create a method to find the most viable of the displayed
possibilities, or it will output multiple possible matchings, allowing the user
to select matchings based on their preference. There are more methods in
discussion while pseudocode and code are being written, but those described
above provide a simple, scalable, and optimizable approach to developing an
efficient product that satisfies all desired requirements, based on the
information that we have access to.

## Program Process Flow

In the perspective of the process flow of the program, the application will be
an executable that is started by the user. Upon startup, the application will
produce a pop-up window with a welcome message, operation instructions, and an
interactive prompt for the user to input files. Upon input, the application
validates the file extension and content format to ensure security and parsing
accuracy. If invalid, an error alert pop-up will be produced and the user will
be prompted to reattempt. If valid, the CSV files will be scanned and mapped
into a list of objects.Students and a list of objects.Courses, where the object
fields are relative to the respective columns in each file being parsed.

The list of objects.Students will then be processed by a graduation priority
algorithm. From there, the list will go through the primary matching algorithm.
After multiple loops, the optimal schedule will be chosen, represented by lists
of students and courses both successfully and unsuccessfully matched. These
lists will be mapped to two CSV files with student and course data and
assignments displayed in a similar format to those that were received. The CSV
files will then be returned and displayed to the user in the pop-up window with
the user capability to download and save them.

## Student-Course Algorithm Explanation

Our approach to creating an algorithm that matches students to classes consists
of two main objects and two smaller algorithms. The two objects, Student and
Course, stem from the students and schedules data — column data is, prior to
algorithm start, mapped to its respective member variable within the object.

The first helper algorithm will calculate the time it takes for a student to
graduate, which will be known as their graduation priority. This value is
calculated with respect to the current date/quarter and the quarter/year the
student is projected to graduate. The list of objects.Students will then be
sorted by graduation priority; lowest value (i.e. 0) being the highest priority
and highest value being the lowest priority. This way, the students who graduate
sooner will be at the beginning of the list and therefore matched to classes
sooner when scanning through the list to assign TAs. With the help of this
algorithm, we can prioritize the matches by a student’s graduation time.

After sorting the list of students, we will then use another assistive algorithm
to match the students to a class. This algorithm will randomly choose a class
from a list of classes that a student has already taken. Then it will go through
a couple of requirement checks to ensure that the student can get assigned to
the class. The requirements include course seat availability, knowledge of
Python and Visual Studio basics, and schedule conflict. If all requirements
pass, the student will get assigned to the class. Otherwise, the process will
start over with another random class chosen from those that the student has
taken previously. Once the entire list of students has been scanned, the
algorithm will store new lists containing successful and unsuccessful student
and course assignments. The matching algorithm will run multiple times to
generate multiple different resulting schedule lists; once ran n times, the
algorithm will then choose the student-course list pair that satisfies the
scheduling guidelines, has the lowest number of unassigned students, and each
class necessitating a TA has been assigned one. This optimal schedule will
guarantee that every course gets matched with at least one student and that the
least amount of students possible are resultantly unmatched.

## Summary

So far, the team has held multiple meetings in order to ideate, model, and
document potential designs and approaches to the program and algorithmic logic
that will satisfy the necessary requirements. Basic object and process modeling
was completed, as well as identification of soft, hard, and ambiguous
requirements, edge cases in the data, and potential blockers as the project
develops. In doing so, we were able to create visual and textual outlines
alongside loggia, pseudo-code for our algorithm which expresses these ideas.

No obstacles were encountered during this phase of our project outside of
ambiguous requirements as the discussion scope got more granular. A primary
obstacle the team faces is time management due to a large scope of work to
complete prior to the project deadline. The project is in the development stage
but moving quickly as the logical pseudo-code for the algorithm has been
documented. Consequently the plan is to progress from pseudo-code to efficient
parallel development through properly organized project layers and
responsibilities.
