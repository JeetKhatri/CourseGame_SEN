using System.Collections.Generic;

[System.Serializable]
public class QuizList
{
    public string responseStatus;
    public List<Quiz> quizBeans;
    public Dictionary<string, Quiz> nameMap;
    public Dictionary<string, Quiz> idMap;

    public QuizList()
    {
        this.createMap();
    }

    public void createMap()
    {
        foreach (Quiz quiz in this.quizBeans)
        {
            nameMap.Add(quiz.name, quiz);
            idMap.Add(quiz.quizId, quiz);
        }
    }
}
