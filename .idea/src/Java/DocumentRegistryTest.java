import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DocumentRegistryTest {
    private DocumentRegistry registry;
    private Document document;
    private Employee creator;
    private Employee approver;

    @BeforeEach
    void setUp() {
        registry = new DocumentRegistry();
        creator = new Employee("1", "Тимур", "Розробник");
        approver = new Employee("2", "Олексій", "Директор");
        document = new Document("DOC-TEST", "Тестовий звіт", creator);
    }

    @Test
    void testAddAndFindDocument() {
        registry.addDocument(document);
        Document found = registry.findDocumentById("DOC-TEST");
        
        assertNotNull(found);
        assertEquals("Тестовий звіт", found.getTitle());
    }

    @Test
    void testAddDuplicateDocumentThrowsException() {
        registry.addDocument(document);
        assertThrows(IllegalArgumentException.class, () -> registry.addDocument(document));
    }

    @Test
    void testProcessApprovalQueue() {
        document.addApprovalStep(approver);
        registry.addDocument(document);

        assertEquals(1, registry.findDocumentById("DOC-TEST").getApprovalQueue().size());
        
        boolean processed = registry.processCurrentApproval("DOC-TEST", "Approved");
        
        assertTrue(processed);
        assertEquals(0, registry.findDocumentById("DOC-TEST").getApprovalQueue().size());
    }

    @Test
    void testRemoveDocument() {
        registry.addDocument(document);
        assertTrue(registry.removeDocument("DOC-TEST"));
        assertNull(registry.findDocumentById("DOC-TEST"));
    }
}