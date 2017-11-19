#if UNITY_EDITOR

using UnityEngine;
using UnityEditor;
using System.Reflection;
using System.Collections;
using System.Collections.Generic;
using UltimateHierarchy;

// UltimateHierarchyLite by The Topicbird - talk@thetopicbird.com

[CustomEditor(typeof(UltimateHierarchyLite))]
public class UltimateHierarchyLiteEditor : Editor {
	UltimateHierarchyLite script; // reference to the ObjectToggle
	Object emptyObject = null;
	
	// settings
	readonly int buttonHeight = 16;
	
	void OnEnable () {
		script = (UltimateHierarchyLite)target;
	}
	
	void OnDisable () {
		refreshRequests ();
	}
	
	
	

	
	public override void OnInspectorGUI() {
		EditorUtility.SetDirty (target); // to keep changes made in play mode

		GUI.color = Color.white;
		
		GUIStyle boldLabel = new GUIStyle(EditorStyles.label);
		boldLabel.fontStyle = FontStyle.Bold;
		Spaces (1);
		
		EditorGUILayout.BeginVertical(EditorStyles.helpBox); // start of objects contents
		
		GUI.color = Color.white;
		EditorGUILayout.BeginHorizontal(); // start of objects contents
		
		GUILayout.Label("Add new here:", boldLabel, GUILayout.MinWidth(105), GUILayout.MaxWidth(105));
		emptyObject = (Object) EditorGUILayout.ObjectField(null, typeof(Object), true);
		if (emptyObject != null) {
			if (emptyObject.GetType() == typeof(GameObject)){
				UltimateHierarchyLite.SlotLite slot = new UltimateHierarchyLite.SlotLite ();
				slot.obj = (GameObject)emptyObject;
				script.list.Add(slot);

				emptyObject = null;
			}
		}

		EditorGUILayout.EndHorizontal(); // end of objects content
		
		EditorGUILayout.EndVertical(); // end of objects content

		if (script.list.Count > 1) {
			EditorGUILayout.BeginHorizontal ();
			drawAllEnableDisable (false);
			drawAllFlip (false);
			EditorGUILayout.EndHorizontal ();
		}
		
		for (int i = 0; i < script.list.Count; i++) { // iterate through all objects
			updateType(i); // get the objects type

			if (script.list[i].obj == null){
				continue;
			}

			EditorGUI.BeginDisabledGroup(!script.list[i].obj.activeInHierarchy);
			EditorGUILayout.BeginHorizontal(EditorStyles.helpBox); // start of objects contents
			EditorGUI.EndDisabledGroup();
			EditorGUI.indentLevel++;
			script.list[i].showTransform = GUILayout.Toggle(script.list[i].showTransform, "", "Foldout", GUILayout.MaxWidth(10));
			
			drawObjectSlot(i);
			EditorGUI.indentLevel--;
			// specific GameObject content
			drawGameobjectEnableDisable(i);
			
			drawSoloButton(i);
			drawDeleteButton(i);
			EditorGUILayout.EndHorizontal(); // end of objects content
			
			EditorGUI.indentLevel++;
			drawTransform(i); // show the public variables of the script
			EditorGUI.indentLevel--;
		}
	}
	
	void Spaces(int s){
		for (int i = 0; i < s; i++) {
			EditorGUILayout.Space();
		}
	}
	
	void drawTransform(int i){
		if (script.list.Count == 0) {
			return;
		}
		
		GUI.color = Color.white;
		if (script.list [i].showTransform) {
			script.list [i].obj.transform.localPosition = EditorGUILayout.Vector3Field ("Position", script.list [i].obj.transform.localPosition);
			var v3 = script.list [i].obj.transform.localRotation.eulerAngles;
			v3 = EditorGUILayout.Vector3Field ("Rotation", v3);
			script.list [i].obj.transform.localRotation = Quaternion.Euler(v3);
			script.list [i].obj.transform.localScale = EditorGUILayout.Vector3Field ("Scale", script.list [i].obj.transform.localScale);
		}
	}	

	void updateType(int i){
		if (script.list[i].obj == null){
			script.delete(i);
		}
	}
	
	bool Foldout(bool foldout, GUIContent content, bool toggleOnLabelClick, GUIStyle style) {
		Rect position = GUILayoutUtility.GetRect(40f, 40f, 16f, 16f, style);
		return EditorGUI.Foldout(position, foldout, content, toggleOnLabelClick, style);
	}
	
	bool Foldout(bool foldout, string content, bool toggleOnLabelClick, GUIStyle style) {
		return Foldout(foldout, new GUIContent(content), toggleOnLabelClick, style);
	}
	
	GUIStyle getFoldoutStyle(){
		GUIStyle style = new GUIStyle(EditorStyles.foldout);
		style.fontStyle = FontStyle.Bold;
		return style;
	}
	
	GUIStyle getVariableFoldout(){
		GUIStyle style = new GUIStyle(EditorStyles.foldout);
		style.padding = new RectOffset ();
		style.margin = new RectOffset ();
		style.fontSize = 0;
		return style;
	}
	
	void drawAllEnableDisable(bool mini){
		string all = "";
		if (!mini)
			all += "ALL ";
		
		if (GUILayout.Button (all + "ON", GUILayout.MinHeight(buttonHeight))) {
			refreshRequests();
			script.setActiveUpdateAll (true);
		}
		if (GUILayout.Button (all + "OFF", GUILayout.MinHeight(buttonHeight))) {
			refreshRequests();
			script.setActiveUpdateAll (false);
		}
	}

	void drawAllFlip(bool mini){
		string all = "";
		if (!mini)
			all += "ALL ";
		
		if (GUILayout.Button (all + "FLIP", GUILayout.MinHeight(buttonHeight))) {
			refreshRequests();
			script.flipAll ();
		}
	}
	
	void drawObjectSlot(int i){
		GUI.color = Color.white;
		EditorGUI.BeginChangeCheck ();
		script.list[i].obj = (GameObject) EditorGUILayout.ObjectField(script.list[i].obj, typeof(GameObject), true);
		if (EditorGUI.EndChangeCheck ()) {
			EditorApplication.RepaintHierarchyWindow();
		}
	}
	
	void drawGameobjectEnableDisable(int i){
		string text = "";
		if (script.list[i].obj.activeSelf){
			GUI.color = Color.green;
			text = "ON";
		} else {
			GUI.color = Color.red;
			text = "OFF";
		}
		
		if (GUILayout.Button (text, GUILayout.MaxWidth(34), GUILayout.MinWidth(34), GUILayout.MinHeight(buttonHeight))) {
			refreshRequests();
			script.toggleActive (i);
		}
	}

	void drawSoloButton(int i){
		if (script.getSolo (i)){
			GUI.color = new Color(0.6f, 0.6f, 0.6f);
		} else {
			GUI.color = Color.white;
		}
		
		if (GUILayout.Button ("SOLO", GUILayout.MaxWidth(44), GUILayout.MinWidth(44), GUILayout.MinHeight(buttonHeight))) {
			refreshRequests();
			script.toggleSolo (i);
		}
	}
	
	void drawDeleteButton(int i){
		if (script.list[i].deleteQuestion){
			GUI.color = Color.red;
			if (GUILayout.Button ("?", GUILayout.MaxWidth(24), GUILayout.MinWidth(24), GUILayout.MinHeight(buttonHeight))) {
				refreshRequests();
				script.delete (i);
			}
		} else {
			GUI.color = (Color.red + Color.white * 2f) / 3f;
			if (GUILayout.Button ("X", GUILayout.MaxWidth(24), GUILayout.MinWidth(24), GUILayout.MinHeight(buttonHeight))) {
				refreshRequests();
				requestDelete (i);
			}
		}
	}
	
	void DebugRepaint(){
		EditorApplication.RepaintHierarchyWindow();
	}
	
	void refreshRequests(){
		for (int i = 0; i < script.list.Count; i++) {
			script.list[i].deleteQuestion = false;
		}
	}
	
	void requestDelete (int id){
		script.list [id].deleteQuestion = true;
	}

	// menu item
	[MenuItem("GameObject/Ultimate Hierarchy/Ultimate Hierarchy Lite", false, 10)]
	static void CreateCustomGameObject(MenuCommand menuCommand) {
		// Create a custom game object
		GameObject go = new GameObject("Ultimate Hierarchy Lite");
		go.AddComponent<UltimateHierarchyLite> ();
		// Ensure it gets reparented if this was a context click (otherwise does nothing)
		GameObjectUtility.SetParentAndAlign(go, menuCommand.context as GameObject);
		// Register the creation in the undo system
		Undo.RegisterCreatedObjectUndo(go, "Create " + go.name);
		Selection.activeObject = go;
	}
} // end Editor

#endif