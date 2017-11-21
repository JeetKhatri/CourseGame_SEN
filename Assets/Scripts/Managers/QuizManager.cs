using UnityEngine;

public class QuizManager{

    public static QuestionList questions;

    public static string currentQuizId;
    public static Quiz currentQuiz;
    public static bool quiz = false;

    public static int currentIndex = 0;

    public static void startQuiz(string id)
    {
        quiz = true;
        currentQuiz = QuizListManager.quizList.idMap[id];
        currentIndex = 0;
    }

    public static void endQuiz()
    {
        quiz = false;
    }

    public static QuestionList getQuestionListFromJson(string data)
    {
        return JsonUtility.FromJson<QuestionList>(data);
    }
}
