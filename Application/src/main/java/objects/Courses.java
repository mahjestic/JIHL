package objects;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.logging.Logger;

public class Courses {

  private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  private String sub;
  private int code;
  private String section;
  private String title;
  private String professor;
  private String level;
  private int seatsAvailable;
  private List<String> TAs;
  private List<String> days;
//  private int startTime;
//  private int endTime;

  private LocalTime startTime;
  private LocalTime endTime;

  private String facilityID;
  private String campus;
  private String professorLastFirst;

  public Courses() {
  }

  public Courses(String sub, int code, String section, String title, String professor, String level,
      int seatsAvailable, List<String> TAs, List<String> days, LocalTime startTime,
      LocalTime endTime, String facilityID, String campus) {
    this.sub = sub;
    this.code = code;
    this.section = section;
    this.title = title;
    this.professor = professor;
    this.level = level;
    this.seatsAvailable = seatsAvailable;
    this.TAs = TAs;
    this.days = days;
    this.startTime = startTime;
    this.endTime = endTime;
    this.facilityID = facilityID;
    this.campus = campus;


  }

  @Override
  public String toString() {
    return "\n" +
        "Level: " + level + ", Course: " + (sub + " " + code) + ", Secton: " + section + ", Title: "
        + title +
        "\nProfessor: " + professorLastFirst + ", Campus: "
        + campus + ", Facility ID: " + facilityID +
        "\n" + "Days: " + days + ", Start Time: " + (Objects.nonNull(startTime) ? startTime
        .toString() : "") + ", End Time: " + (Objects.nonNull(endTime) ? endTime.toString() : "");
  }

  /**
   * @param course
   * @param column
   * @return the sub of the course
   */
  public String addSub(Courses course, String[] column) {

    String sub = column[0];

    return course.sub = sub;
  }

  /**
   * @param course
   * @param column
   * @return the code of the course
   */
  public int addCode(Courses course, String[] column) {

    String code = column[1];

    return course.code = Integer.parseInt(code);
  }

  /**
   * @param course
   * @param column
   * @return the section of the course
   */
  public String addSection(Courses course, String[] column) {

    String section = column[2];

    return course.section = section;
  }

  /**
   * @param course
   * @param column
   * @return the title of the course
   */
  public String addTitle(Courses course, String[] column) {

    String title = column[3];

    return course.title = title;
  }

  /**
   * @param course
   * @param column
   * @return the TA level of the course
   */
  public String addLevel(Courses course, String[] column) {

    int code = course.addCode(course, column);
    String level = "";

    if (code < 300) {
      level = "392";
    } else if (code > 380) {
      level = "492";
    } else {
      level = "both";
    }

    return course.level = level;
  }

  /**
   * @param course
   * @param column
   * @return the facility ID of the course
   */
  public String addFacilID(Courses course, String[] column) {
    String ID;

    if (column[9] == "") {
      ID = "TBD";
    } else {
      ID = column[9];
    }

    return course.facilityID = ID;
  }

  /**
   * @param course
   * @param column
   * @return whether the course is on campus or not
   */
  public String addCampus(Courses course, String[] column) {
    String campus;

    campus = column[column.length - 1];

    return course.campus = campus;
  }

  /**
   * @param course
   * @param column
   * @return the professor of the course
   */
  public String addProfessor(Courses course, String[] column) {

    String professor;
    String firstName = column[4];
    String lastName;

    if (firstName == "") {
      professor = "TBD";
    } else {
      lastName = column[5];
      professor = firstName + " " + lastName;
      this.professorLastFirst = lastName + "," + firstName;
    }

    return course.professor = professor;
  }

  /**
   * @param course
   * @param column
   * @return the days the course will take place
   */
  public List<String> addDays(Courses course, String[] column) {

    List<String> days = new ArrayList<String>();

    String professor = course.addProfessor(course, column);
    String campus = course.addCampus(course, column);

    //If professor is undefined
    if (professor.equals("TBD")) {
      String daysCol = column[5];

      if (daysCol == "" && campus.equals("WEB")) {
        days.add("A");
      } //Asynchronous

      else if (daysCol == "" && campus.equals("EBURG")) {
        days.add("TBD");
      } //Days haven't been set yet

      else {
        String str = daysCol.trim();
        String[] day = str.split(" ");

        for (String d : day) {
          if (d != "") {
            days.add(d);
          }
        }
      }
    }
    //If professor is defined
    else {
      String daysCol = column[6];
      if (daysCol == "" && campus.equals("WEB")) {
        days.add("A");
      } else if (daysCol == "" && campus.equals("EBURG")) {
        days.add("TBD");
      } else {
        String str = daysCol.trim();
        String[] day = str.split(" ");

        for (String d : day) {
          if (d != "") {
            days.add(d);
          }
        }
      }
    }
    return course.days = days;
  }

  /**
   * @param course
   * @param column
   * @return the starting time of the course
   */
  public LocalTime addStartTime(Courses course, String[] column) {
    LocalTime sT = LocalTime.MIDNIGHT;

    String professor = course.addProfessor(course, column);
//    int startTime = 0;

    // If professor column is empty
    if (professor.equals("TBD")) {
      String time = column[6];

      if (time.length() >= 3) {
        String suffix = time.substring(time.length() - 2);
        String[] temp = time.substring(0, time.length() - 2).split(":");
        if (suffix.equals("PM")) {
          if (temp[0].equals("12")) {
            int t = Integer.parseInt(temp[0]) + 11;
            temp[0] = Integer.toString(t);

          } else {
            int t = Integer.parseInt(temp[0]) + 12;
            temp[0] = Integer.toString(t);

          }
        }
        if (temp.length > 1) {
          if (temp[0] == "") {
            temp[0] = "0";
          }
          if (temp[1] == "") {
            temp[1] = "0";
          }
          sT = LocalTime.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
      }

    } else { // If Professor column is not empty
      String time = column[7];
      if (time.length() >= 3) {
        String suffix = time.substring(time.length() - 2);
        log.info(suffix);
        String[] temp = time.substring(0, time.length() - 2).split(":");
        List<String> a = Arrays.asList(temp);
        log.info(a.toString());
        if (suffix.equals("PM")) {
          if (temp[0].equals("12")) {
            int t = Integer.parseInt(temp[0]) + 11;
            temp[0] = Integer.toString(t);

          } else {
            int t = Integer.parseInt(temp[0]) + 12;
            temp[0] = Integer.toString(t);

          }
        }
        if (temp.length > 1) {
          if (temp[0] == "") {
            temp[0] = "0";
          }
          if (temp[1] == "") {
            temp[1] = "0";
          }
          sT = LocalTime.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
      }

    }
    //log.info(sT.toString());
    return course.startTime = sT;

  }

  /**
   * @param course
   * @param column
   * @return the end time of the course
   */
  public LocalTime addEndTime(Courses course, String[] column) {

    String professor = course.addProfessor(course, column);
    LocalTime eT = LocalTime.MIDNIGHT;
//    int endTime = 0;

    // If professor column is empty
    if (professor.equals("TBD")) {
      String time = column[6];
      if (time.length() >= 3) {
        String suffix = time.substring(time.length() - 2);
        String[] temp = time.substring(0, time.length() - 2).split(":");
        if (suffix.equals("PM")) {
          if (temp[0].equals("12")) {
            int t = Integer.parseInt(temp[0]) + 11;
            temp[0] = Integer.toString(t);

          } else {
            int t = Integer.parseInt(temp[0]) + 12;
            temp[0] = Integer.toString(t);

          }
        }
        if (temp.length > 1) {
          if (temp[0] == "") {
            temp[0] = "0";
          }
          if (temp[1] == "") {
            temp[1] = "0";
          }
          eT = LocalTime.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
      }


    } else { // If Professor column is not empty

      String time = column[7];
      if (time.length() >= 3) {
        String suffix = time.substring(time.length() - 2);
        String[] temp = time.substring(0, time.length() - 2).split(":");
        if (suffix.equals("PM")) {
          if (temp[0].equals("12")) {
            int t = Integer.parseInt(temp[0]) + 11;
            temp[0] = Integer.toString(t);

          } else {
            int t = Integer.parseInt(temp[0]) + 12;
            temp[0] = Integer.toString(t);

          }
        }
        if (temp.length > 1) {
          if (temp[0] == "") {
            temp[0] = "0";
          }
          if (temp[1] == "") {
            temp[1] = "0";
          }
          eT = LocalTime.of(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]));
        }
      }
    }
    //log.info(eT.toString());
    return course.endTime = eT;

  }


  public String getSub() {
    return sub;
  }

  public int getCode() {
    return code;
  }

  public String getSection() {
    return section;
  }

  public String getTitle() {
    return title;
  }

  public String getProfessor() {
    return professor;
  }

  public String getProfessorLastFirst() {
    if (Objects.isNull(professorLastFirst)) {
      return professor;
    } else {
      return professorLastFirst;
    }
  }

  public String getLevel() {
    return level;
  }

  public int getSeatsAvailable() {
    return seatsAvailable;
  }

  public List<String> getTAs() {
    return TAs;
  }

  public List<String> getDays() {
    return days;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public String getFacilityID() {
    return facilityID;
  }

  public String getCampus() {
    return campus;
  }
}
