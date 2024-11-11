package tasks1_10;

import java.time.LocalDate;
import java.util.ArrayList;

public class Task2 {
    static String selectedFaculty = "Философия", selectedGroup = "Д20-1";
    static int selectedYear = 2004, selectedCource = 2;

    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        students.add(new Student("Рурурарури", "Рурарарирараруруририрари", "Рирарарурурарарурурарарирари", "г. Москва, ул. Ленина, д. 10",
                "+7 (495) 123-45-67", "Д20-1", "Философия", LocalDate.of(2005, 5, 12), 101, 2));
        students.add(new Student("Нико", "Ридору", "Андреевна", "г. Санкт-Петербург, ул. Невская, д. 25",
                "+7 (812) 987-65-43", "Д20-1", "Физика", LocalDate.of(2004, 9, 24), 102, 3));
        students.add(new Student("Маэбара", "Кэйити", "", "г. Казань, ул. Победы, д. 8",
                "+7 (843) 555-12-34", "Д20-2к", "Философия", LocalDate.of(1968, 12, 5), 103, 2));
        students.add(new Student("Андрос ", "Ник", "", "г. Новосибирск, ул. Ломоносова, д. 15",
                "+7 (383) 777-88-99", "Д20-2к", "Философия", LocalDate.of(2005, 7, 30), 104, 4));

        System.out.println("список студентов заданного факультета (" + selectedFaculty + "):" +
                students.stream().filter(student -> student.getFaculty().equalsIgnoreCase(selectedFaculty) && student.getCourse() == selectedCource).toList());

        System.out.println("\nсписки студентов для каждого факультета и курса (" + selectedFaculty + " | " + selectedCource + " ):" +
                students.stream().filter(student -> student.getFaculty().equalsIgnoreCase(selectedFaculty) && student.getCourse() == selectedCource).toList());

        System.out.println("\nсписок студентов, родившихся после " + selectedYear + " года:" +
                students.stream().filter(student -> student.getBirthDate().getYear() > selectedYear).toList());

        System.out.println("\nсписок учебной группы " +
                students.stream().filter(student -> student.getGroup().equalsIgnoreCase(selectedGroup)).toList());
    }

    static class Student {
        String surname, name, middleName, address, phone, group, faculty;
        LocalDate birthDate;
        int id, course;

        public Student(String surname, String name, String middleName, String address, String phone, String group, String faculty, LocalDate birthDate, int id, int course) {
            this.surname = surname;
            this.name = name;
            this.middleName = middleName;
            this.address = address;
            this.phone = phone;
            this.group = group;
            this.faculty = faculty;
            this.birthDate = birthDate;
            this.id = id;
            this.course = course;
        }

        public Student(String surname, String name, String middleName, String group, String faculty, LocalDate birthDate, int id, int course) {
            this(surname, name, "", "", "", group, faculty, birthDate, id, course);
        }

        public String getSurname() {
            return surname;
        }
        public void setSurname(String surname) {
            this.surname = surname;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getMiddleName() {
            return middleName;
        }
        public void setMiddleName(String middleName) {
            this.middleName = middleName;
        }
        public String getAddress() {
            return address;
        }
        public void setAddress(String address) {
            this.address = address;
        }
        public String getPhone() {
            return phone;
        }
        public void setPhone(String phone) {
            this.phone = phone;
        }
        public String getGroup() {
            return group;
        }
        public void setGroup(String group) {
            this.group = group;
        }
        public String getFaculty() {
            return faculty;
        }
        public void setFaculty(String faculty) {
            this.faculty = faculty;
        }
        public LocalDate getBirthDate() {
            return birthDate;
        }
        public void setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
        }
        public int getId() {
            return id;
        }
        public void setId(int id) {
            this.id = id;
        }
        public int getCourse() {
            return course;
        }
        public void setCourse(int course) {
            this.course = course;
        }

        public String toString() {
            return "\nСтудент: " + id +
                    "\nФИО: " + surname + " " + name + " " + middleName +
                    "\nДата рождения: " + birthDate +
                    "\nАдрес: " + address +
                    "\nНомер телефона: " + phone +
                    "\nФакультет" + faculty +
                    "\nГруппа: " + group +
                    "\nКурс: " + course;
        }
    }
}
