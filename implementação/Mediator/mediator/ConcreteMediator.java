package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ConcreteMediator implements Mediator {

    private Passenger passenger;
    private Driver driver;
    private Admin admin;

    private final List<Report> reports = new ArrayList<>();
    private final List<Component> suspendedUsers = new ArrayList<>();

    public void wire(Passenger p, Driver d, Admin a) {
        this.passenger = p; this.driver = d; this.admin = a;
        p.setMediator(this); d.setMediator(this); a.setMediator(this);
    }

    @Override
    public void notify(Component sender, String event) {
        System.out.printf("[Mediator] sender=%s event=%s%n", sender, event);

        switch (event) {
            case "reportCreated" -> {
                // O último report do passageiro é adicionado à fila geral
                // (num sistema real, viria no payload; aqui simplificamos)
                // Apenas log para demo.
            }
            case "commentCreated" -> {
                // poderíamos notificar analytics ou similar
            }
            case "likeCreated" -> {
                // aumentar score do driver, por ex.
            }
            case "reportApproved" -> {
                // Se report aprovado, suspender usuário alvo (mock)
                Optional<Report> any = reports.stream().filter(r -> r.getStatus() == Report.Status.PENDING).findFirst();
                any.ifPresent(r -> {
                    r.setStatus(Report.Status.APPROVED);
                    admin.suspendUser(r.getTarget());
                });
            }
            case "reportRejected" -> {
                // marca como rejeitado (mock)
                reports.stream()
                        .filter(r -> r.getStatus() == Report.Status.PENDING)
                        .findFirst()
                        .ifPresent(r -> r.setStatus(Report.Status.REJECTED));
            }
            default -> {
                if (event.startsWith("passengerRated:")) {
                    int stars = Integer.parseInt(event.split(":")[1]);
                    // atualizar média do passageiro (mock simples)
                    passenger.updateRating(Math.max(1.0, Math.min(5.0, stars)));
                }
            }
        }
    }

    // Métodos utilitários de domínio
    public Report processReport(Report report) {
        reports.add(report);
        System.out.println("[Mediator] Report recebido: " + report);
        return report;
    }

    public void approveReport(Report report) {
        report.setStatus(Report.Status.APPROVED);
        admin.approveReport(report);
        suspendedUsers.add(report.getTarget());
    }

    public void rejectReport(Report report) {
        report.setStatus(Report.Status.REJECTED);
        admin.rejectReport(report);
    }

    public List<Report> getReports() { return reports; }
    public List<Component> getSuspendedUsers() { return suspendedUsers; }
}
