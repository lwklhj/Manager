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

import static java.lang.Double.parseDouble;

/**
 * Created by Liu Woon Kit on 20/1/2017.
 */
public class gpaCalculatorController implements Initializable{
    private int currentYear = new Calendar().getCurrentYear();
    private GpaDA gpaDA = new GpaDA();
    private ArrayList<String> yearSemestersList = gpaDA.getYearSemestersList();
    private ArrayList<Module> moduleArrayList;
    private String selectedYearSemester;

    
    

    
    

    @FXML
    private Label totalGPA;

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

    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        semesterComboBox.getItems().addAll("S1", "S2");
        for(int i = 0; i < 6; i++)
            yearComboBox.getItems().add(currentYear - i);

        yearSemesterSelector();
        totalGPA.setText(gpaDA.calculateTotalGPA() + "");
    }

    
    public void yearSemesterSelector() {
        for(String s : yearSemestersList) {
            Button yearSemesterBtn = new Button(s);

            yearSemesterBtn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    yearSemesterViewer(s);
                }
            });

            selectorPanel.getChildren().add(yearSemesterBtn);
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

    
    public void yearSemesterViewer(String yearSemester) {

        if(yearSemester != null) {
            yearComboBox.getSelectionModel().select(yearSemester.substring(0, 4));

            semesterComboBox.getSelectionModel().select(1);
            if(yearSemester.substring(4, 6).equals("S1"))
                semesterComboBox.getSelectionModel().select(0);
        }

        
        clearPanel();
        
        ysDisplayPane.setVisible(true);

        moduleArrayList = gpaDA.getModules(yearSemester);


        
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

            tempHBox.getChildren().addAll(m.getModuleNameField(), m.getModuleMaxCreditField(), m.getGradeObtainedField(), deleteModuleBtn);
        }
    }

    @FXML
    void addModule() {
        
        updateYearSemesterModules();

        
        String selectedYearSemester = yearComboBox.getValue() + "" + semesterComboBox.getValue();

        
        gpaDA.storeGPARecord(selectedYearSemester, "NULL", 0.0, "F");

        
        yearSemesterViewer(selectedYearSemester);
    }

    @FXML
    void updateYearSemesterModules() {

        
        for(Module m : moduleArrayList) {
            m.setModuleName(m.getModuleNameFieldText());
            m.setModuleMaxCredit(parseDouble(m.getModuleMaxCreditFieldText()));
            m.setGradeObtained(m.getGradeObtainedFieldText());
        }

        
        String selectedYearSemester = yearComboBox.getValue() + "" + semesterComboBox.getValue();

        
        gpaDA.updateGPARecords(moduleArrayList, selectedYearSemester);

        
        System.out.println("Updated!");
    }

    public void clearPanel() {
        
        modulesPanel.getChildren().clear();
    }

}
