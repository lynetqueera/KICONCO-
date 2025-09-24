abstract class Person {
    // Attributes
    private String name;
    private int age;

    // Constructor to initialize a Person object
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Abstract method (Abstraction) - requires subclasses to implement it
    public abstract void work();

    // Concrete method
    public void greet() {
        System.out.println("Hello, my name is " + name + " and I am " + age + " years old.");
    }

    // Getter methods for encapsulation
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}

// Child class
class FarmWorker extends Person {
    private String employeeId;

    // Constructor for the FarmWorker class
    public FarmWorker(String name, int age, String employeeId) {

        super(name, age);
        this.employeeId = employeeId;
    }

    // Implementing the abstract method from the parent class
    @Override
    public void work() {
        System.out
                .println(getName() + " is performing general farm duties like feeding animals and maintaining crops.");
    }

    // Getter for the specific FarmWorker attribute
    public String getEmployeeId() {
        return employeeId;
    }
}

class Farmer extends Person {
    // Additional attribute for the Farmer class
    private String farmName;

    // Constructor for the Farmer class
    public Farmer(String name, int age, String farmName) {
        super(name, age);
        this.farmName = farmName;
    }

    // Implementing the abstract method from the parent class
    @Override
    public void work() {
        System.out.println(getName() + " is managing the " + farmName
                + " farm, making strategic decisions and overseeing operations.");
    }

    // Getter for the specific Farmer attribute
    public String getFarmName() {
        return farmName;
    }
}

public class FarmerSystem {
    public static void main(String[] args) {

        FarmWorker worker1 = new FarmWorker("Lynet", 26, "FW4567");

        Farmer farmer1 = new Farmer("Kiconco", 45, "Kabale green Farm");

        System.out.println("INTRODUCING FARMER");
        worker1.greet();
        worker1.work();
        System.out.println("Employee ID: " + worker1.getEmployeeId());
        System.out.println();

        // Demonstrate methods on the Farmer object
        System.out.println("INTRODUCING FARMER");
        farmer1.greet();
        farmer1.work();
        System.out.println("Farm Name: " + farmer1.getFarmName());
    }
}