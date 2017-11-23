using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class leaderboardScript : MonoBehaviour {

    private Text first,second,third;
    private Button back;

	// Use this for initialization
	void Start () {
        this.first = GameObject.Find("leaderboardFirst").GetComponent<Text>();
        this.second = GameObject.Find("leaderboardSecond").GetComponent<Text>();
        this.third = GameObject.Find("leaderboardThird").GetComponent<Text>();
        this.back = GameObject.Find("Button").GetComponent<Button>();
        this.back.onClick.AddListener(this.navigator);

        Debug.Log("checking login");
        if (!StudentManager.isLogin())
        {
            //Utils.showToastOnUiThread("You need to login!");
            Debug.Log("You need to login!");
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        //map.Add("batchid", "WwFIwWWJGIGZEWw");

        Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makeGetCall(LeaderboardManager.url, Utils.createQueryString(LeaderboardManager.prms), this, "requestSuccess", "requestFailed"));
    }

    private void navigator()
    {
        NavigationManager.NavigateTO(LeaderboardManager.back);
    }

    public void requestSuccess(string data)
    {
        Debug.Log("main leaderboard: " + data);
        LeaderboardList list = Utils.getLeaderboardListFromJson(data);

        if (list.status.Equals("false"))
        {
            this.requestFailed();
            return;
        }

        this.first.text = list.marklist[0].studentname;
        this.second.text = list.marklist[1].studentname;
        this.third.text = list.marklist[2].studentname;
    }

    public void requestFailed()
    {
        //Utils.showToastOnUiThread("You need to login!");
        Debug.Log("You need to login!");
    }
}
