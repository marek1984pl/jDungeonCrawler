/**
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 10:14
 */

package pl.poligro.GameEngine;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class AssetManager {

    public static final String DATA_DIR = "data";
    public static final String GRAPHICS_DIR = "graph";
    public static final String GRAPHICS_ASSET_FILE = "graphics.txt";

    private static Asset<Image> graphicsAssets;

    private List<String> graphicsFileList;

    public AssetManager() {
        graphicsAssets = new Asset<>();
        graphicsFileList = new ArrayList<>();
    }

    public void loadAssetsData() {
        try {
            // todo change txt file to xml
            Path path = Paths.get(System.getProperty("user.dir"), DATA_DIR);

            BufferedReader br = new BufferedReader(new FileReader(path.resolve(GRAPHICS_ASSET_FILE).toString()));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                graphicsFileList.add(currentLine);
            }

            for (String fileLine : graphicsFileList) {
                String [] file = fileLine.split(";");
                BufferedImage newImage = ImageIO.read(path.resolve(GRAPHICS_DIR).resolve(file[0]).toUri().toURL());
                graphicsAssets.createNewAsset(file[1], newImage);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Asset<Image> getGraphicsAssets() {
        return graphicsAssets;
    }
}
