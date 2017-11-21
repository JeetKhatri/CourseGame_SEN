using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class QuizScript : MonoBehaviour {
	public Text question;	
	public Text op1,op2,op3,op4;

	int cnt=0; // Use this for initialization
	string[] questions = {"Question1","Question2","Question3","Question4"};
	string[] option1 = {"opttion1.1","opttion1.2","opttion1.3","opttion1.4"};
	string[] option2 = {"opttion2.1","opttion2.2","opttion2.3","opttion2.4"};
	string[] option3 = {"opttion3.1","opttion3.2","opttion3.3","opttion3.4"};
	string[] option4 = {"opttion4.1","opttion4.2","opttion4.3","opttion4.4"};

    void Start()
    {
        Dictionary<string, string> map = new Dictionary<string, string>();

        //map.Add("quizid", QuizManager.currentQuiz.quizId);
        map.Add("quizid", "cCecccaiNlCcIlC");

        //Debug.Log("getting data for quizId: " + QuizManager.currentQuiz.quizId);
        StartCoroutine(Utils.makeGetCall(UrlManager.quizListUrl, Utils.createQueryString(map), this, "requestSuccess", "requestFailed"));
    }

    public void ChangeText(){
		if (cnt == 4) {
			SceneManager.LoadScene ("quizSubmit");
		} else {
			question.text = questions [cnt];
			op1.text = option1 [cnt];
			op2.text = option2 [cnt];
			op3.text = option3 [cnt];
			op4.text = option4 [cnt];
		}

		cnt++;
	}

    public void requestSuccess(string data)
    {
        QuizManager.questions = QuizManager.getQuestionListFromJson(data);
        QuizManager.startQuiz(QuizManager.currentQuizId);
    }

	// Update is called once per frame
	void Update () {
		
	}
}
