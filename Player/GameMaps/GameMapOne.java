package GameMaps;

import java.awt.Color;
import java.awt.geom.Rectangle2D;

/**
 * X - Tank Easy Game Map
 */
public class GameMapOne extends GameMap {

    private Rectangle2D.Double west;
    private Rectangle2D.Double east;
    private Rectangle2D.Double north;
    private Rectangle2D.Double south;

    public GameMapOne() {
        super(Color.gray);
        // Rectangle 2D constructor
        // starting x, starting y, rect width, rect height
        west = new Rectangle2D.Double(0, 0, 10, GAME_HEIGHT - 100);
        east = new Rectangle2D.Double(GAME_WIDTH - 25, 0, 10, GAME_HEIGHT - 100);
        north = new Rectangle2D.Double(0, 0, GAME_WIDTH, 10);
        south = new Rectangle2D.Double(0, GAME_HEIGHT - 100, GAME_WIDTH, 10);

        walls.add(west);
        walls.add(east);
        walls.add(north);
        walls.add(south);
    }
}