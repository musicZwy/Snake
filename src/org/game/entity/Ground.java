package org.game.entity;

import org.game.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * 障碍物
 * @author yb
 *
 */
public class Ground {
    private int[][] rocks = new int[Global.HEIGHT][Global.WIDTH];
    public Ground(){
        for(int y = 0; y < Global.HEIGHT;y++){
            for(int x = 0;x < Global.WIDTH;x++){
                if(y==0 || y==Global.HEIGHT-1)
                    rocks[y][x] = 1;
//			   if(x==0 || x==Global.WIDTH-1)
//				   rocks[y][x] = 1;
            }
        }
    }
    public boolean isEatBySnake(Snake snake){
       // System.out.println("判断蛇是否碰到了障碍物.....");
        Point head = snake.getHead();
        for(int y = 0; y < Global.HEIGHT;y++){
            for(int x = 0;x < Global.WIDTH;x++){
                if(rocks[y][x] == 1 && head.x == x && head.y == y)
                    return true;
            }
        }
        return false;
    }
    public void drawMe(Graphics g){
        //设置画笔颜色
        g.setColor(Color.BLACK);
      //  System.out.println("障碍物正在画出自己.....");
        for(int y = 0; y < Global.HEIGHT;y++){
            for(int x = 0;x < Global.WIDTH;x++){
                if(rocks[y][x] == 1){
                    g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
                            Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
    }
    /**
     * 设置食物点的位置
     * @return
     */
    public Point getPoint(Snake snake){
        int x,y;
        do{
            x = new Random().nextInt(Global.WIDTH);
            y = new Random().nextInt(Global.HEIGHT);
        }while(rocks[y][x] == 1 || isFoodSnake(x,y,snake));
        return new Point(x,y);
    }
    /**
     * 判断食物是否出现在蛇身上
     */
    public boolean isFoodSnake(int x,int y,Snake snake){
        System.out.println("执行了吗");
        for(Point p : snake.getBody()){
            if(p.x == x && p.y == y){
                return true;
            }
        }
        return false;
    }
}

