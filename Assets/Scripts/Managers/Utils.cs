using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.Networking;

public class Utils{

    private static string toastString;
    private static AndroidJavaObject currentActivity;

    public static WWWForm createForm(Dictionary<string, string> map)
    {
        WWWForm form = new WWWForm();
        foreach (KeyValuePair<string, string> key in map)
        {
            form.AddField(key.Key, key.Value);
        }
        return form;
    }
    public static string createQueryString(Dictionary<string, string> map)
    {
        string querystring = "";
        foreach (KeyValuePair<string, string> key in map)
        {
            querystring += key.Key + "=" + key.Value + "&";
        }
        querystring = querystring.TrimEnd('&');
        return querystring;
    }
    public static IEnumerator makePostCall(string url, WWWForm form, Object obj, string successMethod, string errorMethod)
    {
        UnityWebRequest www = UnityWebRequest.Post(url, form);
        Debug.Log("making post call inside makepostcall method");
        Debug.Log("sending post call request!");
        yield return www.SendWebRequest();
        Debug.Log("got response!");

        if (www.isNetworkError || www.isHttpError)
        {
            Debug.Log("error: "+www.error);
            obj.GetType().GetMethod(errorMethod).Invoke(obj, null);
        }
        else
        {
            object[] param = new object[1];
            param[0] = www.downloadHandler.text;
            Debug.Log("data before method invoke: " + param[0]);
            obj.GetType().GetMethod(successMethod).Invoke(obj, param);
        }
    }
    public static IEnumerator makeGetCall(string url, string data, Object obj, string successMethod, string errorMethod)
    {
        UnityWebRequest www = UnityWebRequest.Get(url + "?" + data);
        yield return www.SendWebRequest();

        if (www.isNetworkError || www.isHttpError)
        {
            Debug.Log(www.error);
            obj.GetType().GetMethod(errorMethod).Invoke(obj, null);
        }
        else
        {
            object[] param = new object[1];
            param[0] = www.downloadHandler.text;
            obj.GetType().GetMethod(successMethod).Invoke(obj, param);
        }
    }
    public static Dictionary<string, object> getDataFromJson(string json)
    {
        return JsonUtility.FromJson<Dictionary<string, object>>(json);
    }

    public static LeaderboardList getLeaderboardListFromJson(string json)
    {
        LeaderboardList list = JsonUtility.FromJson<LeaderboardList>(json);
        return list;
    }
    public static SubmitStatus getSubmitStatusFromJson(string json)
    {
        SubmitStatus list = JsonUtility.FromJson<SubmitStatus>(json);
        return list;
    }
    public static Status getStatusFromJson(string json)
    {
        Status list = JsonUtility.FromJson<Status>(json);
        return list;
    }

    public static void showToastOnUiThread(string msg)
    {
        AndroidJavaClass UnityPlayer = new AndroidJavaClass("com.unity3d.player.UnityPlayer");

        currentActivity = UnityPlayer.GetStatic<AndroidJavaObject>("currentActivity");
        toastString = msg;

        currentActivity.Call("runOnUiThread", new AndroidJavaRunnable(showToast));
    }
    private static void showToast()
    {
        Debug.Log("Running on UI thread");
        AndroidJavaObject context = currentActivity.Call<AndroidJavaObject>("getApplicationContext");
        AndroidJavaClass Toast = new AndroidJavaClass("android.widget.Toast");
        AndroidJavaObject javaString = new AndroidJavaObject("java.lang.String", toastString);
        AndroidJavaObject toast = Toast.CallStatic<AndroidJavaObject>("makeText", context, javaString, Toast.GetStatic<int>("LENGTH_SHORT"));
        toast.Call("show");
    }
}
