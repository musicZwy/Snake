package org.game.test;


import javax.swing.JFrame;

import org.game.control.Controller;
import org.game.entity.Food;
import org.game.entity.Ground;
import org.game.entity.Snake;
import org.game.util.Global;
import org.game.view.GamePanel;

public class SnakeGameTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        run();
    }
    public static void run(){
        //实体对象的创建
        Snake snake = new Snake();//蛇
        Food food = new Food();//食物
        Ground ground = new Ground();//障碍物

        //视图对象的创建
        GamePanel gamePanel = new GamePanel();//游戏操作界面
        //控制器的创建-->即是蛇的监听器也是键盘的监听器
        Controller c = new Controller(snake, food, ground, gamePanel);
        snake.addSnakeListener(c);
        //键盘监听事件，当键盘按下去c类将被其监听到，然后调用keyPressed(KeyEvent e)方法
        gamePanel.addKeyListener(c);
        //创建窗体
        JFrame frame = new JFrame("贪吃蛇version1.0");
        //width, height
        frame.setSize(Global.CELL_SIZE*Global.WIDTH+25, Global.CELL_SIZE*Global.HEIGHT+50);
        //关闭的时候自动退出
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //放在屏幕中间
        frame.setLocationRelativeTo(null);
        //让面板获得焦点，注：键盘事件要有效，面板必须获得焦点
        gamePanel.setFocusable(true);
        //添加面板
        frame.add(gamePanel);

        //启动游戏
        c.startGame();
        //显示窗体
        frame.setVisible(true);
    }

}

