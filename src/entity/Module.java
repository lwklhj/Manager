package entity;

import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

/**
 * Created by Liu Woon Kit on 20/1/2017.
 */
public class Module {
    private String yearSemester;
    private String moduleName;
    private double moduleMaxCredit;
    private String gradeObtained;

    public Module(String yearSemester, String moduleName, double moduleMaxCredit, String gradeObtained) {
        this.yearSemester = yearSemester;
        this.moduleName = moduleName;
        this.moduleMaxCredit = moduleMaxCredit;
        this.gradeObtained = gradeObtained;
    }

    public String getYearSemester() {
        return yearSemester;
    }

    public void setYearSemester(String yearSemester) {
        this.yearSemester = yearSemester;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public double getModuleMaxCredit() {
        return moduleMaxCredit;
    }

    public void setModuleMaxCredit(double moduleMaxCredit) {
        this.moduleMaxCredit = moduleMaxCredit;
    }

    public String getGradeObtained() {
        return gradeObtained;
    }

    public void setGradeObtained(String gradeObtained) {
        this.gradeObtained = gradeObtained;
    }

    // Module grade point calculation
    public double getModuleGradePoint() {
        switch(gradeObtained) {
            case "DIST":
                return 4.0;
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            case "D+":
                return 1.5;
            case "D":
                return 1.0;
            case "D-":
                return 0.5;
            case "P":
                return 0.5;
            case "F":
                return 0.0;
        }
        return 0.0;
    }

    private TextField moduleNameField;
    private ComboBox moduleMaxCreditComboBox = new ComboBox();
    private ComboBox gradeObtainedComboBox = new ComboBox();

    public TextField getModuleNameField() {
        return moduleNameField = new TextField(moduleName);
    }

    public ComboBox getModuleMaxCreditComboBox() {
        moduleMaxCreditComboBox.getItems().addAll(8.0, 7.0, 6.0, 5.0, 4.0, 3.0, 2.0 , 1.0);
        moduleMaxCreditComboBox.getSelectionModel().select(moduleMaxCredit);
        return moduleMaxCreditComboBox;
    }

    public ComboBox getGradeObtainedComboBox() {
        gradeObtainedComboBox.getItems().addAll("DIST", "A", "B+", "B", "C+", "C", "D+", "D", "D-", "P", "F");
        gradeObtainedComboBox.getSelectionModel().select(gradeObtained);
        return gradeObtainedComboBox;
    }

    // Grab value from fields/combo-box

    public String getModuleNameFieldText() {
        return moduleNameField.getText();
    }

    public double getModuleMaxCreditComboBoxValue() {
        return (double) moduleMaxCreditComboBox.getValue();
    }

    public String getGradeObtainedComboBoxValue() {
        return (String) gradeObtainedComboBox.getValue();
    }
}