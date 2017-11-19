package bomberman.entity.player;

import bomberman.Renderer;
import bomberman.animations.Direction;
import bomberman.constants.GlobalConstants;
import bomberman.animations.Sprite;
import bomberman.entity.Entity;
import bomberman.entity.MovingEntity;
import bomberman.entity.boundedbox.RectBoundedBox;
import java.util.Vector;

public class Player implements MovingEntity {

    private int positionX;
    private int positionY;
    private int health;
    private boolean isAlive;
    RectBoundedBox playerBoundary;
    
    Sprite currentSprite;

    Sprite moveRight;
    Sprite moveLeft;
    Sprite moveUp;
    Sprite moveDown;

    Vector<Sprite> spriteList = new Vector<Sprite>();

    Direction currentDirection;

    int x = 0;
    int y = 0;

    String name;

    public Player() {
        init();
        name = "Unnamed Entity";
        playerBoundary=new RectBoundedBox(positionX,positionY,GlobalConstants.playerWidth, GlobalConstants.playerHeight);
    }

    public Player(int posX, int posY) {
        this();
        health = 100;
        positionX = posX;
        positionY = posY;
        isAlive = true;
    }

    private void init() {
        x = GlobalConstants.playerX;
        y = GlobalConstants.playerY;

        Sprite moveDown = new Sprite(30, 0.1, 0, 0, 3, x, y, GlobalConstants.playerWidth, GlobalConstants.playerHeight, 2, false);
        Sprite moveLeft = new Sprite(30, 0.1, 30, 0, 3, x, y, GlobalConstants.playerWidth, GlobalConstants.playerHeight, 2, false);
        Sprite moveUp = new Sprite(30, 0.1, 60, 0, 3, x, y, GlobalConstants.playerWidth, GlobalConstants.playerHeight, 2, false);
        Sprite moveRight = new Sprite(30, 0.1, 90, 0, 3, x, y, GlobalConstants.playerWidth, GlobalConstants.playerHeight, 2, false);

        setMoveSprites(moveUp, moveDown, moveLeft, moveRight);

        currentSprite = moveDown;
    }

    // This might be better eventually as a state machine I imagine.
    // Because this is very quickly growing into a pain in the ass
    public void move(Direction direction) {
        switch (direction) {
            case UP:
                y--;
                setCurrentSprite(moveUp);
                currentDirection = Direction.UP;
                break;
            case DOWN:
                setCurrentSprite(moveDown);
                currentDirection = Direction.DOWN;
                y++;
                break;
            case LEFT:
                setCurrentSprite(moveLeft);
                currentDirection = Direction.LEFT;
                x--;
                break;
            case RIGHT:
                setCurrentSprite(moveRight);
                currentDirection = Direction.RIGHT;
                x++;
                break;
        }

        recalulatePositionForSprites();
    }

    private void recalulatePositionForSprites() {
        for (Sprite s : spriteList) {
            if (s != null) {
                s.setPosition(x, y);
            }
        }
    }

    private void setCurrentSprite(Sprite s) {
        if (s != null) {
            currentSprite = s;
        } else {
            System.out.println("Sprite missing!");
        }
    }

    public void setMoveSprites(Sprite moveUp, Sprite moveDown, Sprite moveLeft, Sprite moveRight) {
        if (moveUp != null) {
            this.moveUp = moveUp;
        }
        if (moveDown != null) {
            this.moveDown = moveDown;
        }
        if (moveLeft != null) {
            this.moveLeft = moveLeft;
        }
        if (moveRight != null) {
            this.moveRight = moveRight;
        }

        if (!spriteList.contains(moveUp)) {
            spriteList.add(moveUp);
        }
        if (!spriteList.contains(moveLeft)) {
            spriteList.add(moveLeft);
        }
        if (!spriteList.contains(moveDown)) {
            spriteList.add(moveDown);
        }
        if (!spriteList.contains(moveRight)) {
            spriteList.add(moveRight);
        }
    }

    public int getHealth() {
        return health;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public String toString() {
        return name;
    }

    @Override
    public boolean isColliding(Entity b) {
        RectBoundedBox otherEntityBoundary=(RectBoundedBox)b;
        return playerBoundary.checkCollision(otherEntityBoundary);
    }

    @Override
    public void draw() {
        if (currentSprite != null) {
            Renderer.playAnimation(currentSprite);
        }
    }

    @Override
    public void move(int steps, Direction direction) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void die() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reduceHealth(int damage) {
        if (health - damage <= 0) {
            this.die();
        } else {
            health -= damage;
        }

    }

    @Override
    public void removeFromScene() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
