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
        west = new Rectangle2D.Double(0, 0, 10, GAME_HEIGHT - 100);
        east = new Rectangle2D.Double(GAME_WIDTH - 25, 0, 10, GAME_HEIGHT - 100);
        north = new Rectangle2D.Double(0, 0, GAME_WIDTH, 10);
        south = new Rectangle2D.Double(0, GAME_HEIGHT - 100, GAME_WIDTH, 10);

        walls.add(west);
        walls.add(east);
        walls.add(north);
        walls.add(south);

        // Below are all perpendicular (maze) walls

        // Top
        Rectangle2D.Double obs1 = new Rectangle2D.Double(450, 0, 10, 200);
        Rectangle2D.Double obs2 = new Rectangle2D.Double(GAME_WIDTH - 450, 0, 10, 200);
        walls.add(obs1);
        walls.add(obs2);

        // Sides
        Rectangle2D.Double obs3 = new Rectangle2D.Double(0, GAME_HEIGHT / 2, 125, 10);
        Rectangle2D.Double obs4 = new Rectangle2D.Double(GAME_WIDTH - 150, GAME_HEIGHT / 2, 125, 10);
        walls.add(obs3);
        walls.add(obs4);

        // Bottom
        Rectangle2D.Double obs5 = new Rectangle2D.Double(450, GAME_HEIGHT - 200, 10, 100);
        Rectangle2D.Double obs6 = new Rectangle2D.Double(GAME_WIDTH - 450, GAME_HEIGHT - 200, 10, 100);
        walls.add(obs5);
        walls.add(obs6);
    }
}