import helper.GameRecord;
import helper.XMLHelper;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static io.restassured.RestAssured.given;

public class SteamTest {


    @Test
    public void getSteamGamesList(){
        //Set the API Key and Steam ID of user - user needs to be public to get info
        String API_KEY = "E31290FCA644B0BC72600B3F7E8953C4";
        String STEAM_ID = "76561197965637424";
        String gameCount = null;
        //Construct the URL for the REST Call
        String URL = "http://api.steampowered.com/IPlayerService/GetOwnedGames/v0001/?key=" + API_KEY + "&steamid=" + STEAM_ID + "&format=xml&include_appinfo=true";

        System.out.println("Calling Steam API");
        //Perform call
        ValidatableResponse response = given().when().get(URL).then().statusCode(200);
        //Save response as XML String
        String xmlResponse = response.extract().body().asString();
        //System.out.println(xmlResponse);
        //Convert XML String to XML Document type
        Document doc = XMLHelper.convertXMLToDocument(xmlResponse);
        NodeList c = doc.getElementsByTagName("game_count");
        gameCount = c.item(0).getTextContent();
        System.out.println("Game count: " + gameCount);
        //Create new output file
        System.out.println("Creating new file...");
        File output = new File("games.txt");
        //Create new ArrayList of GameRecords
        ArrayList<GameRecord> games = new ArrayList<GameRecord>();
        try {
            FileWriter writer = new FileWriter(output);
            //Get all nodes with the tag "message"
            NodeList nodeList = doc.getElementsByTagName("message");
            //Loop through Node list, print names and write to output text file
            for(int i = 0; i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if(node.getNodeType() == Node.ELEMENT_NODE){
                    NodeList attr = node.getChildNodes();
                    String appId = attr.item(1).getTextContent();
                    String name = attr.item(3).getTextContent();
                    String imgIconUrl = attr.item(7).getTextContent();
                    String imgLogoUrl = attr.item(9).getTextContent();
                    GameRecord game = new GameRecord(appId, name, imgIconUrl, imgLogoUrl);
                    //Add to games ArrayList
                    games.add(game);
                    for(int j = 0; j < attr.getLength(); j++){
                        Node childNode = attr.item(j);
                        if(childNode.getNodeName() == "name"){
                            //System.out.println(childNode.getTextContent());
                            writer.append(childNode.getTextContent() + "\n");
                        }
                    }

                }
            }
            //Close file writer
            writer.close();
        }
        catch(IOException e){
            System.err.println(e.getMessage());
        }

        //Write XML to file
        XMLHelper.writeXMLToFile(xmlResponse);

        //Pick random game
        Random rand = new Random();
        int randomNum = rand.nextInt(games.size());

        GameRecord randomGame = games.get(randomNum);

        System.out.println("Random game selected:");
        System.out.println("Name: " + randomGame.getGameName());
        System.out.println("AppID: " + randomGame.getAppId());
        System.out.println("Img Icon URL: " + randomGame.getImgIconUrl());
        System.out.println("Img Logo URL: " + randomGame.getImgLogoUrl());
        String message = "Random game selected: \nName: " + randomGame.getGameName() + "\nAppID: " + randomGame.getAppId() + "\n";
        String iconURL = "http://media.steampowered.com/steamcommunity/public/images/apps/" + randomGame.getAppId() + "/" + randomGame.getImgIconUrl() + ".jpg";
        String logoURL = "http://media.steampowered.com/steamcommunity/public/images/apps/" + randomGame.getAppId() + "/" + randomGame.getImgLogoUrl() + ".jpg";
        System.out.println("Icon URL: " + iconURL);
        System.out.println("Logo URL: " + logoURL);
        //JOptionPane.showMessageDialog(null, message);


    }
}
