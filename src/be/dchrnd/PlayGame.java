package be.dchrnd;

import be.dchrnd.Graphics.CaseG;
import be.dchrnd.Objects.Tuples;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PlayGame extends Application
{
    private GridPane grid;
    private CaseG[][] board;
    private Text time;
    private Text score;
    private Timer timer;
    private Stage stage;

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
        stage = primaryStage;


        grid.setOnMouseClicked(e->calculusClick(e.getX(),e.getY()));
        pane.setOnKeyPressed(keyEvent ->
        {
            if (keyEvent.getCode() == KeyCode.DOWN)
                Control.getInstance().debug();
        });
        timer = new Timer();timer.schedule(task,1000,1000);
    }

    private void calculusClick(double x, double y)
    {
        int x_ =(int)(x/(Control.WIDTH+Control.SPACEX));
        int y_ =(int)(y/(Control.HEIGHT+Control.SPACEY));

        if (isIn(x_,y_))
            analyzedClick(Control.getInstance().isClick(new Tuples(x_,y_)));

    }

    private void analyzedClick(ArrayList<Tuples> list)
    {
        if (list == null)
        {
            timer.purge();timer.cancel();
            try{
                new Main().start(new Stage());
                stage.close();
            } catch (Exception e){e.printStackTrace();}
        }
        else
        {
            for (Tuples tpl : list)
            {
                try{board[tpl.x][tpl.y].click();}
                catch (FileNotFoundException e){e.printStackTrace();}
            }
        }
    }

    private boolean isIn(int x, int y)
    {
        return x>=0 && x< Control.getInstance().getNumberCases() && y >= 0 && y < Control.getInstance().getNumberCases();
    }


    private void initBoard()
    {
        board = new CaseG[Control.getInstance().getNumberCases()][Control.getInstance().getNumberCases()];
        for (int i=0; i < Control.getInstance().getNumberCases(); i++)
        {
            for (int j=0; j < Control.getInstance().getNumberCases(); j++)
                board[j][i] = new CaseG(Control.getInstance().getCase(j,i));
        }
    }

    private void drawBoard()
    {
        grid.setHgap(Control.SPACEX);
        grid.setVgap(Control.SPACEY);
        for (int i=0; i < Control.getInstance().getNumberCases(); i++)
        {
            for (int j=0; j < Control.getInstance().getNumberCases(); j++)
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
