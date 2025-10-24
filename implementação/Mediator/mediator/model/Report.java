
package mediator.model;

import mediator.Component;

public class Report {
    private final Component author;
    private final Component target;
    private final String reason;
    private Status status = Status.PENDING;

    public enum Status { PENDING, APPROVED, REJECTED }

    public Report(Component author, Component target, String reason) {
        this.author = author; this.target = target; this.reason = reason;
    }

    public Component getAuthor() { return author; }
    public Component getTarget() { return target; }
    public String getReason() { return reason; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }

    @Override public String toString() {
        return "Report{author="+author+", target="+target+", reason='"+reason+"', status="+status+"}";
    }
}
