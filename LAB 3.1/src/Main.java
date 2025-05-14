import java.util.*;

class Product {
    protected String name;
    protected double price;
    protected String category;

    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public double applyDiscount() {
        return price;
    }
}

class Electronics extends Product {
    public Electronics(String name, double price) {
        super(name, price, "Electronics");
    }

    @Override
    public double applyDiscount() {
        return price * 0.9;
    }
}

class Clothing extends Product {
    public Clothing(String name, double price) {
        super(name, price, "Clothing");
    }

    @Override
    public double applyDiscount() {
        return price * 0.8;
    }
}

class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
    }

    public void removeProduct(Product product) {
        items.remove(product);
    }

    public double totalPrice() {
        return items.stream().mapToDouble(Product::applyDiscount).sum();
    }
}

class Student {
    protected String name;
    protected Map<String, Integer> grades = new HashMap<>();

    public Student(String name) {
        this.name = name;
    }

    public void addGrade(String subject, int grade) {
        grades.put(subject, grade);
    }

    public double averageGrade() {
        return grades.values().stream().mapToInt(Integer::intValue).average().orElse(0);
    }
}

class Undergraduate extends Student {
    private int year;

    public Undergraduate(String name, int year) {
        super(name);
        this.year = year;
    }
}

class Graduate extends Student {
    private String thesisTopic;

    public Graduate(String name, String thesisTopic) {
        super(name);
        this.thesisTopic = thesisTopic;
    }
}

class GradeBook {
    private List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public List<String> failingStudents() {
        List<String> failing = new ArrayList<>();
        for (Student student : students) {
            if (student.averageGrade() < 60) {
                failing.add(student.name);
            }
        }
        return failing;
    }
}

class Customer {
    private String name;
    private String email;
    private String phone;

    public Customer(String name, String email, String phone) {
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer) obj;
        return Objects.equals(email, customer.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}

class CustomerDatabase {
    private Set<Customer> customers = new HashSet<>();

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void removeCustomer(Customer customer) {
        customers.remove(customer);
    }

    public boolean hasCustomer(String email) {
        return customers.stream().anyMatch(c -> c.getEmail().equals(email));
    }
}

public class Main {
    public static void main(String[] args) {
        // Тестування ShoppingCart
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(new Electronics("Laptop", 1000));
        cart.addProduct(new Clothing("T-Shirt", 50));
        System.out.println("Total price: " + cart.totalPrice());

        // Тестування GradeBook
        GradeBook gradeBook = new GradeBook();
        Student student1 = new Undergraduate("Alice", 2);
        student1.addGrade("Math", 80);
        student1.addGrade("History", 50);
        gradeBook.addStudent(student1);
        System.out.println("Failing students: " + gradeBook.failingStudents());

        // Тестування CustomerDatabase
        CustomerDatabase database = new CustomerDatabase();
        Customer customer1 = new Customer("John Doe", "john@example.com", "123456789");
        database.addCustomer(customer1);
        System.out.println("Has customer? " + database.hasCustomer("john@example.com"));
    }
}
