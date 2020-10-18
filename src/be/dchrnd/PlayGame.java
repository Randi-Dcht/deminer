package be.dchrnd;

import be.dchrnd.Graphics.CaseG;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayGame extends Application
{
    private GridPane grid;
    private CaseG[][] board;
    private Text time;
    private Text score;
    private Timer timer;

    private int sec = 0;
    private int min = 0;

    @Override
    public void start(Stage primaryStage)
    {
        grid = new GridPane();
        BorderPane pane = new BorderPane();
        Scene scene = new Scene(pane,1200,1200);

        initBoard();
        drawBoard();

        HBox topBar = new HBox();
        topBar.setSpacing(50);
        score = new Text();score.setText("0");
        time  = new Text();time.setText(String.format("%d:%d",min,sec));
        topBar.getChildren().addAll(score,time);

        pane.setCenter(grid);
        pane.setTop(topBar);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Play to deminer");
        primaryStage.show();

        grid.setOnMouseClicked(e->calculusClick(e.getX(),e.getY()));
        timer = new Timer();timer.schedule(task,1000,1000);
    }

    private void calculusClick(double x, double y)
    {
        int x_ =(int)(x/(Control.WIDTH+Control.SPACEX));
        int y_ =(int)(y/(Control.HEIGHT+Control.SPACEY));

        if (isIn(x_,y_))
        {
            try {
                board[x_][y_].click();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean isIn(int x, int y)
    {
        return x>=0 && x< Control.size && y >= 0 && y < Control.size;
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

    TimerTask task = new TimerTask()
    {
        @Override
        public void run()
        {
            sec++;
            if (sec == 60)
            {
                sec =0;
                min++;
            }
            time.setText(String.format("%d:%d",min,sec));
        }
    };
}
