import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Set;
import java.util.HashSet;
public class Form1 {
    public JFrame window;
    public JTable table, stable;
    public Form1(int x, int y, int width, int height, String title)
    {
        window = CreateUI(x,y,width,height,title);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setResizable(false);
    }
    public static JFrame CreateUI(int x, int y, int width, int height, String title)
    {
        JFrame window = new JFrame(title);
        window.setBounds(x,y,width,height);
        window.setMinimumSize(new Dimension(width,height));
        window.setLayout(null);
        window.setVisible(true);

        return window;
    }

    public void InputIntArrayWindow()
    {
        final JFileChooser fc = new JFileChooser();
        //Таблица
        DefaultTableModel model1 = new DefaultTableModel();
        model1.addColumn("");model1.addColumn("");model1.addColumn("");
        model1.addRow(new Object[]{"Высота","Ширина","Длина"});

        DefaultTableModel model2 = new DefaultTableModel();
        for(int i = 0; i < 6; i++)
        {
            model2.addColumn("");
        }

        model2.addRow(new Object[]{"Фамилия","Имя","Отчество","Пол","Курс","Средний балл"});

        table = new JTable(10,3);
        JTable table_title = new JTable(model1);

        table.setBounds(10,25,window.getWidth()-window.getX()*3-650,window.getHeight() - 140);
        table_title.setBounds(table.getX(),table.getY()-16,table.getWidth(),15);
        table_title.setDefaultEditor(Object.class,null);

        stable = new JTable(10,6);
        JTable stable_title = new JTable(model2);

        stable.setBounds(table.getX() + table.getWidth() + 10,table.getY(),window.getWidth()-(table.getX() + table.getWidth() + 35),table.getHeight());
        stable_title.setBounds(stable.getX(),stable.getY()-16,stable.getWidth(),15);
        stable_title.setDefaultEditor(Object.class,null);
        //Открыть 1
        JButton open_button = new JButton("Открыть");
        open_button.setBounds(10,table.getHeight()+table.getY() + 10,100,30);
        //Открыть 2
        JButton open_button2 = new JButton("Открыть");
        open_button2.setBounds(stable.getX(),table.getHeight()+table.getY() + 10,100,30);
        //Ввод количества студентов
        JTextField input = new JTextField();
        input.setText("5");
        input.setBounds(open_button2.getX(),open_button2.getY() + open_button2.getHeight() + 5,open_button2.getWidth(),open_button2.getHeight());
        ActionListener openAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path = file.getPath();
                }
                try {
                    WriteIntTable(Filee.ReadIntArray(path),table);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Данные из файла считаны!");
            }
        };
        ActionListener openAct2 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int returnVal = fc.showOpenDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path = file.getPath();
                }
                try {
                    WriteStudentsTable(Filee.ReadStudents(path),stable);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                System.out.println("Данные из файла считаны!");
            }
        };
        open_button.addActionListener(openAct);
        open_button2.addActionListener(openAct2);
        //Сохранить в файл
        JButton saveBox_button = new JButton("Сохранить");
        saveBox_button.setBounds(open_button.getX(), open_button.getY()+ open_button.getHeight() + 5, open_button.getWidth(), open_button.getHeight());
        ActionListener saveAct = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int[][] rd = ReadIntTable(table);

                int returnVal = fc.showSaveDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path = file.getPath();
                }
                try {
                    Filee.WriteIntArray(path,rd);

                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        saveBox_button.addActionListener(saveAct);

        JButton saveStudent_button = new JButton("Сохранить");
        saveStudent_button.setBounds(open_button2.getX()+110, open_button2.getY(), open_button2.getWidth(), open_button2.getHeight());
        ActionListener saveAct1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
              Student[][] rd = ReadTable(stable);

                int returnVal = fc.showSaveDialog(null);
                String path = null;

                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    path = file.getPath();
                }
                try {

                    Filee.writeStudentssToFile(rd,path);

                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        saveStudent_button.addActionListener(saveAct1);
        //Задание 2
        JButton task2 = new JButton("Задание 2");
        task2.setBounds(open_button.getX()+open_button.getWidth()+20,open_button.getY(),120,30);
        ActionListener actionListener1 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                var ff = Main.sortBoxes(Box.GetFromArray(ReadIntTable(table)));
                String res = "";
                for (int i = 0;i < ff.size();i++)
                {
                    int a = i+1;
                    res += ("Коробка № " +a +" с объемом " + ff.get(i) +"\n");
                }
                JOptionPane.showMessageDialog(null,res);
            }
        };
        task2.addActionListener(actionListener1);

        //Задание 31
        JButton task31 = new JButton("Задание 31");
        task31.setBounds(saveBox_button.getX()+saveBox_button.getWidth()+20,saveBox_button.getY(),120,30);

        ActionListener actionListener31 = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Student> studentList;
                int N;
                String resultt ="";
                try {

                    NumberFormat format = NumberFormat.getInstance(Locale.getDefault());
                    Number number = format.parse(input.getText());
                    N = number.intValue();
                    studentList = ReadStudentsTable(stable);

                    int CourseCount = 0;
                    Set set = new HashSet();
                    for(Student st:studentList) {
                        if(set.add(st.getCourse())) CourseCount++;
                    }
                     if (N < CourseCount)
                        {throw new Exception("Количество стипендий должно быть больше, либо равняться количеству курсов!");}


                        WriteStudentsTable(Main.SortStudents(studentList, N), stable);
                       resultt = "Повышенную стипендию получат: \n";

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                    return;
                }
                Main.SortStudents(studentList, N);

                for (int i = 0; i < studentList.size(); i++) {
                    resultt += (studentList.get(i).toString() + "\n");
                }
                JOptionPane.showMessageDialog(null, resultt);
            }
        };
        task31.addActionListener(actionListener31);

        window.add(table); window.add(table_title);
        window.add(stable); window.add(stable_title);
        window.add(open_button); window.add(open_button2);
        window.add(saveBox_button);
        window.add(task2);
        window.add(task31);
        window.add(input);
window.add(saveStudent_button);
        window.hide();
        window.show();
    }

    public static Student[][] ReadTable(JTable tablet) {
       Student[][] students = new Student[0][0];
        for (int i = 0; i < tablet.getRowCount(); i++) {
            if (tablet.getValueAt(i, 0) == null) continue;
            Student[] temp = new Student[0];

                Student tempo = new Student();
            if (tablet.getValueAt(i, 0) != null && tablet.getValueAt(i, 1) != null
            &&tablet.getValueAt(i, 2) != null &&tablet.getValueAt(i, 3) != null
            &&tablet.getValueAt(i, 4) != null &&tablet.getValueAt(i, 5) != null){
                tempo = new Student(tablet.getValueAt(i, 0).toString(),
                        tablet.getValueAt(i, 1).toString(),
                        tempo.Patronymic = tablet.getValueAt(i, 2).toString(),
                        tempo.Sex = tablet.getValueAt(i, 3).toString(),
                        (int) Integer.parseInt(tablet.getValueAt(i, 4).toString()),
                        (double) Double.parseDouble(tablet.getValueAt(i, 5).toString()));

            }
                temp = Main.Add(temp, tempo);


            students = Main.Add(students, temp);
                }
        return students;
    }
    public static int[][] ReadIntTable(JTable tablet) {
        int[][] nums = new int[0][0];
        for (int i = 0; i < tablet.getRowCount(); i++)
        {
            if(tablet.getValueAt(i,0)==null) continue;
            int[] t_arr = new int[0];
            for (int j = 0; j < tablet.getColumnCount(); j++) {
                int tmp = Integer.MIN_VALUE;
                if (tablet.getValueAt(i, j) != null) {
                    try {
                        tmp = (int)Integer.parseInt(tablet.getValueAt(i, j).toString());
                    } catch (Exception ex) {
                    }
                    if (tmp != Integer.MIN_VALUE) {
                        t_arr = Main.Add(t_arr, tmp);
                    }
                }
            }
            nums = Main.Add(nums,t_arr);
        }
        return nums;
    }
    public static DefaultTableModel WriteIntTable(int[][] array,JTable table) {
        DefaultTableModel model = new DefaultTableModel(array.length,array[0].length);
        table.setModel(model);
        for (int i = 0; i < array.length; i++)
        {
            for (int j = 0; j < array[i].length; j++) {
                table.setValueAt(array[i][j],i,j);
            }
        }
        return model;
    }
    public static DefaultTableModel WriteStudentsTable(List<Student> array, JTable table) {
        DefaultTableModel model = new DefaultTableModel(array.size(),6);
        table.setModel(model);
        for (int i = 0; i < array.size(); i++)
        {
            table.setValueAt(array.get(i).Surname,i,0);
            table.setValueAt(array.get(i).Name,i,1);
            table.setValueAt(array.get(i).Patronymic,i,2);
            table.setValueAt(array.get(i).Sex,i,3);
            table.setValueAt(array.get(i).Course,i,4);
            table.setValueAt(array.get(i).MidScore,i,5);

        }
        return model;
    }
    public static List<Student> ReadStudentsTable(JTable tablet) {
        List<Student > students = new ArrayList<Student>();
        for (int i = 0; i < tablet.getRowCount(); i++)
        {
            Student tempo = new Student();
            if(tablet.getValueAt(i,0)==null) continue;
            tempo.Surname=tablet.getValueAt(i, 0).toString();
            tempo.Name=tablet.getValueAt(i, 1).toString();
            tempo.Patronymic=tablet.getValueAt(i, 2).toString();
            tempo.Sex = tablet.getValueAt(i, 3).toString();
            tempo.Course=(int)Integer.parseInt(tablet.getValueAt(i, 4).toString());
            tempo.MidScore=(double)Double.parseDouble(tablet.getValueAt(i, 5).toString());

     students.add(tempo);
        }
        return students;
    }


}




