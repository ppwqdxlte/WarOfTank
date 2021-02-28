package builder;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:李罡毛
 * @date:2021/2/28 21:48
 * 地形
 */
public class Terrain implements Serializable {
    private static final long serialVersionUID = -7446595026326497276L;
    private List<Wall> walls = new ArrayList<>();
    private List<Fort> forts = new ArrayList<>();
    private List<Mine> mines = new ArrayList<>();

    public Terrain() {
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public List<Fort> getForts() {
        return forts;
    }

    public void setForts(List<Fort> forts) {
        this.forts = forts;
    }

    public List<Mine> getMines() {
        return mines;
    }

    public void setMines(List<Mine> mines) {
        this.mines = mines;
    }
}

/**
 * 墙体
 */
class Wall{
    int x,y,h,w;

    public Wall(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
}

/**
 * 碉堡，堡垒
 */
class Fort{
    int x,y,h,w;

    public Fort(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
}

/**
 * 地雷
 */
class Mine{
    int x,y,h,w;

    public Mine(int x, int y, int h, int w) {
        this.x = x;
        this.y = y;
        this.h = h;
        this.w = w;
    }
}

