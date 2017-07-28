package autoworklog.logviewer.ui;

import javax.swing.*;

/**
 * The main JFrame of the application
 * Created by kagy on 28.07.2017.
 */
public class JMainFrame extends JFrame{
    //The panel that shows the current work day
    private JWorkLogPanel workLogPanel;

    /**
     * Creates a new JMain frame with no open WorkLog.
     */
    public JMainFrame() {
        //creting the work log panel
        workLogPanel=new JWorkLogPanel();
        this.setContentPane(workLogPanel);

        //todo: add menubar herer
        //doing exit behaviour
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * The main entry point of the program.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        JMainFrame frame=new JMainFrame();

        frame.setBounds(10, 10, 600, 400);
        frame.setVisible(true);
    }
}
