
package mediator;

import mediator.model.Comment;
import mediator.model.Report;

import java.util.ArrayList;
import java.util.List;

public class Passenger extends Component {
    private final List<Report> reports = new ArrayList<>();
    private final List<Comment> comments = new ArrayList<>();
    private int likes = 0;
    private double rating = 5.0;

    public Passenger(String id, String name, String email) {
        super(id, name, email);
    }

    public void createReport(Driver driver, String reason) {
        Report report = new Report(this, driver, reason);
        reports.add(report);
        if (mediator != null) mediator.notify(this, "reportCreated");
    }

    public void createComment(String text) {
        comments.add(new Comment(text));
        if (mediator != null) mediator.notify(this, "commentCreated");
    }

    public void createLike(Driver driver) {
        likes++;
        if (mediator != null) mediator.notify(this, "likeCreated");
    }

    public void updateRating(double newAvg) { this.rating = newAvg; }

    @Override public String toString() {
        return super.toString()+"{rating="+rating+", likes="+likes+"}";
    }
}