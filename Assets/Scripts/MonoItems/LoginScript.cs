using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class LoginScript : MonoBehaviour
{
    public void studentLogin ()
	{
        string emailid = GameObject.Find("inputUsername").GetComponent<InputField>().text;
        string password = GameObject.Find("inputPassword").GetComponent<InputField>().text;
        string url = UrlManager.studentLoginUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("emailid", emailid);
        map.Add("password", password);

        Debug.Log("making post call!");
        StartCoroutine(Utils.makePostCall(url, Utils.createForm(map),this,"successLogin","errorMethod"));
	}

    public void successLogin(string data)
    {
        Debug.Log("Got data: " + data);
        Student student = StudentManager.getStudentFromJson(data);
        if (student.responseStatus.Equals("false"))
        {
            this.errorMethod();
            return;
        }
        StudentManager.setStudent(student);
        NavigationManager.NavigateTO(NavigationManager.main);
    }
    public void errorMethod()
    {
        Utils.showToastOnUiThread("Invalid username or password!");
    }

}
