package builder;

/**
 * @author:李罡毛
 * @date:2021/2/28 21:06
 */
public class Test {
    public static void main(String[] args) {
        Student student = new Student.StudentBuilder()
                .buildBasicInfo("287369876","李罡毛",30)
//                .buildClassId(1)
                .buildGreadId(3)
//                .buildIsMale(false)
                .buildLocation(new Location("石岩街道","204"))
                .buildP1("property1")
//                .buildP2("property2")
                .buildP3("property3")
                .buildP4("property4")
//                .buildP5("property5")
                .build();
        System.out.println(student.toString());

        Terrain terrain = new SimpleTerrainBuilder()
                .buildWall(new Wall(10,23,100,200))
                .buildWall(new Wall(150,23,50,200))
                .buildFort(new Fort(50,200,50,50))
                .buildMine(new Mine(10,300,10,10))
                .buildMine(new Mine(300,300,10,10))
                .build();
        terrain.getMines().stream().forEach(System.out::println);

    }
}
