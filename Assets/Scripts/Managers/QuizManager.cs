using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizManager{

    public static QuestionList questions;
    public static List<string> answers;
    public static QuestionList getQuestionListFromJson(string data)
    {
        return JsonUtility.FromJson<QuestionList>(data);
    }

    public static Quiz currentQuiz;
    public static int currentIndex = 0;
    public static bool quizRunning=false;
}
