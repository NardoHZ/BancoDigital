/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ClassePrincipal;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author Anderson
 */
public class LoginCadastro extends Application {
    private static Stage stage;
    
    private double x;
    private double y;
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View/LoginCadastro.fxml"));
        
        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });
        
        root.setOnMouseDragged(e -> {
            stage.setX(e.getScreenX()-x);
            stage.setY(e.getScreenY()-y);
        });
        
        Scene scene = new Scene(root);
        
        stage.setTitle("Banco Digital");
        stage.setResizable(false);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
        
    public static Stage getStage() {
        return stage;
    }
    
    public static void setStage(Stage stage) {
        LoginCadastro.stage = stage;
    }    
}
