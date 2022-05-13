package org.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.game.util.Global;

/**
 * 食物
 * @author yb
 *
 */
public class Food extends Point{
    public void drawMe(Graphics g){
        g.setColor(Color.red);
       // System.out.println("食物正在画出自己.....");
        g.fill3DRect(x* Global.CELL_SIZE, y*Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
    }
    /**
     * 蛇是否碰到食物
     * 只要判断蛇头的点是否和食物的位置重合
     * 要得到蛇头
     * @param snake
     * @return
     */
    public boolean isEatBySnake(Snake snake){
      //  System.out.println("判断蛇是否碰到了食物");
        Point head = snake.getHead();
        if(this.equals(head))
            return true;
        return false;
    }
    public void addFood(Point p){
        this.x = p.x;
        this.y = p.y;
    }
}

