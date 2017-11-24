using UnityEngine;

public class Navigator : MonoBehaviour {

	public void forgotPassword()
    {
        NavigationManager.NavigateTO(NavigationManager.forgotPassword);
    }

    public void showQuizes()
    {
        NavigationManager.NavigateTO(NavigationManager.game);
    }

    public void toMain()
    {
        NavigationManager.NavigateTO(NavigationManager.main);
    }

    public void toLogin()
    {
        NavigationManager.NavigateTO(NavigationManager.login);
    }

    public void toGame()
    {
        NavigationManager.NavigateTO(NavigationManager.game);
    }

    public void toSettings()
    {
        NavigationManager.NavigateTO(NavigationManager.settings);
    }

    public void toLeaderboard()
    {
        NavigationManager.NavigateTO(NavigationManager.leaderboard);
    }

    public void toAchivements()
    {
        NavigationManager.NavigateTO(NavigationManager.achivement);
    }

    public void toQuiz()
    {
        NavigationManager.NavigateTO(NavigationManager.quiz);
    }

    public void toQuizStart()
    {
        NavigationManager.NavigateTO(NavigationManager.quizStart);
    }

    public void toQuizSubmit()
    {
        NavigationManager.NavigateTO(NavigationManager.quizSubmit);
    }
	public void toArrayGame()
	{
		NavigationManager.NavigateTO(NavigationManager.ArrayGamePlay);
	}

    public void toLogout()
    {
        StudentManager.setStudent(null);
        NavigationManager.NavigateTO(NavigationManager.login);
    }
}
