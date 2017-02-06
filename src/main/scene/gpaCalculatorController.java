package main.scene;

import database.GpaDA;
import entity.Calendar;
import entity.Module;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Liu Woon Kit on 20/1/2017.
 */
public class gpaCalculatorController implements Initializable{
    private int currentYear = new Calendar().getCurrentYear();
    private GpaDA gpaDA = new GpaDA();
    private ArrayList<String> yearSemestersList;
    private ArrayList<Module> moduleArrayList;

    @FXML
    Label interimGPA;

    @FXML
    private Label overallGPA;

    @FXML
    private VBox selectorPanel;

    @FXML
    private AnchorPane ysDisplayPane;

    @FXML
    private VBox modulesPanel;

    @FXML
    private ComboBox yearComboBox;

    @FXML
    private ComboBox semesterComboBox;

    // Starting things
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Set ComboBox items
        semesterComboBox.getItems().addAll("S1", "S2");
        for(int i = 0; i < 6; i++)
            yearComboBox.getItems().add(currentYear - i);

        yearSemesterSelector();
        overallGPA.setText(String.format("%.2f",gpaDA.calculateTotalGPA()));
    }

    // Shows UI for picking a yearSemester
    public void yearSemesterSelector() {
        clearPanel(selectorPanel);
        yearSemestersList = gpaDA.getYearSemestersList();
        for(String s : yearSemestersList) {
            if(!s.substring(0, 4).equals("null")) {
                Button yearSemesterBtn = new Button(s);

                yearSemesterBtn.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        yearSemesterViewer(s);
                    }
                });

                selectorPanel.getChildren().add(yearSemesterBtn);
            }
        }

        Button addYearSemesterBtn = new Button("+");
        addYearSemesterBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                yearSemesterViewer(null);
            }
        });

        selectorPanel.getChildren().add(addYearSemesterBtn);
    }

    // Shows the UI for what stored modules are in a particular year yearSemester
    public void yearSemesterViewer(String yearSemester) {

        // Set interimGPA counter
        interimGPA.setText(String.format("%.2f",gpaDA.calculateInterimGPA(yearSemester)));

        if(yearSemester != null) {
            yearComboBox.getSelectionModel().select(yearSemester.substring(0, 4));

            semesterComboBox.getSelectionModel().select(1);
            if(yearSemester.substring(4, 6).equals("S1"))
                semesterComboBox.getSelectionModel().select(0);
        }
        else {
            yearComboBox.getSelectionModel().clearSelection();
            yearComboBox.setValue(null);
            semesterComboBox.getSelectionModel().clearSelection();
        }

        // Wipe existing HBoxes if any
        clearPanel(modulesPanel);
        // Change display pane to visible
        ysDisplayPane.setVisible(true);

        moduleArrayList = gpaDA.getModules(yearSemester);

        // Create new HBox (and append it to the VBox) for every module in the ArrayList
        for(Module m : moduleArrayList) {
            HBox tempHBox = new HBox();
            modulesPanel.getChildren().add(tempHBox);

            Button deleteModuleBtn = new Button("X");
            deleteModuleBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    gpaDA.deleteModule(yearSemester, m.getModuleName(), m.getModuleMaxCredit(), m.getGradeObtained());
                    yearSemesterViewer(yearSemester);
                }
            });

            tempHBox.getChildren().addAll(m.getModuleNameField(), m.getModuleMaxCreditComboBox(), m.getGradeObtainedComboBox(), deleteModuleBtn);
        }
    }

    @FXML
    void addModule() {
        // Safe existing modules
        updateYearSemesterModules();

        // Get values from ComboBoxes
        String selectedYearSemester = yearComboBox.getValue() + "" + semesterComboBox.getValue();

        // Create blank module
        gpaDA.storeGPARecord(selectedYearSemester, "Enter Name Here", 0.0, "F");

        // Refresh display to see added entity
        yearSemesterViewer(selectedYearSemester);
    }

    @FXML
    void updateYearSemesterModules() {

        // update modules with their own current TextFields values
        for(Module m : moduleArrayList) {
            m.setModuleName(m.getModuleNameFieldText());
            m.setModuleMaxCredit(m.getModuleMaxCreditComboBoxValue());
            m.setGradeObtained(m.getGradeObtainedComboBoxValue());
        }

        // Get values from ComboBoxes to get the yearSemester
        String selectedYearSemester = yearComboBox.getValue() + "" + semesterComboBox.getValue();

        // Give objects to the DA
        gpaDA.updateGPARecords(moduleArrayList, selectedYearSemester);

        // Refresh the yearSemesterSelector
        yearSemesterSelector();

        // Refresh GPA counter
        overallGPA.setText(String.format("%.2f",gpaDA.calculateTotalGPA()));

        // Refresh interim GPA counter
        interimGPA.setText(String.format("%.2f",gpaDA.calculateInterimGPA(selectedYearSemester)));

        // Debug
        System.out.println("Updated!");
    }

    public void clearPanel(VBox vBox) {
        // Wipe children objects from pane
        vBox.getChildren().clear();
    }

}
