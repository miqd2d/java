import java.util.Scanner;

class Employee {

    String name;
    Integer id;
    Long phono;
    Float sal;
    Float net_sal;
    Float da;
    Float ta;
    String qual;
    String address;

    Scanner s = new Scanner(System.in);
    
    void setInfo(){
        System.out.println("=====Enter Details====");
        System.out.print("Enter Name : ");
        this.name = s.nextLine();

        System.out.print("Enter ID : ");
        this.id = s.nextInt();

        System.out.print("Enter Phone No. : ");
        this.phono = s.nextLong();
        s.nextLine();

        System.out.print("Enter Qaulification : ");
        this.qual = s.nextLine();

        System.out.print("Enter Address : ");
        this.address = s.nextLine();

        System.out.print("Enter Salary : ");
        this.sal = s.nextFloat();

        System.out.print("Enter Travel Allowance % : ");
        this.ta = s.nextFloat();

        System.out.print("Enter Dearness Allowance % : ");
        this.da = s.nextFloat();

        setSal(this.sal,this.ta,this.da);
    }

    void setSal(Float sal , Float ta , Float da){
        this.net_sal = sal + ((ta/100)*sal) + ((da/100)*sal);
    }

    void getInfo(){
        System.out.println("\n=====Employee Details====");
        System.out.println("Name : "+this.name );
        System.out.println("ID : "+this.id );
        System.out.println("Phone No. : "+this.phono );
        System.out.println("Qualification : "+this.qual );
        System.out.println("Address : "+this.address );
        System.out.println("Net Salary : "+this.net_sal );
    }

}

public class lab2 {
    public static void main (String args []){
        Scanner sc = new Scanner(System.in);
        
        Employee e1 = new Employee();
        e1.setInfo();
        e1.getInfo();

        sc.close();
    }    
}
