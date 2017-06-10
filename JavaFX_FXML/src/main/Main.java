
package main;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application{

    public static void main(String[] args) {
      Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL fxmlURL = getClass().getClassLoader().getResource("view/add_package.fxml");
        VBox root = FXMLLoader.<VBox>load(fxmlURL);

        Scene scene = new Scene(root);
        scene.getStylesheets().add("style/styleCss.css");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
}
