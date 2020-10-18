package be.dchrnd;

import be.dchrnd.Graphics.CaseG;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.io.FileNotFoundException;

public class PlayGame extends Application
{
    private GridPane grid;
    private CaseG[][] board;

    @Override
    public void start(Stage primaryStage)
    {
        grid = new GridPane();
        Scene scene = new Scene(grid,1200,1200);

        initBoard();
        drawBoard();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Play to deminer");
        primaryStage.show();

        grid.setOnMouseClicked(e->calculusClick(e.getX(),e.getY()));
    }

    private void calculusClick(double x, double y)
    {
        int x_ =(int)(x/(Control.WIDTH+Control.SPACEX));
        int y_ =(int)(y/(Control.HEIGHT+Control.SPACEY));

        try {
            board[x_][y_].click();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private void initBoard()
    {
        board = new CaseG[Control.size][Control.size];
        for (int i=0; i < Control.size; i++)
        {
            for (int j=0; j < Control.size; j++)
                board[j][i] = new CaseG(Control.getCase(j,i));
        }
    }

    private void drawBoard()
    {
        grid.setHgap(Control.SPACEX);
        grid.setVgap(Control.SPACEY);
        for (int i=0; i < Control.size; i++)
        {
            for (int j=0; j < Control.size; j++)
                grid.add(board[j][i].draw(),j,i);
        }
    }
}
