import java.io.*;
import java.util.ArrayList;

public class parse{
    public static void main(String[] args) {
        //File path's
        String studentCSV = "/Users/lenale/Desktop/cs480/students.csv"; //change the file paths to match
        String scheduleCSV = "/Users/lenale/Desktop/cs480/schedule.csv";    
        
        // scheduleFileParser(scheduleCSV); // Run this for the schedule file
        studentFileParser(studentCSV); // Run this for the student file
    }

    // Schedule File Parser Method
    public static Courses scheduleFileParser(String file){
        BufferedReader lineRead = null;

        String input = "";

        Courses data = new Courses(); 

        try{

            lineRead = new BufferedReader(new FileReader(file));
            lineRead.readLine();//first line
           
            while((input = lineRead.readLine()) != null){
                
                String[] column = input.split(",");

                // -- Sub --
                data.getSub(data, column);
                
                // -- Cat --
                data.getCode(data, column);
             
                // -- Section --
                data.getSection(data, column);
                
                // -- Title --
                data.getTitle(data, column);
            
                // -- Level --
                data.getLevel(data, column);
                
                // -- Facility ID --
                data.getFacilID(data, column);

                // -- Campus --
                data.getCampus(data, column);
                                
                // -- Professor --
                data.getProfessor(data, column);
                
                // -- Days --
                data.getDays(data, column);
                
                // -- Start Time --
                data.getStartTime(data, column);
               
                // -- End Time --
                data.getEndTime(data, column);

                ArrayList<Courses> coursesData = new ArrayList<Courses>();

                coursesData.add(data); //Adds Schedule data to courseData arrayList

                System.out.println(coursesData); //Prints out data when method is called
            }

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace(); 
        } catch(NumberFormatException e){
            e.printStackTrace();
        }finally{
            if(lineRead != null){
                try{
                    lineRead.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return data;
    }

    //Student File Parser Method
    public static Students studentFileParser(String file){
        BufferedReader lineRead = null;

        String input = "";
        
        Students data = new Students();
        
        try {

            lineRead = new BufferedReader(new FileReader(file));
            lineRead.readLine(); // Skip first line
            lineRead.readLine(); // Skip second line
                
            while( (input = lineRead.readLine()) != null ){
                String[] column = input.split(",");
                int length = column.length;
                
                // -- Name --
                data.getFirstName(data, column);
                data.getLastName(data, column);
                    
                // -- Information --
                data.getID(data, column);
                data.getEmail(data, column);
                    
                // -- Applied TA course --
                data.getAppliedTACourse(data, column);
                    
                // -- Are they on campus? --
                data.isOnCampus(data, column);
                    
                // -- Do they know python? --
                data.knowsPythong(data, column);
                
                // -- Do they know VS Basics? --
                data.knowsVS(data, column);
                
                // -- Schdeule --
                data.getSchedule(data, column);

                // -- Courses Taken --
                data.getCoursesTaken(data, column);

                ArrayList<Students> studentData = new ArrayList<Students>();
                    
                studentData.add(data); // Adds student data to studentData ArrayList
                System.out.println(studentData); // Prints out data when method is called
            }
        }catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace(); 
        } catch(NumberFormatException e){
            e.printStackTrace();
        }finally{
            if(lineRead != null){
                try{
                    lineRead.close();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return data;
    }
}

    