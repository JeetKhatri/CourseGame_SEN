using System.Collections;
using System.Collections.Generic;
using UnityEngine;
//using UnityEditor;
using UnityEngine.UI;

public class toogleChange : MonoBehaviour {

	//public int number;
	//public GameObject[] selectArr;
	private float vx = -3.0f;
	private float vy = 2.0f;
	public InputField input;
	public int i;
	AndroidJavaObject currentActivity;
	private string toastString;

	//private float vz = 0.0f;
	public GameObject[] ball = new GameObject[5];
	//int increment= 5;
	public bool[] gen={false,false,false,false,false};
	public int  instantiated=0;
		

   public void toggle() 
    {
		
		input = GameObject.Find("IndexNumber").GetComponent<InputField>();

		int.TryParse(input.text,out i);
		Debug.Log (i);

		if (i > 4) {
			//EditorUtility.DisplayDialog ("Error", "ArrayIndexOutOfBound","OK");
			if (Application.platform == RuntimePlatform.Android) {
				showToastOnUiThread ("Error: ArrayIndexOutOfBound : Enter Value Between 0-4");
			}
		}
		else if (instantiated < 5 && input.text != "" && gen [i] == false && i < 5) {
			input.enabled = false;	
			Instantiate (ball [i], new Vector3 (vx, vy, -0.19f), Quaternion.identity);  
			gen [i] = true;	
			instantiated++;
		}


	}

	void showToastOnUiThread(string toastString){
		AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");

		currentActivity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
		this.toastString = toastString;

		currentActivity.Call ("runOnUiThread", new AndroidJavaRunnable (showToast));
	}
	void showToast(){
		Debug.Log ("Running on UI thread");
		AndroidJavaObject context = currentActivity.Call<AndroidJavaObject>("getApplicationContext");
		AndroidJavaClass Toast = new AndroidJavaClass("android.widget.Toast");
		AndroidJavaObject javaString=new AndroidJavaObject("java.lang.String",toastString);
		AndroidJavaObject toast = Toast.CallStatic<AndroidJavaObject> ("makeText", context, javaString, Toast.GetStatic<int>("LENGTH_SHORT"));
		toast.Call ("show");
	}	
		
}
