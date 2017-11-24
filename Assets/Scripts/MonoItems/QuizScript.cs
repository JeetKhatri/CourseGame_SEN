using UnityEngine;
using UnityEngine.UI;
using UnityEngine.SceneManagement;

public class QuizScript : MonoBehaviour {
	public Text question;	
	public Text op1,op2,op3,op4;
    public Toggle tog1, tog2, tog3, tog4;

    private string selectedAns = "";

    void Start()
    {
        Debug.Log("checking login");
        if (!StudentManager.isLogin())
        {
            if (Application.platform == RuntimePlatform.Android)
                Utils.showToastOnUiThread("You need to login!");
            Debug.Log("You need to login!");
            NavigationManager.NavigateTO(NavigationManager.login);
            return;
        }

        this.tog1 = GameObject.Find("Option1").GetComponent<Toggle>();
        this.tog2 = GameObject.Find("Option2").GetComponent<Toggle>();
        this.tog3 = GameObject.Find("Option3").GetComponent<Toggle>();
        this.tog4 = GameObject.Find("Option4").GetComponent<Toggle>();

        Debug.Log("starting quiz");
        this.loadQuestion();
    }

    public void loadQuestion()
    {
        this.tog1.isOn = false;
        this.tog2.isOn = false;
        this.tog3.isOn = false;
        this.tog4.isOn = false;

        if (QuizManager.questions.quizcontent.Count == QuizManager.currentIndex)
        {
            NavigationManager.NavigateTO(NavigationManager.quizSubmit);
            return;
        }
        else if (QuizManager.questions.quizcontent.Count - 1 == QuizManager.currentIndex)
        {
            Debug.Log("time to submit");
        }

        Question qst=QuizManager.questions.quizcontent[QuizManager.currentIndex];
        this.question.text=qst.question;
        this.op1.text=qst.option1;
        this.op2.text=qst.option2;
        this.op3.text=qst.option3;
        this.op4.text=qst.option4;

        Debug.Log("question uploaded");
    }

    public void optionA()
    {
        this.selectedAns = "A";
    }
    public void optionB()
    {
        this.selectedAns = "B";
    }
    public void optionC()
    {
        this.selectedAns = "C";
    }
    public void optionD()
    {
        this.selectedAns = "D";
    }

    public void next()
    {
        QuizManager.answers.Add(QuizManager.questions.quizcontent[QuizManager.currentIndex].quizContentId,this.selectedAns);
        QuizManager.currentIndex++;
        this.loadQuestion();
    }
}
