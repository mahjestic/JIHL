/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ui;

import algorithm.ScheduleMatcher;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import objects.Courses;
import objects.Students;
import parser.Parse;


/**
 * @author Owner
 */
public class GUI extends javax.swing.JFrame {

  private static final Logger log = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
  private final JFileChooser selectFileChooser;
  public File fileToRead;
  public File fileToRead2;
  public String studentCSV;
  public String scheduleCSV;

  /**
   * Creates new form GUI
   */
  public GUI() {
    initComponents();

    selectFileChooser = new JFileChooser();
    selectFileChooser.setCurrentDirectory(new File("c:\\"));
    selectFileChooser.setFileFilter(new FileNameExtensionFilter("CSV, Files", "csv"));
  }


  private void runButtonActionPerformed(java.awt.event.ActionEvent evt) {
    // TODO add your handling code here:
    log.info("Booting up Application...");
    Parse parser = new Parse();
    List<Students> studentApplicants = parser.studentFileParser(studentCSV);
    studentApplicants.forEach(s -> {
      log.info(s.toString());
    });
    List<Courses> courses = parser.scheduleFileParser(scheduleCSV);

    log.info("GUI.java: runButtionActionPerformed: " + courses.toString());

    ScheduleMatcher matchMachine = new ScheduleMatcher(studentApplicants, courses);
    HashMap<Integer, Integer> results = matchMachine.hallsAlgorithm();

  }

  /**
   * This method is called from within the constructor to initialize the form. WARNING: Do NOT
   * modify this code. The content of this method is always regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">
  private void initComponents() {

    selectFileButton = new javax.swing.JButton();
    messageLabel = new javax.swing.JLabel();
    instructionLabel1 = new javax.swing.JLabel();
    instructionLabel2 = new javax.swing.JLabel();
    instructionLabel3 = new javax.swing.JLabel();
    instructionLabel4 = new javax.swing.JLabel();
    instructionLabel5 = new javax.swing.JLabel();
    runButton = new javax.swing.JButton();
    selectFileButton1 = new javax.swing.JButton();
    messageLabel1 = new javax.swing.JLabel();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("TA Assignment Program");

    selectFileButton.setText("Select Student File...");
    selectFileButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        selectFileButtonActionPerformed(evt);
      }
    });

    messageLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    messageLabel.setText("Please select a Student information CSV file.");

    instructionLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    instructionLabel1.setText("Hello, and welcome to our TA Assignment Program.");
    instructionLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    instructionLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    instructionLabel2.setText("First, Select your CSV files using the \"Select\" button below.");
    instructionLabel2.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    instructionLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    instructionLabel3.setText("Afterwards, hit the \"Run\" button to run the program,");
    instructionLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    instructionLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    instructionLabel4
        .setText("and you should receive a CSV file as output, with the TAs organized into");
    instructionLabel4.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    instructionLabel5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    instructionLabel5.setText("their appropriate classes.");
    instructionLabel5.setVerticalAlignment(javax.swing.SwingConstants.TOP);

    runButton.setText("Run");
    runButton.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        runButtonActionPerformed(evt);
      }
    });

    selectFileButton1.setText("Select Course File...");
    selectFileButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        selectFileButton1ActionPerformed(evt);
      }
    });

    messageLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    messageLabel1.setText("Please select a Course information CSV file.");

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(instructionLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectFileButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageLabel, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(instructionLabel2, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(instructionLabel3, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(instructionLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(instructionLabel5, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(runButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(selectFileButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(messageLabel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
    );
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectFileButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(messageLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(selectFileButton1))
                .addGap(12, 12, 12)
                .addComponent(instructionLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(instructionLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(runButton)
                .addContainerGap(116, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>

  private void selectFileButtonActionPerformed(java.awt.event.ActionEvent evt) {
    int returnValue = selectFileChooser.showOpenDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      fileToRead = selectFileChooser.getSelectedFile();
      studentCSV = fileToRead.getAbsolutePath();
      messageLabel.setText(fileToRead.getAbsolutePath());
    } else {
      messageLabel.setText("No file selected.");
    }
  }


  private void selectFileButton1ActionPerformed(java.awt.event.ActionEvent evt) {
    int returnValue = selectFileChooser.showOpenDialog(this);

    if (returnValue == JFileChooser.APPROVE_OPTION) {
      fileToRead2 = selectFileChooser.getSelectedFile();
      scheduleCSV = fileToRead2.getAbsolutePath();
      messageLabel1.setText(fileToRead2.getAbsolutePath());
    } else {
      messageLabel1.setText("No file selected.");
    }
  }

//  /**
//   * @param args the command line arguments
//   */
//  public static void main(String args[]) {
//    /* Set the Nimbus look and feel */
//    //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//    /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//     * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//     */
//    try {
//      for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
//          .getInstalledLookAndFeels()) {
//        if ("Nimbus".equals(info.getName())) {
//          javax.swing.UIManager.setLookAndFeel(info.getClassName());
//          break;
//        }
//      }
//    } catch (ClassNotFoundException ex) {
//      java.util.logging.Logger.getLogger(GUI.class.getName())
//          .log(java.util.logging.Level.SEVERE, null, ex);
//    } catch (InstantiationException ex) {
//      java.util.logging.Logger.getLogger(GUI.class.getName())
//          .log(java.util.logging.Level.SEVERE, null, ex);
//    } catch (IllegalAccessException ex) {
//      java.util.logging.Logger.getLogger(GUI.class.getName())
//          .log(java.util.logging.Level.SEVERE, null, ex);
//    } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//      java.util.logging.Logger.getLogger(GUI.class.getName())
//          .log(java.util.logging.Level.SEVERE, null, ex);
//    }
//    //</editor-fold>
//
//    /* Create and display the form */
////        java.awt.EventQueue.invokeLater(new Runnable() {
////            public void run() {
////                new GUI().setVisible(true);
////            }
////        });
//  }

  // Variables declaration - do not modify
  private javax.swing.JLabel instructionLabel1;
  private javax.swing.JLabel instructionLabel2;
  private javax.swing.JLabel instructionLabel3;
  private javax.swing.JLabel instructionLabel4;
  private javax.swing.JLabel instructionLabel5;
  private javax.swing.JLabel messageLabel;
  private javax.swing.JLabel messageLabel1;
  private javax.swing.JButton runButton;
  private javax.swing.JButton selectFileButton;
  private javax.swing.JButton selectFileButton1;
  // End of variables declaration
}
