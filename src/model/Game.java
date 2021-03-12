package model;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Game implements KeyListener {

    private Snake snake;
    private Food food;
    private Graphics graphics;

    public int score;

    private JFrame window;

    public static final int width = 30;
    public static final int height = 30;
    public static final int dimension = 20;

    public Game() {

        window = new JFrame();

        snake = new Snake();
        food = new Food(snake);
        score = 0;

        graphics = new Graphics(this);

        window.add(graphics);

        window.setTitle("Snake in the Grass");
        window.setSize(width * dimension + 2, height * dimension + 4);
        window.setVisible(true);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void start() {
        graphics.state = "RUNNING";
    }

    public void update() {
        if (graphics.state == "RUNNING") {
            if (isFoodEaten()) {
                snake.moveWithFood();
                food.setFoodRandomLocation(snake);
                score++;
            } else if (isDead()) {
                graphics.state = "END";
            } else {
                snake.moveNoFood();
            }
        }
    }

    private boolean isDead() {
        return (snake.isEatSelf() || isHitWall());
    }

    private boolean isHitWall() {
        return ((snake.getHeadPosX() < 0) || (snake.getHeadPosY() < 0) ||
                (snake.getHeadPosX() >= width * dimension) || (snake.getHeadPosY() >= height * dimension));
    }

    private boolean isFoodEaten() {
        return ((snake.getHeadPosX() == food.getPosX() * dimension) &&
                (snake.getHeadPosY() == food.getPosY() * dimension));
    }

    public Snake getSnake() {
        return snake;
    }

    public Food getFood() {
        return food;
    }

    public int getScore() {
        return score;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // not used
    }

    // assign values to keys
    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (graphics.state == "RUNNING") {
            if ((keyCode == KeyEvent.VK_LEFT) && (snake.getDirection() != "RIGHT")) {
                snake.setLeft();
            } else if ((keyCode == KeyEvent.VK_RIGHT) && (snake.getDirection() != "LEFT")) {
                snake.setRight();
            } else if ((keyCode == KeyEvent.VK_UP) && (snake.getDirection() != "DOWN")) {
                snake.setUpward();
            } else if ((keyCode == KeyEvent.VK_DOWN) && (snake.getDirection() != "UP")) {
                snake.setDownward();
            }
        } else {
            this.start();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // not used
    }
}
