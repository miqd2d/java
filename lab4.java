class Shapes{
    Float area;
    String shape;

    void setArea(Float rad){
        this.area = 3.14f * rad * rad;
        this.shape = "Circle";
        printArea();
    }

    void setArea(Integer side){
        this.area = (float)side * side;
        this.shape = "Square";
        printArea();
    }

    void setArea(Float side1 , Float side2){
        this.area = side1 * side2;
        this.shape = "Rectangle";
        printArea();
    }

    void printArea(){
        System.out.println("Area of "+this.shape+ " : "+ this.area);
    }
}



public class lab4 {
    public static void main(String args[]){
        Shapes s1 = new Shapes();
        s1.setArea(4f);

        Shapes s2 = new Shapes();
        s2.setArea(4f,5f);

        Shapes s3 = new Shapes();
        s3.setArea(4);

    }    
}
