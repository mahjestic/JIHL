package parser;
import objects.Courses;
import objects.Students;
import java.time.LocalTime;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.io.IOException;
import java.io.*;

public class CSVWriter {
    private static final String outputCSV = "src/main/java/Results/matchedTAs.csv";

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

    private static List<String> TAs;
    private static int studentID;
    private static String studentEmail;

    private static Parse parser = new Parse();

    public CSVWriter() {
    }
    
    public void createCSVFile(String scheduleCSV) throws IOException {
        List<Courses> courses = parser.scheduleFileParser(scheduleCSV);

        try (
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputCSV));
            
            CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
            .withHeader("Sub", "Cat", "Sec","Title", "Professor","Days","Start Time","End Time","Facility ID","Campus","TAs", "Student ID","Student Email"));
            ){
                

                for (int i = 0; i < courses.size(); i++) {
                    sub = courses.get(i).getSub();
                    code = courses.get(i).getCode();
                    section = courses.get(i).getSection();
                    title = courses.get(i).getTitle();
                    professor = courses.get(i).getProfessor();
                    days = courses.get(i).getDays();
                    startTime = courses.get(i).getStartTime();
                    endTime = courses.get(i).getEndTime();
                    facilityID = courses.get(i).getFacilityID();
                    campus = courses.get(i).getCampus();
                    TAs = courses.get(i).getTAs();

                    csvPrinter.printRecord(sub,code,section,title,professor,days,startTime,endTime,facilityID,campus);
                }

                csvPrinter.flush();            
            }
    }
}
