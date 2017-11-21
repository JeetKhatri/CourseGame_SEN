using System.Collections.Generic;
using UnityEngine;

public class QuizListScript : MonoBehaviour {

    public void Start()
    {
        Debug.Log("checking login");
        StudentManager.checkStudentLogin();

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("batchid", StudentManager.getStudent().batchId);

        Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makePostCall(UrlManager.quizListUrl + "?" + Utils.createQueryString(map), Utils.createForm(map), this, "quizListSuccess", "quizListFailed"));
    }

    public void quizListSuccess(string data)
    {
        Debug.Log("quiz list: " + data);
        QuizListManager.quizList=QuizListManager.getQuizListFromJson(data);
    }

    public void startArray()
    {
        string id = QuizListManager.quizList.nameMap["Array"].quizId;
        QuizManager.currentQuizId = id;

        NavigationManager.NavigateTO(NavigationManager.quizStart);
    }

    public void quizListFailed()
    {
        Debug.Log("error");
    }

    // Update is called once per frame
    void Update () {
		
	}
}
