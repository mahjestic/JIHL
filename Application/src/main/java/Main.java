import algorithm.ScheduleMatcher;
import java.util.Arrays;
import java.util.List;

public class Main {

  public static final List<String> names = Arrays
      .asList("Emily", "Lena", "Jon", "Isabelle", "Arne", "Derek", "Steven", "Caroline", "Anna",
          "Kimberly", "Elsa", "Sam", "Forrest", "Logan");
  public static final List<String> lastNames = Arrays
      .asList("Hansen", "Le", "Burks", "Melton", "Leitert", "Waldeck", "Hunt", "Smith", "Roberts",
          "Lonowski", "Peters", "Teske", "Lins", "Davis");


  public static void main(String[] args) {

    ScheduleMatcher matcher = new ScheduleMatcher();
    matcher.hallsAlgorithm();
  }

//  public static List<Students> createMockStudents(int num) {
//    ArrayList<Students> students = new ArrayList<>();
//    for (int i = 0; i < num; i++) {
//      int course = 492;
//      if (i % 2 == 0) {
//        course = 392;
//      }
//      Students s = new Students(
//          names.get(i),
//          lastNames.get(i),
//          i,
//          "email@gmail.com",
//          course,
//          true,
//          true,
//          true,
//          )
//    }
//  }

}
