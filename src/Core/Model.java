package Core;

import Models.Entities.Department;
import Models.Entities.Lecturer;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

public class Model {

    protected HashMap<String, Department> departmentsList;
    protected HashMap<String, Lecturer> lecturersList;


    public HashMap<String, Department> getDepartmentsList()
    {
        return departmentsList;
    }

    public HashMap<String, Lecturer> getLecturersList()
    {
        return lecturersList;
    }

    public void removeRelatedFiles(String path, ArrayList<String> idsList)
    {
        for (String id : idsList)
        {
            removeFile(path, id);
        }
    }

    public void toFile(String path, String filename, Object dataObject)
    {
        try {
            FileOutputStream fileStream = new FileOutputStream("src\\Files\\" + path + filename + ".ser");
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);

            objectStream.writeObject(dataObject);

            objectStream.close();
            fileStream.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Object fromFile(File file)
    {
        Object fromFile = null;

        try {
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);

            fromFile = objectStream.readObject();

            objectStream.close();
            fileStream.close();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return fromFile;
    }



    private void removeFile(String path, String id)
    {
        File file = new File(path + id + ".ser");

        if (file.isFile())
            file.delete();
    }

    public void setFields(HashMap<String, Object> dataList)
    {
        for (String key : dataList.keySet())
        {
            try {

                Method m = this.getClass().getMethod("set" + key, String.class);

                String value = String.valueOf(dataList.get(key));

                m.invoke(this, value);

            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                System.err.println("Error in Model: " + e.getMessage() + ", " + e.getCause());
            }
        }
    }
}
