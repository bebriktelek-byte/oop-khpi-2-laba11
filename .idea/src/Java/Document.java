import java.util.ArrayDeque;
import java.util.Queue;

public class Document {
    private final String id;
    private final String title;
    private final Employee creator;
    private final Queue<ApprovalStep> approvalQueue;

    public Document(String id, String title, Employee creator) {
        this.id = id;
        this.title = title;
        this.creator = creator;
        this.approvalQueue = new ArrayDeque<>(); // Двобічна черга як заміна стандартній LinkedList
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public Employee getCreator() { return creator; }
    public Queue<ApprovalStep> getApprovalQueue() { return approvalQueue; }

    public void addApprovalStep(Employee employee) {
        approvalQueue.offer(new ApprovalStep(employee));
    }

    @Override
    public String toString() {
        return String.format("Документ №%s: \"%s\" (Автор: %s) | Етапів на погодження: %d", 
                id, title, creator.getName(), approvalQueue.size());
    }
}