using System.Collections.Generic;
using UnityEngine;

public class QuizSubmitScript : MonoBehaviour {

	// Use this for initialization
	void Start () {

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

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("studentid", studentId);
        map.Add("quizid", quizId);
        map.Add("answers", answers);

        Utils.makePostCall(UrlManager.quizSubmitUrl, Utils.createForm(map), this, "requestSuccess", "requestFailed");

        NavigationManager.NavigateTO(NavigationManager.game);
    }

    public void requestSuccess(string data)
    {
        Debug.Log("Submit result: "+data);
        SubmitStatus status = Utils.getSubmitStatusFromJson(data);
    }

    public void requestFailed()
    {
        Debug.Log("error");
    }
}
