package main.scene;

import database.CalendarDA;
import entity.Calendar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 1/12/2016.
 */
public class CalendarController extends MainSceneController implements Initializable {
    
    private Label[] daysLabel = new Label[7];
    private Button[] daysButton = new Button[31];
    private Button[] monthButton = new Button[12];
    private Button[] yearButton = new Button[49];

    
    protected static Calendar cal = new Calendar();
    private String[] dayOfWeek = cal.getDayOfWeek();
    private String[] monthOfYear = cal.getMonthOfYear();

    
    private String selectedMode = "DayPicker";

    @FXML
    GridPane calGrid;

    @FXML
    Button modeDisplay;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daySelector();
    }

    @FXML
    void setMode(ActionEvent event) {
        switch (selectedMode) {

            case "DayPicker":
                monthSelector();
                break;

            case "MonthPicker":
                yearSelector();
                break;

        }
    }

    public void periodicView() throws IOException {
        new SecondaryScene("CalendarPeriod.fxml", "null", true);
    }

    public void daySelector() {
        clearGrid();
        modeDisplay.setText(cal.getStringDate());

        
        for (int u = 0; u < 7; u++) {
            calGrid.add(daysLabel[u] = new Label(dayOfWeek[u]), u, 0);
        }

        
        
        /*for(int i = 0, x = 1, y = 0; i < 42; i++) {
            if(y > 6) {
                y = 0;
                x++;
            }
            calGrid.add(daysButton[i] = new Button(), y, x);

            
            int tempIndex = i;
            daysButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    if(daysButton[tempIndex].getText() == "")
                        return;

                    else {
                        cal.setSelectedDay(parseInt(daysButton[tempIndex].getText()));
                        try {
                            periodicView();
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }

                }
            });

            y++;
        }

        
        for(int i = cal.getFirstDay(), j = 0; j < cal.getLastDayOfMonth(); i++) {
            daysButton[i - 1].setText((j + 1) + "");
            j++;
        }*/


        
        

        for (int i = 0, x = (cal.getFirstDay() - 1), y = 1; i < cal.getLastDayOfMonth(); i++) {
            if (x > 6) {
                x = 0;
                y++;
            }
            calGrid.add(daysButton[i] = new Button(Integer.toString(i + 1)), x, y);

            int tempNum = i + 1;
            daysButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    cal.setSelectedDay(tempNum);
                    try {
                        periodicView();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

            x++;
        }

        displayHolidays();
        displayTaskDays();
    }

    public void monthSelector() {
        clearGrid();
        selectedMode = "MonthPicker";
        modeDisplay.setText(cal.getSelectedYear() + "");

        for (int i = 0, x = 0, y = 0; i <= 11; i++) {
            if (x > 6) {
                y = 1;
                x = 0;
            }

            calGrid.add(monthButton[i] = new Button(monthOfYear[i]), x, y);

            int tempNum = i;
            monthButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    selectedMode = "DayPicker";
                    cal.setSelectedMonth(tempNum);
                    daySelector();
                }
            });

            x++;
        }
    }

    public void yearSelector() {
        clearGrid();
        selectedMode = "YearPicker";
        modeDisplay.setText("2000 - 2042");
        for (int i = 0, x = 0, y = 0, year = cal.getSelectedYear(); i < 49; i++) {
            if (y > 6) {
                x++;
                y = 0;
            }
            calGrid.add(yearButton[i] = new Button(year + ""), y, x);

            int j = i;

            yearButton[i].setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    System.out.println(yearButton[j].getText());
                }
            });

            y++;
            year++;
        }
    }

    public void clearGrid() {
        
        calGrid.getChildren().clear();
    }

    public void ButtonDown() {
        cal.setSelectedMonth(cal.getSelectedMonth() + 1);
        refreshDisplay();
    }

    public void ButtonUp() {
        cal.setSelectedMonth(cal.getSelectedMonth() - 1);
        refreshDisplay();
    }

    public void refreshDisplay() {
        
        switch (selectedMode) {

            case "DayPicker":
                daySelector();
                break;

            case "MonthPicker":
                monthSelector();
                break;

            case "YearPicker":
                yearSelector();
                break;

        }
    }

    public void displayTaskDays() {
        /*TaskAccess taskDB = new TaskAccess();
        for (int i = 1; i < 32; i++) {
            ArrayList arr = null;
            try {
                arr = taskDB.getTask(user.getSchool(), i + "/" + cal.getSelectedMonth() + "/" + cal.getSelectedYear());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (arr.size() > 0) {
                daysButton[cal.getFirstDay()].setStyle("-fx-background-color: #f44336;");
            }
        }*/
    }

    private CalendarDA calendarDA = new CalendarDA();

    private void displayHolidays() {
        for (int i = 1, z = cal.getLastDayOfMonth() + 1; i < z; i++) {
            String date = cal.arrangeDate(cal.getSelectedYear(), cal.getSelectedMonth() + 1, i);
            if (calendarDA.checkHoliday(date) == true) {
                daysButton[i - 1].setStyle("-fx-background-color: #e91e63; -fx-text-fill: #ffffff;");
            }
        }
    }
    
}