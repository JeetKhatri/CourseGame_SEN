using UnityEngine;

public class StudentManager
{
    //password: y5cpvkybndbnv5g
    private static Student student;

    public static bool isLogin()
    {
        return !student.responseStatus.Equals("false");
    }

    public static Student getStudent()
    {
        return student;
    }
    public static void setStudent(Student param)
    {
        student = param;
    }

    public static Student getStudentFromJson(string json)
    {
        Student student = JsonUtility.FromJson<Student>(json);
        return student;
    }

    public static void checkStudentLogin()
    {
        if (!isLogin())
        {
            Debug.Log("checklogin failed, navigating to login page");
            NavigationManager.NavigateTO(NavigationManager.login);
        }
    }
}
