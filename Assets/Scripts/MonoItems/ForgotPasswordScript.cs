using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ForgotPasswordScript : MonoBehaviour {

    public void forgotPassword()
    {
        string emailid = GameObject.Find("inputUsername_fp").GetComponent<InputField>().text;
        string url = UrlManager.studentForgotPasswordUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("emailid", emailid);

        StartCoroutine(Utils.makePostCall(url, Utils.createForm(map), this, "successForgotPassword", "errorMethod"));
    }

    public void successForgotPassword(string data)
    {
        Debug.Log("Got data: " + data);
    }

    public void errorMethod()
    {
        Debug.Log("error");
    }
}
