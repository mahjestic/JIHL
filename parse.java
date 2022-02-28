import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class parse{
    public static void main(String[] args) {
        //File path's
        String studentCSV = "/Users/lenale/Desktop/cs480/students.csv"; //change the file paths to match
        String scheduleCSV = "/Users/lenale/Desktop/cs480/schedule.csv";    
        
        scheduleFileParser(scheduleCSV); // Run this for the schedule file
        // studentFileParser(studentCSV); // Run this for the student file
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
                int length = column.length;

                // -- Sub --
                data.sub = column[0];
                
                // -- Cat --
                data.code = Integer.parseInt(column[1]);
                
                // -- Section --
                data.section = column[2];
                
                // -- Title --
                data.title = column[3];
            
                // -- Level --
                if(data.code < 300){ data.level = "392"; }
                
                else if(data.code > 380){ data.level = "492"; }
                
                else{ data.level = "both"; }
                
                // -- Facility ID --
                if(column[9] == ""){ data.facilityID = "TBD"; }

                else{ data.facilityID = column[9]; }

                // -- Campus --
                data.campus = column[length - 1];
                                
                // -- Days --
                List<String> days = new ArrayList<String>();
                
                data.days = days;
                
                // -- Professor --
                String firstName = column[4];
                String lastName = column[5];

                String professor = firstName + " " + lastName;
                
                if(column[4] == ""){ 
                    data.professor = "TBD"; 
                    if(column[5] == "" && data.campus.equals("WEB")){ days.add("A"); }
                
                    else if(column[5] == "" && data.campus.equals("EBURG")){ days.add("TBD"); }
                    
                    else{
                        String str = column[5].trim();
                        String[] day = str.split(" ");
                        
                        for(String d: day){
                            if(d != ""){
                                days.add(d);
                            }
                        }
                    }
                }else{ data.professor = professor; } 
                
                // -- Days cont. - If Professor column is not empty  --
                if(column[4] != "" && column[6] == "" && data.campus.equals("WEB")){ 
                    days.add("A"); 
                }
                
                else if(column[4] != "" && column[6] == "" && data.campus.equals("EBURG")){ 
                    days.add("TBD"); 
                }
                else if(column[4] != ""){
                    String str = column[6].trim();
                    String[] day = str.split(" ");
                    
                    for(String d: day){
                        if(d != ""){
                            days.add(d);
                        }
                    }
                }
                
                // -- Start Time --
                // 8 9 10 11 12 13 14 15 16 17 18 19
                // 0 = Asynch
                if(column[4] == ""){ // If professor column is empty
                    String startTime = column[6];
                    char c = startTime.charAt(0);
                    String str = startTime.substring(0,2);
                    
                    if(c == ':'){ data.startTime = 0; }
    
                    switch(c){
                        case '1':
                        data.startTime = 13;
                        break;
                        case '2':
                        data.startTime = 14;
                        break;
                        case '3':
                        data.startTime = 15;
                        break;
                        case '6':
                        data.startTime = 18;
                        break;
                        case '8':
                        data.startTime = 8;
                        break;
                        case '9':
                        data.startTime = 9;
                        break;
                    }
                    switch(str){
                        case "10":
                        data.startTime = 10;
                        break;
                        case "11":
                        data.startTime = 11;
                        break;
                        case "12":
                        data.startTime = 12;
                        break;
                    }
                    
                } else if(column[4] != ""){ // Professor column is not empty
               
                String startTime = column[7];
                char c = startTime.charAt(0);
                String str = startTime.substring(0,2);
                
                if(c == ':'){ data.startTime = 0; }
                    switch(c){
                        case '1':
                        data.startTime = 13;
                        break;
                        case '2':
                        data.startTime = 14;
                        break;
                        case '3':
                        data.startTime = 15;
                        break;
                        case '6':
                        data.startTime = 18;
                        break;
                        case '8':
                        data.startTime = 8;
                        break;
                        case '9':
                        data.startTime = 9;
                        break;
                    }
                    switch(str){
                        case "10":
                        data.startTime = 10;
                        break;
                        case "11":
                        data.startTime = 11;
                        break;
                        case "12":
                        data.startTime = 12;
                        break;
                    }
                }
                // -- End Time --
                // 8 9 10 11 12 13 14 15 16 17 18 19
                // 0 = Asynch
                if(column[4] == ""){ // Professor column is empty
                    String endTime = column[7];
                    char ch = endTime.charAt(0);
                    String s= endTime.substring(0,2);
                    
                    if(ch == ':'){ data.endTime = 0; }
                        
                    switch(ch){
                        case '1':
                            data.endTime = 14;
                            break;
                        case '2':
                            data.endTime = 15;
                            break;
                        case '3':
                            data.endTime = 16;
                            break;
                        case '6':
                            data.endTime = 19;
                            break;
                        case '8':
                            data.endTime = 9;
                            break;
                        case '9':
                            data.endTime = 10;
                            break;
                    }
                    switch(s){
                        case "10":
                            data.endTime = 11;
                            break;
                        case "11":
                            data.endTime = 12;
                            break;
                        case "12":
                            data.endTime = 1;
                            break;
                    }
                    
                }else if(column[4] != ""){
                    String endTime = column[8];
                    char ch = endTime.charAt(0);
                    String s = endTime.substring(0,2);
                    
                    if(ch == ':'){ data.endTime = 0; }

                    switch(ch){
                        case '1':
                            data.endTime = 14;
                            break;
                        case '2':
                            data.endTime = 15;
                            break;
                        case '3':
                            data.endTime = 16;
                            break;
                        case '6':
                            data.endTime = 17;
                            break;
                        case '8':
                            data.endTime = 9;
                            break;
                        case '9':
                            data.endTime = 10;
                            break;
                    }
                    switch(s){
                        case "10":
                            data.endTime = 11;
                            break;
                        case "11":
                            data.endTime = 12;
                            break;
                        case "12":
                            data.endTime = 13;
                            break;
                    }
                }
                
                
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
                data.name = column[0]; 
                data.lastName = column[1];
                    
                // -- Information --
                data.id = Integer.parseInt(column[2]);
                data.email = column[3];
                    
                // -- Applied TA course --
                data.taCourse = Integer.parseInt(column[6]);
                    
                // -- Are they on campus? --
                String onCampus = column[7];
                boolean isOnCampus = onCampus.equalsIgnoreCase("yes");
                data.onCampus = isOnCampus;  
                    
                // -- Do they know python? --
                String python = column[40];
                boolean knowsPy = python.equalsIgnoreCase("yes");
                data.python = knowsPy;
                
                // -- Do they know VS Basics? --
                String vsBasics = column[41];
                boolean knowsVS = vsBasics.equalsIgnoreCase("yes");
                data.vsBasics = knowsVS;  
                
                // -- Schdeule --
                HashMap<String, HashMap<Integer, Boolean>> schedule = new HashMap<>();
                
                String isAvailable = "Open";
                
                // -- Initializing availability -- 
                // -- Loops through entire schedule --
                for (int i = 8; i < 40;) {
                    // -- Loops through week --
                    HashMap<Integer, Boolean> availability = new HashMap<>();

                    for (int j = 0; j < 8; j++) {
    
                        boolean available = isAvailable.equalsIgnoreCase(column[i]);
                            
                        switch(i % 8){
                            case 0:
                                if(available) availability.put(8, true);                                        
                                else availability.put(8, false);
                                break;
                            case 1:
                                if(available) availability.put(9, true);                                        
                                else availability.put(9, false);
                                break;
                            case 2:
                                if(available) availability.put(10, true);                                        
                                else availability.put(10, false);
                                break;
                            case 3:
                                if(available) availability.put(11, true);                                        
                                else availability.put(11, false);
                                break;
                            case 4:
                                if(available) availability.put(12, true);                                        
                                else availability.put(12, false);
                                break;
                            case 5:
                                if(available) availability.put(13, true);                                        
                                else availability.put(13, false);
                                break;  
                            case 6:
                                if(available) availability.put(14, true);                                        
                                else availability.put(14, false);
                                break;
                            case 7:
                                if(available) availability.put(15, true);                                        
                                else availability.put(15, false);
                                break;
                        }
                        i++;
                    }
                    if(i / 8 == 2){
                        schedule.put("M", availability);
                    }
                    else if(i / 8 == 3){
                        schedule.put("T", availability);
                    }
                    else if(i / 8 == 4){
                        schedule.put("W", availability);
                    }
                    else if(i / 8 == 5){
                        schedule.put("TH", availability);
                    }
                }
                data.schedule = schedule;

                // -- Courses Taken --
                String[] courses = {"CS 102","CS 105","CS 107","CS 109","CS 110","CS 111","CS 112",
                                    "CS 301","CS 302","CS 311","CS 312","CS 361","CS 362","CS 380",
                                    "CS 420","CS 427","CS 430","CS 440","CS 467","CS 470", "CS 480"};
                List<String> coursesTaken = new ArrayList<String>();
                
                int count = 0;
                for (int i = 42; i < length; i++) {
                    if(column[i] != ""){
                        coursesTaken.add(courses[count]);
                    }
                    count++;
                }
                data.coursesTaken = coursesTaken;

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

    