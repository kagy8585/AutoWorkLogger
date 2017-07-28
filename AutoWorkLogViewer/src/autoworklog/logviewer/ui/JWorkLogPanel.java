package autoworklog.logviewer.ui;

import autoworklog.logviewer.WorkDay;
import autoworklog.logviewer.WorkLog;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;

/**
 * JPanel for showing work log and all their details.
 * Created by kagy on 28.07.2017.
 */
public class JWorkLogPanel extends JPanel{

    /**
     * The padding used in the panels.
     */
    private static final int PADDING =6;

    //The inner panels.
    private JWorkLogInfoPanel infoPanel;
    private DefaultListModel<String> dayListModel;
    private JList<String> dayList;
    private JScrollPane dayScrollPane;
    private JWorkDayPanel dayPanel;

    /**
     * The work log presented in the panel.
     */
    private WorkLog workLog;

    /**
     * The array of work days extracted form the work log.
     */
    private WorkDay[] workDays;

    /**
     * Creates a new JWorkLog pane without actually creating
     */
    public JWorkLogPanel() {
        workLog=null;
        workDays=new WorkDay[0];

        //setting up the UI
        this.setLayout(new BorderLayout());
        //adding panel for the work log global info
        infoPanel=new JWorkLogInfoPanel();
        infoPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.add(infoPanel, BorderLayout.NORTH);

        //adding the day list in the center
        dayListModel=new DefaultListModel<String>();
        dayList=new JList<String>(dayListModel);
        //setting up single selection
        dayList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);

        //setting up scroll pane for the day list
        dayScrollPane =new JScrollPane(dayList);
        dayScrollPane.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        dayScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        dayScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.add(dayScrollPane, BorderLayout.CENTER);

        //adding the day info panel in the bottom
        dayPanel=new JWorkDayPanel();
        dayPanel.setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
        this.add(dayPanel, BorderLayout.SOUTH);
    }

    /**
     * Updates the UI panel, when WorkLog changes.
     */
    private void updateUIPanel() {
        //getting the work days
        workDays=(workLog==null ? new WorkDay[0]: workLog.getDays());

        //clearing the day information
        dayPanel.setDay(null);

        //updating the list
        dayListModel.removeAllElements();
        for (WorkDay day : workDays) {
            dayListModel.addElement(day.getDay().toString());
        }
        //the day info panel will change with the selection
        if (workDays.length>0) {
            dayList.setSelectedIndex(0);
        }
        //updating the day panel
        updateDayPanel();
    }

    /**
     * Updates the element selected on the day panel.
     */
    private void updateDayPanel() {
        WorkDay selectedDay=null;
        int selectedIndex=dayList.getSelectedIndex();

        //putting the selected day
        if (selectedIndex>=0 && selectedIndex<workDays.length) {
            selectedDay=workDays[selectedIndex];
        }
        //seting up the selected day
        dayPanel.setDay(selectedDay);
    }

    /**
     * Gets the work log shown by the panel.
     * @return The WorkLog show on the panel.
     */
    public WorkLog getWorkLog() {
        return workLog;
    }


    /**
     * Sets the work log presented in the panel.
     * @param newWorkLog The new work log to be presented in the panel.
     */
    public void setWorkLog(WorkLog newWorkLog) {

        workLog=newWorkLog;
        updateUIPanel();
    }

    /**
     * Listener for the list changed event.
     */
    private class DayListSelectionChangedListener implements ListSelectionListener {
        @Override
        public void valueChanged(ListSelectionEvent e) {
            updateDayPanel();
        }
    }
}
