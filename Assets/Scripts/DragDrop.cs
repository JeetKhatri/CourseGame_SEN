using System.Collections;
using System.Collections.Generic;
using UnityEngine;
using UnityEngine.UI;


// Try this code only it will work on mobile devices as well.. Code for touch will be hard for u now.
public class DragDrop : MonoBehaviour
{

    public GameObject gameObjectDrag;

    public Vector3 GOcenter;
    public Vector3 touchPosition;
    public Vector3 Offset;
    public Vector3 NGOcenter;

    RaycastHit hit;

    public bool isDrag = false;

    // Update is called once per frame
    void Update()
    {

        if (Input.GetMouseButton(0))
        {
            //Ray ray = Camera.main.ScreenPointToRay(Input.mousePosition);

            RaycastHit2D hit = Physics2D.Raycast(Camera.main.ScreenToWorldPoint(Input.mousePosition), Vector2.zero);

			if (hit.collider != null) {
				if (hit.collider.tag == "ball") {
					gameObjectDrag = hit.collider.gameObject;


					GOcenter = gameObjectDrag.transform.position;

					touchPosition = Camera.main.ScreenToWorldPoint (Input.mousePosition);

					Offset = touchPosition - GOcenter;

					isDrag = true;

				}
			}
        }

        if (Input.GetMouseButton(0))
        {
            if (isDrag)
            {
                touchPosition = Camera.main.ScreenToWorldPoint(Input.mousePosition);

                NGOcenter = touchPosition - Offset;

                gameObjectDrag.transform.position = new Vector3(NGOcenter.x, NGOcenter.y, GOcenter.z);



            }
        }

        if (Input.GetMouseButtonUp(0))
        {

            isDrag = false;
        }
    }


}
