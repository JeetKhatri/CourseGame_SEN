using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class MouseManager : MonoBehaviour {
	// Update is called once per frame
	float gragSpeed=10f;
	Rigidbody2D grabbedObject=null;
	void Update(){
		if (Input.GetMouseButtonDown (0)) {
			Vector3 mouseWorldPos3D = Camera.main.ScreenToWorldPoint (Input.mousePosition);	
			Vector2 mousePos2D = new Vector2 (mouseWorldPos3D.x, mouseWorldPos3D.y);
			Vector2 dir = Vector2.zero;

			RaycastHit2D hit= Physics2D.Raycast (mousePos2D, dir);
			if (hit != null && hit.collider!=null) {
				if (hit.collider.GetComponent<Rigidbody2D> () != null) {
					grabbedObject=hit.collider.GetComponent<Rigidbody2D> ();
					grabbedObject.gravityScale = 0;
				}
			}
		}if (Input.GetMouseButtonUp (0)) {
			grabbedObject.gravityScale = 1;
			grabbedObject = null;
		}
	}
	void FixedUpdate () {
		if (grabbedObject != null) {
			Vector3 mouseWorldPos3D = Camera.main.ScreenToWorldPoint (Input.mousePosition);	
			Vector2 mousePos2D = new Vector2 (mouseWorldPos3D.x, mouseWorldPos3D.y);
			Vector2 dir = mousePos2D - grabbedObject.position;
			dir *= gragSpeed;
			grabbedObject.velocity =dir;
		}

	}
}
