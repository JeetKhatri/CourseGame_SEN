using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class ForgotPasswordScript : MonoBehaviour {

    private InputField inputUsername_fp;

    void Start()
    {
        this.inputUsername_fp = GameObject.Find("inputUsername_fp").GetComponent<InputField>();
    }

    public void forgotPassword()
    {
        string emailid = this.inputUsername_fp.text;
        string url = UrlManager.studentForgotPasswordUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("emailid", emailid);

        StartCoroutine(Utils.makePostCall(url, Utils.createForm(map), this, "successForgotPassword", "errorMethod"));
    }

    public void successForgotPassword(string data)
    {
        if (data.Equals("true"))
        {
            Utils.showToastOnUiThread("Mail has been sent to your email address!");
        }
        else
        {
            Utils.showToastOnUiThread("You are not registered!");
        }
    }

    public void errorMethod()
    {
        Utils.showToastOnUiThread("We are sorry. Server got into trouble!");
    }
}
