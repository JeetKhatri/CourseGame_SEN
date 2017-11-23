using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizManager{

    public static QuestionList questions;
    public static Dictionary<string,string> answers;
    public static QuestionList getQuestionListFromJson(string data)
    {
        return JsonUtility.FromJson<QuestionList>(data);
    }

    public static void reset()
    {
        questions = null;
        answers = null;
        currentIndex = 0;
        currentQuiz = null;
        quizRunning = false;
    }

    public static Quiz currentQuiz;
    public static int currentIndex = 0;
    public static bool quizRunning=false;
}
