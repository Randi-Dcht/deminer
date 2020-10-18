package be.dchrnd;

import be.dchrnd.Enum.Level;
import be.dchrnd.Enum.Sizes;
import be.dchrnd.Enum.Types;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application
{
    private Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        BorderPane root = new BorderPane();

        primaryStage.setTitle("Main_Deminer");
        primaryStage.setScene(new Scene(root, 500, 500));

        Text title = new Text();
        title.setText("Main of Deminer");
        root.setTop(title);

        ChoiceBox<Sizes> listSize = new ChoiceBox<Sizes>(FXCollections.observableArrayList(Sizes.values()));
        ChoiceBox<Types> listType = new ChoiceBox<Types>(FXCollections.observableArrayList(Types.values()));
        ChoiceBox<Level> listLevel = new ChoiceBox<Level>(FXCollections.observableArrayList(Level.values()));
        VBox vb = new VBox();vb.setSpacing(10);vb.getChildren().addAll(listSize,listLevel,listType);
        root.setCenter(vb);

        Button start = new Button("START");
        root.setBottom(start);

        primaryStage.show();
        this.stage = primaryStage;


        start.setOnAction(e->
        {
            Sizes sz = listSize.getValue();
            Level lv = listLevel.getValue();
            if (sz != null && lv != null)
            {
                Control.initLogicBoard(sz.getSize(),sz.getSizeCase());
                Control.placeBomb(lv.getMax());//TODO
                new PlayGame().start(new Stage());
                stage.close();
            }
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
