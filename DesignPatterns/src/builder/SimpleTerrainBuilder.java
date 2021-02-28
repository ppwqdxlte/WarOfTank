package builder;

/**
 * @author:李罡毛
 * @date:2021/2/28 22:00
 */
public class SimpleTerrainBuilder implements TerrainBuilder{

    Terrain terrain = new Terrain();

    @Override
    public TerrainBuilder buildWall(Wall wall) {
        terrain.getWalls().add(wall);
        return this;
    }

    @Override
    public TerrainBuilder buildFort(Fort fort) {
        terrain.getForts().add(fort);
        return this;
    }

    @Override
    public TerrainBuilder buildMine(Mine mine) {
        terrain.getMines().add(mine);
        return this;
    }

    @Override
    public Terrain build() {
        return terrain;
    }
}
