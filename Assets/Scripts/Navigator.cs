using System.Collections;
using System.Collections.Generic;
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
}
