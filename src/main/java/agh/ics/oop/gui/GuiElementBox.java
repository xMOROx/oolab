package agh.ics.oop.gui;

import agh.ics.oop.interfaces.IMapElement;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

public class GuiElementBox {

    private  VBox VBox;
    private Label label;
    private static Map<String, Image> images = new HashMap<>();
    public GuiElementBox(@NotNull IMapElement element) throws FileNotFoundException {
        try{
            ImageView imageView;
            if(images.containsKey(element.getResource()))
            {
                imageView = new ImageView(images.get(element.getResource()));
            } else {
                String filePath = new File("").getAbsolutePath();
                filePath = filePath.concat("\\src\\main\\resources\\");
                Image image = new Image(new FileInputStream(filePath.concat(element.getResource())));
                imageView = new ImageView(image);

                images.put(element.getResource(), image);
            }
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);

            label = new Label(element.getObjectLabel());

            this.VBox = new VBox(1);
            this.VBox.getChildren().addAll(imageView, label);
            this.VBox.setPadding(Insets.EMPTY);
            this.VBox.setAlignment(Pos.CENTER);

        } catch(FileNotFoundException error) {
            error.printStackTrace();
        }

    }

    public VBox getElement() {
        return this.VBox;
    }
}
