using System.Collections.Generic;
using UnityEngine;

public class QuizListScript : MonoBehaviour {

    private bool status = false;
    private bool arrayStatus = false, llStatus = false;
    private bool arrayCheck = false, llCheck = false;

    private string arrayid = "dSDaGRYGa2uamSS";
    private string llid = "dSDaGRYGa2uamSS";

    public void reset()
    {
        this.status = false;
        this.arrayCheck = false;
        this.llCheck = false;
        this.arrayStatus = false;
        this.llStatus = false;
    }

    public void Start()
    {
        this.reset();

        Debug.Log("checking login");
        if (!StudentManager.isLogin())
        {
            Debug.Log("You need to login again!");
            Utils.showToastOnUiThread("You need to login again!");
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("batchid", StudentManager.getStudent().batchId);
        //map.Add("batchid","iNiJblvoVlbsVwF");

        Debug.Log("getting data for batchID: " + StudentManager.getStudent().batchId);
        StartCoroutine(Utils.makePostCall(UrlManager.quizListUrl + "?" + Utils.createQueryString(map), Utils.createForm(map), this, "quizListSuccess", "quizListFailed"));

        this.checkArray();
    }

    public void checkArray()
    {
        Debug.Log("checking if array: " + this.arrayid + " available for student: " + StudentManager.getStudent().studentId);
        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("studentid", StudentManager.getStudent().studentId);
        map.Add("quizid", this.arrayid);
        StartCoroutine(Utils.makePostCall(UrlManager.quizCheckUrl, Utils.createForm(map), this, "arraySuccess", "arrayFailed"));
    }
    public void checkLL()
    {
        Debug.Log("checking if linkedlist: " + this.llid + " available for student: " + StudentManager.getStudent().studentId);
        Dictionary<string, string> map = new Dictionary<string, string>();
        map.Add("studentid", StudentManager.getStudent().studentId);
        map.Add("quizid", this.llid);
        StartCoroutine(Utils.makePostCall(UrlManager.quizCheckUrl, Utils.createForm(map), this, "llSuccess", "llFailed"));
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
            this.status = false;
            return;
        }
        this.status = true;
    }

    public void arraySuccess(string data)
    {
        this.arrayCheck = true;
        Status st = Utils.getStatusFromJson(data);
        if (st.status.Equals("true"))
        {
            this.arrayStatus = false;
            return;
        }
        this.arrayStatus = true;
    }
    public void llSuccess(string data)
    {
        this.arrayCheck = true;
        Status st = Utils.getStatusFromJson(data);
        if (st.status.Equals("true"))
        {
            Utils.showToastOnUiThread("You've already given this test!");
            this.llStatus = false;
            return;
        }
        this.llStatus = true;
    }
    public void arrayFailed()
    {
        this.arrayCheck = true;
        this.arrayStatus = false;
        Utils.showToastOnUiThread("Unable to check array available for user or not!");
        Debug.Log("Unable to check array available for user or not!");
    }
    public void llFailed()
    {
        this.arrayCheck = true;
        this.llStatus = false;
        Utils.showToastOnUiThread("Unable to check ll available for user or not!");
        Debug.Log("Unable to check ll available for user or not!");
    }

    public void startArray()
    {
        if (!this.status)
        {
            Utils.showToastOnUiThread("Data are being fetched! Please wait...");
            Debug.Log("Data are being fetched! Please wait...");
            return;
        }
        if (!this.arrayCheck)
        {
            Utils.showToastOnUiThread("Checking if array available for you! Please wait...");
            Debug.Log("Checking if array available for you! Please wait...");
            return;
        }
        if (!this.arrayStatus)
        {
            Utils.showToastOnUiThread("You've already given this test!");
            Debug.Log("You've already given this test!");
            return;
        }
        try
        {
            QuizManager.currentQuiz = QuizListManager.quizList.idMap[this.arrayid];
            QuizManager.currentIndex = 0;

            NavigationManager.NavigateTO(NavigationManager.ArrayGamePlay);
        }
        catch(KeyNotFoundException e)
        {
            Debug.Log("Please wait!!");
        }
    }

    public void startLinkedList()
    {
        if (!this.status)
        {
            Utils.showToastOnUiThread("Data are being fetched! Please wait...");
            Debug.Log("Data are being fetched! Please wait...");
            return;
        }
        if (!this.llCheck)
        {
            Utils.showToastOnUiThread("Checking if Stack is available for you! Please wait...");
            Debug.Log("Checking if linkedList available for you! Please wait...");
            return;
        }
        if (!this.llStatus)
        {
            Utils.showToastOnUiThread("You've already given this test!");
            Debug.Log("You've already given this test!");
            return;
        }

        try
        {
            QuizManager.currentQuiz = QuizListManager.quizList.idMap[this.llid];
            QuizManager.currentIndex = 0;

            NavigationManager.NavigateTO(NavigationManager.quizStart);
        }
        catch (KeyNotFoundException e)
        {
            Debug.Log("Please wait!!");
        }
    }

    public void quizListFailed()
    {
        Utils.showToastOnUiThread("Unable to fetch data of quiz list!");
        Debug.Log("Unable to fetch data of quiz list!");
    }
}
