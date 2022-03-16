package parser;
import objects.Courses;
import objects.Students;
import algorithm.ScheduleMatcher;
import java.time.LocalTime;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.io.IOException;
import java.net.SecureCacheResponse;
import java.io.*;

public class CSVWriter {
    private static final String outputCSV = "Application/src/main/java/results/matchedTAs.csv";

    private static String sub;
    private static int code;
    private static String section;
    private static String title;
    private static String professor;
    private static List<String> days;
    private static LocalTime startTime;
    private static LocalTime endTime;
    private static String facilityID;
    private static String campus; 
    private static String TAs;
    private static int studentID;
    private static String studentEmail;

    private static Parse parser = new Parse();
    public CSVWriter() {
    }
    public static void main(String[] args) throws IOException {
        String scheduleCSV = "/Users/lenale/Desktop/cs480/schedule.csv";
        String studentCSV = "/Users/lenale/Desktop/cs480/students.csv";
        createCSVFile(scheduleCSV,studentCSV);
    }

    public static void createCSVFile(String studentCSV, String scheduleCSV) throws IOException {
        List<Courses> courses = parser.scheduleFileParser(scheduleCSV);
        List<Students> students = parser.studentFileParser(studentCSV);
      
        ScheduleMatcher matchMachine = new ScheduleMatcher(students, courses);
        HashMap<Integer, Integer> results = matchMachine.hallsAlgorithm();
 
        HashMap<Students,Courses> assignedTAs = matchMachine.assignedTAs(results);


        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputCSV));
            
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader("Sub", "Cat", "Sec","Title", "Professor","Days","Start Time","End Time","Facility ID","Campus","TAs", "Student ID","Student Email"));
            ){
                
                for (Courses course : courses) {
                    sub = course.getSub();
                    code = course.getCode();
                    section = course.getSection();
                    title = course.getTitle();
                    professor = course.getProfessor();
                    days = course.getDays();
                    startTime = course.getStartTime();
                    endTime = course.getEndTime();
                    facilityID = course.getFacilityID();
                    campus = course.getCampus();
                    //HELPS
                    for(Students student : students){
                        // System.out.println(student.getFirstName()+ " "+ assignedTAs.get(student).getTitle());
                        if(course == assignedTAs.get(student)){
                            TAs = student.getFirstName() + " " + student.getLastName();
                        }else if (assignedTAs.get(student) == null){
                            TAs = "NO TA";
                        }

                    }

                    csvPrinter.printRecord(sub,code,section,title,professor,days,startTime,endTime,facilityID,campus,TAs);
                }
 
                csvPrinter.flush();            
            }
    }
}