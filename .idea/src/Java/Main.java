import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Лабораторна робота №11 | Варіант 9 | Документообіг ===");
        
        DocumentRegistry registry = new DocumentRegistry();

        Employee timur = new Employee("EMP-01", "Тимур Ткаченко", "Team Lead");
        Employee ivan = new Employee("EMP-02", "Іван Іванов", "Project Manager");
        Employee olena = new Employee("EMP-03", "Олена Петрівна", "CTO");

        Document doc = new Document("DOC-2026", "Технічне завдання проекту", timur);
        doc.addApprovalStep(ivan);
        doc.addApprovalStep(olena);

        registry.addDocument(doc);
        System.out.println("\nДокумент успішно додано до реєстру.");

        System.out.println("\n--- Поточний реєстр документів (Обхід через entrySet()): ---");
        for (Map.Entry<String, Document> entry : registry.getAllDocuments().entrySet()) {
            System.out.println("Ключ (ID): " + entry.getKey() + " | Значення: " + entry.getValue());
        }

        System.out.println("\n--- Швидкий пошук за ID 'DOC-2026': ---");
        Document found = registry.findDocumentById("DOC-2026");
        System.out.println("Знайдено: " + found);

        System.out.println("\n--- Процес погодження документа (Робота з Queue): ---");
        System.out.println("Кількість кроків до погодження: " + found.getApprovalQueue().size());

        registry.processCurrentApproval("DOC-2026", "ТЗ перевірено, терміни влаштовують.");
        System.out.println("Після 1-го етапу залишилось кроків: " + found.getApprovalQueue().size());

        registry.processCurrentApproval("DOC-2026", "Погоджую архітектуру рішення.");
        System.out.println("Після 2-го етапу залишилось кроків: " + found.getApprovalQueue().size());

        System.out.println("\n--- Видалення документа з реєстру: ---");
        boolean deleted = registry.removeDocument("DOC-2026");
        System.out.println("Результат видалення: " + (deleted ? "Успішно" : "Помилка"));
        System.out.println("Загальна кількість документів в реєстрі: " + registry.getAllDocuments().size());
    }
}