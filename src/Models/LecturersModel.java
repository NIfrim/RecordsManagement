package Models;

import Core.Model;
import Models.Entities.Department;
import Models.Entities.Lecturer;

import java.io.File;
import java.util.*;

public class LecturersModel extends Model
{
    public LecturersModel()
    {
        this.lecturersList = new HashMap<>();
        this.departmentsList = new HashMap<>();

        findAll();
    }

    private void findAll()
    {
        ArrayList<File> lecturerFiles = new ArrayList<>(Arrays.asList(new File("src\\Files\\Lecturers").listFiles()));
        ArrayList<File> departmentFiles = new ArrayList<>(Arrays.asList(new File("src\\Files\\Departments").listFiles()));

        for (File file : lecturerFiles) {
            Lecturer lecturer = ((Lecturer) new Model().fromFile(file));
            addLecturer(lecturer);
        }

        for (File file : departmentFiles)
        {
            Department department = ((Department) new Model().fromFile(file));
            addDepartment(department);
        }
    }

    public File getFile(String id)
    {
        return new File("src\\Files\\Lecturers" + id + ".ser");
    }

    public void addLecturer(Lecturer lecturer)
    {
        lecturersList.put(lecturer.getId(), lecturer);
    }

    public void addDepartment(Department department)
    {
        departmentsList.put(department.getDepartmentName(), department);
    }

    public void removeLecturer(String id) { lecturersList.remove(id); }
}
