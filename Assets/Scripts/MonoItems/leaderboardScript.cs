using System.Collections.Generic;
using UnityEngine.UI;
using UnityEngine;

public class leaderboardScript : MonoBehaviour {

    private Text first,second,third;

	// Use this for initialization
	void Start () {
        this.first = GameObject.Find("leaderboardFirst").GetComponent<Text>();
        this.second = GameObject.Find("leaderboardSecond").GetComponent<Text>();
        this.third = GameObject.Find("leaderboardThird").GetComponent<Text>();

        Debug.Log("checking login");
        if (!StudentManager.isLogin())
        {
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("batchid", StudentManager.getStudent().batchId);
        //map.Add("batchid", "WwFIwWWJGIGZEWw");

        Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makeGetCall(UrlManager.mainLeaderboardUrl, Utils.createQueryString(map), this, "requestSuccess", "requestFailed"));
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
        Debug.Log("error");
    }
}
