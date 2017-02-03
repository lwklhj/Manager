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
    private SqlStoreData sqlStoreData = new SqlStoreData();
    SqlDeleteData sqlDeleteData = new SqlDeleteData();
    private SqlRetrieveData sqlRetrieveData = new SqlRetrieveData();
    private String adminNo = new UserDA().getUser().getAdminNo();

    public void storeGPARecord(String yearSemester, String moduleName, double moduleMaxCredit, String gradeObtained) {
        SqlAccess sqlAccess = new SqlAccess();
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
        SqlAccess sqlAccess = new SqlAccess();
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
            storeGPARecord(yearSemester, m.getModuleName(), (int)m.getModuleMaxCredit(), m.getGradeObtained());
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
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        if(rs!=null) {
            try {
                while (rs.next()) {
                    String yearSemester = rs.getString("yearSemester");
                    if (!yearSemestersList.contains(yearSemester))
                        yearSemestersList.add(yearSemester);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            util.Util.prln("null");
        }
        return yearSemestersList;
    }

    public void deleteModule(String yearSemester, String moduleName, double moduleMaxCredit, String gradeObtained) {
        SqlAccess sqlAccess = new SqlAccess();
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

    public double calculateTotalGPA() {
        double totalGPA = 0.0;
        String sqlQuery = "SELECT * FROM gpa WHERE adminNo='"+adminNo+"' ";
        sqlRetrieveData.openConnection();
        ResultSet rs = sqlRetrieveData.retriveData(sqlQuery);
        try {
            while(rs.next()) {

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        sqlRetrieveData.openConnection();
        return totalGPA;
    }
}
