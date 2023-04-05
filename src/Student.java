import java.util.ArrayList;
import java.util.List;

public class Student {
    public String Name, Surname, Patronymic, Sex;
    public int Course;
    public double MidScore;
    boolean Stipendiya;
    public Student(String Surname, String Name,  String Patronymic, String Sex, int Course, double MidScore)
    {
        this.Name = Name; this.Surname = Surname; this.Patronymic = Patronymic;
        this.Sex = Sex; this.Course = Course; this.MidScore = MidScore;
    }
    public Student(){}
    public String getName (){return Name;}
    public String getSex(){return Sex;}
    public int getCourse(){return  Course;}
    public double getMidScore(){return MidScore;}

    @Override
    public String toString()
    {
       String mes = this.Surname + " " + this.Name + " " + this.Patronymic
                + ": Пол: " + this.Sex
                + ", Курс: " + this.Course
                + ", Средний балл: " + this.MidScore;
        if(this.Stipendiya) mes += ", Повышенная стипендия: ДА";
        else mes+= ", Повышенная стипендия: НЕТ";
        return mes;
    }


}
