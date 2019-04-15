/*
 * Copyright 2018 Marek Morawiec
 * User: marek
 * Date: 31.05.2018
 * Time: 10:14
 */

package pl.poligro.graphics_engine.assets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.poligro.graphics_engine.exceptions.AssetNotFoundException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;

public class AssetManager {

    public static final String DATA_DIR = "data";
    public static final String GRAPHICS_DIR = "graph";
    public static final String GRAPHICS_ASSET_FILE = "graphics.txt";
    public static final String STRING_RESOURCES_FILE = "strings.txt";

    private static Map<String, Asset> assets = new HashMap<>();
//    private static Map<String, Asset<GameUiString>> stringAssets = new HashMap<>();

    private static Logger log = LoggerFactory.getLogger(AssetManager.class.getName());

    public AssetManager() {
//        imageAsset = new Asset<>();
//        stringResources = new Asset<>();
    }

    public static Asset getAsset(String assetName) {
        if (assets.get(assetName) == null) {
            try {
                throw new AssetNotFoundException(assetName);
            } catch (AssetNotFoundException e) {
                log.debug(e.getMessage());
            }
        }
        return assets.get(assetName);
    }

//    public Collection<T> getAll() {
//        return assets.values();
//    }

    public void createNewAsset(String nameOfObject, Asset asset) {
        assets.put(nameOfObject, asset);
    }

    public void loadAsset(AssetType assetType) {

    }

    //todo load all asset types data
    public void loadImageAssets() {
        try {
            List<String> graphicsFileList = new ArrayList<>();
//             todo change txt file to xml
            Path path = Paths.get(System.getProperty("user.dir"), DATA_DIR);

            BufferedReader br = new BufferedReader(new FileReader(path.resolve(GRAPHICS_ASSET_FILE).toString()));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                graphicsFileList.add(currentLine);
            }

            for (String fileLine : graphicsFileList) {
                String [] file = fileLine.split(";");
                BufferedImage newImage = ImageIO.read(path.resolve(GRAPHICS_DIR).resolve(file[0]).toUri().toURL());
//                imageAssets.createNewAsset(file[1], newImage);
                Asset<Image> asset = new Asset<>(file[1], AssetType.IMAGE, "", newImage);
                assets.put(asset.getName(), asset);
            }


        } catch (IOException e) {
            log.error(e.getMessage());
        }

        log.info("Graphics assets loaded!");
//
//        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        try {
//            DocumentBuilder builder = factory.newDocumentBuilder();
//            Path path = Paths.getAsset(System.getProperty("user.dir"), DATA_DIR);
//
//            File file = path.resolve("graphics.xml").toFile();
//            Document doc = builder.parse(file);
//
//            Element root = doc.getDocumentElement();
//            NodeList children = root.getChildNodes();
//
//            List<Element> nodeList = new ArrayList<>();
//
//            for (int i = 0; i < children.getLength(); ++i) {
//                if (children.item(i) instanceof Element) {
//                    nodeList.add((Element) children.item(i));
//                }
//            }
//
//            String st = "xxx";
//
//        } catch (IOException | ParserConfigurationException | SAXException e) {
//            e.printStackTrace();
//        }
    }

    public void loadStringResources() {
        try {
            List<String> stringResourceList = new ArrayList<>();
            Path path = Paths.get(System.getProperty("user.dir"), DATA_DIR);

            BufferedReader br = new BufferedReader(new FileReader(path.resolve(STRING_RESOURCES_FILE).toString()));
            String currentLine;
            while ((currentLine = br.readLine()) != null) {
                stringResourceList.add(currentLine);
            }

            for (String fileLine : stringResourceList) {
                String [] file = fileLine.split(";");
                String string = file[1];
                Color color = Color.decode(file[2]);
                Integer posX = Integer.valueOf(file[3]);
                Integer posY = Integer.valueOf(file[4]);
                GameUiString newString = new GameUiString(file[0], string, color, posX,posY);
//                stringResources.createNewAsset(file[0], newString);
            }

        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

//    public static Image getGraphicsAsset(String assetName) {
//        todo change this
//        Image image = null;
//        try {
//            image = imageAssets.getAsset(assetName);
//        } catch (AssetNotFoundException e) {
//            log.error(e.getMessage());
//            System.exit(-1);
//        }
//        return image;
//    }


//    public static GameUiString getStringResource(String assetName) {
//        todo change this
//        GameUiString string = null;
//        try {
//            string = stringResources.getAsset(assetName);
//        } catch (AssetNotFoundException e) {
//            log.error(e.getMessage());
//            System.exit(-1);
//        }
//        return string;
//    }

//    public static Asset<GameUiString> getStringResources() {
//        return stringResources;
//    }
}
