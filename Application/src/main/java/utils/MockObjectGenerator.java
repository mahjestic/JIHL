package utils;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.logging.Logger;
import objects.Courses;
import objects.Students;

public class MockObjectGenerator {

  private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

  public List<Integer> courseCodes = Arrays
      .asList(102, 105, 107, 109, 110, 111, 112, 184, 301, 302, 311, 312, 325, 361, 362, 380, 392,
          420, 427, 430, 440, 470, 480, 481, 489, 492); // 26 classes

  public List<String> quarters = Arrays.asList("Fall", "Winter", "Spring", "Summer");

  public Integer numTotalCourses = courseCodes.size();

  public List<List<String>> possibleDays = new ArrayList<List<String>>() {{
    //Singular
    add(Arrays.asList("M", "T", "W", "TH"));
    //Usually paired with "T", "TH"
    add(Arrays.asList("M", "W"));
    // Either singular, or paired with an online row with same section (usually online row is "M", "W"), or paired with a duplicate row different section and time
    add(Arrays.asList("T", "TH"));
    // Either alone or WEB with one in person lab
    add(Arrays.asList("W"));
    // These are usually paired (lecture/lab)
    add(Arrays.asList("M", "W", "TH"));
    add(Arrays.asList("T"));
  }};

  public MockObjectGenerator() {

  }

  public List<Integer> getCourseCodes() {
    return courseCodes;
  }

  public List<Courses> createMockCourses(int num) {

    log.info("Creating " + num + " Mock Courses...");
    List<Courses> response = new ArrayList<>();

    Random randy = new Random();

    for (int i = 0; i < num; i++) {

      String sub = "CS";    // Sub
      Integer code = courseCodes.get(i);  //Course code
      String section = "1";   // Section
      String courseName = "Course number " + i;   // Course Title
      String profName = "Professor";    // Professor Name

      String level = "492"; // Level
      if (randy.nextBoolean()) {
        level = "392";
      }

      // Days
      Collections.shuffle(possibleDays);
      List<String> tempDays = possibleDays.get(0);
      List<String> secondRowDays = checkDays(
          tempDays);   // This is when the same section has a second row (like lecture/lab)

      int mult = randy.nextInt(12 - 0); // multiplier to 5 minute possible minute intervals
      LocalTime sT = LocalTime
          .of((randy.nextInt((19 - 6) + 1) + 6), (5 * mult));   // Possible times for classes
      LocalTime eT = sT
          .plusMinutes(50);    // End time is just going to be one hour after start at the moment

      //[TODO] classes with multi-hour durations

      String facilID = UUID.randomUUID().toString();  // Facility ID
      String camp = "EBURG";  // On Campus or Web
      if (randy.nextBoolean()) {
        camp = "WEB";
      }

      if (!camp.equals("EBURG") && (secondRowDays.isEmpty() && randy.nextBoolean())) {
        if (randy.nextBoolean()) {
          sT = LocalTime.MIDNIGHT;
          eT = LocalTime.MIDNIGHT;
        }

      } else if (!camp.equals("EBURG") &&
          (!secondRowDays.isEmpty() &&
              (!secondRowDays.get(0).equals("") &&
                  randy.nextBoolean()))) {
        sT = LocalTime.MIDNIGHT;
        eT = LocalTime.MIDNIGHT;
      }

      Courses courseToAdd = new Courses(
          sub,
          code,
          section,
          courseName,
          profName,
          level,
          1,
          null,
          tempDays,
          sT,
          eT,
          facilID,
          camp
      );

      log.info("Course " + i + " Created: " + courseToAdd.toString());
      response.add(courseToAdd);

      // If there is a secondary row assigned to this class (usually different day/time),
      // then create another class value
      if (!secondRowDays.isEmpty()) {

        LocalTime startTime = LocalTime.MIDNIGHT;
        LocalTime endTime = LocalTime.MIDNIGHT;
        String campus = "EBURG";
        if (randy.nextBoolean() || secondRowDays.get(0).equals("")) {
          campus = "WEB";
        }

        if (!secondRowDays.get(0).equals("")) {   // If it is not an online hybrid (async)
          if (randy.nextBoolean() && campus.equals("WEB")) {     // 50% chance that the web
            int multiplier = randy.nextInt(12 - 0);
            startTime = LocalTime.of((randy.nextInt((19 - 6) + 1) + 6), (5 * multiplier));

            //[TODO] classes with multi-hour durations
            endTime = startTime.plusMinutes(50);
          }


        }

        Courses secondCourseSameSection = new Courses(
            sub,
            code,
            section,
            courseName,
            profName,
            level,
            1,
            null,
            secondRowDays,
            startTime,
            endTime,
            facilID,
            campus
        );

        log.info(
            "Part 2 Of Course " + i + "(" + courseToAdd.getTitle() + " " + courseToAdd.getSection()
                + ") Created: " + secondCourseSameSection.toString());
        response.add(secondCourseSameSection);
      }
    }
    return response;
  }


  public List<String> checkDays(List<String> days) {

    // Based on which Day schedule we picked, we determine if another row/Course of the
    // same section is needed (like a lecture with a lab)

    List<String> secondSectionDays = new ArrayList<>();
    Random randy = new Random();

    // Should be paired with a Lab (IP/W; if one is O then other != W)
    if (days.equals(Arrays.asList("M", "W", "TH"))) {
      secondSectionDays.add("T");

      // Same thing as above applies, but inverse
    } else if (days.equals(Arrays.asList("T"))) {
      secondSectionDays.add("M");
      secondSectionDays.add("W");
      secondSectionDays.add("TH");

      // Singular 'W' is often either one singular row (IP/W) or the lab for a hybrid course(IP)
    } else if (days.equals(Arrays.asList("W"))) {

      // Determines if this will be one section one row, or if it will be one section
      // with another row same section but web asynchronous
      if (randy.nextBoolean()) {
        secondSectionDays.add("");    // if true then make another section

      }

      // Usually either a singular row or paired with an online 'M', 'W' online portion
    } else if (days.equals(Arrays.asList("T", "TH"))) {

      // determine if singular or with hybrid portion
      if (randy.nextBoolean()) {

        if (randy.nextBoolean()) {  // determine whether secondary row is async or on M,W
          secondSectionDays.add("");  // Add asynchronous hybrid online row

          // OR add secondary row with 'M', 'W' days
        } else {
          secondSectionDays.add("M");
          secondSectionDays.add("W");
        }

        // If false overall, the course is a singular 2-day course,
        // so no secondary days (2nd row) are added
      } else {

      }
      // Same as case above, but inverse
    } else if (days.equals(Arrays.asList("M", "W"))) {
      secondSectionDays.add("T");
      secondSectionDays.add("TH");

      // Only other option is M, T, W, TH (which is singular, so no secondary days added)
    } else {

    }

    return secondSectionDays;
  }

  public List<Students> createMockStudents(int num) {
    log.info("Creating " + num + " Mock Students...");

    List<String> classes = Arrays
        .asList("CS102", "CS105", "CS107", "CS109", "CS110", "CS111", "CS112",
            "CS301", "CS302", "CS311", "CS312", "CS361", "CS362", "CS380", "CS420", "CS427",
            "CS430", "CS440", "CS467", "CS470", "CS480");
    Random randy = new Random();
    ArrayList<Students> students = new ArrayList<>();

    for (int i = 0; i < num; i++) {
      int course = 492;
      if (i % 2 == 0) {
        course = 392;
      }

      HashMap<String, HashMap<LocalTime, Boolean>> schedule = new HashMap<String, HashMap<LocalTime, Boolean>>();
      List<String> days = Arrays.asList("M", "T", "W", "TH");
      days.forEach(day -> {
        schedule.put(day, new HashMap<LocalTime, Boolean>() {{
          put(LocalTime.of(6, 0), randy.nextBoolean());
          put(LocalTime.of(7, 0), randy.nextBoolean());
          put(LocalTime.of(8, 0), randy.nextBoolean());
          put(LocalTime.of(9, 0), randy.nextBoolean());
          put(LocalTime.of(10, 0), randy.nextBoolean());
          put(LocalTime.of(11, 0), randy.nextBoolean());
          put(LocalTime.of(12, 0), randy.nextBoolean());
          put(LocalTime.of(13, 0), randy.nextBoolean());
          put(LocalTime.of(14, 0), randy.nextBoolean());
          put(LocalTime.of(15, 0), randy.nextBoolean());
          put(LocalTime.of(16, 0), randy.nextBoolean());
          put(LocalTime.of(17, 0), randy.nextBoolean());
          put(LocalTime.of(18, 0), randy.nextBoolean());
          put(LocalTime.of(19, 0), randy.nextBoolean());
          put(LocalTime.of(20, 0), randy.nextBoolean());
        }});
      });

      int numCourses = randy.nextInt(10 - 1) + 1;
      List<String> coursesTaken = new ArrayList<>();
      for (int j = 0; j < numCourses; j++) {
        Collections.shuffle(classes);
        coursesTaken.add(classes.get(0));
      }

      // Want to generate a large amount of students, so no personalized names
      String mockFirstName = "StudFirstName#" + i;
      String mockLastName = "StudLastName#" + i;
      String mockGradQuarter = quarters.get(randy.nextInt(4 - 0));
      Integer currYear = LocalDate.now().getYear();
      String mockGradYear = Integer.toString(randy.nextInt(((currYear + 5) - 2022) + 2022));

      Students s = new Students(
          mockFirstName,
          mockLastName,
          i,
          "email@gmail.com",
          mockGradQuarter,
          Integer.toString(currYear),
          course,
          true,
          true,
          true,
          schedule,
          coursesTaken
      );

      log.info("Student " + i + " Created: " + s.toString());
      students.add(s);
    }

    return students;
  }

  public String toStudentCSVString(List<Students> students) {
    StringBuilder sb = new StringBuilder();
    students.forEach(s -> {

      // Appending the first portion of the student row
      sb.append(
          s.getFirstName() + "," + s.getLastName() + "," + s.getId() + "," + s.getEmail() + ","
              + s.getGradQuarter() + "," + s.getGradYear() + "," + s.getTaCourse());

      // Extracting and formatting the days
      HashMap<String, HashMap<LocalTime, Boolean>> studSched = s.getSchedule();
      List<String> days = Arrays.asList("M", "T", "W", "TH");
      days.forEach(d -> {
        HashMap<LocalTime, Boolean> businessDay = studSched.get(d);

        Iterator iterator = businessDay.entrySet().iterator();

        while (iterator.hasNext()) {
          Map.Entry element = (Map.Entry) iterator.next();
          LocalTime key = ((LocalTime) element.getKey());
          Boolean val = ((Boolean) element.getValue());

          String temp = "Conflict";
          if (val) {
            temp = "Open";
          }
          sb.append("," + temp);
        }
      });
      // Appending the Python and VSBasic parts
      sb.append("," + s.isPython() + "," + s.isVsBasics());

      // Appending the class thing
      courseCodes.forEach(code -> {
        if (s.getCoursesTaken().contains(("CS" + code))) {
          sb.append(",X");
        } else {
          sb.append(",");
        }
      });

      sb.append("\n");
    });

    return sb.toString();
  }


    /*

    EXAMPLE SNIPPET OF DESIRED OUTPUT FORMAT

    CS,102,1,Health and Technology,"Salter,Rosemary", T  TH,9:00AM,9:50AM,SAMU171,EBURG
    CS,105,1,The Logical Basis of Computing,"Abdul-Wahid,Sarah",,:AM,:AM,,EBURG
    CS,105,1,The Logical Basis of Computing,"Abdul-Wahid,Sarah", T  TH,11:00AM,11:50AM,SAMU171,EBURG
    CS,107,1,Make a Game with Computer Sci,"Harrison,Tatiana F", T  TH,2:00PM,2:50PM,SAMU147,EBURG
    CS,107,1,Make a Game with Computer Sci,"Harrison,Tatiana F",M  W,2:00PM,2:50PM,,EBURG
    CS,109,1,Quantitative Reasoning: Python,"Abdul-Wahid,Sarah", T  TH,1:00PM,1:50PM,SAMU171,EBURG
    CS,110,A01,Programming Fundamentals I,"Hueffed,Joseph Dominic",  W,6:00PM,6:45PM,ONLINE,WEB
    CS,110,A01,Programming Fundamentals I,"Hueffed,Joseph Dominic",  W,6:45PM,7:30PM,ONLINE,WEB
     */

  public String toCourseCSVString(List<Courses> courses) {
    StringBuilder sb = new StringBuilder();

    courses.forEach(c -> {
      // Handle Initial rows Sub, Code, Sec, Title, Name
      sb.append(
          c.getSub() + "," + c.getCode() + "," + c.getSection() + "," + c.getTitle() + ",\"" + c
              .getProfessorLastFirst() + "\"");

      // Append the Days
      List<String> tempDays = c.getDays();
      if (tempDays.isEmpty()) {
        sb.append(",");
      } else {
        sb.append(",");
        tempDays.forEach(day -> {
          sb.append(day + " ");
        });
        sb.append(",");
      }

      // Append Start Time
      if (c.getStartTime().equals(LocalTime.MIDNIGHT)) {
        sb.append(",:AM");
      } else {
        sb.append("," + c.getStartTime().getHour() + ":" + c.getStartTime().getMinute());
      }

      // Append End Time
      if (c.getEndTime().equals(LocalTime.MIDNIGHT)) {
        sb.append(",:AM");
      } else {
        sb.append("," + c.getEndTime() + ":" + c.getEndTime().getMinute());

      }

      // Append Faculty ID and campus
      sb.append("," + c.getFacilityID() + "," + c.getCampus());
    });

    return sb.toString();
  }

}
