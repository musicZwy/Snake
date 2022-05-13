package org.game.control;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JOptionPane;

import org.game.entity.Food;
import org.game.entity.Ground;
import org.game.entity.Snake;
import org.game.listener.SnakeListener;
import org.game.view.GamePanel;
/**
 * 控制器类
 * @author yb
 *
 */
public class Controller extends KeyAdapter implements SnakeListener{
    private Snake snake;
    private Food food;
    private Ground ground;

    private GamePanel gamePanel;

    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        super();
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // TODO Auto-generated method stub
        // 返回这个事件中和键相关的整数键
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_UP:
                snake.changeDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.changeDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.changeDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.changeDirection(Snake.RIGHT);
                break;
        }
    }
    @Override
    public void snakeMoved(Snake snake) {
        System.out.println("判断蛇是否碰到身体、是否碰到食物、障碍物");
        if(food.isEatBySnake(snake)){
            snake.eatFood(food);
            //食物被吃掉了，就应该有新的食物产生
            food.addFood(ground.getPoint(snake));
        }
        if(ground.isEatBySnake(snake)|| snake.isEatSelf()){
            //吃到后弹出一个对话框
            snake.setLife(false);
            JOptionPane.showConfirmDialog(null, "GameOver");
            System.exit(0);
        }
        //显示身体、食物、障碍物
        gamePanel.display(snake, food, ground);
    }
    /**
     * 游戏开始
     */
    public void startGame(){
        snake.start();
        food.addFood(ground.getPoint(snake));
    }

}

