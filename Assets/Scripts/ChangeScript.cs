using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.SceneManagement;
//#if UNITY_ANDROID
//using UnityEngine.WSA;
//#endif

public class ChangeScript : MonoBehaviour
{
	public void Update ()
	{
		
		if (UnityEngine.Application.platform == RuntimePlatform.Android) {

			if (Input.GetKeyUp (KeyCode.Escape)) {
				Scene scene = SceneManager.GetActiveScene ();
				string sceneString = scene.name;
				if (sceneString == "SettingMenu") {
                    NavigationManager.NavigateTO(NavigationManager.main);
				} else if (sceneString == "GameMenu") {
                    NavigationManager.NavigateTO(NavigationManager.main);
                } else if (sceneString == "MAIN") {
					UnityEngine.Application.Quit ();
				} else if (sceneString == "forgotpassword") {
                    NavigationManager.NavigateTO(NavigationManager.login);
                } else if (sceneString == "leaderboard") {
                    NavigationManager.NavigateTO(NavigationManager.settings);
                } else if (sceneString == "achivement") {
                    NavigationManager.NavigateTO(NavigationManager.settings);
                } else if (sceneString == "login") {
					UnityEngine.Application.Quit ();
				}

			} 
		}

	}
}
