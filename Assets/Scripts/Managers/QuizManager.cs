using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizManager{

    private static QuizList quizList;
    public static QuizList getQuizList()
    {
        return quizList;
    }
    public static void setQuizList(QuizList param)
    {
        quizList = param;
    }
    public static QuizList getQuizListFromJson(string json)
    {
        QuizList list = JsonUtility.FromJson<QuizList>(json);
        return list;
    }
}
