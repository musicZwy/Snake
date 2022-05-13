package org.game.listener;

import org.game.entity.Snake;
/**
 * 蛇的监听器
 * 主要监听蛇的移动
 * @author lenovo
 *
 */
public interface SnakeListener {
    /**
     * 该方法去监听蛇是否碰到了自己、食物、障碍物
     * @param snake
     */
     void snakeMoved(Snake snake);
}

