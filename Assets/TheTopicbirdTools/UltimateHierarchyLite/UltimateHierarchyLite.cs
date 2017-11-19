using UnityEngine;
using System;
using System.Collections.Generic;
using UltimateHierarchy;

// UltimateHierarchyLite by The Topicbird - talk@thetopicbird.com

namespace UltimateHierarchy {
	
	[ExecuteInEditMode] public class UltimateHierarchyLite : MonoBehaviour {
		// a class that stores our GameObjects informations
		[Serializable] public class SlotLite {
			public GameObject obj;
			[HideInInspector] public bool deleteQuestion = false;

			public bool showTransform = false; // foldout for GameObjects and Prefabs
		}

		// the list of Slots
		[HideInInspector] public List <SlotLite> list = new List<SlotLite>();
		
		/// <summary>Adds a custom-made Slot to the list. (Can be set up in the Inspector because ObjectToggle.Slot is Serializable)</summary>
		public void addSlot(SlotLite slot){
			if (!list.Contains (slot)) {
				list.Add (slot);
			}
		}
		
		/// <summary>Adds a specific GameObject to the list.</summary>
		public void addGameObject(GameObject go){
			SlotLite slot = new SlotLite ();
			slot.obj = go;
			addSlot (slot);
		}
		
		/// <summary>Removes a specific GameObject from the list.</summary>
		public void removeGameObject(GameObject go){
			if (list == null || list.Count == 0) {
				return;
			}
			
			for (int i = list.Count - 1; i >= 0; i--) {
				if (list[i].obj == go){
					list.Remove (list[i]);
				}
			}
		}
		
		
		
		
		
		/// <summary>Deletes a specific object.</summary>
		public void delete (int id){
			if (id >= 0 && id < list.Count) {
				list.Remove (list [id]);
			} else {
				Debug.LogError(this + " Slot [" + id + "] is out of list index.");
			}
		}
		
		
		
		
		
		/// <summary>Return if a GameObject / Prefab / Script is turned ON (true) or OFF (false)./summary>
		public bool getSolo (int id){
			if (!list [id].obj.activeSelf){
				return false;
			}
	
			bool allDisabled = true;
			for (int i = 0; i < list.Count; i++){
				if (i == id) {
					continue;
				}
				
				if (list[i].obj != null) {
					if (list [i].obj.activeSelf){
						allDisabled = false;
					}
				}
			}
			return allDisabled;
		}
		
		/// <summary>Set an Object to be the only one of its kind to be Active or Disabled.</summary>
		public void setSolo(int id, bool b){
			for (int i = 0; i < list.Count; i++) {
				setActive(i, !b);
			}

			setActive(id, b);
		}

		/// <summary>Toggle an Object to be the only one of its kind to be Active or Disabled.</summary>
		public void toggleSolo(int id){
			setSolo (id, !getSolo (id));
		}

		
		
		
		
		/// <summary>Get a specific objects Active status.</summary>
		public bool getActive(int id){
			if (list [id].obj != null) {
				return list [id].obj.activeSelf;
			}
			return false;
		}
		
		/// <summary>Set a specific objects Active status.</summary>
		public void setActive(int id, bool b){
			if (list [id].obj != null) {
				list [id].obj.SetActive (b);
			}
		}
		
		/// <summary>Toggle a specific objects Active status.</summary>
		public void toggleActive (int id){
			if (list [id].obj != null) {
				setActive (id, !getActive(id));
			} 
		}
		
		
		
		
		
		/// <summary>Set all (respective types) objects Active status.</summary>
		public void setActiveUpdateAll (bool b){
			for (int i = 0; i < list.Count; i++){
				if (list[i].obj == null) {
					continue;
				}
				
				list [i].obj.SetActive (b);
			}
		}
		
		/// <summary>Flip all (respective types) objects Active status.</summary>
		public void flipAll (){
			for (int i = 0; i < list.Count; i++){
				if (list[i].obj == null) {
					continue;
				}
				
				toggleActive(i);
			}
		}
	} // end MonoBehaviour
	
} // end namespace