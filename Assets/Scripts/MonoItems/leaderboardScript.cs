using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class leaderboardScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
        Debug.Log("checking login");
        StudentManager.checkStudentLogin();

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("batchid", StudentManager.getStudent().batchId);

        Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makePostCall(UrlManager.leaderboardUrl + "?" + Utils.createQueryString(map), Utils.createForm(map), this, "quizListSuccess", "quizListFailed"));
    }

    public void quizListSuccess(string data)
    {
        Debug.Log("quiz list: " + data);
        QuizManager.setQuizList(QuizManager.getQuizListFromJson(data));
    }

    public void quizListFailed()
    {
        Debug.Log("error");
    }

    // Update is called once per frame
    void Update () {
		
	}
}
