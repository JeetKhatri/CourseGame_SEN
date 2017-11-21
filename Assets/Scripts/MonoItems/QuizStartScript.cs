using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizStartScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	}

	public void startQuiz(){
		string url = UrlManager.questionListUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();
        //map.Add("quizid",QuizManager.currentQuiz.quizId);
        map.Add("quizid", "cCecccaiNlCcIlC");

        StartCoroutine(Utils.makeGetCall(url, Utils.createQueryString(map), this, "requestSuccess", "requestFailed"));
	}
	
	public void requestSuccess(string data){
		QuizManager.questions=QuizManager.getQuestionListFromJson(data);
		QuizManager.currentIndex=0;
		QuizManager.quizRunning=true;
        QuizManager.answers = new Dictionary<string, string>();

		NavigationManager.NavigateTO(NavigationManager.quiz);
	}

    public void requestFailed()
    {
        Debug.Log("error bc");
    }
}
