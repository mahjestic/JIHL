package parser;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.logging.Logger;
import objects.Courses;
import objects.Students;

public class Parse {

  private final static Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  public static String studentCSV;
  public static String scheduleCSV;

  public Parse(String studentCSV, String scheduleCSV) {
    this.studentCSV = studentCSV;
    this.scheduleCSV = scheduleCSV;
  }

  public Parse() {
  }

  ;

  public Parse(String csvString, Boolean isStudentCSV) {
    if (isStudentCSV) {
      studentCSV = csvString;
    } else {
      scheduleCSV = csvString;
    }
  }

  public void parseFiles() {
    if (!studentCSV.equals("") && Objects.nonNull(studentCSV)) {
      studentFileParser(studentCSV); // Run this for the student file

    }
    if (!scheduleCSV.equals("") && Objects.nonNull(scheduleCSV)) {
      scheduleFileParser(scheduleCSV); // Run this for the schedule file
    }
  }

  // Schedule File Parser Method
  public Courses scheduleFileParser(String file) {
    BufferedReader lineRead = null;

    String input = "";

    Courses data = new Courses();

    try {

      lineRead = new BufferedReader(new FileReader(file));
      lineRead.readLine();//first line

      while ((input = lineRead.readLine()) != null) {
        String[] column = input.split(",");

        System.out.println(input);
        // --Sub--
        data.addSub(data, column);

        // -- Cat --
        data.addCode(data, column);

        // -- Section --
        data.addSection(data, column);

        // -- Title --
        data.addTitle(data, column);

        // -- Level --
        data.addLevel(data, column);

        // -- Facility ID --
        data.addFacilID(data, column);

        // -- Campus --
        data.addCampus(data, column);

        // -- Professor --
        data.addProfessor(data, column);

        // -- Days --
        data.addDays(data, column);

        // -- Start Time --
        data.addStartTime(data, column);

        // -- End Time --
        data.addEndTime(data, column);

        ArrayList<Courses> coursesData = new ArrayList<Courses>();

        coursesData.add(data); //Adds Schedule data to courseData arrayList
        //***** log.info(coursesData.toString());   // Logs info when method is called

      }

    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } finally {
      if (lineRead != null) {
        try {
          lineRead.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return data;
  }

  //Student File Parser Method
  public Students studentFileParser(String file) {
    BufferedReader lineRead = null;

    String input = "";

    Students data = new Students();

    try {

      lineRead = new BufferedReader(new FileReader(file));
      lineRead.readLine(); // Skip first line
      lineRead.readLine(); // Skip second line

      while ((input = lineRead.readLine()) != null) {

        //log.info(input);
        String[] column = input.split(",");
        int length = column.length;

        // -- Name --
        data.addFirstName(data, column);
        data.addLastName(data, column);

        // -- Information --
        data.addID(data, column);
        data.addEmail(data, column);

        // -- Expected Graduation --
        data.addGradQuarter(data, column);
        data.addGradYear(data, column);

        // -- Applied TA course --
        data.addAppliedTACourse(data, column);

        // -- Are they on campus? --
        data.isOnCampus(data, column);

        // -- Do they know python? --
        data.knowsPython(data, column);

        // -- Do they know VS Basics? --
        data.knowsVS(data, column);

        // -- Schdeule --
        data.addSchedule(data, column);

        // -- objects.Courses Taken --
        data.addCoursesTaken(data, column);
        ArrayList<Students> studentData = new ArrayList<Students>();

        studentData.add(data); // Adds student data to studentData ArrayList
        //***** log.info(studentData.toString());   // Logs info when method is called
      }
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    } catch (IOException e) {
      e.printStackTrace();
    } catch (NumberFormatException e) {
      e.printStackTrace();
    } finally {
      if (lineRead != null) {
        try {
          lineRead.close();
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
    return data;
  }
}

