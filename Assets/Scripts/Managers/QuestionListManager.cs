using UnityEngine;

public class QuestionListManager{

    public static QuestionList questions;

    public static QuestionList getQuestionListFromJson(string data)
    {
        return JsonUtility.FromJson<QuestionList>(data);
    }
}
