using System.Collections.Generic;
using UnityEngine;

public class QuizListScript : MonoBehaviour {

    private bool status=true;

    public void Start()
    {
        Debug.Log("checking login");
        if (!StudentManager.isLogin())
        {
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("batchid", StudentManager.getStudent().batchId);
        //map.Add("batchid","iNiJblvoVlbsVwF");

        //Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makePostCall(UrlManager.quizListUrl + "?" + Utils.createQueryString(map), Utils.createForm(map), this, "quizListSuccess", "quizListFailed"));
    }

    public void quizListSuccess(string data)
    {
        Debug.Log("quiz list: " + data);
        QuizListManager.quizList=QuizListManager.getQuizListFromJson(data);
        QuizListManager.quizList.createMap();

        Debug.Log(QuizListManager.quizList.responseStatus);
        Debug.Log(QuizListManager.quizList.quizBeans);

        if (QuizListManager.quizList.responseStatus.Equals("false")){
            this.quizListFailed();
            this.status=false;
            return;
        }
    }

    public void startArray()
    {
        if(!status){
            return;
        }
        try
        {
            QuizManager.currentQuiz = QuizListManager.quizList.idMap["UyybEGkvSG11vx7"];
            QuizManager.currentIndex = 0;

            NavigationManager.NavigateTO(NavigationManager.quizStart);
        }
        catch(KeyNotFoundException e)
        {
            Debug.Log("Please wait!!");
        }
    }

    public void quizListFailed()
    {
        Debug.Log("error");
    }
}
