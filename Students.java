import java.util.HashMap;
import java.util.List;

class Students {
    String name;
    String lastName;
    int id;
    String email;
    int taCourse;
    boolean onCampus;
    boolean python;
    boolean vsBasics;
    HashMap<String, HashMap<Integer, Boolean>> schedule;
    List<String> coursesTaken;

    @Override
    public String toString() {
        return "\n" + "First Name: " + name + ", Last Name: " + lastName + ", ID: " + id + ", Email: " + email + 
        ", TA Course: " + taCourse + ", In Person?: " + onCampus + " Python?: " + python + 
        ", VS Basics?: " + vsBasics +"\n" + 
        "Schedule: " + "\n" + 
        "    Monday: " + schedule.get("M") + "\n" +
        "    Tuesday: " + schedule.get("T") +  "\n" +
        "    Wednesday: " + schedule.get("W") +  "\n" +
        "    Thursday: " + schedule.get("TH") + "\n" + 
        "Courses: " + "\n" + coursesTaken;

    }
}
