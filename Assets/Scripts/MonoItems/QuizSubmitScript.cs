using System.Collections.Generic;
using UnityEngine;

public class QuizSubmitScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
        Debug.Log("checking login in quizsubmit");
        if (!StudentManager.isLogin())
        {
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }
    }

    public void submit()
    {
        string studentId = StudentManager.getStudent().studentId;
        string quizId = QuizManager.currentQuiz.quizId;
        string answers = "";
        foreach (KeyValuePair<string,string> pair in QuizManager.answers)
        {
            answers += pair.Key + ";" + pair.Value + ":";
        }
        answers=answers.Trim(':');

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("studentid", studentId);
        map.Add("quizid", quizId);
        map.Add("answers", answers);

        Debug.Log("studentid: " + studentId);
        Debug.Log("quizid: " + quizId);
        Debug.Log("answers: " + answers);

        Debug.Log("making post call to submit quiz");
        StartCoroutine(Utils.makePostCall(UrlManager.quizSubmitUrl, Utils.createForm(map), this, "requestSuccess", "requestFailed"));
    }

    public void requestSuccess(string data)
    {
        Debug.Log("Submit result: "+data);
        SubmitStatus status = Utils.getSubmitStatusFromJson(data);
        if (!status.responseStatus)
        {
            this.requestFailed();
            return;
        }

        QuizManager.reset();
        NavigationManager.NavigateTO(NavigationManager.game);
    }

    public void requestFailed()
    {
        Debug.Log("error");
    }
}
