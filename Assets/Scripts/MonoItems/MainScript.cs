using System.Collections.Generic;
using UnityEngine;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class MainScript : MonoBehaviour
{
    public void Start()
    {
        Debug.Log("checking login in main script");
        if (!StudentManager.isLogin())
        {
            Debug.Log("You need to login again!");
            Utils.showToastOnUiThread("You need to login again!");
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }
    }
}
