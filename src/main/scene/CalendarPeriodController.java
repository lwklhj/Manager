package main.scene;

import database.TaskDA;
import entity.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import main.scene.CalendarController;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 16/12/2016.
 */
public class CalendarPeriodController extends CalendarController implements Initializable {
    private String[] monthOfYear = cal.getMonthOfYear();
    private TaskDA taskDA = new TaskDA();

    
    private ObservableList tasksList
            
            
            
            = FXCollections.observableArrayList();

    @FXML
    private TableView eventTable;

    @FXML
    Label dateLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dateLabel.setText(cal.getSelectedDay() + " " + monthOfYear[cal.getSelectedMonth()].toLowerCase() + " " + cal.getSelectedYear());

        
        getTimeSlots();

        
        updateScreen();
    }

    
    public void getTimeSlots() {
        String date = cal.arrangeDate(cal.getSelectedYear(), (cal.getSelectedMonth()+1), cal.getSelectedDay());

        
        System.out.println("Selected date(YYY-MM-DD): " + date);

        
        ArrayList<Task> taskArrayList = taskDA.getCustomTasks(date);
        
        for(int i = 0; i < taskArrayList.size(); i++) {
            tasksList.add(taskArrayList.get(i));
        }

    }

    public void updateScreen() {
        
        
        
        
        

        
        TableColumn taskTimeColumn = new TableColumn("Time");
        taskTimeColumn.setCellValueFactory(new PropertyValueFactory("dueTime"));

        
        TableColumn taskDescriptionColumn = new TableColumn("Task");
        taskDescriptionColumn.setCellValueFactory(new PropertyValueFactory("title"));

        
        eventTable.getColumns().addAll(taskTimeColumn, taskDescriptionColumn);

        
        eventTable.setItems(tasksList);
    }

    
    public void addTask() {
        new SecondaryScene("AddTask.fxml", "Add Task", true);
    }
}