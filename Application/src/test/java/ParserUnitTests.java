import java.util.List;
import objects.Courses;
import objects.Students;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.mockito.MockitoAnnotations;
import parser.Parse;
import utils.MockObjectGenerator;

@TestInstance(Lifecycle.PER_CLASS)
public class ParserUnitTests {

  private final MockObjectGenerator mockObjectGenerator = new MockObjectGenerator();
  private int NUM_MOCK_STUDENTS = 50;
  private List<Integer> courseCodes = mockObjectGenerator.courseCodes;
  private List<Students> mockStudents = mockObjectGenerator.createMockStudents(NUM_MOCK_STUDENTS);
  private String MOCK_STUDENT_CSV_STRING = mockObjectGenerator.toStudentCSVString(mockStudents);
  private List<Courses> mockCourses = mockObjectGenerator.createMockCourses(courseCodes.size());
  private String MOCK_COURSES_CSV_STRING = mockObjectGenerator.toCourseCSVString(mockCourses);

  private final Parse parser = new Parse();

//  private final Parse parser = new Parse(MOCK_STUDENT_CSV_STRING, )

  @BeforeAll
  public void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  public void testStudentParser() {
    String studCSVString = mockObjectGenerator.toStudentCSVString(mockStudents);

//    List<Students> results = parser.studentFileParser(studCSVString);
  }


  @Test
  public void testScheduleParser() {

  }

}
