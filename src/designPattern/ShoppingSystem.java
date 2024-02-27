package designPattern;
import java.util.ArrayList;
import java.util.List;

// Observer Pattern
interface Observer {
    void update(String message);
}

// Müşteri sınıfı, gözlemci deseni uygulayan Observer arayüzünden türetilir.
class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + ": " + message);
    }
}

// Stok sınıfı, gözlemci deseni kullanarak müşterilere stok durumu hakkında bildirim gönderir.
class Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockStatus;

    // Müşteri eklemek için bir metot olusturuldu.
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Stok durumunu ayarlamak ve gözlemcilere bildirim göndermek için bir metot oluşturuldu.
    public void setStockStatus(String status) {
        this.stockStatus = status;
        notifyObservers();
    }

    // Gözlemcilere bildirim gönderen özel bir metot oluşturuldu.
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update("Stok durumu değişti: " + stockStatus);
        }
    }
}

// (Adapter Pattern)
// Ödeme işlemlerini bir arayüz üzerinden gerçekleştiren PaymentAdapter arayüzü oluşturuldu.
interface PaymentAdapter {
    void pay(int amount);
}

// Kredi kartı ödeme adaptörü, PaymentAdapter arayüzünden türetilir ve gerekli ödeme metotunu implement eder.
class CreditCardPaymentAdapter implements PaymentAdapter {
    @Override
    public void pay(int amount) {
        // Kredi kartı ödeme mantığı.
        //System.out.println(amount + " lira kredi kartıyla ödendi");
    }
}

// (Factory Pattern)
// Ürün sınıfı, farklı türdeki ürünleri temsil eder ve türetilen sınıfları içerir.
abstract class Product {
    abstract void display();
}

// Laptop sınıfı, Product sınıfından türetilir ve display metodu implement edilir.
class Laptop extends Product {
    @Override
    void display() {
        System.out.println("Laptop üretildi. ");
    }
}

// Smartphone sınıfı, Product sınıfından türetilir ve display metodu implement edilir.
class Smartphone extends Product {
    @Override
    void display() {
        System.out.println("Akıllı Telefon üretildi. ");
    }
}

// Ürün fabrikası, farklı ürünleri yaratmak için kullanılır.
class ProductFactory {
    Product createProduct(String type) {
        if ("Laptop".equalsIgnoreCase(type)) {
            return new Laptop();
        } else if ("Smartphone".equalsIgnoreCase(type)) {
            return new Smartphone();
        }
        return null;
    }
}

//  (Strategy Pattern)
// Ödeme stratejisi arayüzü, farklı ödeme stratejilerini temsil eder.
interface PaymentStrategy {
    void pay(int amount);
}

// Kredi kartı ödeme stratejisi, PaymentStrategy arayüzünden türetilir ve gerekli ödeme metodu implement eder.
class CreditCardPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Kredi kartı ile " + amount +" lira ödendi." );
    }
}

// Banka transferi ödeme stratejisi, PaymentStrategy arayüzünden türetilir ve gerekli ödeme metodu implement eder.
class BankTransferPayment implements PaymentStrategy {
    @Override
    public void pay(int amount) {
        System.out.println("Havale ile " + amount + " lira ödendi.");
    }
}

// Ana alışveriş sistemi sınıfı oluşturuldu.
public class ShoppingSystem {
    public static void main(String[] args) {
        //(Observer Pattern)
        Customer customer1 = new Customer("Ali");
        Customer customer2 = new Customer("Bora");

        Stock stock = new Stock();
        stock.addObserver(customer1);
        stock.addObserver(customer2);

        stock.setStockStatus("Stok var.");

        //(Adapter Pattern)
        PaymentAdapter creditCardPayment = new CreditCardPaymentAdapter();
        creditCardPayment.pay(100);

        //(Factory Pattern)
        ProductFactory productFactory = new ProductFactory();
        Product laptop = productFactory.createProduct("Laptop");
        Product smartphone = productFactory.createProduct("Smartphone");

        laptop.display();
        smartphone.display();

        //(Strategy Pattern)
        PaymentStrategy creditCardStrategy = new CreditCardPayment();
        PaymentStrategy bankTransferStrategy = new BankTransferPayment();

        creditCardStrategy.pay(200);
        bankTransferStrategy.pay(150);
    }
}
