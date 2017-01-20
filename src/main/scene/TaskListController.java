package main.scene;

import database.SqlDeleteData;
import database.SqlRetrieveData;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import entity.Task;

/**
 * Created by lerai on 12/7/2016.
 */
public class TaskListController implements Initializable {

    private ResultSet rs;
    private ObservableList<Task> personalList = FXCollections.observableArrayList();

    @FXML
    private javafx.scene.control.ListView listContent;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //change the listview whenever peronal list has change
        personalList.addListener(new ListChangeListener<Task>() {
            @Override
            public void onChanged(Change<? extends Task> c) {

                ObservableList<String> displayTitle=FXCollections.observableArrayList();
                for(int i=0;i<personalList.size();i++){
                    displayTitle.add(personalList.get(i).getTitle());

                }
                listContent.setItems(displayTitle);
            }
        });
        //set mouse click event of list view,open context menu by location
        listContent.setOnMouseClicked(event -> showContectMenu(listContent.getSelectionModel().getSelectedIndex()));
        retriveData ();

    }

    @FXML
    void addTask(ActionEvent event) throws IOException{

        Parent root = FXMLLoader.load(getClass().getResource("AddTask.fxml"));
        Scene scene=new Scene(root);
        Stage stage=new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);//block all other window
        stage.setTitle("Add Task");
        stage.setScene(scene);
        stage.showAndWait();//wait add task window to close
        retriveData();

    }
    //should have othermethod that get last record use id
    private void retriveData (){
        personalList.clear();
        System.out.println(personalList.size());
        SqlRetrieveData data= new SqlRetrieveData();
        data.openConnection();

        rs=data.retriveWholeTable("task");
        data.closeConnection();
        //process data
        try {
            while(rs.next()){
                Task task = new Task();
                task.setTitle(rs.getString("title"));
                task.setLocation(rs.getString("location"));
                task.setPriority(rs.getString("priority"));
                task.setDueDate(rs.getDate("dueDate"));
                task.setDueTime(rs.getInt("dueTime"));

               personalList.add(task);


            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


       // listContent.setItems(personalList);
    }

    //display reight click menu
    private void showContectMenu(int index){
        System.out.println("show context");
        ContextMenu contextMenu=new ContextMenu();
        contextMenu.setOnShowing(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.out.println("showing");
            }
        });
        contextMenu.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }

        });
        //Label label=new Label("Menu");
        MenuItem delete=new MenuItem("delete");
        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Task task=personalList.get(index);
                SqlDeleteData sql=new SqlDeleteData();
                sql.openConnection();
                sql.deleteTableRow("task","title","=","\""+task.getTitle()+"\"");
                sql.closeConnection();
                retriveData();

            }
        });
        contextMenu.getItems().addAll(delete);
        listContent.setContextMenu(contextMenu);
    }



}
