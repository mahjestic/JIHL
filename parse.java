import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class parse extends Students{
    public static void main(String[] args) {
        String studentFile = "/Users/lenale/Desktop/cs480/students.csv";
        BufferedReader br = null;
        String input = "";
        
        Students data = new Students();
        
        try {
            br = new BufferedReader(new FileReader(studentFile));
            
            br.readLine();//first line
            br.readLine();//second line
            
            while((input = br.readLine()) != null){
                String[] columns = input.split(",");
                // -- Name --
                data.name = columns[0]; 
                data.lastName = columns[1];
                
                // -- Information --
                data.id = Integer.parseInt(columns[2]);
                data.email = columns[3];
                
                // -- Applied TA course --
                data.taCourse = Integer.parseInt(columns[6]);
                
                // -- Are they on campus? --
                String onCampus = columns[7];
                boolean isOnCampus = onCampus.equalsIgnoreCase("yes");
                data.inPerson = isOnCampus;  
                
                // -- Do they know python? --
                String python = columns[40];
                boolean knowsPy = python.equalsIgnoreCase("yes");
                data.python = knowsPy;
                
                // -- Do they know VS Basics? --
                String vsBasics = columns[41];
                boolean knowsVS = vsBasics.equalsIgnoreCase("yes");
                data.vsBasics = knowsVS;  
                
                ArrayList<Students> studentData = new ArrayList<Students>();
                
                studentData.add(data);
                
                System.out.println(studentData);
                
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace(); 
        } catch(NumberFormatException e){
            e.printStackTrace();
        }

    }
    

}

// HashMap<String, HashMap<Integer, Boolean>> schedule = new HashMap<>();
// HashMap<Integer, Boolean> availability = new HashMap<>();

// for (int t = 8; t <= 16; t++) {
//     input = br.readLine();
//     // -- Splitting time strings into parts --
//     String time[] = columns[t].split(" "); 
//     String AmPm = time[1];
//     int hh;

//     // -- converting strings to integer --
//     hh = Integer.parseInt(time[0]);

//     String checkPM = "pm";
//     String checkAM = "am";

//     if(AmPm.equalsIgnoreCase(checkAM) && hh == 12){
//         hh = 00;
//     }else if(AmPm.equalsIgnoreCase(checkPM) && hh < 12){
//         hh += 12;
//     }
//     availability.put(hh, null);
// }