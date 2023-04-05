import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args) {

        Form1 ui = new Form1(5, 5, 900, 300, "");
        ui.InputIntArrayWindow();
    }
    static int[] Add(int[] arr, int element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    static int[][] Add(int[][] arr, int[] element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    static Student[][] Add(Student[][] arr, Student[] element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    static Student[] Add(Student[] arr, Student element) {
        final int N = arr.length;
        arr = Arrays.copyOf(arr, N + 1);
        arr[N] = element;
        return arr;
    }
    public static List<Integer> sortBoxes(List<Box> boxes) {
        Collections.sort(boxes);
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < boxes.size(); i++) {
            int[] arr = new int[boxes.size()];
            arr[i] = boxes.get(i).getVolume();
            result.add(arr[i]);
        }
        return result;
    }
    public static List<Student> SortStudents(List<Student> stud, int N) {

            Map<Integer, List<Student>> groupedStudents = new HashMap<>();
            for (Student student : stud) {
                groupedStudents.computeIfAbsent(student.Course, k -> new ArrayList<>()).add(student);
            }
            for (List<Student> courseStudents : groupedStudents.values()) {
                Collections.sort(courseStudents, (s1, s2) -> Double.compare(s2.MidScore, s1.MidScore));
            }

            // Раздаем стипендии лучшим студентам на каждом курсе
            int StipendiaCount = N;
            for (List<Student> courseStudents : groupedStudents.values()) {
                if (!courseStudents.isEmpty()) {
                    Student best = courseStudents.get(0);
                    best.Stipendiya = true;
                    StipendiaCount--;
                }
            }

            // Раздаем оставшиеся стипендии лучшим студентам вне зависимости от курса
            if (StipendiaCount > 0) {
                Collections.sort(stud, (s1, s2) -> Double.compare(s2.MidScore, s1.MidScore));
                for (Student student : stud) {
                    if (!student.Stipendiya) {
                        student.Stipendiya = true;
                        StipendiaCount--;
                    }
                    if (StipendiaCount <= 0) {
                        break;
                    }
                }
            }

            for (Student student : stud) {
                System.out.println(student.Surname +" " + student.Name +" "+ student.Patronymic +" " + student.Sex + " Курс " + student.Course + ", Средний балл "
                        + student.MidScore  + " - " + (student.Stipendiya ? "ДА" : "НЕТ"));
            }
            return stud;

    }

}