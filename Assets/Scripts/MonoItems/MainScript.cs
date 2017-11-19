using System.Collections.Generic;
using UnityEngine;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class MainScript : MonoBehaviour
{
    public void Start()
    {
        Debug.Log("checking login");
        StudentManager.checkStudentLogin();
    }
}
