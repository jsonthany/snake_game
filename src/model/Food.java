package model;

public class Food {

    // game dimensions
    private int h = Game.height;
    private int w = Game.width;

    // x and y position of the food
    private int xPos;
    private int yPos;

    // randomly generate the food location
    public Food(Snake player) {
        this.setFoodRandomLocation(player);
    }

    // sets food to random x and y coordinates that cannot be within snake's body
    public void setFoodRandomLocation(Snake player) {

        boolean isSameLocation = true;

        while (isSameLocation) {

            isSameLocation = false;

            // adjusted for screen size
            this.xPos = (int) (Math.random() * this.w - 1);
            this.yPos = (int) (Math.random() * this.h - 1);

            for (int i = 0; i < player.getBody().size(); i++) {
                if ((player.getBody().get(i).x == this.xPos) && (player.getBody().get(i).y == this.yPos)) {
                    isSameLocation = true;
                }
            }

        }

    }

    public int getPosX() {
        return xPos;
    }

    public int getPosY() {
        return yPos;
    }

}
