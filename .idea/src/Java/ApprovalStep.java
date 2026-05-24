public class ApprovalStep {
    private final Employee approver;
    private boolean approved;
    private String comment;

    public ApprovalStep(Employee approver) {
        this.approver = approver;
        this.approved = false;
        this.comment = "Очікує розгляду";
    }

    public Employee getApprover() { return approver; }
    public boolean isApproved() { return approved; }
    
    public void approve(String comment) {
        this.approved = true;
        this.comment = comment;
    }

    @Override
    public String toString() {
        return String.format("[Етап] Погоджує: %s | Статус: %s | Коментар: %s", 
                approver.getName(), approved ? "ПОГОДЖЕНО" : "У ЧЕРЗІ", comment);
    }
}