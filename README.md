# Mahjestic TA Assignment Program
The Mahjestic TA Assignment program is an application that assigns students to TA courses based on given prerequisites.

## Installation
Download the program from https://github.com/mahjestic/JIHL

## Usage
To use the program, simply run the executable jar file from the downloaded bundle, select the desired input files using the labelled buttons within the UI, and then click on the "Run" button to output a list of assigned TAs.

## Contributing
Normally, there would be some form of way to submit suggestions or contributions to the program here. However, since this is a graded assignment for a course, and not an actual sold program, please make suggestions via canvas.

## License
To our knowledge, no license is necessary for this submission.

___
## How The Program Works

This program takes in two CSV files from the user: one specifying different courses that require TAs, and the other specifying students that need a TA assignment. Our program applies a parsing system to read through each file and then feeds that data into the algorithm. The algorithm creates a truth matrix of students to courses that declares what courses each student is eligible for, and utilizes maximum bipartite matching to find the best schedule based on the different requirements for each student. After establishing the schedule, the program organizes it into a new CSV file that is then given to the user to download.  
