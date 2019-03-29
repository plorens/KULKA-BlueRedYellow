package Data;

import Model.Game;
import Objects.*;
import Model.Vector2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Klasa służąca do ładowania poziomu z pliku konfiguracyjnego
 */
public class GameLoader {

    /**Funkcja parsująca dane z pliku konfiguracyjnego i tworząca na podstawie ich obiekt typu Game
     *
     * @param level Nazwa pliku konfiguracyjnego
     * @return Obiekt Game
     * @throws EndOfGameException
     */
    public Game loadLevel(String level) throws EndOfGameException
    {
        JSONParser parser= new JSONParser();
        try {
            JSONObject root= (JSONObject)parser.parse(new FileReader("config/"+level+".JSON"));
            Object obj = ((JSONObject)root.get("size")).get("w");
            Vector2 size=new Vector2((double)((JSONObject)root.get("size")).get("w"),(double)((JSONObject)root.get("size")).get("h"));
            Vector2 start=new Vector2((double)((JSONObject)root.get("start")).get("x"),(double)((JSONObject)root.get("start")).get("y"));
            Vector2 meta=new Vector2((double)((JSONObject)root.get("meta")).get("x"),(double)((JSONObject)root.get("meta")).get("y"));
            JSONArray obsticles= (JSONArray)root.get("obsticles");
            JSONArray plusgravityfields= (JSONArray)root.get("plusgravityfields");
            JSONArray minusgravityfields= (JSONArray)root.get("minusgravityfields");
            JSONArray increaseballfields= (JSONArray)root.get("increaseballfields");

            Game game= new Game(new Ball(1, 1, start),size, new Meta(2, meta), 10);

            for(Object object:obsticles)
            {
                JSONObject obsticle=(JSONObject)object;
                Vector2 position=new Vector2((double)obsticle.get("x"),(double)obsticle.get("y"));
                Vector2 obsticlesize=new Vector2((double)obsticle.get("w"),(double)obsticle.get("h"));
                game.addObsticle(new Ground(obsticlesize.x,obsticlesize.y,position));
            }

            for(Object object:plusgravityfields)
            {
                JSONObject field=(JSONObject)object;
                Vector2 position=new Vector2((double)field.get("x"),(double)field.get("y"));
                Vector2 fieldsize=new Vector2((double)field.get("w"),(double)field.get("h"));
                game.addField(new PlusGravityField(fieldsize.x,fieldsize.y,position));
            }

            for(Object object:minusgravityfields)
            {
                JSONObject field=(JSONObject)object;
                Vector2 position=new Vector2((double)field.get("x"),(double)field.get("y"));
                Vector2 fieldsize=new Vector2((double)field.get("w"),(double)field.get("h"));
                game.addField(new MinusGravityField(fieldsize.x,fieldsize.y,position));
            }

            for(Object object:increaseballfields)
            {
                JSONObject field=(JSONObject)object;
                Vector2 position=new Vector2((double)field.get("x"),(double)field.get("y"));
                Vector2 fieldsize=new Vector2((double)field.get("w"),(double)field.get("h"));
                game.addField(new IncreaseBallField(fieldsize.x,fieldsize.y,position));
            }

             return game;
        } catch (FileNotFoundException e) {
            throw new EndOfGameException();
        } catch (ParseException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}
