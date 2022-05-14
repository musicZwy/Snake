package org.game.entity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.LinkedList;

import org.game.listener.SnakeListener;
import org.game.util.Global;

/**
 * 蛇
 * @author yb
 *
 */
public class Snake {
    private SnakeListener snakeListener;
    private boolean life = true;
    private LinkedList<Point> body = new LinkedList<Point>();
    public static final int UP = 1;
    public static final int DOWN = -1;
    public static final int LEFT = 3;
    public static final int RIGHT = -3;
    private int direction;//存储当前方向
    public int score;
    private int oldDirection,newDirection;
    private Point tail;//存储尾巴
    public Snake(){
        init();
    }
    private void init(){
        int x = Global.WIDTH/2;
        int y = Global.HEIGHT/2;
        for(int i=0;i<3;i++){
            body.add(new Point(x-i,y));
        }
//		this.direction = RIGHT;
        this.oldDirection = this.newDirection = RIGHT;
    }
    /**
     * 蛇移动的方法
     */
    public void move(){
        //去尾巴
        tail = body.removeLast();
        //加头-->得到当前的头部
        int x = body.getFirst().x;
        int y = body.getFirst().y;
        /*
         * 获得新的头部
         * 要确定方向，才能知道新的头部
         * 在初始化构造蛇身的时候默认的方向其实认为是向右的
         * 我们定义出所有的方向
         * 并完成changeDirection方法
         */
        //this.direction
        if(this.oldDirection+this.newDirection!=0)
            this.oldDirection = this.newDirection;
        switch (this.oldDirection) {
            case UP:
                y--;
                if(y<0) y=Global.HEIGHT-1;
                break;
            case DOWN:
                y++;
                if(y>=Global.HEIGHT)y=0;
                break;
            case LEFT:
                x--;
                if(x<0)x=Global.WIDTH-1;
                break;
            case RIGHT:
                x++;
                if(x>=Global.WIDTH)x=0;
                break;
        }
        body.addFirst(new Point(x,y));
       // System.out.println("蛇正在移动.....");
    }
    /**
     * 吃食物
     * 去掉的尾巴加回来即可
     * @param food
     */
    public void eatFood(Food food){
        body.addLast(tail);
        score+=10;
       // System.out.println("蛇正在吃食物.....");
    }
    /**
     * 改变方向
     */
    public void changeDirection(int direction){
//    	if(this.direction+direction!=0)
//    	this.direction = direction;
        this.newDirection = direction;
        //System.out.println("蛇正在改变方向.....");
    }
    public void drawMe(Graphics g){
        //System.out.println("蛇正在画出自己.....");
        g.setColor(Color.BLUE);
        for(Point p : body){
            //用预定的颜色填充一个突出显示的矩形
            g.fill3DRect(p.x * Global.CELL_SIZE, p.y
                            * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE,
                    true);
        }
    }
    /**
     * 是否吃到自己
     * @return
     */
    public boolean isEatSelf(){
        for(int i = 1;i < body.size();i++){
            if(body.get(i).equals(getHead())){
                return true;
            }
        }
        return false;
    }
    public void addSnakeListener(SnakeListener snakeListener){
        if(snakeListener!=null)
            this.snakeListener = snakeListener;
    }
    public void start(){
        new SnakeDriver().start();
    }
    /**
     * 获取蛇头
     * @return
     */
    public Point getHead(){
        return body.getFirst();
    }
    /**
     * 蛇的存活状态
     * @param life
     */
    public void setLife(boolean life) {
        this.life = life;
    }
    /**
     * 设置公有的get、set方法
     * @author lenovo
     *
     */
    public LinkedList<Point> getBody(){
        return body;
    }
    //内部类
    private class SnakeDriver extends Thread{
        @Override
        public void run() {
            // TODO Auto-generated method stub
            while(life){
                move();
                snakeListener.snakeMoved(Snake.this);
                if(score>50){
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else if(score>100){try {
                    Thread.sleep(30);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }}
                else{
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

}

