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

}
