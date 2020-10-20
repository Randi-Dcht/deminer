package be.dchrnd;

import be.dchrnd.Enum.Level;
import be.dchrnd.Enum.Sizes;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;



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
        ChoiceBox<Level> listLevel = new ChoiceBox<Level>(FXCollections.observableArrayList(Level.values()));
        ToggleGroup groupR = new ToggleGroup();
        RadioButton auto = new RadioButton("Image normal");
        RadioButton user = new RadioButton("Image de mon prof préféré.");
        auto.setToggleGroup(groupR);user.setToggleGroup(groupR);
        VBox vb = new VBox();vb.setSpacing(10);vb.getChildren().addAll(listSize,listLevel,auto,user);
        root.setCenter(vb);

        Button start = new Button("START");
        root.setBottom(start);

        primaryStage.show();
        this.stage = primaryStage;

        groupR.selectedToggleProperty().addListener(new ChangeListener<Toggle>()
        {
            @Override
            public void changed(ObservableValue<? extends Toggle> observableValue, Toggle toggle, Toggle t1)
            {
                RadioButton select = (RadioButton) groupR.getSelectedToggle();
                if (select != null)
                {
                    if (!select.getText().equals("Image normal"))
                    {
                        vb.getChildren().removeAll(auto,user);
                        FileChooser chooseF = new FileChooser();
                        File file =  chooseF.showOpenDialog(stage);
                        if (file != null && isPicture(file.getName()))
                            Control.PATH = file.getPath();
                        else
                            vb.getChildren().addAll(auto,user);
                    }
                }
            }
        });

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

    private boolean isPicture(String pathName)
    {
        String[] list = pathName.split("\\.");
        if (list.length >= 2)
        {
            String ext = list[list.length-1];
            return ext.equalsIgnoreCase("png") || ext.equalsIgnoreCase("jpeg") || ext.equalsIgnoreCase("gif") || ext.equalsIgnoreCase("jpg");
        }
        return false;
    }


    public static void main(String[] args)
    {
        launch(args);
    }
}
