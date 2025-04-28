import java.util.Scanner;

class Employee {
    String Emp_name;
    int Emp_id;
    String Address;
    String Mail_id;
    String Mobile_no;
    float BasicPay; 

    public Employee(String name, int id, String address, String mail, String mobile, float basicPay) {
        this.Emp_name = name;
        this.Emp_id = id;
        this.Address = address;
        this.Mail_id = mail;
        this.Mobile_no = mobile;
        this.BasicPay = basicPay;
    }

    public float calculateDA() {
        return 0.97f * BasicPay;
    }

    public float calculateHRA() {
        return 0.10f * BasicPay;
    }

    public float calculatePF() {
        return 0.12f * BasicPay;
    }

    public float calculateStaffClubFund() {
        return 0.001f * BasicPay;
    }

    public float calculateGrossSalary() {
        return BasicPay + calculateDA() + calculateHRA();
    }

    public float calculateNetSalary() {
        return calculateGrossSalary() - calculatePF() - calculateStaffClubFund();
    }

    public void generatePaySlip() {
        System.out.println("\n===== Pay Slip for " + Emp_name + " (ID: " + Emp_id + ") =====");
        System.out.println("Company: Google");
        System.out.println("Basic Pay: " + BasicPay);
        System.out.println("Dearness Allowance (97%): " + calculateDA());
        System.out.println("House Rent Allowance (10%): " + calculateHRA());
        System.out.println("Provident Fund (12%): " + calculatePF());
        System.out.println("Staff Club Fund (0.1%): " + calculateStaffClubFund());
        System.out.println("----------------------------------");
        System.out.println("Gross Salary: " + calculateGrossSalary());
        System.out.println("Net Salary: " + calculateNetSalary());
        System.out.println("----------------------------------");
    }
}

class Programmer extends Employee {
    String CodingLanguage;

    public Programmer(String name, int id, String address, String mail, String mobile, float basicPay, String codingLanguage) {
        super(name, id, address, mail, mobile, basicPay);
        this.CodingLanguage = codingLanguage;
    }
}

class TeamLead extends Employee {
    int teamSize;

    public TeamLead(String name, int id, String address, String mail, String mobile, float basicPay, int teamSize) {
        super(name, id, address, mail, mobile, basicPay);
        this.teamSize = teamSize;
    }

}

class AssistantProjectManager extends Employee {
    int projectsManaged;

    public AssistantProjectManager(String name, int id, String address, String mail, String mobile, float basicPay, int projectsManaged) {
        super(name, id, address, mail, mobile, basicPay);
        this.projectsManaged = projectsManaged;
    }

}

class ProjectManager extends Employee {
    int teamCount;
    int budgetManaged;

    public ProjectManager(String name, int id, String address, String mail, String mobile, float basicPay, int teamCount, int budgetManaged) {
        super(name, id, address, mail, mobile, basicPay);
        this.teamCount = teamCount;
        this.budgetManaged = budgetManaged;
    }

}

public class lab3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter details for Programmer:\n");
        System.out.print("Name: ");
        String pName = scanner.nextLine();
        System.out.print("ID: ");
        int pId = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Address: ");
        String pAddress = scanner.nextLine();
        System.out.print("Mail ID: ");
        String pMail = scanner.nextLine();
        System.out.print("Mobile No: ");
        String pMobile = scanner.nextLine();
        System.out.print("Basic Pay: ");
        float pBasicPay = scanner.nextFloat();
        scanner.nextLine(); // Consume newline
        System.out.print("Coding Language: ");
        String pLanguage = scanner.nextLine();

        Programmer programmer = new Programmer(pName, pId, pAddress, pMail, pMobile, pBasicPay, pLanguage);
        programmer.generatePaySlip();

        scanner.close();
    }
}