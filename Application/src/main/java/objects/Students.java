package objects;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Students {

  String firstName;
  String lastName;
  int id;
  String email;
  int taCourse;
  boolean onCampus;
  boolean python;
  boolean vsBasics;
  HashMap<String, HashMap<Integer, Boolean>> schedule;
  List<String> coursesTaken;

  public Student(String firstName, String lastName, int id, String email, int taCourse,
      boolean onCampus, boolean python, boolean vsBasics,
      HashMap<String, HashMap<Integer, Boolean>> schedule,
      List<String> coursesTaken) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.id = id;
    this.email = email;
    this.taCourse = taCourse;
    this.onCampus = onCampus;
    this.python = python;
    this.vsBasics = vsBasics;
    this.schedule = schedule;
    this.coursesTaken = coursesTaken;

  }

  @Override
  public String toString() {
    return "\n" + "First Name: " + firstName + ", Last Name: " + lastName + ", ID: " + id
        + ", Email: " + email + "\n" +
        "TA Course: " + taCourse + ", In Person: " + onCampus + ", Python: " + python +
        ", VS Basics?: " + vsBasics + "\n" +
        "Schedule: " + "\n" +
        "    Monday: " + schedule.get("M") + "\n" +
        "    Tuesday: " + schedule.get("T") + "\n" +
        "    Wednesday: " + schedule.get("W") + "\n" +
        "    Thursday: " + schedule.get("TH") + "\n" +
        "objects.Courses: " + "\n" + coursesTaken;
  }

  /**
   * @param student
   * @param column
   * @return the student's first name
   */
  public String addFirstName(Students student, String[] column) {
    String name = column[0];

    return student.firstName = name;
  }

  /**
   * @param student
   * @param column
   * @return the student's last name
   */
  public String addLastName(Students student, String[] column) {
    String name = column[1];

    return student.lastName = name;
  }

  /**
   * @param student
   * @param column
   * @return the student's student ID
   */
  public int addID(Students student, String[] column) {
    int ID = Integer.parseInt(column[2]);

    return student.id = ID;
  }

  /**
   * @param student
   * @param column
   * @return the student's email
   */
  public String addEmail(Students student, String[] column) {
    String email = column[3];

    return student.email = email;
  }

  /**
   * @param student
   * @param column
   * @return the student's applied TA course
   */
  public int addAppliedTACourse(Students student, String[] column) {
    int taCourse = Integer.parseInt(column[6]);

    return student.taCourse = taCourse;
  }

  /**
   * @param student
   * @param column
   * @return status of student's location (on campus, not on campus)
   */
  public boolean isOnCampus(Students student, String[] column) {
    String onCampus = column[7];

    boolean isOnCampus = onCampus.equalsIgnoreCase("yes");

    return student.onCampus = isOnCampus;
  }

  /**
   * @param student
   * @param column
   * @return whether the student knows python or not
   */
  public boolean knowsPython(Students student, String[] column) {
    String python = column[40];

    boolean knowsPy = python.equalsIgnoreCase("yes");

    return student.python = knowsPy;
  }

  /**
   * @param student
   * @param column
   * @return whether the student knows VS Basics or not
   */
  public boolean knowsVS(Students student, String[] column) {
    String vsBasics = column[41];

    boolean knowsVS = vsBasics.equalsIgnoreCase("yes");

    return student.vsBasics = knowsVS;
  }

  /**
   * @param student
   * @param column
   * @return the student's schedule
   */
  public HashMap<String, HashMap<Integer, Boolean>> addSchedule(Students student, String[] column) {

    HashMap<String, HashMap<Integer, Boolean>> schedule = new HashMap<>();

    String isAvailable = "Open";

    // -- Loops through entire schedule --
    for (int i = 8; i < 40; ) {
      // -- Loops through week --
      HashMap<Integer, Boolean> availability = new HashMap<>();

      for (int j = 0; j < 8; j++) {

        boolean available = isAvailable.equalsIgnoreCase(column[i]);

        switch (i % 8) {
          case 0:
            if (available) {
              availability.put(8, true);
            } else {
              availability.put(8, false);
            }
            break;
          case 1:
            if (available) {
              availability.put(9, true);
            } else {
              availability.put(9, false);
            }
            break;
          case 2:
            if (available) {
              availability.put(10, true);
            } else {
              availability.put(10, false);
            }
            break;
          case 3:
            if (available) {
              availability.put(11, true);
            } else {
              availability.put(11, false);
            }
            break;
          case 4:
            if (available) {
              availability.put(12, true);
            } else {
              availability.put(12, false);
            }
            break;
          case 5:
            if (available) {
              availability.put(13, true);
            } else {
              availability.put(13, false);
            }
            break;
          case 6:
            if (available) {
              availability.put(14, true);
            } else {
              availability.put(14, false);
            }
            break;
          case 7:
            if (available) {
              availability.put(15, true);
            } else {
              availability.put(15, false);
            }
            break;
        }
        i++;
      }
      if (i / 8 == 2) {
        schedule.put("M", availability);
      } else if (i / 8 == 3) {
        schedule.put("T", availability);
      } else if (i / 8 == 4) {
        schedule.put("W", availability);
      } else if (i / 8 == 5) {
        schedule.put("TH", availability);
      }
    }

    return student.schedule = schedule;
  }

  /**
   * @param student
   * @param column
   * @return the courses the student's have taken
   */
  public List<String> addCoursesTaken(Students student, String[] column) {
    String[] courses = {"CS 102", "CS 105", "CS 107", "CS 109", "CS 110", "CS 111", "CS 112",
        "CS 301", "CS 302", "CS 311", "CS 312", "CS 361", "CS 362", "CS 380",
        "CS 420", "CS 427", "CS 430", "CS 440", "CS 467", "CS 470", "CS 480"};
    List<String> coursesTaken = new ArrayList<String>();

    int count = 0;
    for (int i = 42; i < column.length; i++) {
      if (column[i] != "") {
        coursesTaken.add(courses[count]);
      }
      count++;
    }
    return student.coursesTaken = coursesTaken;
  }

}
