using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizList
{
    public string responseStatus;
    public List<Quiz> quizBeans;
    public Dictionary<string, Quiz> map;

    public QuizList()
    {
        this.createMap();
        this.quizBeans = null;
    }

    public void createMap()
    {
        foreach(Quiz quiz in this.quizBeans)
        {
            map.Add(quiz.name, quiz);
        }
    }
}
