.

import java.util.Scanner;

public class Kartoteka {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> kartoteka = new List<>();

        while (true) {
            System.out.println("\nМеню:");
            System.out.println("1. Добавить студента");
            System.out.println("2. Удалить студента");
            System.out.println("3. Вывести всех студентов");
            System.out.println("4. Очистить картотеку");
            System.out.println("5. Проверить картотеку на пустоту");
            System.out.println("0. Выход");
            System.out.print("Введите номер операции: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Считываем символ новой строки

            switch (choice) {
                case 1:
                    Student student = new Student();
                    student.setAttr(scanner);
                    kartoteka.add(student);
                    break;
                case 2:
                    if (kartoteka.isEmpty()) {
                        System.out.println("Картотека пуста!");
                    } else {
                        System.out.println("Введите имя студента для удаления: ");
                        String nameToDelete = scanner.nextLine();
                        kartoteka.delete(nameToDelete);
                    }
                    break;
                case 3:
                    kartoteka.display();
                    break;
                case 4:
                    kartoteka.clear();
                    System.out.println("Картотека очищена.");
                    break;
                case 5:
                    if (kartoteka.isEmpty()) {
                        System.out.println("Картотека пуста.");
                    } else {
                        System.out.println("Картотека не пуста.");
                    }
                    break;
                case 0:
                    System.out.println("Выход из программы.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Неверный выбор операции.");
            }
        }
    }
}

class Student {
    String name;
    int age;

    public void setAttr(Scanner scanner) {
        System.out.print("Введите имя: ");
        name = scanner.nextLine();
        System.out.print("Введите возраст: ");
        age = scanner.nextInt();
    }

    public void display() {
        System.out.println("Имя: " + name + ", Возраст: " + age);
    }
}

class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

class List<T> {
    private Node<T> tail;

    public List() {
        this.tail = null;
    }

    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (tail == null) {
            tail = newNode;
            tail.next = tail; // Ссылка на себя для кольцевого списка
        } else {
            newNode.next = tail.next; // Вставляем перед головой
            tail.next = newNode;
            tail = newNode; // Обновляем tail
        }
    }

    public void delete(String name) {
        if (tail == null) {
            return;
        }
        if (tail.data instanceof Student && ((Student) tail.data).name.equals(name)) {
            if (tail.next == tail) { // Если единственный узел
                tail = null;
            } else {
                tail.next.prev = tail.prev; // Обходим удаляемый узел
                tail = tail.next;
            }
            return;
        }
        Node<T> current = tail.next;
        while (current != tail) {
            if (current.data instanceof Student && ((Student) current.data).name.equals(name)) {
                current.prev.next = current.next;
                current.next.prev = current.prev;
                if (current == tail) {
                    tail = current.prev;
                }
                return;
            }
            current = current.next;
        }
    }

    public void display() {
        if (tail == null) {
            System.out.println("Картотека пуста.");
            return;
        }
        Node<T> current = tail.next;
        while (current != tail) {
            if (current.data instanceof Student) {
                ((Student) current.data).display();
            }
            current = current.next;
        }
        if (current.data instanceof Student) { // Выводим последний узел
            ((Student) current.data).display();
        }
    }

    public void clear() {
        tail = null;
    }

    public boolean isEmpty() {
        return tail == null;
    }

}