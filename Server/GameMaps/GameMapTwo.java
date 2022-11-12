package GameMaps;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

public class GameMapTwo extends GameMap {

    private Rectangle2D.Double west;
    private Rectangle2D.Double east;
    private Rectangle2D.Double north;
    private Rectangle2D.Double south;

    public GameMapTwo() {
        super(Color.gray);
        // Rectangle 2D constructor
        // starting x, starting y, rect width, rect height
        west = new Rectangle2D.Double(0, 0, 10, GAME_HEIGHT);
        east = new Rectangle2D.Double(GAME_WIDTH - 25, 0, 10, GAME_HEIGHT);
        north = new Rectangle2D.Double(0, 0, GAME_WIDTH, 10);
        south = new Rectangle2D.Double(0, GAME_HEIGHT - 100, GAME_WIDTH, 10);

        walls.add(west);
        walls.add(east);
        walls.add(north);
        walls.add(south);

        // Below are all perpendicular (maze) walls

        // Top
        walls.add(new Rectangle2D.Double(450, 0, 10, 200));
        walls.add(new Rectangle2D.Double(GAME_WIDTH-450, 0, 10, 200));

        // Sides
        walls.add(new Rectangle2D.Double(0, GAME_HEIGHT/2, 125, 10));
        walls.add(new Rectangle2D.Double(GAME_WIDTH-150, GAME_HEIGHT/2, 125, 10));
        
        //Bottom
        walls.add(new Rectangle2D.Double(450, GAME_HEIGHT-200, 10, 100));
        walls.add(new Rectangle2D.Double(GAME_WIDTH-450, GAME_HEIGHT-200, 10, 100));
    }
}