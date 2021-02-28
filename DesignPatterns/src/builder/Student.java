package builder;

import java.io.Serializable;

/**
 * @author:李罡毛
 * @date:2021/2/28 13:17
 */
public class Student implements Serializable {
    private static final long serialVersionUID = 787817891677779070L;
    private String ID;
    private String actualName;
    private int age;
    private int gradeId;
    private int classId;
    private boolean isMale;
    private Location location;
    private String property1;
    private String property2;
    private String property3;
    private String property4;
    private String property5;

    public Student() {
    }

    @Override
    public String toString() {
        return "Student{" +
                "ID='" + ID + '\'' +
                ", actualName='" + actualName + '\'' +
                ", age=" + age +
                ", gradeId=" + gradeId +
                ", classId=" + classId +
                ", isMale=" + isMale +
                ", location=" + location.toString() +
                ", property1='" + property1 + '\'' +
                ", property2='" + property2 + '\'' +
                ", property3='" + property3 + '\'' +
                ", property4='" + property4 + '\'' +
                ", property5='" + property5 + '\'' +
                '}';
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getActualName() {
        return actualName;
    }

    public void setActualName(String actualName) {
        this.actualName = actualName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getGradeId() {
        return gradeId;
    }

    public void setGradeId(int gradeId) {
        this.gradeId = gradeId;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }

    public String getProperty3() {
        return property3;
    }

    public void setProperty3(String property3) {
        this.property3 = property3;
    }

    public String getProperty4() {
        return property4;
    }

    public void setProperty4(String property4) {
        this.property4 = property4;
    }

    public String getProperty5() {
        return property5;
    }

    public void setProperty5(String property5) {
        this.property5 = property5;
    }

    public static class StudentBuilder{
        public StudentBuilder() {

        }
        private Student student = new Student();
        public StudentBuilder buildBasicInfo(String ID,String actualName,int age){
            student.actualName = actualName;
            student.ID = ID;
            student.age = age;
            return this;
        }
        public StudentBuilder buildClassId(int classId){
            student.classId = classId;
            return this;
        }
        public StudentBuilder buildGreadId(int greadId){
            student.gradeId = greadId;
            return this;
        }
        public StudentBuilder buildIsMale(boolean isMale){
            student.isMale = isMale;
            return this;
        }public StudentBuilder buildLocation(Location location){
            student.location = location;
            return this;
        }public StudentBuilder buildP1(String p1){
            student.property1 = p1;
            return this;
        }public StudentBuilder buildP2(String p2){
            student.property2 = p2;
            return this;
        }public StudentBuilder buildP3(String p3){
            student.property3 = p3;
            return this;
        }
        public StudentBuilder buildP4(String p4){
            student.property4 = p4;
            return this;
        }
        public StudentBuilder buildP5(String p5){
            student.property5 = p5;
            return this;
        }
        public Student build(){
            return student;
        }

    }

}
class Location{
    String street;
    String roomNo;

    @Override
    public String toString() {
        return "Location{" +
                "street='" + street + '\'' +
                ", roomNo='" + roomNo + '\'' +
                '}';
    }

    public Location(String street, String roomNo) {
        this.street = street;
        this.roomNo = roomNo;
    }
}