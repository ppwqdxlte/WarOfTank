package builder;

public interface TerrainBuilder {
    TerrainBuilder buildWall(Wall wall);
    TerrainBuilder buildFort(Fort fort);
    TerrainBuilder buildMine(Mine mine);
    Terrain build();
}
