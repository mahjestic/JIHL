import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import objects.Courses;
import objects.Students;

public class MockObjectGenerator {

  public MockObjectGenerator() {

  }


  public final List<String> names = Arrays
      .asList("Emily", "Lena", "Jon", "Isabelle", "Arne", "Derek", "Steven", "Caroline", "Anna",
          "Kimberly", "Elsa", "Sam", "Forrest", "Logan");
  public final List<String> lastNames = Arrays
      .asList("Hansen", "Le", "Burks", "Melton", "Leitert", "Waldeck", "Hunt", "Smith", "Roberts",
          "Lonowski", "Peters", "Teske", "Lins", "Davis");

  public static List<Integer> courseCodes = Arrays
      .asList(102, 105, 107, 109, 110, 111, 112, 184, 301, 302, 311, 312, 325, 361, 362, 380, 392,
          420, 427, 430, 440, 470, 480, 481, 489, 492); // 26 classes

  public static Integer numTotalCourses = courseCodes.size();

  public static List<List<String>> possibleDays = new ArrayList<List<String>>() {{
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


  public static List<Courses> createMockCourses(int num) {

    List<Courses> response = new ArrayList<>();

    Random randy = new Random();
    List<Integer> tempCourseList = courseCodes;

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
      List<String> randomizedDays = new ArrayList<>();
      Collections.shuffle(possibleDays);
      List<String> tempDays = possibleDays.get(0);
      List<String> secondRowDays = checkDays(
          tempDays);   // This is when the same section has a second row (like lecture/lab)

      Integer sT = randy.nextInt((19 - 6) + 1) + 6; //Start Time, just rounding into whole hours
      Integer eT = sT + 1;  // End time is just going to be one hour after start at the moment

      String facilID = UUID.randomUUID().toString();  // Facility ID
      String camp = "EBURG";  // On Campus or Web
      if (randy.nextBoolean()) {
        camp = "WEB";
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

      response.add(courseToAdd);

      if (!secondRowDays.isEmpty()) {

        Integer startTime = 0;
        Integer endTime = 0;
        String campus = "EBURG";
        if (randy.nextBoolean() || secondRowDays.get(0).equals("")) {
          campus = "WEB";
        }

        if (!secondRowDays.get(0).equals("")) {   // If it is not an online hybrid (async)
          startTime = randy.nextInt((19 - 6) + 1) + 6;
          endTime = startTime + 1;
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

        response.add(secondCourseSameSection);
      }
    }

    return response;

  }


  public static List<String> checkDays(List<String> days) {
    // Based on which Day schedule we picked, we determine if another row/Course of the same section is needed (like a lecture with a lab)
    List<String> secondSectionDays = new ArrayList<>();
    Random randy = new Random();
    if (days.equals(Arrays.asList("M", "W", "TH"))) {
      // Should be paired with a Lab (doesnt matter if in person or online
      secondSectionDays.add("T");
    } else if (days.equals(Arrays.asList("T"))) {
      secondSectionDays.add("M");
      secondSectionDays.add("W");
      secondSectionDays.add("TH");


    } else if (days.equals(Arrays.asList("W"))) {

      if (randy
          .nextBoolean()) {  // Determines if this will be one section one row, or if it will be one section with another row same section but web asynchronous
        // if true then make another section
        secondSectionDays.add("");
      }

    } else if (days.equals(Arrays.asList("T", "TH"))) {
      // determine if singular or with hybrid portion
      if (randy.nextBoolean()) {

        if (randy.nextBoolean()) {
          secondSectionDays.add("");

        } else {
          secondSectionDays.add("M");
          secondSectionDays.add("W");
        }
      } else {
        // else it is singular
      }
    } else if (days.equals(Arrays.asList("M", "W"))) {
      secondSectionDays.add("T");
      secondSectionDays.add("TH");
    } else {  // Only other option is M, T, W, TH

    }

    return secondSectionDays;
  }

//  public static void printCourseList(List<Courses> courses) {
//    courses.forEach(course -> {
//      System.out.print(course.getSub() + course.getCode() + " " + course.getTitle() + " || " + course.getProfessor() +
//      " ");
//    });
//  }

  public static List<Students> createMockStudents(int num) {

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

      HashMap<String, HashMap<Integer, Boolean>> schedule = new HashMap<String, HashMap<Integer, Boolean>>();
      schedule.put("M", new HashMap<Integer, Boolean>() {{
        put(6, randy.nextBoolean());
        put(7, randy.nextBoolean());
        put(8, randy.nextBoolean());
        put(9, randy.nextBoolean());
        put(10, randy.nextBoolean());
        put(11, randy.nextBoolean());
        put(12, randy.nextBoolean());
        put(13, randy.nextBoolean());
        put(14, randy.nextBoolean());
        put(15, randy.nextBoolean());
        put(16, randy.nextBoolean());
        put(17, randy.nextBoolean());
        put(18, randy.nextBoolean());
        put(19, randy.nextBoolean());
        put(20, randy.nextBoolean());

      }});
      schedule.put("T", new HashMap<Integer, Boolean>() {{
        put(6, randy.nextBoolean());
        put(7, randy.nextBoolean());
        put(8, randy.nextBoolean());
        put(9, randy.nextBoolean());
        put(10, randy.nextBoolean());
        put(11, randy.nextBoolean());
        put(12, randy.nextBoolean());
        put(13, randy.nextBoolean());
        put(14, randy.nextBoolean());
        put(15, randy.nextBoolean());
        put(16, randy.nextBoolean());
        put(17, randy.nextBoolean());
        put(18, randy.nextBoolean());
        put(19, randy.nextBoolean());
        put(20, randy.nextBoolean());
      }});
      schedule.put("W", new HashMap<Integer, Boolean>() {{
        put(6, randy.nextBoolean());
        put(7, randy.nextBoolean());
        put(8, randy.nextBoolean());
        put(9, randy.nextBoolean());
        put(10, randy.nextBoolean());
        put(11, randy.nextBoolean());
        put(12, randy.nextBoolean());
        put(13, randy.nextBoolean());
        put(14, randy.nextBoolean());
        put(15, randy.nextBoolean());
        put(16, randy.nextBoolean());
        put(17, randy.nextBoolean());
        put(18, randy.nextBoolean());
        put(19, randy.nextBoolean());
        put(20, randy.nextBoolean());
      }});
      schedule.put("TH", new HashMap<Integer, Boolean>() {{
        put(6, randy.nextBoolean());
        put(7, randy.nextBoolean());
        put(8, randy.nextBoolean());
        put(9, randy.nextBoolean());
        put(10, randy.nextBoolean());
        put(11, randy.nextBoolean());
        put(12, randy.nextBoolean());
        put(13, randy.nextBoolean());
        put(14, randy.nextBoolean());
        put(15, randy.nextBoolean());
        put(16, randy.nextBoolean());
        put(17, randy.nextBoolean());
        put(18, randy.nextBoolean());
        put(19, randy.nextBoolean());
        put(20, randy.nextBoolean());
      }});

      int numCourses = randy.nextInt(10 - 1) + 1;
      List<String> coursesTaken = new ArrayList<>();
      for (int j = 0; j < numCourses; j++) {
        Collections.shuffle(classes);
        coursesTaken.add(classes.get(0));
      }

      // Want to generate a large amount of students, so no personalized names
      String mockFirstName = "StudFirstName#" + i;
      String mockLastName = "StudLastName#" + i;

      Students s = new Students(
          mockFirstName,
          mockLastName,
          i,
          "email@gmail.com",
          course,
          true,
          true,
          true,
          schedule,
          coursesTaken
      );

      students.add(s);
    }

    return students;
  }

}
