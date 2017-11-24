using System.Collections.Generic;
using UnityEngine;

public class QuizStartScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
        Debug.Log("checking login in quizstart");
        if (!StudentManager.isLogin())
        {
            if (Application.platform == RuntimePlatform.Android)
                Utils.showToastOnUiThread("You need to login!");
            Debug.Log("You need to login!");
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        if (QuizManager.currentQuiz == null)
        {
            if (Application.platform == RuntimePlatform.Android)
                Debug.Log("Questions not found! Please contact admin");
            Utils.showToastOnUiThread("Questions not found! Please contact admin");
            NavigationManager.NavigateTO(NavigationManager.game);
            return;
        }
    }

	public void startQuiz(){
		string url = UrlManager.questionListUrl;

        Dictionary<string, string> map = new Dictionary<string, string>();

        map.Add("quizid",QuizManager.currentQuiz.quizId);
        //map.Add("quizid", "cCecccaiNlCcIlC");

        Debug.Log("getting question for quizid: "+QuizManager.currentQuiz.quizId);
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
        Debug.Log("Unable to fetch list of questions of quiz: " + QuizManager.currentQuiz.name);
        if (Application.platform == RuntimePlatform.Android)
            Utils.showToastOnUiThread("Unable to fetch list of questions of quiz: "+QuizManager.currentQuiz.name);
    }
}
