package Models;

import Core.Model;
import Models.Entities.Department;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DepartmentsModel extends Model
{
    public DepartmentsModel()
    {
        this.departmentsList = new HashMap<>();

        findAll();
    }

    private void findAll()
    {
        ArrayList<File> departmentFiles = new ArrayList<>(Arrays.asList(new File("src\\Files\\Departments").listFiles()));

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

    public void addDepartment(Department department)
    {
        departmentsList.put(department.getId(), department);
    }

    public void removeDepartment(String id) { departmentsList.remove(id); }
}
