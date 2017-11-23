using UnityEngine;

public class QuizListManager{

    public static QuizList quizList;

    public static QuizList getQuizListFromJson(string json)
    {
        Debug.Log("inside conversion method: " + json);

        QuizList list = JsonUtility.FromJson<QuizList>(json);
        return list;
    }
}
