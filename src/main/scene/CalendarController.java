package main.scene;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import entity.Calendar;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static javafx.fxml.FXMLLoader.load;

/**
 * Created by Liu Woon Kit on 1/12/2016.
 */
public class CalendarController extends MainSceneController implements Initializable {
    private final Label[] daysLabel = new Label[7];
    private final Button[] daysButton = new Button[42];
    private final Button[] monthButton = new Button[12];

    private final String[] dayOfWeek = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
    private final String[] monthOfYear = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    Calendar cal = new Calendar();

    @FXML
    GridPane calGrid;

    @FXML
    Button calPicker;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        daySelector();
    }

    @FXML
    void setPicker(ActionEvent event) {
        if(cal.getCurrentMode() == "PeriodPicker") {
            cal.setCurrentMode("DayPicker");
            daySelector();
        }
        else if(cal.getCurrentMode() == "DayPicker")
            monthSelector();
        else if (cal.getCurrentMode() == "MonthPicker")
            yearSelector();
    }

    public CalendarController() {

    }

    public void periodSelector() throws IOException {
        //fuck dis
        Parent root = FXMLLoader.load(getClass().getResource("CalendarPeriod.fxml"));
        Scene scene = new Scene(root);
        Stage secondaryStage = new Stage();
        secondaryStage.setScene(scene);
        secondaryStage.show();
    }

    public void daySelector() {
        clearGrid();
        calPicker.setText(cal.getStringDate());
        //To-do: clean up more codes

        //set labels
        for(int u = 0; u < 7; u++) {
            calGrid.add(daysLabel[u] = new Label(dayOfWeek[u]), u, 0);
        }

        //create blank buttons with events
        //row is x, column is y, set using column by row
        for(int i = 0, x = 1, y = 0; i < 42; i++) {
            if(y > 6) {
                y = 0;
                x++;
            }
            calGrid.add(daysButton[i] = new Button(), y, x);
            int tempIndex = i;
            daysButton[i].setOnAction(ActionEvent -> {
                cal.setCurrentMode("PeriodPicker");
                //debug line
                System.out.println(Integer.parseInt(daysButton[tempIndex].getText()));
                cal.setSelectedDay(Integer.parseInt(daysButton[tempIndex].getText()));
                try {
                    periodSelector();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            y++;
        }

        //fill blank buttons with numbers
        for(int i = cal.getFirstDay(), j = 0; j < cal.getLastDayOfMonth(); i++) {
            daysButton[i - 1].setText((j + 1) + "");
            j++;
        }
    }

    public void monthSelector(){
        clearGrid();
        cal.setCurrentMode("MonthPicker");
        calPicker.setText(cal.getSelectedYear() + "");

        for(int i = 0, x = 0, y = 0; i<11; i++) {
            if(x > 6) {
                y = 1;
                x = 0;
            }
            calGrid.add(monthButton[i] = new Button(monthOfYear[i]), x, y);
            x++;

            //set j = i to bypass error
            int j = i;
            //set onclick event
            monthButton[i].setOnAction(ActionEvent -> {
                cal.setCurrentMode("DayPicker");
                System.out.println(monthButton[j].getText());
                //To-do: clean up this part using array, note please don't touch this team
                for(int p = 0; p < 6; p++) {
                    if (monthButton[j].getText() == monthOfYear[p]) {
                        cal.setSelectedMonth(p);
                        break;
                    }
                }
                daySelector();
            });


        }
    }

    public void yearSelector() {
        clearGrid();
        cal.setCurrentMode("YearPicker");
        calPicker.setText("2000 - 2099");
}

    public void clearGrid() {
        //wipe grid of elements
        calGrid.getChildren().clear();
    }

    public void ButtonPlus() {
        cal.setSelectedButton(1);
        ButtonCommon();
    }

    public void ButtonMinus() {
        cal.setSelectedButton(-1);
        ButtonCommon();
    }

    public void ButtonCommon() {
        if(cal.getCurrentMode() == "DayPicker")
            daySelector();
        else if(cal.getCurrentMode() == "MonthPicker")
            monthSelector();
        else if(cal.getCurrentMode() == "YearPicker")
            yearSelector();
    }
}
