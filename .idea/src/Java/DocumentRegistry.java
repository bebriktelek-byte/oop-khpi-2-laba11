import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DocumentRegistry {
    private final Map<String, Document> documents;

    public DocumentRegistry() {
        this.documents = new HashMap<>();
    }

    public void addDocument(Document doc) {
        if (documents.containsKey(doc.getId())) {
            throw new IllegalArgumentException("Документ з ID " + doc.getId() + " вже існує!");
        }
        documents.put(doc.getId(), doc);
    }

    public Document findDocumentById(String id) {
        return documents.get(id);
    }

    public boolean removeDocument(String id) {
        return documents.remove(id) != null;
    }

    public boolean processCurrentApproval(String docId, String comment) {
        Document doc = documents.get(docId);
        if (doc == null) return false;

        Queue<ApprovalStep> queue = doc.getApprovalQueue();
        ApprovalStep currentStep = queue.peek();

        if (currentStep != null && !currentStep.isApproved()) {
            currentStep.approve(comment);
            queue.poll();
            return true;
        }
        return false;
    }

    public Map<String, Document> getAllDocuments() {
        return Collections.unmodifiableMap(documents);
    }
}