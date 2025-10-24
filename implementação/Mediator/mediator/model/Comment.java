package mediator.model;

import mediator.Component;

public class Comment {
    private final String text;
    public Comment(String text) { this.text = text; }
    public String getText() { return text; }
    @Override public String toString() { return "Comment{\"" + text + "\"}"; }
}
