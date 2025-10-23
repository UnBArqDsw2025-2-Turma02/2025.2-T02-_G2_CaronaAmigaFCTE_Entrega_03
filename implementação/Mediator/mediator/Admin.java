package mediator;

import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Admin extends Component {
    private final List<Report> managedReports = new ArrayList<>();
    private final List<Component> suspendedUsers = new ArrayList<>();
    private final List<String> privileges = new ArrayList<>();

    public Admin(String id, String name, String email) {
        super(id, name, email);
        privileges.add("REPORT_MODERATION");
        privileges.add("USER_SUSPEND");
    }

    public void approveReport(Report report) {
        managedReports.add(report);
        if (mediator != null) mediator.notify(this, "reportApproved");
    }

    public void rejectReport(Report report) {
        managedReports.add(report);
        if (mediator != null) mediator.notify(this, "reportRejected");
    }

    public void suspendUser(Component user) {
        suspendedUsers.add(user);
        user.deactivate();
        if (mediator != null) mediator.notify(this, "userSuspended");
    }
}
