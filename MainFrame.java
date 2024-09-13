import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author acv
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    private String currentSemester;
    private String author;
    private String project;

    public MainFrame() {
        initComponents();
        checkData();
        rebuildSemesterComboBoxes();
        rebuildCourseComboBox();
        rebuildStudentComboBoxes();
    }

    public void rebuildSemesterComboBoxes() {
        ArrayList<String> semesters = SemesterQueries.getSemesterList();
        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel(semesters.toArray()));
        if (semesters.size() > 0) {
            currentSemesterLabel.setText(semesters.get(0));
            currentSemester = semesters.get(0);
        } else {
            currentSemesterLabel.setText("None, add a semester.");
            currentSemester = "None";
        }
    }
    public void rebuildCourseComboBox() {
        ArrayList<String> courseCodes = CourseQueries.getAllCourseCodes();
        classComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
        chooseClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
        selectClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
        chooseClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
        chooseClassToDropStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
        chooseClassToDropAdminComboBox.setModel(new javax.swing.DefaultComboBoxModel(courseCodes.toArray()));
    }
    public void rebuildClassComboDropped(){
        ArrayList<String> codes=ClassQueries.getAllCourseCodes(currentSemester);
        classComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
        chooseClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
        selectClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
        chooseClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
        chooseClassToDropStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
        chooseClassToDropAdminComboBox.setModel(new javax.swing.DefaultComboBoxModel(codes.toArray()));
    }
    public void rebuildClassesTables(){
        DefaultTableModel model=(DefaultTableModel)displayClassTable.getModel();
        model.setRowCount(0);
        ArrayList<ClassDescription> courses=MultiTableQueries.getAllCourseCodes(currentSemester);
        for(ClassDescription course:courses){
            Object[] row= {course.getCourseCode(),course.getDescription(),course.getSeats()};
            model.addRow(row);
        }
    }
    public void rebuildStudentComboBoxes(){
        ArrayList<StudentEntry> students=StudentQueries.getAllStudents();
        selectStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(students.toArray()));
        selectStudentScheduleComboBox.setModel(new javax.swing.DefaultComboBoxModel(students.toArray()));
        chooseStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel(students.toArray()));
        chooseStudentDropClassComboBox.setModel(new javax.swing.DefaultComboBoxModel(students.toArray()));
    }
    public void rebuildScheduleTable(){
        DefaultTableModel model=(DefaultTableModel)scheduleTable.getModel();
        model.setRowCount(0);
        String id=((StudentEntry)selectStudentScheduleComboBox.getSelectedItem()).getStudentID();
        ArrayList<ScheduleEntry> entries=ScheduleQueries.getScheduleByStudent(currentSemester,id);
        for(ScheduleEntry entry:entries){
            Object[] row= {entry.getCourseCode(),entry.getStatus()};
            model.addRow(row);
        }
    }
    public void rebuildDisplayTables(){
        ArrayList<StudentEntry> scheduled=MultiTableQueries.getScheduledStudentsByClass(currentSemester,(String)chooseClassComboBox.getSelectedItem());
        ArrayList<StudentEntry> waitlisted=MultiTableQueries.getWaitlistedStudentsByClass(currentSemester,(String)chooseClassComboBox.getSelectedItem());
        DefaultTableModel scheduleModel=(DefaultTableModel)scheduledClassListTable.getModel();
        DefaultTableModel waitlistModel=(DefaultTableModel)waitlistTable.getModel();
        scheduleModel.setRowCount(0);
        waitlistModel.setRowCount(0);
        for(StudentEntry e:scheduled){
            Object[] row={e.getLastName(),e.getFirstName(),e.getStudentID()};
            scheduleModel.addRow(row);
        }
        for(StudentEntry e:waitlisted){
            Object[] row={e.getLastName(),e.getFirstName(),e.getStudentID()};
            waitlistModel.addRow(row);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        admin = new javax.swing.JPanel();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        addSemester = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        addSemesterTextfield = new javax.swing.JTextField();
        addSemesterSubmitButton = new javax.swing.JButton();
        addSemesterStatusLabel = new javax.swing.JLabel();
        addCourse = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        addCourseTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        addCourseDescTextField = new javax.swing.JTextField();
        courseSubmitButton = new javax.swing.JButton();
        addCourseStatus = new javax.swing.JLabel();
        addStudent = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        lastNameTextField = new javax.swing.JTextField();
        IDTextField = new javax.swing.JTextField();
        firstNameTextField = new javax.swing.JTextField();
        studentSubmit = new javax.swing.JButton();
        studentStatusBar = new javax.swing.JLabel();
        addClass = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        classComboBox = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        seatsSpinner = new javax.swing.JSpinner();
        classSubmit = new javax.swing.JButton();
        addClassStatusBar = new javax.swing.JLabel();
        displayClassList = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        chooseClassComboBox = new javax.swing.JComboBox<>();
        displayClassListButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        scheduledClassListTable = new javax.swing.JTable();
        jLabel16 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        waitlistTable = new javax.swing.JTable();
        dropStudent = new javax.swing.JPanel();
        jLabel17 = new javax.swing.JLabel();
        chooseStudentComboBox = new javax.swing.JComboBox<>();
        dropStudentButton = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        dropStudentDisplay = new javax.swing.JTextArea();
        dropClassAdmin = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        chooseClassToDropAdminComboBox = new javax.swing.JComboBox<>();
        dropClassAdminButton = new javax.swing.JButton();
        jScrollPane6 = new javax.swing.JScrollPane();
        dropClassAdminDisplay = new javax.swing.JTextArea();
        student = new javax.swing.JPanel();
        jTabbedPane3 = new javax.swing.JTabbedPane();
        displayClasses = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        displayClassTable = new javax.swing.JTable();
        displayClassesButton = new javax.swing.JButton();
        scheduleClass = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        selectClassComboBox = new javax.swing.JComboBox<>();
        jLabel12 = new javax.swing.JLabel();
        selectStudentComboBox = new javax.swing.JComboBox<>();
        scheduleSubmit = new javax.swing.JButton();
        scheduleStatusBar = new javax.swing.JLabel();
        displaySchedule = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        scheduleTable = new javax.swing.JTable();
        displayScheduleButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        selectStudentScheduleComboBox = new javax.swing.JComboBox<>();
        dropClassStudent = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        chooseStudentDropClassComboBox = new javax.swing.JComboBox<>();
        jLabel21 = new javax.swing.JLabel();
        chooseClassToDropStudentComboBox = new javax.swing.JComboBox<>();
        dropClassStudentButton = new javax.swing.JButton();
        jScrollPane7 = new javax.swing.JScrollPane();
        dropClassStudentDisplay = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        currentSemesterLabel = new javax.swing.JLabel();
        currentSemesterComboBox = new javax.swing.JComboBox<>();
        changeSemesterButton = new javax.swing.JButton();
        aboutButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 30)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 153));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Course Scheduler");

        jLabel3.setText("Semester Name:");

        addSemesterTextfield.setColumns(20);

        addSemesterSubmitButton.setText("Submit");
        addSemesterSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSemesterSubmitButtonActionPerformed(evt);
            }
        });

        addSemesterStatusLabel.setText("                                                   ");

        javax.swing.GroupLayout addSemesterLayout = new javax.swing.GroupLayout(addSemester);
        addSemester.setLayout(addSemesterLayout);
        addSemesterLayout.setHorizontalGroup(
            addSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSemesterLayout.createSequentialGroup()
                .addGroup(addSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(addSemesterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(addSemesterLayout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addComponent(addSemesterSubmitButton))
                    .addGroup(addSemesterLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(addSemesterStatusLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 361, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addSemesterLayout.setVerticalGroup(
            addSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSemesterLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addSemesterLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(addSemesterTextfield, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(addSemesterSubmitButton)
                .addGap(18, 18, 18)
                .addComponent(addSemesterStatusLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Semester", addSemester);

        jLabel4.setText("Course Code:");

        jLabel5.setText("Course Description:");

        courseSubmitButton.setText("Submit");
        courseSubmitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                courseSubmitButtonActionPerformed(evt);
            }
        });

        addCourseStatus.setText("  ");

        javax.swing.GroupLayout addCourseLayout = new javax.swing.GroupLayout(addCourse);
        addCourse.setLayout(addCourseLayout);
        addCourseLayout.setHorizontalGroup(
            addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addCourseLayout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCourseStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 274, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addCourseLayout.createSequentialGroup()
                        .addGroup(addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(courseSubmitButton)
                            .addComponent(addCourseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(addCourseDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addCourseLayout.setVerticalGroup(
            addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addCourseLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(addCourseTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addCourseLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(addCourseDescTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(courseSubmitButton)
                .addGap(18, 18, 18)
                .addComponent(addCourseStatus)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Course", addCourse);

        jLabel6.setText("Student ID:");

        jLabel7.setText("First Name:");

        jLabel10.setText("Last Name:");

        studentSubmit.setText("Submit");
        studentSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                studentSubmitActionPerformed(evt);
            }
        });

        studentStatusBar.setText("      ");

        javax.swing.GroupLayout addStudentLayout = new javax.swing.GroupLayout(addStudent);
        addStudent.setLayout(addStudentLayout);
        addStudentLayout.setHorizontalGroup(
            addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(studentStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 384, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addStudentLayout.createSequentialGroup()
                        .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(49, 49, 49)
                        .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(studentSubmit)
                            .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        addStudentLayout.setVerticalGroup(
            addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(IDTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(firstNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(addStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(lastNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(studentSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(studentStatusBar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Student", addStudent);

        jLabel8.setText("Course Code:");

        classComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel9.setText("Seats:");

        classSubmit.setText("Submit");
        classSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classSubmitActionPerformed(evt);
            }
        });

        addClassStatusBar.setText("      ");

        javax.swing.GroupLayout addClassLayout = new javax.swing.GroupLayout(addClass);
        addClass.setLayout(addClassLayout);
        addClassLayout.setHorizontalGroup(
            addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addClassStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 695, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(addClassLayout.createSequentialGroup()
                        .addGroup(addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(classSubmit)
                            .addComponent(seatsSpinner)
                            .addComponent(classComboBox, 0, 90, Short.MAX_VALUE))))
                .addContainerGap(121, Short.MAX_VALUE))
        );
        addClassLayout.setVerticalGroup(
            addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(classComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(addClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(seatsSpinner, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(classSubmit)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(addClassStatusBar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Add Class", addClass);

        jLabel14.setText("Choose Class:");

        chooseClassComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        displayClassListButton.setText("Display");
        displayClassListButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayClassListButtonActionPerformed(evt);
            }
        });

        jLabel15.setText("Scheduled Students in the Class");

        scheduledClassListTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Last Name", "First Name", "StudentID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(scheduledClassListTable);

        jLabel16.setText("Waitlisted Students in the Class in Waitlist Order");

        waitlistTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Last Name", "First Name", "StudentID"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                true, true, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane5.setViewportView(waitlistTable);
        if (waitlistTable.getColumnModel().getColumnCount() > 0) {
            waitlistTable.getColumnModel().getColumn(2).setResizable(false);
        }

        javax.swing.GroupLayout displayClassListLayout = new javax.swing.GroupLayout(displayClassList);
        displayClassList.setLayout(displayClassListLayout);
        displayClassListLayout.setHorizontalGroup(
            displayClassListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayClassListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayClassListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayClassListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(displayClassListLayout.createSequentialGroup()
                            .addComponent(jLabel14)
                            .addGap(50, 50, 50)
                            .addComponent(chooseClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(displayClassListButton))
                        .addComponent(jLabel16)
                        .addComponent(jScrollPane3)
                        .addComponent(jScrollPane5))
                    .addComponent(jLabel15))
                .addContainerGap(364, Short.MAX_VALUE))
        );
        displayClassListLayout.setVerticalGroup(
            displayClassListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayClassListLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayClassListLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(chooseClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayClassListButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel15)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel16)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Display Class List", displayClassList);

        jLabel17.setText("Choose Student:");

        chooseStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropStudentButton.setText("Submit");
        dropStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropStudentButtonActionPerformed(evt);
            }
        });

        dropStudentDisplay.setColumns(20);
        dropStudentDisplay.setRows(5);
        jScrollPane4.setViewportView(dropStudentDisplay);

        javax.swing.GroupLayout dropStudentLayout = new javax.swing.GroupLayout(dropStudent);
        dropStudent.setLayout(dropStudentLayout);
        dropStudentLayout.setHorizontalGroup(
            dropStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(dropStudentLayout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(18, 18, 18)
                        .addGroup(dropStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dropStudentButton)
                            .addComponent(chooseStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(284, Short.MAX_VALUE))
        );
        dropStudentLayout.setVerticalGroup(
            dropStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(chooseStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropStudentButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Drop Student", dropStudent);

        jLabel19.setText("Choose Class:");

        chooseClassToDropAdminComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropClassAdminButton.setText("Submit");
        dropClassAdminButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropClassAdminButtonActionPerformed(evt);
            }
        });

        dropClassAdminDisplay.setColumns(20);
        dropClassAdminDisplay.setRows(5);
        jScrollPane6.setViewportView(dropClassAdminDisplay);

        javax.swing.GroupLayout dropClassAdminLayout = new javax.swing.GroupLayout(dropClassAdmin);
        dropClassAdmin.setLayout(dropClassAdminLayout);
        dropClassAdminLayout.setHorizontalGroup(
            dropClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropClassAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(dropClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(dropClassAdminButton)
                        .addGroup(dropClassAdminLayout.createSequentialGroup()
                            .addComponent(jLabel19)
                            .addGap(58, 58, 58)
                            .addComponent(chooseClassToDropAdminComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(534, Short.MAX_VALUE))
        );
        dropClassAdminLayout.setVerticalGroup(
            dropClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropClassAdminLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropClassAdminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(chooseClassToDropAdminComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(dropClassAdminButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(38, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Drop Class", dropClassAdmin);

        javax.swing.GroupLayout adminLayout = new javax.swing.GroupLayout(admin);
        admin.setLayout(adminLayout);
        adminLayout.setHorizontalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        adminLayout.setVerticalGroup(
            adminLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(adminLayout.createSequentialGroup()
                .addComponent(jTabbedPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 302, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Admin", admin);

        displayClassTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Course Code", "Description", "Seats"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(displayClassTable);

        displayClassesButton.setText("Display");
        displayClassesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayClassesButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout displayClassesLayout = new javax.swing.GroupLayout(displayClasses);
        displayClasses.setLayout(displayClassesLayout);
        displayClassesLayout.setHorizontalGroup(
            displayClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayClassesLayout.createSequentialGroup()
                .addGroup(displayClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayClassesLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 633, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayClassesLayout.createSequentialGroup()
                        .addGap(294, 294, 294)
                        .addComponent(displayClassesButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displayClassesLayout.setVerticalGroup(
            displayClassesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayClassesLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(displayClassesButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Display Classes", displayClasses);

        jLabel11.setText("Select Class:");

        selectClassComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel12.setText("Select Student:");

        selectStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        scheduleSubmit.setText("Submit");
        scheduleSubmit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scheduleSubmitActionPerformed(evt);
            }
        });

        scheduleStatusBar.setText("   ");

        javax.swing.GroupLayout scheduleClassLayout = new javax.swing.GroupLayout(scheduleClass);
        scheduleClass.setLayout(scheduleClassLayout);
        scheduleClassLayout.setHorizontalGroup(
            scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scheduleClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(scheduleSubmit)
                        .addGroup(scheduleClassLayout.createSequentialGroup()
                            .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel11)
                                .addComponent(jLabel12))
                            .addGap(31, 31, 31)
                            .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(selectStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(selectClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(scheduleStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        scheduleClassLayout.setVerticalGroup(
            scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(scheduleClassLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(selectClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(scheduleClassLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(selectStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(scheduleSubmit)
                .addGap(18, 18, 18)
                .addComponent(scheduleStatusBar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Schedule Class", scheduleClass);

        scheduleTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Course Code", "Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(scheduleTable);

        displayScheduleButton.setText("Display");
        displayScheduleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayScheduleButtonActionPerformed(evt);
            }
        });

        jLabel13.setText("Select Student:");

        selectStudentScheduleComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout displayScheduleLayout = new javax.swing.GroupLayout(displaySchedule);
        displaySchedule.setLayout(displayScheduleLayout);
        displayScheduleLayout.setHorizontalGroup(
            displayScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayScheduleLayout.createSequentialGroup()
                .addGroup(displayScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(displayScheduleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 391, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(displayScheduleLayout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(displayScheduleButton))
                    .addGroup(displayScheduleLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel13)
                        .addGap(18, 18, 18)
                        .addComponent(selectStudentScheduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        displayScheduleLayout.setVerticalGroup(
            displayScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(displayScheduleLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(displayScheduleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(selectStudentScheduleComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(displayScheduleButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Display Schedule", displaySchedule);

        jLabel20.setText("Choose Student:");

        chooseStudentDropClassComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel21.setText("Choose Class:");

        chooseClassToDropStudentComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        dropClassStudentButton.setText("Submit");
        dropClassStudentButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropClassStudentButtonActionPerformed(evt);
            }
        });

        dropClassStudentDisplay.setColumns(20);
        dropClassStudentDisplay.setRows(5);
        jScrollPane7.setViewportView(dropClassStudentDisplay);

        javax.swing.GroupLayout dropClassStudentLayout = new javax.swing.GroupLayout(dropClassStudent);
        dropClassStudent.setLayout(dropClassStudentLayout);
        dropClassStudentLayout.setHorizontalGroup(
            dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropClassStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane7)
                    .addComponent(dropClassStudentButton)
                    .addGroup(dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(dropClassStudentLayout.createSequentialGroup()
                            .addComponent(jLabel21)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(chooseClassToDropStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(dropClassStudentLayout.createSequentialGroup()
                            .addComponent(jLabel20)
                            .addGap(18, 18, 18)
                            .addComponent(chooseStudentDropClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        dropClassStudentLayout.setVerticalGroup(
            dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dropClassStudentLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(chooseStudentDropClassComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(dropClassStudentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(chooseClassToDropStudentComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(dropClassStudentButton)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane3.addTab("Drop Class", dropClassStudent);

        javax.swing.GroupLayout studentLayout = new javax.swing.GroupLayout(student);
        student.setLayout(studentLayout);
        studentLayout.setHorizontalGroup(
            studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane3))
        );
        studentLayout.setVerticalGroup(
            studentLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(studentLayout.createSequentialGroup()
                .addComponent(jTabbedPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Student", student);

        jLabel2.setFont(new java.awt.Font("Comic Sans MS", 1, 16)); // NOI18N
        jLabel2.setText("Current Semester: ");

        currentSemesterLabel.setFont(new java.awt.Font("Comic Sans MS", 0, 16)); // NOI18N
        currentSemesterLabel.setText("           ");

        currentSemesterComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        changeSemesterButton.setText("Change Semester");
        changeSemesterButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeSemesterButtonActionPerformed(evt);
            }
        });

        aboutButton.setText("About");
        aboutButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentSemesterLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(changeSemesterButton)
                        .addGap(31, 31, 31)
                        .addComponent(aboutButton))
                    .addComponent(jTabbedPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(currentSemesterLabel)
                    .addComponent(currentSemesterComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeSemesterButton)
                    .addComponent(aboutButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(79, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void aboutButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutButtonActionPerformed
        // TODO add your handling code here:
        // display about information.
        JOptionPane.showMessageDialog(null, "Author: " + author + " Project: " + project);
    }//GEN-LAST:event_aboutButtonActionPerformed

    private void changeSemesterButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeSemesterButtonActionPerformed
        currentSemesterLabel.setText((String)currentSemesterComboBox.getSelectedItem());
        currentSemester=(String)currentSemesterComboBox.getSelectedItem();
        rebuildCourseComboBox();
    }//GEN-LAST:event_changeSemesterButtonActionPerformed

    private void scheduleSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_scheduleSubmitActionPerformed
        String courseCode=(String)selectClassComboBox.getSelectedItem();
        String studentID=((StudentEntry)selectStudentComboBox.getSelectedItem()).getStudentID();
        String status="";
        ArrayList<ClassDescription> list=MultiTableQueries.getAllCourseCodes(currentSemester);
        for(ClassDescription c:list){
            if(c.getCourseCode().equals(courseCode)){
                if(c.getSeats()>ScheduleQueries.getScheduledStudentCount(currentSemester,courseCode)){
                    status="S";
                    scheduleStatusBar.setText(courseCode+" has been scheduled for student "+studentID);
                }
                else{
                    status="W";
                    scheduleStatusBar.setText(courseCode+" has been waitlisted for student "+studentID);
                }
            }
        }
        ScheduleEntry entry=new ScheduleEntry(currentSemester,courseCode,studentID,status,new java.sql.Timestamp(Calendar.getInstance().getTime().getTime()));
        ScheduleQueries.addScheduleEntry(entry);        
    }//GEN-LAST:event_scheduleSubmitActionPerformed

    private void displayScheduleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayScheduleButtonActionPerformed
        rebuildScheduleTable();
    }//GEN-LAST:event_displayScheduleButtonActionPerformed

    private void displayClassesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayClassesButtonActionPerformed
        rebuildClassesTables();
    }//GEN-LAST:event_displayClassesButtonActionPerformed

    private void classSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classSubmitActionPerformed
        String courseCode=(String)classComboBox.getSelectedItem();
        int seats=(int)seatsSpinner.getValue();
        ClassQueries.addClass(currentSemester,courseCode,seats);
        addClassStatusBar.setText("Class "+courseCode+" has been added.");
    }//GEN-LAST:event_classSubmitActionPerformed

    private void studentSubmitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_studentSubmitActionPerformed
        String studentID = IDTextField.getText();
        String firstName = firstNameTextField.getText();
        String lastName=lastNameTextField.getText();
        StudentQueries.addStudent(studentID,firstName,lastName);
        rebuildStudentComboBoxes();
        studentStatusBar.setText(lastName+", "+firstName + " has been added.");
    }//GEN-LAST:event_studentSubmitActionPerformed

    private void courseSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_courseSubmitButtonActionPerformed
        String courseCode = addCourseTextField.getText();
        String courseDesc = addCourseDescTextField.getText();
        CourseQueries.addCourse(courseCode,courseDesc);
        addCourseStatus.setText(courseCode + " has been added.");
        rebuildCourseComboBox();
    }//GEN-LAST:event_courseSubmitButtonActionPerformed

    private void addSemesterSubmitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSemesterSubmitButtonActionPerformed
        String semester = addSemesterTextfield.getText();
        SemesterQueries.addSemester(semester);
        addSemesterStatusLabel.setText("Semester " + semester + " has been added.");
        rebuildSemesterComboBoxes();
    }//GEN-LAST:event_addSemesterSubmitButtonActionPerformed

    private void displayClassListButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayClassListButtonActionPerformed
        rebuildDisplayTables();
    }//GEN-LAST:event_displayClassListButtonActionPerformed

    private void dropStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropStudentButtonActionPerformed
        String id=((StudentEntry)chooseStudentComboBox.getSelectedItem()).getStudentID();
        String result=chooseStudentComboBox.getSelectedItem()+" "+id+" has been dropped from the list of students.\n";
        for(String s:SemesterQueries.getSemesterList()){
            ArrayList<ScheduleEntry> scheduled=ScheduleQueries.getScheduleByStudent(s,id);
            result+="For Semester: "+s+"\n";
            for(ScheduleEntry e:scheduled){
                ArrayList<ScheduleEntry> waitlisted=ScheduleQueries.getWaitlistedStudentsByClass(s,e.getCourseCode());
                ScheduleQueries.dropStudentScheduleByCourse(s,id,e.getCourseCode());
                result+=chooseStudentComboBox.getSelectedItem()+" "+id+" has been dropped from "+e.getCourseCode()+"\n";
                if(!waitlisted.isEmpty()){
                    ScheduleEntry waitlistedSchedule=waitlisted.get(0);
                    String waitlistedID=waitlistedSchedule.getStudentID();
                    if(!waitlistedID.equals(id)){
                        ScheduleQueries.updateScheduleEntry(waitlistedSchedule);
                        result+=StudentQueries.getStudent(waitlistedID)+" "+waitlistedID+" has been schedule into "+e.getCourseCode()+"\n";
                    }
                }
            }
        }
        StudentQueries.dropStudent(id);
        dropStudentDisplay.setText(result);
        rebuildStudentComboBoxes();
    }//GEN-LAST:event_dropStudentButtonActionPerformed

    private void dropClassAdminButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropClassAdminButtonActionPerformed
       String code=(String)chooseClassToDropAdminComboBox.getSelectedItem();
       ArrayList<StudentEntry> scheduled=MultiTableQueries.getScheduledStudentsByClass(currentSemester,code);
       String scheduledString="";
       for(StudentEntry s:scheduled) scheduledString+=s+" "+s.getStudentID()+"\n";
       ArrayList<StudentEntry> waitlisted=MultiTableQueries.getWaitlistedStudentsByClass(currentSemester,code);
       String waitlistedString="";
       for(StudentEntry s:waitlisted) waitlistedString+=s+"\n";
       ClassQueries.dropClass(currentSemester,code);
       ScheduleQueries.dropScheduleByCourse(currentSemester,code);
       dropClassAdminDisplay.setText("Scheduled students dropped from the course:\n"+scheduledString+"\nWaitlisted students dropped from the course:\n"+waitlistedString);
       rebuildClassComboDropped();
    }//GEN-LAST:event_dropClassAdminButtonActionPerformed

    private void dropClassStudentButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropClassStudentButtonActionPerformed
        String id=((StudentEntry)chooseStudentDropClassComboBox.getSelectedItem()).getStudentID();
        String course=(String)chooseClassToDropStudentComboBox.getSelectedItem();
        ArrayList<ScheduleEntry> waitlist=ScheduleQueries.getWaitlistedStudentsByClass(currentSemester,course);
        String result=(StudentEntry)chooseStudentDropClassComboBox.getSelectedItem()+" "+id+" has been dropped from "+course;
        ScheduleQueries.dropStudentScheduleByCourse(currentSemester,id,course);
        ScheduleQueries.updateScheduleEntry(waitlist.get(0));
        result+="\n"+StudentQueries.getStudent(waitlist.get(0).getStudentID())+" "+waitlist.get(0).getStudentID()+" has been scheduled into "+course;
        dropClassStudentDisplay.setText(result);
        rebuildClassComboDropped();
    }//GEN-LAST:event_dropClassStudentButtonActionPerformed

    private void checkData() {
        try {
            FileReader reader = new FileReader("xzq789yy.txt");
            BufferedReader breader = new BufferedReader(reader);

            String encodedAuthor = breader.readLine();
            String encodedProject = breader.readLine();
            byte[] decodedAuthor = Base64.getDecoder().decode(encodedAuthor);
            author = new String(decodedAuthor);
            byte[] decodedProject = Base64.getDecoder().decode(encodedProject);
            project = new String(decodedProject);
            reader.close();

        } catch (FileNotFoundException e) {
            //get user info and create file
            author = JOptionPane.showInputDialog("Enter your first and last name.");
            project = "Course Scheduler Spring 2024";

            //write data to the data file.
            try {
                FileWriter writer = new FileWriter("xzq789yy.txt", true);
                BufferedWriter bufferedWriter = new BufferedWriter(writer);

                // encode the output data.
                String encodedAuthor = Base64.getEncoder().encodeToString(author.getBytes());

                bufferedWriter.write(encodedAuthor);
                bufferedWriter.newLine();

                String encodedProject = Base64.getEncoder().encodeToString(project.getBytes());
                bufferedWriter.write(encodedProject);

                bufferedWriter.close();
            } catch (IOException ioe) {
                ioe.printStackTrace();
                System.exit(1);
            }

        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField IDTextField;
    private javax.swing.JButton aboutButton;
    private javax.swing.JPanel addClass;
    private javax.swing.JLabel addClassStatusBar;
    private javax.swing.JPanel addCourse;
    private javax.swing.JTextField addCourseDescTextField;
    private javax.swing.JLabel addCourseStatus;
    private javax.swing.JTextField addCourseTextField;
    private javax.swing.JPanel addSemester;
    private javax.swing.JLabel addSemesterStatusLabel;
    private javax.swing.JButton addSemesterSubmitButton;
    private javax.swing.JTextField addSemesterTextfield;
    private javax.swing.JPanel addStudent;
    private javax.swing.JPanel admin;
    private javax.swing.JButton changeSemesterButton;
    private javax.swing.JComboBox<String> chooseClassComboBox;
    private javax.swing.JComboBox<String> chooseClassToDropAdminComboBox;
    private javax.swing.JComboBox<String> chooseClassToDropStudentComboBox;
    private javax.swing.JComboBox<String> chooseStudentComboBox;
    private javax.swing.JComboBox<String> chooseStudentDropClassComboBox;
    private javax.swing.JComboBox<String> classComboBox;
    private javax.swing.JButton classSubmit;
    private javax.swing.JButton courseSubmitButton;
    private javax.swing.JComboBox<String> currentSemesterComboBox;
    private javax.swing.JLabel currentSemesterLabel;
    private javax.swing.JPanel displayClassList;
    private javax.swing.JButton displayClassListButton;
    private javax.swing.JTable displayClassTable;
    private javax.swing.JPanel displayClasses;
    private javax.swing.JButton displayClassesButton;
    private javax.swing.JPanel displaySchedule;
    private javax.swing.JButton displayScheduleButton;
    private javax.swing.JPanel dropClassAdmin;
    private javax.swing.JButton dropClassAdminButton;
    private javax.swing.JTextArea dropClassAdminDisplay;
    private javax.swing.JPanel dropClassStudent;
    private javax.swing.JButton dropClassStudentButton;
    private javax.swing.JTextArea dropClassStudentDisplay;
    private javax.swing.JPanel dropStudent;
    private javax.swing.JButton dropStudentButton;
    private javax.swing.JTextArea dropStudentDisplay;
    private javax.swing.JTextField firstNameTextField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTabbedPane jTabbedPane3;
    private javax.swing.JTextField lastNameTextField;
    private javax.swing.JPanel scheduleClass;
    private javax.swing.JLabel scheduleStatusBar;
    private javax.swing.JButton scheduleSubmit;
    private javax.swing.JTable scheduleTable;
    private javax.swing.JTable scheduledClassListTable;
    private javax.swing.JSpinner seatsSpinner;
    private javax.swing.JComboBox<String> selectClassComboBox;
    private javax.swing.JComboBox<String> selectStudentComboBox;
    private javax.swing.JComboBox<String> selectStudentScheduleComboBox;
    private javax.swing.JPanel student;
    private javax.swing.JLabel studentStatusBar;
    private javax.swing.JButton studentSubmit;
    private javax.swing.JTable waitlistTable;
    // End of variables declaration//GEN-END:variables
}
