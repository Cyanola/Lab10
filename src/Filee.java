import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Filee {

    public static int[][] ReadIntArray(String path)
            throws IOException {
        int[][] array = new int[0][0];
        Scanner scanner = new Scanner(new File(path));


        while (scanner.hasNextLine())
        {
            Scanner colReader = new Scanner(scanner.nextLine());
            int[] tempo = new int[0];
            while (colReader.hasNextInt())
            {
                tempo = Main.Add(tempo,colReader.nextInt());
            }
            array = Main.Add(array,tempo);
            colReader.close();
        }
        scanner.close();
        return array;
    }
    public static void writeStudentssToFile(Student[][] students, String fileName) {
        try {
            FileWriter writer = new FileWriter(fileName);
            for (int i = 0; i < students.length; i++) {
                for (int j = 0; j < students[i].length; j++) {
                    writer.write(students[i][j].Surname + " ");
                    writer.write(students[i][j].Name + " ");

                    writer.write(students[i][j].Patronymic + " ");
                    writer.write(students[i][j].Sex + " ");
                    writer.write(students[i][j].Course + " ");
                    writer.write(students[i][j].MidScore + "\n");
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static int WriteIntArray(String path, int[][] array) throws IOException {
        BufferedWriter outputWriter = null;
        outputWriter = new BufferedWriter(new FileWriter(path));
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++)
            {
                outputWriter.write(Integer.toString(array[i][j]));
                if (j != array[i].length-1) outputWriter.write(" ");
            }
            outputWriter.newLine();
        }
        outputWriter.flush();
        outputWriter.close();

        return 1;
    }
    public static List<Student> ReadStudents(String path)
            throws IOException {
        List<Student> list = new ArrayList<>();
        Scanner scanner = new Scanner(new File(path));
        while (scanner.hasNextLine())
        {

            Scanner colReader = new Scanner(scanner.nextLine());

            Student student = new Student();

            student.Surname = colReader.next();
            student.Name = colReader.next();
            student.Patronymic = colReader.next();
            student.Sex = colReader.next();
            student.Course = Integer.parseInt(colReader.next());
            student.MidScore = Double.parseDouble(colReader.next());
list.add(student);
            colReader.close();
        }
        scanner.close();
return list;
    }
}
