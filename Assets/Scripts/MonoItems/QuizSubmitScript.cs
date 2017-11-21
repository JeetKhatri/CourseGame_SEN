using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class QuizSubmitScript : MonoBehaviour {

	// Use this for initialization
	void Start () {

	}

    public void submit()
    {
        foreach (string str in QuizManager.answers)
        {
            Debug.Log(str);
        }

        NavigationManager.NavigateTO(NavigationManager.game);
    }

    public void submitData()
    {

    }
}
