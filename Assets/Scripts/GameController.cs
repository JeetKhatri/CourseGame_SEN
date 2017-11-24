using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;

public class GameController : MonoBehaviour {

    private InputField input;
    void Awake ()
    {
        input = GameObject.Find ("IndexNumber").GetComponent<InputField>();
    }

    public void GetInput(string indexNumber)
    {
		if (input.text == "") {
			Debug.Log ("NULL");
		} else {
			//Debug.Log("You Entered " + indexNumber);		
		}
 
        


    }
}
