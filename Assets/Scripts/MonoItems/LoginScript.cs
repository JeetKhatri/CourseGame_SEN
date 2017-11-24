using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class LoginScript : MonoBehaviour
{
    private InputField inputUsername,inputPassword;

    void Start()
    {
        this.inputUsername = GameObject.Find("inputUsername").GetComponent<InputField>();
        this.inputPassword = GameObject.Find("inputPassword").GetComponent<InputField>();
    }

    public void studentLogin ()
	{
        string emailid = this.inputUsername.text;
        string password = this.inputPassword.text;
        string url = UrlManager.studentLoginUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("emailid", emailid);
        map.Add("password", password);

        Debug.Log("making post call for login!");
        StartCoroutine(Utils.makePostCall(url, Utils.createForm(map),this,"successLogin","errorMethod"));
	}

    public void successLogin(string data)
    {
        Debug.Log("Got data: " + data);
        Student student = StudentManager.getStudentFromJson(data);
        if (student.responseStatus.Equals("false"))
        {
            Debug.Log("Invalid username or password!");
            if (Application.platform == RuntimePlatform.Android)
                Utils.showToastOnUiThread("Invalid username or password!");
            return;
        }
        StudentManager.setStudent(student);
        NavigationManager.NavigateTO(NavigationManager.main);
    }
    public void errorMethod()
    {
        Debug.Log("We are sorry. Server got into trouble!");
        if (Application.platform == RuntimePlatform.Android)
            Utils.showToastOnUiThread("We are sorry. Server got into trouble!");
    }

}
