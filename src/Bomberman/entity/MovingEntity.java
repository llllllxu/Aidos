/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.entity;

import bomberman.animations.Direction;

/**
 *
 * @author kdost
 */
public interface MovingEntity extends Entity {
    
    
    public void move(int steps, Direction direction);
    public void die();
    public void reduceHealth(int damage);
    public int getHealth();

}
