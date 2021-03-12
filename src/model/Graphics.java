package model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Graphics extends JPanel implements ActionListener {

    private Timer t = new Timer(100, this);
    public String state;

    private Snake snake;
    private Food food;
    private Game game;

    public Graphics (Game g) {
        t.start();
        state = "START";

        this.game = g;

        this.snake = game.getSnake();
        this.food = game.getFood();

        this.addKeyListener(game);
        this.setFocusable(true);
        this.setFocusTraversalKeysEnabled(false);

    }

    public void paintComponent (java.awt.Graphics game) {
        super.paintComponent(game);

        Graphics2D g2d = (Graphics2D) game;

        g2d.setColor(Color.black);
        g2d.fillRect(0,0,Game.width * Game.dimension + 5, Game.height * Game.dimension + 5);

        if (state == "START") {
            g2d.setColor(Color.white);
            g2d.drawString("Press Any Key", Game.width / 2 * Game.dimension - 40,
                    Game.height / 2 * Game.dimension - 20);
        } else if (state == "RUNNING") {
            // color for food
            g2d.setColor(Color.red);
            g2d.fillRect(food.getPosX() * Game.dimension, food.getPosY() * Game.dimension,
                    Game.dimension, Game.dimension);

            // score board
            g2d.setColor(Color.white);
            g2d.drawString("Score: " + this.game.getScore(), Game.width * Game.dimension - Game.dimension * 3,
                    Game.dimension);

            // color for snake
            g2d.setColor(Color.green);
//            for (int i = 0; i < snake.getBody().size(); i++) {
//                g2d.fillRect(snake.getBody().get(i).x * Game.dimension, snake.getBody().get(i).y * Game.dimension,
//                        Game.dimension, Game.dimension);
            for (Rectangle r : snake.getBody()) {
                g2d.fill(r);
            }
        } else {
            g2d.setColor(Color.white);
            g2d.drawString("Your Score is: " + this.game.getScore(), Game.width / 2 * Game.dimension - 40,
                    Game.height / 2 * Game.dimension - 20);
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        game.update();
    }
}
