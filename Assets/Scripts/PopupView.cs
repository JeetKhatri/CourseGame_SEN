using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
public class PopupView : MonoBehaviour {
	// Use this for initialization
	InputField email=null;
	public void Start () {
	//	MyShowToastMethod ();

	}
	string toastString;
	AndroidJavaObject currentActivity;

	public void MyShowToastMethod ()
	{
		
		if (Application.platform == RuntimePlatform.Android) {
			if (email== null) {
				showToastOnUiThread ("enter proper email id");
			} else {
				showToastOnUiThread (email.text);
			}
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
