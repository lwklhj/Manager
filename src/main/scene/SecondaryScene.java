package main.scene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Liu Woon Kit on 29/12/2016.
 */
public class SecondaryScene {
    private String TARGET_FXML;
    private String WINDOW_TITLE;
    private boolean MODALITY_BOOLEAN;

    public SecondaryScene(String TARGET_FXML, String WINDOW_TITLE, boolean MODALITY_BOOLEAN) {
        this.TARGET_FXML = TARGET_FXML;
        this.WINDOW_TITLE = WINDOW_TITLE;
        this.MODALITY_BOOLEAN = MODALITY_BOOLEAN;
        startStage();
    }

    public void startStage() {
        Stage secondaryStage = new Stage();
        Parent p = null;
        try {
            p = FXMLLoader.load(getClass().getResource(TARGET_FXML));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(p);
        secondaryStage.setScene(scene);

        if(WINDOW_TITLE != "null") {
            secondaryStage.setTitle(WINDOW_TITLE);
        }

        secondaryStage.getIcons().add(new Image("/image/icon.png"));

        if(MODALITY_BOOLEAN == true)
            secondaryStage.initModality(Modality.APPLICATION_MODAL);

        secondaryStage.setResizable(false);
        secondaryStage.sizeToScene();
        secondaryStage.show();
    }
}
