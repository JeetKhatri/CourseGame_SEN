using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class ChangeScript : MonoBehaviour
{
	
	public void changeScript ()
	{
		SceneManager.LoadScene ("MAIN");
	}

	public void nevigateTOGame ()
	{
		SceneManager.LoadScene ("GameMenu");
	}

	public void NavigateTOSetting ()
	{
		SceneManager.LoadScene ("SettingMenu");
	}

	public void NavigateTOForgot ()
	{
		SceneManager.LoadScene ("forgotpassword");
	}

	public void NavigateTOLogin ()
	{
		SceneManager.LoadScene ("login");
	}
	public void Update ()
	{
		
		if (UnityEngine.Application.platform == RuntimePlatform.Android) {

			if (Input.GetKeyUp (KeyCode.Escape)) {
				Scene scene = SceneManager.GetActiveScene ();
				string sceneString = scene.name;
				if (sceneString == "SettingMenu") {
					changeScript ();
				} else if (sceneString == "GameMenu") {
					changeScript ();
				} else if (sceneString == "Main") {
					UnityEngine.Application.Quit ();
				} else if (sceneString == "forgotpassword") {
					NavigateTOLogin ();
				}
			} else {
				UnityEngine.Application.Quit ();	
			}
		}

	}
		
	//public void MakeToast(){
	//	Toast t=null;
	//	t =Toast.Create ("","Hello World");
//		t.Show ();
//	}
}
