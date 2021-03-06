package application;

/*
 * 
 * https://coderanch.com/t/645737/java/Add-tabs-dynamically-runtime
 * 
 * criação de tabs automáticas
 * 
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
 
import java.util.Random;
 
public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
 
    private int tabNum = 0;
    private static final Random random = new Random(42);
 
    @Override
    public void start(Stage stage) throws Exception {
    	
        TabPane tabPane = new TabPane();
        tabPane.setPrefSize(200, 150);
 
        VBox layout = new VBox(10, 
                createTabControls(tabPane), 
                tabPane
        );
        layout.setPadding(new Insets(10));
        VBox.setVgrow(tabPane, Priority.ALWAYS);
 
        final Scene scene = new Scene(layout);
        stage.setScene(scene);
        stage.show();
    }
 
    private Button createTabControls(TabPane tabPane) {
        Button addTab = new Button("New Tab");
        addTab.setOnAction(event -> {
            tabPane.getTabs().add(
                    createTab()
            );
            tabPane.getSelectionModel().selectLast();
        });
        addTab.setMinSize(
                addTab.USE_PREF_SIZE,
                addTab.USE_PREF_SIZE
        );
        return addTab;
    }
 
    private Tab createTab() {
        tabNum++;
        Tab tab = new Tab("Tab: " + tabNum);
 
        StackPane tabLayout = new StackPane();
        tabLayout.setStyle("-fx-background-color: " + randomRgbColorString());
        Label tabText = new Label("" + tabNum);
        tabText.setStyle("-fx-font-size: 40px;");
        tabLayout.getChildren().add(tabText);
 
        tab.setContent(tabLayout);	
 
        return tab;
    }
 
    private String randomRgbColorString() {
        return "rgb("
                    + random.nextInt(255) + ", "
                    + random.nextInt(255) + ", "
                    + random.nextInt(255) + 
                ");";
    }
}