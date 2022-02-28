import java.util.ArrayList;
import java.util.List;

class Courses {
    String sub;
    int code;
    String section;
    String title;
    String professor;
    String level;
    int seatsAvailable;
    List<String> TAs;
    List<String> days;
    int startTime;
    int endTime;
    String facilityID;
    String campus;
    
    @Override
    public String toString(){
        return "\n" + "Level: " + level + ", Course: " + (sub + " " + code) + ", Secton: " + section + ", Title: " + title +
               ", Professor: " + professor + ", Campus: " + campus + ", Facility ID: " + facilityID + 
               "\n" + "Days: " + days + ", Start Time: " + startTime + ", End Time: " + endTime;
    }
    // Method to get Sub
    public String getSub(Courses course, String[] column){
        
        String sub = column[0];
        
        return course.sub = sub;
    }

    // Method to get Code
    public int getCode(Courses course, String[] column){
        
        String code = column[1];

        return course.code = Integer.parseInt(code);
    }
    
    // Method to get Section
    public String getSection(Courses course, String[] column){
        
        String section = column[2];
        
        return course.section = section;
    }
    
    // Method to get Title
    public String getTitle(Courses course, String[] column){
        
        String title = column[3];
        
        return course.title = title;
    }
    // Method to get Level
    public String getLevel(Courses course, String[] column){
        
        int code = course.getCode(course,column);
        String level = "";

        if(code < 300){ level = "392"; }
                
        else if(code > 380){ level = "492"; }
        
        else{ level = "both"; }
    
        return course.level = level;
    }
    
    // Method to get Facility ID
    public String getFacilID(Courses course, String[] column){
        String ID;

        if(column[9] == ""){ ID = "TBD"; }

        else{ ID = column[9]; }
        
        return course.facilityID = ID;
    }
    // Method to get Campus
    public String getCampus(Courses course, String[] column){
        String campus;

        campus = column[column.length - 1];
        
        return course.campus = campus;
    }

    // Method to get Professor
    public String getProfessor(Courses course, String[] column){

        String professor;
        String firstName = column[4];
        String lastName;
        
        if(firstName == ""){ professor = "TBD"; }
        
        else{
            lastName = column[5];
            professor = firstName + " " + lastName;
        }
        
        return course.professor = professor;
    }

    // Method to get Days
    public List<String> getDays(Courses course, String[] column){
        
        List<String> days = new ArrayList<String>();
        
        String professor = course.getProfessor(course, column);
        String campus = course.getCampus(course, column);

        //If professor is undefined
        if(professor.equals("TBD")){ 
            String daysCol = column[5];

            if(daysCol == "" && campus.equals("WEB")){ days.add("A"); } //Asynchronous
        
            else if(daysCol == "" && campus.equals("EBURG")){ days.add("TBD"); } //Days haven't been set yet
            
            else{
                String str = daysCol.trim();
                String[] day = str.split(" ");
                
                for(String d: day){
                    if(d != ""){
                        days.add(d);
                    }
                }
            }
        }
        //If professor is defined
        else {
            String daysCol = column[6];
            if(daysCol == "" && campus.equals("WEB")){  days.add("A"); }
               
            else if(daysCol == "" && campus.equals("EBURG")){ 
                days.add("TBD"); 
            }
            else{
                String str = daysCol.trim();
                String[] day = str.split(" ");
                
                for(String d: day){
                    if(d != ""){
                        days.add(d);
                    }
                }
            }
        }
        return course.days = days;
    }
    // Method to get Start Time
    public int getStartTime(Courses course, String[] column){

        String professor = course.getProfessor(course, column);
        int startTime = 0;

        // If professor column is empty
        if(professor.equals("TBD")){ 
            String time = column[6];
            char c = time.charAt(0);

            String s = time.substring(0,2);
            
            if(c == ':'){ startTime = 0; }

            switch(c){
                case '1':
                startTime = 13;
                break;
                case '2':
                startTime = 14;
                break;
                case '3':
                startTime = 15;
                break;
                case '6':
                startTime = 18;
                break;
                case '8':
                startTime = 8;
                break;
                case '9':
                startTime = 9;
                break;
            }
            switch(s){
                case "10":
                startTime = 10;
                break;
                case "11":
                startTime = 11;
                break;
                case "12":
                startTime = 12;
                break;
            }
            
        }else{ // If Professor column is not empty
       
            String time = column[7];
            char c = time.charAt(0);
            String s = time.substring(0,2);
        
            if(c == ':'){ startTime = 0; }
                
            switch(c){
                    case '1':
                    startTime = 13;
                    break;
                    case '2':
                    startTime = 14;
                    break;
                    case '3':
                    startTime = 15;
                    break;
                    case '6':
                    startTime = 18;
                    break;
                    case '8':
                    startTime = 8;
                    break;
                    case '9':
                    startTime = 9;
                    break;
            }
            switch(s){
                    case "10":
                    startTime = 10;
                    break;
                    case "11":
                    startTime = 11;
                    break;
                    case "12":
                    startTime = 12;
                    break;
            }
        }
        
        return course.startTime = startTime;
    }

    // Method to get End Time
    public int getEndTime(Courses course, String[] column){

        String professor = course.getProfessor(course, column);
        int endTime = 0;

        // If professor column is empty
        if(professor.equals("TBD")){ 
            String time = column[6];
            char c = time.charAt(0);

            String s = time.substring(0,2);
            
            if(c == ':'){ endTime = 0; }

            switch(c){
                case '1':
                endTime = 14;
                break;
                case '2':
                endTime = 15;
                break;
                case '3':
                endTime = 16;
                break;
                case '6':
                endTime = 19;
                break;
                case '8':
                endTime = 9;
                break;
                case '9':
                endTime = 10;
                break;
            }
            switch(s){
                case "10":
                endTime = 11;
                break;
                case "11":
                endTime = 12;
                break;
                case "12":
                endTime = 13;
                break;
            }
            
        }else{ // If Professor column is not empty
       
            String time = column[7];
            char c = time.charAt(0);
            String s = time.substring(0,2);
        
            if(c == ':'){ endTime = 0; }
                
            switch(c){
                    case '1':
                    endTime = 14;
                    break;
                    case '2':
                    endTime = 15;
                    break;
                    case '3':
                    endTime = 16;
                    break;
                    case '6':
                    endTime = 19;
                    break;
                    case '8':
                    endTime = 9;
                    break;
                    case '9':
                    endTime = 10;
                    break;
            }
            switch(s){
                    case "10":
                    endTime = 11;
                    break;
                    case "11":
                    endTime = 12;
                    break;
                    case "12":
                    endTime = 13;
                    break;
            }
        }
        
        return course.endTime = endTime;
    }

    
}
