package autoworklog.logviewer.ui;

import autoworklog.logviewer.WorkLog;
import autoworklog.logviewer.WorkLogParser;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

/**
 * The main JFrame of the application
 * Created by kagy on 28.07.2017.
 */
public class JMainFrame extends JFrame{

    /**
     * The name of the open action.
     */
    private static final String OPEN_ACTION_NAME ="Open";

    /**
     * The name of the exit action.
     */
    private static final String EXIT_ACTION_NAME="Exit";

    //The panel that shows the current work day
    private JWorkLogPanel workLogPanel;
    //the menu components
    private JMenuBar mainMenuBar;
    private JMenu fileMenu;
    private JMenuItem openMenuItem;
    private JMenuItem exitMenuItem;
    //the actions
    private OpenAction openAction;
    private ExitAction exitAction;
    //the file chooser and filter
    private JFileChooser fileChooser;
    private FileNameExtensionFilter fileFilter;

    /**
     * Creates a new JMain frame with no open WorkLog.
     */
    public JMainFrame() {

        InputMap workLogPanelInputMap;

        //creating the actions
        openAction=new OpenAction();
        openAction.setEnabled(true);

        exitAction=new ExitAction();
        exitAction.setEnabled(true);

        //creating the work log panel
        workLogPanel=new JWorkLogPanel();
        workLogPanel.getActionMap().put(OPEN_ACTION_NAME, openAction);

        this.setContentPane(workLogPanel);

        //creating menubar
        mainMenuBar=new JMenuBar();

        //creating file menu
        fileMenu=new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        //creating open menu item
        openMenuItem=new JMenuItem("Open");
        openMenuItem.setMnemonic(KeyEvent.VK_O);
        openMenuItem.setAction(openAction);
        fileMenu.add(openMenuItem);

        //creating file menu item
        exitMenuItem=new JMenuItem("Exit");
        exitMenuItem.setMnemonic(KeyEvent.VK_E);
        exitMenuItem.setAction(exitAction);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitMenuItem);

        //adding the file menu
        mainMenuBar.add(fileMenu);
        this.setJMenuBar(mainMenuBar);

        //creating the file chooser
        //creating input file chooser dialogs
        fileChooser=new JFileChooser();
        fileFilter=new FileNameExtensionFilter(
                "Log files (*."+ WorkLogParser.DEFAULT_EXTENSION+")", WorkLogParser.DEFAULT_EXTENSION);
        fileChooser.setFileFilter(fileFilter);

        //doing exit behaviour
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    /**
     * Opens an input log file.
     */
    private void open() {
        int r;

        r=fileChooser.showOpenDialog(this);
        if (r==JFileChooser.APPROVE_OPTION) {
            WorkLogParser parser=new WorkLogParser(fileChooser.getSelectedFile().getAbsolutePath());
            WorkLog workLog=null;
            boolean logRead=false;

            try {
                workLog = parser.createWorkLog();
                logRead=true;
            }
            catch (RuntimeException e) {
                //showing the error message
                JOptionPane.showMessageDialog(this, "Can not open input file!",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            //the UIs updated only if the log is read
            if (logRead)
                workLogPanel.setWorkLog(workLog);
        }
    }

    /**
     * The open action.
     */
    private class OpenAction extends AbstractAction {

        OpenAction() {
            putValue(NAME, OPEN_ACTION_NAME);
            putValue(SHORT_DESCRIPTION, "Open log file.");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            open();
        }
    }

    /**
     * The exit action used to close the program.
     */
    private class ExitAction extends AbstractAction {

        ExitAction() {
            putValue(NAME, EXIT_ACTION_NAME);
            putValue(SHORT_DESCRIPTION, "Exit from program.");
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    /**
     * The main entry point of the program.
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        JMainFrame frame=new JMainFrame();

        frame.setBounds(10, 10, 800, 400);
        frame.setVisible(true);
    }
}
