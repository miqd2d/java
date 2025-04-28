import java.util.*;

class Student{
    // Data
    String name;
    Integer age;

    private Integer prn;
    
    static String school = "Carmel School Kuwait";

    // Constructors
    Student(String name , Integer age){
        this.name = name;
        this.age = age;
    }

    // Methods
    public void setPRN(Integer prn){
        this.prn = prn;
    }
    public void getInfo(){
        System.out.println("=======Student Info========");
        System.out.println("School  : "+ school);
        System.out.println("Name : "+ this.name);
        System.out.println("Age  : "+ this.age);
        System.out.println("PRN: "+ this.prn);
    }

}

public class lab1 {
    public static void main(String args []){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name : ");
        String _name = sc.nextLine();

        System.out.print("Enter Age : " );
        Integer _age = sc.nextInt();

        Student s1 = new Student(_name,_age);
        s1.setPRN(1032221413);
        s1.getInfo();

        sc.close();
    }
}
