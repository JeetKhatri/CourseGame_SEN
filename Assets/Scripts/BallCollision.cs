//using UnityEditor;
using UnityEngine;
public class BallCollision : MonoBehaviour {
	public toogleChange t;
	bool a=false;
	private static int counter =0;
	AndroidJavaObject currentActivity;
	private string toastString;

	void start()
	{
		//counter = 0;
	}
	public void OnCollisionEnter2D(Collision2D collision){
		int num = t.i;

		if (collision.gameObject.name == ("Ball"+(num)+"(Clone)") && gameObject.name == ("lower"+(num)) && a==false) 
		{
			t.input.enabled=true;
            //EditorUtility.DisplayDialog ("This is Correct Bowl.", "Game Over","Cancel");
            Debug.Log("correct!");
            if (Application.platform == RuntimePlatform.Android) {
				showToastOnUiThread ("Bravo ! It's Correct");
            }
			a = true;
			collision.gameObject.GetComponent<Rigidbody2D> ().simulated = false;
			collision.gameObject.GetComponent<CircleCollider2D>().enabled = false;

			BallCollision.counter++;
			Debug.Log (BallCollision.counter + "this is count");
			if (BallCollision.counter== 5) {
				if (Application.platform == RuntimePlatform.Android) {
                    
					showToastOnUiThread ("Congratulations : You've Passed");
				} else {
                    Debug.Log("you have passed");
                    //EditorUtility.DisplayDialog ("Congratulations", "You've Passed","OK");
                }
                NavigationManager.NavigateTO(NavigationManager.quizStart);
            }
			//Debug.Log (counter);
		} 
		else
		{
			
			//EditorUtility.DisplayDialog ("This is wrong Bowl.", "OK","Cancel");
			if (Application.platform == RuntimePlatform.Android) {
				showToastOnUiThread ("Wrong Bowl: Choose correct bowl");
			}
			a = true;
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
