package algorithm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import objects.Courses;
import objects.Students;

public class ScheduleMatcher {

  // M is number of applicants
  // and N is number of jobs
  static final int M = 6;
  static final int N = 6;

  static List<Students> students;
  static List<Courses> courses;
  static int numStud;
  static int numCourses;

  public ScheduleMatcher(List<Students> students, List<Courses> courses) {
    this.students = students;
    this.courses = courses;
    this.numStud = students.size();
    this.numCourses = courses.size();
  }


  // A DFS based recursive function that returns true if a matching
  // for vertex u is possible
  public static boolean bpm(Boolean bpGraph[][], int u, Boolean seen[], int matchR[]) {

    // Try every job one by one
    for (int v = 0; v < numCourses; v++) {

      // If applicant u is interested
      // in job v and v is not visited
      if (bpGraph[u][v] && !seen[v]) {

        // Mark v as visited
        seen[v] = true;

        // if job 'v' is not assigned to an applicant OR
        // previously assigned applicant for job v (which is
        // matchR[v]) has an alternate job available
        // Since v is marked as visited in the above line, matchR[v]
        // in the following recursive call will not get job 'v' again

        if (matchR[v] < 0 || bpm(bpGraph, matchR[v], seen, matchR)) {
          matchR[v] = u;
          return true;
        }
      }
    }
    return false;
  }

  // Returns maximum number of matching from M to N
  public static List<Integer> maxBPM(Boolean bpGraph[][]) {

    // An array to keep track of the applicants assigned to jobs.
    // The value of matchR[i] is the applicant nummber assigned to job i,
    // the value -1 indicates nobody is assigned
    int matchR[] = new int[numCourses];

    // Initially all jobs are available
    for (int i = 0; i < numCourses; i++) {
      matchR[i] = -1;
    }

    // Count of jobs assigned to applicants
    int result = 0;
    for (int u = 0; u < numStud; u++) {
      // Mark all jobs as not seen for next applicant
      Boolean seen[] = new Boolean[numCourses];
      for (int i = 0; i < numCourses; i++) {
        seen[i] = false;
      }

      // Find if the applicant 'u' can get a job
      if (bpm(bpGraph, u, seen, matchR)) {
        result++;
      }
    }
    //return result;
    //return matchR;
    List<Integer> results = new ArrayList<>();
    results.add(result);
    for (int k = 0; k < matchR.length; k++) {
      results.add(matchR[k]);
    }
    return results;
//    results.add(matchR);
  }


  public static HashMap<Integer, Integer> hallsAlgorithm() {

    Boolean bpGraph[][] = createBPGraph();

    List<Integer> result = maxBPM(bpGraph);

    int maxMatching = result.remove(0);
    System.out.println(
        "MAXIMUM POSSIBLE STUDENTS MATCHED: " + maxMatching + " OUT OF " + numStud + " APPLIED");

    System.out.println("Maximum number of applicants that can" +
        " get job is: ");

    result.forEach(r -> {
      System.out.println("Student Index: " + result.indexOf(r) + " || Result: " + r);
    });

    HashMap<Integer, Integer> mappedResults = new HashMap<>();
    result.forEach(r -> {
      mappedResults.put(result.indexOf(r), r);
    });
    return mappedResults;
  }


  public static Boolean[][] createBPGraph() {
    List<Students> tempStudents = students;
    List<Courses> tempCourses = courses;

    List<List<Boolean>> graph = new ArrayList<>();

    // For each day in Course we find the key in Student's Day-Time (find hour) that matches
    // If the value is false, then the course and student cannot be matched

    // So set a boolean (true) that is going to verify whether false or not
    // do this in a while loop, when a student

    // Loop through all students
    for (int j = 0; j < tempStudents.size(); j++) {

      // Create an array for the student's part of the graph (boolean)
      ArrayList<Boolean> studentConflicts = new ArrayList<>();
      // for each student, loop through courses
      for (int k = 0; k < tempCourses.size(); k++) {

        boolean isAMatch = true;
        boolean web = false;

        List<String> days = tempCourses.get(k).getDays();

        // Course has array of days
        // for each day, find the day in the current student's schedule
        for (int m = 0; m < days.size(); m++) {

          HashMap<Integer, Boolean> studentDay = tempStudents.get(j).getSchedule().get(days.get(m));
          int courseST = tempCourses.get(k).getStartTime();   // Course start time
          int courseDuration = tempCourses.get(k).getEndTime() - courseST;  // Course duration

          // compare the course start and duration to the student's timeschedule for the day
          // grab the matching start time in the student day map
          if (courseST == 0) {
            web = true;
          }
          if (isAMatch && !web) {
            isAMatch = studentDay.get(courseST);
          }

          // If course is over 1 hour, then need to check if student is available during those hours
          if (isAMatch && courseDuration > 1) {
            while (courseDuration > 1) {
              isAMatch = studentDay.get(courseST + (courseDuration - 1));
              courseDuration--;
            }
          }

        }

        studentConflicts.add(isAMatch);
        web = false;

      }

      graph.add(studentConflicts);

    }

    //System.out.println(graph);

    for (int j = 0; j < graph.size(); j++) {
      System.out.println(
          tempStudents.get(j).getFirstName() + " " + tempStudents.get(j).getLastName() + ": ");
      int numMatches = 0;
      int numConflicts = 0;

      for (int k = 0; k < graph.get(j).size(); k++) {
        if (graph.get(j).get(k).equals(true)) {
          numMatches++;
        } else {
          numConflicts++;
        }
      }
      System.out.println("Number of Matches: " + numMatches);
      System.out.println("Number of Conflicts: " + numConflicts);
    }
    ArrayList<Boolean[]> temp = new ArrayList<>();
    graph.forEach(student -> {
      Boolean[] secondTemp = student.toArray(new Boolean[student.size()]);
      temp.add(secondTemp);
    });

    // map 2D ArrayList to 2D array to be passed to graph bipartite matching algo
    // String[][] stringArray = mainList.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
    Boolean[][] boolArray = graph.stream().map(u -> u.toArray(new Boolean[0]))
        .toArray(Boolean[][]::new);
    //Boolean[][] finalResponse = graph.toArray(new Boolean[][]);
    return boolArray;

  }

}