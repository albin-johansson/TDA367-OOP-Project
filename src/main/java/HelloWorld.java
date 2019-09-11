import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HelloWorld extends Application {

  @Override
  public void start(Stage stage) {
    System.out.println("Starting JavaFX");

    stage.setScene(new Scene(new AnchorPane(), 800, 600));

    stage.setWidth(800);
    stage.setHeight(600);

    stage.show();
  }

  public static void main(String[] args) {
    System.out.println("HelloWorld");
    Application.launch();
  }

}