package entity;

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

    
    public double getModuleGradePoint() {
        switch(gradeObtained) {
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
    private TextField moduleMaxCreditField;
    private TextField gradeObtainedField;
    

    public TextField getModuleNameField() {
        return moduleNameField = new TextField(moduleName);
    }

    public TextField getModuleMaxCreditField() {
        return moduleMaxCreditField = new TextField(moduleMaxCredit + "");
    }

    public TextField getGradeObtainedField() {
        return gradeObtainedField = new TextField(gradeObtained);
    }

    

    public String getModuleNameFieldText() {
        return moduleNameField.getText();
    }

    public String getModuleMaxCreditFieldText() {
        return moduleMaxCreditField.getText();
    }

    public String getGradeObtainedFieldText() {
        return gradeObtainedField.getText();
    }
}
