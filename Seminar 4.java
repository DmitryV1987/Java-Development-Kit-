/*
Метод `findEmployeeById` возвращает `null`, если сотрудник с указанным табельным номером не найден. 
Можно изменить это поведение, если предполагается обрабатывать такие ситуации иначе (например, выдавать исключение).

В примере использования создается экземпляр справочника сотрудников, 
добавляются сотрудники и демонстрируется использование методов класса `EmployeeDirectory`.


*/

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// Класс, описывающий сотрудника
class Employee {
    private int id; // Табельный номер
    private String phoneNumber; // Номер телефона
    private String name; // Имя
    private int experience; // Стаж

    public Employee(int id, String phoneNumber, String name, int experience) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.experience = experience;
    }

    // Геттеры и сеттеры
    public int getId() {
        return id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getName() {
        return name;
    }

    public int getExperience() {
        return experience;
    }
}

// Класс, описывающий справочник сотрудников
class EmployeeDirectory {
    private List<Employee> employees = new ArrayList<>();

    // Метод для добавления нового сотрудника в справочник
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    // Метод для поиска сотрудника по стажу
    public List<Employee> findEmployeesByExperience(int experience) {
        return employees.stream()
                .filter(employee -> employee.getExperience() == experience)
                .collect(Collectors.toList());
    }

    // Метод для получения номера телефона сотрудника по имени
    public List<String> getPhoneNumbersByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .map(Employee::getPhoneNumber)
                .collect(Collectors.toList());
    }

    // Метод для поиска сотрудника по табельному номеру
    public Employee findEmployeeById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null); // Возвращает null, если сотрудник не найден
    }
}

// Пример использования
public class EmployeeDirectoryExample {
    public static void main(String[] args) {
        EmployeeDirectory directory = new EmployeeDirectory();
        
        // Добавление сотрудников в справочник
        directory.addEmployee(new Employee(1, "1234567890", "Иван", 5));
        directory.addEmployee(new Employee(2, "0987654321", "Мария", 3));
        directory.addEmployee(new Employee(3, "1122334455", "Иван", 10));

        // Поиск сотрудников по стажу
        List<Employee> employeesWith5YearsExperience = directory.findEmployeesByExperience(5);
        
        // Получение номера телефона сотрудника по имени
        List<String> phonesOfIvan = directory.getPhoneNumbersByName("Иван");
        
        // Поиск сотрудника по табельному номеру
        Employee employeeById = directory.findEmployeeById(1);
    }
}
