package org.game.view;

import java.awt.*;

import javax.swing.JPanel;

import org.game.entity.Food;
import org.game.entity.Ground;
import org.game.entity.Snake;

/**
 * 操作界面
 *
 * @author yb
 *
 */
public class GamePanel extends JPanel {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private Snake snake;
    private Food food;
    private Ground ground;
    public void display(Snake snake, Food food, Ground ground) {
        System.out.println("面板正在显示.....");
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        repaint();//调repaint()做画的工作，相当于调用了paintComponent(Graphics g)
    }

    // 画的方法
    @Override
    protected void paintComponent(Graphics g) {
        //把上一次面板清空
        super.paintComponent(g);
        if (snake != null && food != null && ground != null) {
            snake.drawMe(g);
            food.drawMe(g);
            ground.drawMe(g);
        }
        g.setColor(new Color(11, 14, 13, 255));
        g.setFont(new Font("隶书",Font.BOLD,30));
        g.drawString("积分:"+snake.score,120,90);
    }
}

