import mediator.model.Report;
import mediator.model.Comment;
import mediator.*;

public class Main {
    public static void main(String[] args) {
        // Mock de instâncias
        Passenger passenger = new Passenger("p-1", "Alice", "alice@demo.com");
        Driver driver = new Driver("d-1", "Bruno", "bruno@demo.com");
        Admin admin = new Admin("a-1", "Carla", "carla@demo.com");

        // Mediator e fiação
        ConcreteMediator mediator = new ConcreteMediator();
        mediator.wire(passenger, driver, admin);

        // Fluxo de exemplo
        System.out.println("\n=== Passenger cria um report contra o Driver ===");
        Report rep = new Report(passenger, driver, "Condução perigosa");
        mediator.processReport(rep);
        mediator.approveReport(rep); // Admin aprova via mediator → usuário alvo suspenso

        System.out.println("\n=== Driver avalia o Passenger ===");
        driver.ratePassenger(passenger, 4);

        System.out.println("\n=== Estado final ===");
        mediator.getReports().forEach(r -> System.out.println(" - " + r));
        mediator.getSuspendedUsers().forEach(u -> System.out.println(" - Suspenso: " + u));
    }
}
