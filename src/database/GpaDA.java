package database;

import entity.Module;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * Created by Liu Woon Kit on 20/1/2017.
 */
public class GpaDA {
    private SqlAccess sqlAccess = new SqlAccess();
    private SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
    private String adminNo = new UserDA().getUser().getAdminNo();

    public void storeGPARecord(String yearSemester, String moduleName, double moduleMaxCredit, String gradeObtained) {
        sqlAccess.openConnection();
        Statement statement = sqlAccess.getStatement();
        try {
            statement.executeUpdate("INSERT INTO gpa VALUES('"+yearSemester+"', '"+moduleName+"', '"+moduleMaxCredit+"', '"+gradeObtained+"', '"+adminNo+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlAccess.closeConnection();
    }

    public void updateGPARecords(ArrayList<Module> moduleArrayList, String yearSemester) {
        // Process
        // 1st: Delete all rows that has the selected yearSemester
        // 2nd: Re-add all yearSemester modules

        sqlAccess.openConnection();
        Statement statement=sqlAccess.getStatement();

        try {
            statement.executeUpdate("DELETE FROM gpa WHERE yearSemester='"+yearSemester+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlAccess.closeConnection();
        System.out.println("Records Successfully Deleted!");

        for(Module m : moduleArrayList) {
            storeGPARecord(yearSemester, m.getModuleName(), m.getModuleMaxCredit(), m.getGradeObtained());
        }
        System.out.println("Successfully updated modules");
    }

    // Return an ArrayList of modules from a particular semester
    public ArrayList<Module> getModules(String yearSemester) {
        ArrayList<Module> moduleArrayList = new ArrayList<Module>();
        String sqlQuery = "SELECT * FROM gpa WHERE yearSemester='"+yearSemester+"' && adminNo='"+adminNo+"' ";

        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);

        try {
            while(rs.next()) {
                moduleArrayList.add(new Module(rs.getString("yearSemester"), rs.getString("moduleName"), rs.getDouble("moduleMaxCredit"), rs.getString("gradeObtained")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return moduleArrayList;
    }

    // Get list of taken yearly semesters
    public ArrayList<String> getYearSemestersList() {
        ArrayList<String> yearSemestersList = new ArrayList<>();
        String sqlQuery = "SELECT * FROM gpa WHERE adminNo='"+adminNo+"' ";
        sqlRetrieveData.openConnection();
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        try {
            while(rs.next()) {
                String yearSemester = rs.getString("yearSemester");
                if(!yearSemestersList.contains(yearSemester))
                    yearSemestersList.add(yearSemester);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetrieveData.closeConnection();
        return yearSemestersList;
    }

    public void deleteModule(String yearSemester, String moduleName, double moduleMaxCredit, String gradeObtained) {
        sqlAccess.openConnection();
        Statement statement=sqlAccess.getStatement();

        try {
            statement.executeUpdate("DELETE FROM gpa WHERE yearSemester='"+yearSemester+"' AND moduleName='"+moduleName+"' AND moduleMaxCredit='"+moduleMaxCredit+"' AND gradeObtained='"+gradeObtained+"' AND adminNo='"+adminNo+"' ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlAccess.closeConnection();
        System.out.println("Record Successfully Deleted!");
    }


    // GPA counters

    public double calculateTotalGPA() {
        ArrayList<Module> allModulesArrayList = new ArrayList<Module>();
        String sqlQuery = "SELECT * FROM gpa WHERE adminNo='"+adminNo+"' ";
        sqlRetrieveData.openConnection();
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        try {
            while(rs.next()) {
                allModulesArrayList.add(new Module(rs.getString("yearSemester"), rs.getString("moduleName"), rs.getDouble("moduleMaxCredit"), rs.getString("gradeObtained")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int numerator = 0, denominator = 0;

        for(Module m : allModulesArrayList) {
            numerator+=m.getModuleGradePoint() * m.getModuleMaxCredit();
            //System.out.println(numerator);
            denominator+=m.getModuleMaxCredit();
            //System.out.println(denominator);
        }

        sqlRetrieveData.closeConnection();
        if(denominator == 0.0)
            return 0.0;

        System.out.println((double)numerator/denominator);
        return (double)numerator/denominator;
    }

    public double calculateInterimGPA(String yearSemester) {
        ArrayList<Module> interimModulesArrayList = new ArrayList<Module>();
        String sqlQuery = "SELECT * FROM gpa WHERE adminNo='"+adminNo+"' && yearSemester='"+yearSemester+"' ";
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        try {
            while(rs.next()) {
                interimModulesArrayList.add(new Module(yearSemester, rs.getString("moduleName"), rs.getDouble("moduleMaxCredit"), rs.getString("gradeObtained")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        int numerator = 0, denominator = 0;

        for(Module m : interimModulesArrayList) {
            numerator+=m.getModuleGradePoint() * m.getModuleMaxCredit();
            //System.out.println(numerator);
            denominator+=m.getModuleMaxCredit();
            //System.out.println(denominator);
        }

        sqlRetrieveData.closeConnection();
        if(denominator == 0.0)
            return 0.0;

        System.out.println((double)numerator/denominator);
        return (double)numerator/denominator;
    }
}
