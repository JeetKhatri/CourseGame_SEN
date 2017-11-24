using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class InitialVelocity : MonoBehaviour {
	public Vector3 initVel;
	// Use this for initialization
	void Start () {
		this.GetComponent<Rigidbody2D>().velocity=initVel;
	}
	
	// Update is called once per frame

}
