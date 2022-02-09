import java.util.HashMap;
import java.util.List;

class Students {
    String name;
    String lastName;
    int id;
    String email;
    int taCourse;
    boolean inPerson;
    boolean python;
    boolean vsBasics;
    HashMap<String, HashMap<Integer, Boolean>> schedule;
    List<String> coursesTaken;

    @Override
    public String toString() {
        return "First Name: " + name + ", Last Name: " + lastName + ", ID: " + id + ", Email: " + email + 
        ", TA Course: " + taCourse + ", In Person?: " + inPerson + " Python?: " + python + 
        ", VS Basics?: " + vsBasics + "\n";
    }
}
