using System.Collections.Generic;
using UnityEngine;

[System.Serializable]
public class QuizList
{
    public List<Quiz> quizBeans;
    public string responseStatus;
    public Dictionary<string, Quiz> idMap;

    public QuizList()
    {
        this.idMap = new Dictionary<string, Quiz>();
        //this.createMap();
    }

    public void createMap()
    {
        foreach (Quiz quiz in this.quizBeans)
        {
            idMap.Add(quiz.quizId, quiz);
        }
    }
}
