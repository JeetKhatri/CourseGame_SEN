using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class LeaderboardManager{

    public static string back = NavigationManager.settings;
    public static string url = UrlManager.mainLeaderboardUrl;
    public static Dictionary<string, string> prms = new Dictionary<string, string>();

    public static void reset()
    {
        back = NavigationManager.settings;
        url = UrlManager.mainLeaderboardUrl;
        prms = new Dictionary<string, string>();
    }
}
