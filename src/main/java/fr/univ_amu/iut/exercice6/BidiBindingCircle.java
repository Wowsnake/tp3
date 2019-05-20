package fr.univ_amu.iut.exercice6;


import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.converter.NumberStringConverter;

public class BidiBindingCircle extends Application {

    private Circle c = new Circle();
    private Slider slider = new Slider();
    private TextField textField = new TextField();

    private Pane pane = new Pane();
    private BorderPane root = new BorderPane();
    private Scene scene = new Scene(root);

    @Override
    public void start(Stage stage) throws Exception {
        addPane();
        addSlider();
        addTextField();
        createBindings();
        configStage(stage);
    }

    private void addTextField() {
        textField.setTextFormatter(new TextFormatter<String>(change -> {
            change.setText(change.getText().replace(',', '.'));
            String content = change.getControlNewText();
            if (content.isEmpty() || Double.parseDouble(content) > 250)
                change.setText("");
            return change;
        }));

        root.setBottom(textField);
    }

    private void createBindings() {
        c.centerXProperty().bind(pane.widthProperty().divide(2));
        c.centerYProperty().bind(pane.widthProperty().divide(2));
        Bindings.bindBidirectional(c.radiusProperty(), slider.valueProperty());
        c.setRadius(150);
        Bindings.bindBidirectional(textField.textProperty(), c.radiusProperty(), new NumberStringConverter());
        //throw new RuntimeException("Not yet implemented !");
    }

    private void configStage(Stage stage) {
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setTitle("BidiBindingCircle");
        stage.show();
    }

    private void addSlider() {
        pane.getChildren().add(c);
        slider.setMax(250);
        slider.setMin(0);
        BorderPane.setMargin(slider, new Insets(20, 5, 5, 5));
        //throw new RuntimeException("Not yet implemented !");
    }

    private void addPane() {
        pane.getChildren().add(slider);
        pane.setPrefWidth(500);
        pane.setPrefHeight(500);
        root.setCenter(pane);
    }
}
