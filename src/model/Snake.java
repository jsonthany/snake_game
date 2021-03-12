package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Snake {

    // game dimensions
    private int h = Game.height;
    private int w = Game.width;
    private int d = Game.dimension;

    // snake properties
    private List<Rectangle> body;
    private String direction; // NONE, LEFT, RIGHT, UP, and DOWN

    public Snake() {

        // construct empty list for body
        body = new ArrayList<>();

        // construct a rectangle for body
        Rectangle temp = new Rectangle(d, d);

        // construct the first body at middle of screen
        temp.setLocation(w / 2 * d, h / 2 * d);
        body.add(temp);

        // snake starts off without any movement until prompted
        direction = "NONE";

    }

    // move in presence of eating food; add one rectangle to head
    public void moveWithFood() {

        if (direction != "NONE") {

            Rectangle head = body.get(0);
            Rectangle newHead = new Rectangle(d, d);

            if (direction.equals("LEFT")) {
                newHead.setLocation(head.x - d, head.y);
            } else if (direction.equals("RIGHT")) {
                newHead.setLocation(head.x + d, head.y);
            } else if (direction.equals("UP")) {
                newHead.setLocation(head.x, head.y - d);
            } else if (direction.equals("DOWN")) {
                newHead.setLocation(head.x, head.y + d);
            }

            body.add(0, newHead);
        }

    }

    // move in absence of eating food; remove tail, add one rectangle to head
    public void moveNoFood() {

        moveWithFood();
        body.remove(body.size() - 1);

    }

    // check to see if snake has died due to self collision
    public boolean isEatSelf() {

        // check whether head is at same location of any of its body parts
        for (int i = 1; i < body.size(); i++) {
            if ((getHeadPosX() == body.get(i).x) && (getHeadPosY() == body.get(i).y)) {
                return true;
            }
        }

        // if not, return false
        return false;

    }

    // get x position of head of snake
    public int getHeadPosX() {
        return body.get(0).x;
    }

    // get y position of head of snake
    public int getHeadPosY() {
        return body.get(0).y;
    }

    // set direction to LEFT
    public void setLeft() {
        this.direction = "LEFT";
    }

    // set direction to RIGHT
    public void setRight() {
        this.direction = "RIGHT";
    }

    // set direction to UP
    public void setUpward() {
        this.direction = "UP";
    }

    // set direction to DOWN
    public void setDownward() {
        this.direction = "DOWN";
    }

    // return body list
    public List<Rectangle> getBody() {
        return body;
    }

    // return current direction of snake
    public String getDirection() {
        return direction;
    }

}
