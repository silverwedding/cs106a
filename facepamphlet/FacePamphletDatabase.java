/*
 * File: FacePamphletDatabase.java
 * -------------------------------
 * This class keeps track of the profiles of all users in the
 * FacePamphlet application.  Note that profile names are case
 * sensitive, so that "ALICE" and "alice" are NOT the same name.
 */

import java.util.*;

public class FacePamphletDatabase implements FacePamphletConstants {

	/** 
	 * Constructor
	 * This method takes care of any initialization needed for 
	 * the database.
	 */
	public FacePamphletDatabase() {
		
	}
	
	
	/** 
	 * This method adds the given profile to the database.  If the 
	 * name associated with the profile is the same as an existing 
	 * name in the database, the existing profile is replaced by 
	 * the new profile passed in.
	 */
	public void addProfile(FacePamphletProfile profile) {
		profiles.put(profile.getName(), profile);
	}

	
	/** 
	 * This method returns the profile associated with the given name 
	 * in the database.  If there is no profile in the database with 
	 * the given name, the method returns null.
	 */
	public FacePamphletProfile getProfile(String name) {
		if(profiles.get(name) != null) {
			return profiles.get(name);
		}
		else {		
			return null;
		}
	}
	
	
	/** 
	 * This method removes the profile associated with the given name
	 * from the database.  It also updates the list of friends of all
	 * other profiles in the database to make sure that this name is
	 * removed from the list of friends of any other profile.
	 * 
	 * If there is no profile in the database with the given name, then
	 * the database is unchanged after calling this method.
	 */
	public void deleteProfile(String name) {
		if(profiles.get(name) != null) {
			FacePamphletProfile profile = profiles.get(name);
			Iterator<String> friends = profile.getFriends();	
			
			while (profile.getFriends().hasNext()){	
				String friend = friends.next();
				FacePamphletProfile friendProfile = profiles.get(friend);
				friendProfile.removeFriend(name);
			}
			profiles.remove(name);			
		}
		
	}

	
	/** 
	 * This method returns true if there is a profile in the database 
	 * that has the given name.  It returns false otherwise.
	 */
	public boolean containsProfile(String name) {
		if(profiles.get(name) != null) {
			return true;
		}
		else {		
			return false;
		}
	}

	
	//Create hashmap of profiles
	private HashMap<String,FacePamphletProfile> profiles =
		new HashMap<String,FacePamphletProfile>();
}
