import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import java.awt.Point;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.GroupLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Window extends javax.swing.JFrame {
	
    private JButton jButtonAdd;
    private JButton jButtonConnect;
    private JButton jButtonRestart;
    private JButton jButtonEnd;
    private JButton jButtonHelp;
    private JButton jButtonAbout;
    private JButton jButtonProcess;
    private JButton jButtonFileReport;
    private JButton jButtonChange;
    private JButton jButtonCrit;
    
    private javax.swing.JPanel  jPanel1;
    
    private List<Node> nodes;
    private List<Add> connect;
    Processor processor;
  
    private int APPLET_WIDTH = 800, APPLET_HEIGHT = 600;
    
    public Window() {
        
        initComponents();
    }

    private void initComponents() {
        // Node and connection data
        this.nodes = new ArrayList<>();
        this.connect = new ArrayList<>();
        
        //jPanel Creation
        jPanel1 = new Panel( this.nodes, this.connect, new Panel.NodeCreator(){
            public Node createNode( Point point ){
                return actionCreateNode( point );
            }
        } );
        
        //jButton Creation
        jButtonAdd = new JButton();
        jButtonConnect = new JButton();
        jButtonRestart = new JButton();
        jButtonEnd = new JButton();
        jButtonHelp = new JButton();
        jButtonAbout = new JButton();
        jButtonProcess = new JButton();
        jButtonFileReport = new JButton();
        jButtonChange = new JButton();
        jButtonCrit = new JButton();
        
        //EXIT ON CLOSE as Default
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        
        //Title
        setTitle("Network Diagram");
        
        //Set background
        jPanel1.setBackground(new java.awt.Color(153, 153, 153));
        
        /**LAYOUT**/
        
        //Set group layout on jpanel1
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        
        //Set horizontal group
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 525, Short.MAX_VALUE));
        
        //Set vertical group
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 523, Short.MAX_VALUE)); 
        
        /**END LAYOUT**/
        
        /**BUTTON SETUP**/
        
        /*All buttons setup with setBackground, setText, and configured ActionListener*/
        
       //Crits Application Button
        jButtonCrit.setBackground(new java.awt.Color(51, 204, 255));
        jButtonCrit.setText("Critical Paths");
        
        jButtonCrit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonCritActionPerformed(evt);
            }
        });
        
        //End Application Button
        jButtonEnd.setBackground(new java.awt.Color(51, 204, 255));
        jButtonEnd.setText("End Application");
        
        jButtonEnd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonEndActionPerformed(evt);
            }
        });
        
        //Restart Button
        jButtonRestart.setBackground(new java.awt.Color(51, 204, 255));
        jButtonRestart.setText("Restart");
        
        jButtonRestart.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonRestartActionPerformed(evt);
            }
        });
        
        //Add Activity Button
        jButtonAdd.setBackground(new java.awt.Color(51, 204, 255));
        jButtonAdd.setText("Add Activity");
        
        jButtonAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });
        
        //Connect Activities Button
        jButtonConnect.setBackground(new java.awt.Color(51, 204, 255));
        jButtonConnect.setText("Connect Activities");
        
        jButtonConnect.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonConnectActionPerformed(evt);
            }
        });
        
        //About Button
        jButtonAbout.setBackground(new java.awt.Color(51, 204, 255));
        jButtonAbout.setText("About");
        
        jButtonAbout.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonAboutActionPerformed(evt);
            }
        });
        
        //Help Button
        jButtonHelp.setBackground(new java.awt.Color(51, 204, 255));
        jButtonHelp.setText("Help");
        
        jButtonHelp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonHelpActionPerformed(evt);
            }
        });
        
        //Process Button
        jButtonProcess.setBackground(new java.awt.Color(51, 204, 255));
        jButtonProcess.setText("Process");

        jButtonProcess.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonProcessActionPerformed(evt);
            }
        });

        //FileReport Button
        jButtonFileReport.setBackground(new java.awt.Color(51, 204, 255));
        jButtonFileReport.setText("Report");

        jButtonFileReport.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                jButtonProcessFileReportPerformed(evt);
            }
        });
        
      // Change Duration Button
        jButtonChange.setBackground(new java.awt.Color(51, 204, 255));
        jButtonChange.setText("Change Duration");
        
        jButtonChange.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
               jButtonChangeActionPerformed(evt);
            }
        });

        /**END BUTTON SETUP**/
        
        /**ADDING COMPONENTS TO JPANEL**/
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                	.addComponent(jButtonAdd, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                	.addComponent(jButtonConnect, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                	.addComponent(jButtonProcess, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                	.addComponent(jButtonCrit, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                	.addComponent(jButtonChange, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jButtonRestart, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jButtonAbout, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jButtonHelp, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jButtonFileReport, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
                    .addComponent(jButtonEnd, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                
                .addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(jButtonAdd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonConnect, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonProcess, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonCrit, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonChange, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonRestart, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonAbout, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonHelp, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonFileReport, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButtonEnd, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()))))));
        pack();
        setSize(APPLET_WIDTH, APPLET_HEIGHT);
        
        /**END ADDING COMPONENTS TO JPANEL**/
       
    }
    
    /**ACTIONS**/
    private void jButtonAddActionPerformed(ActionEvent evt) {
       
       JOptionPane.showMessageDialog( this, "Click where you want the activity located.");
    }

    private void jButtonConnectActionPerformed(ActionEvent evt) {

        JOptionPane.showMessageDialog( this, "Right click the predecessor activity to link the activities.");
    }
    
    private void jButtonRestartActionPerformed(ActionEvent evt) {

        JOptionPane.showMessageDialog( this, "Network Diagram Cleared."); 
        
        // clear data
        connect.clear(); 
        nodes.clear();

        repaint();
    }
    
    private void jButtonEndActionPerformed(ActionEvent evt) {

        JOptionPane.showMessageDialog( this, "Application will close.");
        System.exit(0);
    }
    
    private void jButtonChangeActionPerformed(ActionEvent evt) {
 
    	String activity = "";
    	String newDuration; 
	    
        try {
            String activityToChange = JOptionPane.showInputDialog("Enter the name of the activity to change: ");
            
            if (activityToChange.equals(null) || activityToChange.replace(" ", "").equals("")) {
                JOptionPane.showMessageDialog(this, "Write a valid activity name.");
           }
            else
            {
            	activity = activityToChange; 
            }
           
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Write a valid activity name.");
        }
        try{
    		newDuration = JOptionPane.showInputDialog("Enter a new duration for activity" + " " + activity);
    		int nDuration = Integer.parseInt(newDuration);
    
    		for(int i = 0; i < nodes.size(); i++)
    		{
    		  if((nodes.get(i).getActivityName()).equals(activity))
                  {
    			  	//changes to only change duration of given node
    			  	nodes.get(i).setActivityDuration(nDuration);
    			  	repaint();
    			  	JOptionPane.showMessageDialog(this, "Duration updated." + " Activity " + nodes.get(i).getActivityName() +"'s new duration is: " + nodes.get(i).getActivityDuration());
    		  }
    		}
            
    	}catch(Exception ex){
            JOptionPane.showMessageDialog(this, "Enter an integer for the duration.");
            
        }
    }

    private void jButtonAboutActionPerformed(ActionEvent evt) {

        JOptionPane.showMessageDialog( this, "The program is designed to process activity diagrams from user inputs.");
        		
    }
    
    private void jButtonHelpActionPerformed(ActionEvent evt) {

        JOptionPane.showMessageDialog( this, "Start by creating an activity node."
                + "\nClicking one of the many butons will show some information on what it does."
                + "\nWriting a report requires a valid graph."
        		+ "\nTo exit the program, press the End Application button.");
    }
    
    
    private void jButtonCritActionPerformed(ActionEvent evt) {
    	processor = null;
        Processor p = processor = new Processor( this.nodes ); 
        p.buildPaths();
        if( p.failed() ){
            JOptionPane.showMessageDialog( this, p.failureMessage(), "Processing error.", JOptionPane.ERROR_MESSAGE );
        } else {
        	JOptionPane.showMessageDialog( this, "Critical Paths: "+p.crits());
        }
    	
    }
    private void jButtonProcessActionPerformed(ActionEvent evt) {
    	processor = null;
        Processor p = processor; 
        if( processor == null ){
            p = processor = new Processor( this.nodes );
            p.buildPaths();
        }

        if( p.failed() ){
            JOptionPane.showMessageDialog( this, p.failureMessage(), "Processing error.", JOptionPane.ERROR_MESSAGE );
        } else {
            JOptionPane.showMessageDialog( this, "Paths: "+p.outputString());
        }
    }

    private void jButtonProcessFileReportPerformed( ActionEvent evt ){
    	processor = null;
        Processor p = processor; 
        p = processor = new Processor( this.nodes );
        p.buildPaths();
        
        if( p.failed() ){
            JOptionPane.showMessageDialog( this, "Can't write report: " + p.failureMessage(), "Processing error.", JOptionPane.ERROR_MESSAGE );
            return;
        }

        JFileChooser chooser = new JFileChooser(".");
        chooser.setFileFilter( new FileNameExtensionFilter("Network Reports", "txt") );
        int choice = chooser.showOpenDialog( this );

        if ( choice != JFileChooser.APPROVE_OPTION ){ return; }

        java.io.File file = chooser.getSelectedFile();

        if( !file.exists() ){
            JOptionPane.showMessageDialog( this, "A new file will be created." );

            if( !file.getName().endsWith(".txt") ){
                file = new java.io.File( file.getAbsolutePath() + ".txt" );
            }

        } else {
            if( !file.getName().endsWith(".txt") ){
                JOptionPane.showMessageDialog( this, "Incorrect file type.", 
                    "Extension not TXT", JOptionPane.ERROR_MESSAGE );
                return;
            }

            String question = "Are you sure you would like to overwrite this file?";
            int response = JOptionPane.showConfirmDialog( this, question, "Confirm", 
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
            
            if( response != JOptionPane.YES_OPTION ){ return; } 
        }

        Report report = new Report( this.nodes, this.processor );
        report.write( file );
    }

    private Node actionCreateNode( Point point ){
        return actionCreateNode( "", "", point );
    } 

    private Node actionCreateNode( String nameS, String durationS, Point point ) {
       
        // name duration and parents field
         javax.swing.JTextField name =      new javax.swing.JTextField( nameS );
         javax.swing.JTextField duration =  new javax.swing.JTextField( durationS );

        final javax.swing.JComponent[] inputs = new javax.swing.JComponent[] {
                new javax.swing.JLabel("Name"), name,
                new javax.swing.JLabel("Duration"), duration
        };
       
        int result = JOptionPane.showConfirmDialog(this, inputs, "Add Activity", JOptionPane.PLAIN_MESSAGE);
        nameS = name.getText();
        durationS = duration.getText();

        if( result == JOptionPane.OK_OPTION ){
            // check if input valid
            String err = "ERROR";
            
            boolean invalidName = ( nameS.equals(null) || nameS.replace(" ", "").equals("") );
            boolean invalidDuration = true;

            boolean nameExists = false;
            
            // naming collisions
            for( Node node : this.nodes ){
                if( node.getActivityName().equals( nameS ) ){
                    nameExists = true;
                    break;
                }
            }

            int foundDuration = 0;
            Scanner scanner = new Scanner(durationS);
            if( scanner.hasNextInt() ){
                foundDuration = scanner.nextInt();
                invalidDuration = scanner.hasNext();
            }

            if( nameExists ){
                err = "A node already has this name. Please choose different one.";
            } else if( invalidName ){
                err = "An invalid name has been entered";
            }
            else if( invalidDuration ){
                err = "Please enter an integer value.";
            } else {
                
                // : ACTION SUCCESFULLY CREATED HERE :
                Node n = new Node();
                n.setActivityName(nameS);
                n.setActivityDuration(foundDuration);
                n.setPoint(point);

                this.nodes.add(n);
                processor = null; // every time network changes, it has not been processed.
                return n;
            }

            JOptionPane.showMessageDialog( this, err, "Error: Enter a valid name/duration.", JOptionPane.ERROR_MESSAGE );
            return actionCreateNode( nameS, durationS, point );
        } else {
 
            return null;
        }
    }

    /**END ACTIONS**/
    
    public static void main(String args[]) {
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Window().setVisible(true);
            }
        });
    }
}