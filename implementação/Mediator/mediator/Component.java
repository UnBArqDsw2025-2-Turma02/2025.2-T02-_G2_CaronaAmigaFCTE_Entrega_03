package mediator;

public abstract class Component {
    protected Mediator mediator;
    protected String id;
    protected String name;
    protected String email;
    protected boolean active = true;

    public Component(String id, String name, String email) {
        this.id = id; this.name = name; this.email = email;
    }

    public void setMediator(Mediator mediator) { this.mediator = mediator; }

    public boolean isActive() { return active; }
    public void deactivate() { this.active = false; }

    public String getId() { return id; }
    public String getName() { return name; }

    // “Gancho” para ações genéricas
    public void action() { /* default no-op */ }

    @Override public String toString() {
        return getClass().getSimpleName()+"{id='"+id+"', name='"+name+"'}";
    }
}