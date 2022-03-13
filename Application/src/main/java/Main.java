public class Main {

  public static void main(String[] args) {

    java.awt.EventQueue.invokeLater(new Runnable() {
      public void run() {
        new ui.GUI().setVisible(true);
      }
    });

//    utils.MockObjectGenerator generator = new utils.MockObjectGenerator();
//
//    List<Students> studs = generator.createMockStudents(50);
//    studs.forEach(student -> {
//      System.out.println(student.toString());
//    });

//    List<Courses> courses = generator.createMockCourses(
//        generator.numTotalCourses);
////    courses.forEach(course -> {
////      System.out.println(course.toString());
////    });
//
//    ScheduleMatcher matcher = new ScheduleMatcher(studs, courses);
//    HashMap<Integer, Integer> results = matcher.hallsAlgorithm();

  }

}
