package be.dchrnd.Graphics;

import be.dchrnd.Control;
import be.dchrnd.Objects.Cases;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CaseG
{
    public Cases cases;
    private Rectangle rectangle;
    private Group pane;

    public CaseG(Cases cases)
    {
        this.cases = cases;
        pane = new Group();
    }

    public void click() throws FileNotFoundException
    {
        if (cases.isOccupied())
        {
            pane.getChildren().remove(rectangle);
            FileInputStream stream = new FileInputStream(Control.PATH);
            Image ig = new Image(stream);
            ImageView img = new ImageView();
            img.setImage(ig);
            img.setFitHeight(Control.HEIGHT);
            img.setFitWidth(Control.WIDTH);
            pane.getChildren().add(img);
        }
        else
            rectangle.setFill(Color.GRAY);
    }

    public Node draw()
    {
        pane.prefHeight(Control.HEIGHT);pane.prefWidth(Control.WIDTH);
        rectangle = new Rectangle();
        rectangle.setWidth(Control.WIDTH);rectangle.setHeight(Control.HEIGHT);
        rectangle.setFill(Color.BLACK);
        pane.getChildren().add(rectangle);
        return pane;
    }
}
