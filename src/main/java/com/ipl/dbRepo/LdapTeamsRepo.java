package com.ipl.dbRepo;
import java.util.*;
import com.ipl.entity.TeamEntity;
import com.ipl.dbConnection.LdapConnection;
import javax.naming.directory.*;
import javax.naming.NamingException;
import javax.naming.NamingEnumeration;

public class LdapTeamsRepo{

	        private final LdapConnection lc = new LdapConnection(); 

//==========LDAP=========================================================================================================
public boolean addTeamInLdap(TeamEntity team, int generatedId) throws NamingException {
    DirContext ctx = null;
    try {
        // Get LDAP connection
        ctx = lc.getConnection();
        if (ctx == null) {
            System.out.println("Connection failed!");
            return false;
        }

        // Use uid in the DN
        String dn = "uid=" + generatedId + ",ou=Teams,dc=krushna,dc=com";

        // Prepare attributes
        Attributes attrs = new BasicAttributes();
        Attribute objClass = new BasicAttribute("objectClass");
        objClass.add("inetOrgPerson");
        attrs.put(objClass);

        // Required attributes for inetOrgPerson 
        attrs.put("uid", String.valueOf(generatedId));        
        attrs.put("cn", team.getT_Name());                    
        attrs.put("sn", team.getCity());                       

        attrs.put("description", "Season: " + team.getSeason());

        System.out.println("Creating LDAP entry with DN: " + dn);
        ctx.createSubcontext(dn, attrs);

        return true;

    } finally {
        try {
            if (ctx != null) ctx.close();
        } catch (Exception e) {
            System.out.println("Error closing LDAP context: " + e.getMessage());
        }
    }
}
//========end===================================================================================

//-----View Teams In LDAP-----------------------------------------------------------------------------------------------
public Map<String, String> viewAllTeamsFromLdap() {
    DirContext ctx = null;
    Map<String, String> teamMap = new HashMap<>();

    try {
        // Get connection 
        ctx = lc.getConnection();
        if (ctx == null) {
            System.out.println("Connection failed!");
            return null;
        }

        // Base DN where teams are stored
        String baseDn = "ou=Teams,dc=krushna,dc=com";

        // Search filter to get all objects that have cn
        String filter = "(uid=*)";

        // Attributes to return
        String[] attrsToReturn = {"cn", "description" , "sn"};

        // Search controls
        SearchControls searchControls = new SearchControls();
        
        // only inside ou
        searchControls.setSearchScope(SearchControls.ONELEVEL_SCOPE); 
        searchControls.setReturningAttributes(attrsToReturn);

        // Perform search
        NamingEnumeration<SearchResult> results = ctx.search(baseDn, filter, searchControls);

        System.out.println("======== Teams in LDAP ===================");
        while (results.hasMore()) {
            SearchResult sr = results.next();
            Attributes attrs = sr.getAttributes();

            String teamName = (attrs.get("cn") != null) ? attrs.get("cn").get().toString() : "NA";
            String desc = (attrs.get("description") != null) ? attrs.get("description").get().toString() : "NA";
       //     String sn =   (attrs.get("sn") != null) ? attrs.get("sc").get() : "NA";
            //System.out.println("sn = " + sn);
            System.out.println("Team: " + teamName + ", Description: " + desc + "city -");
            teamMap.put(teamName, desc );
        }
    } catch (Exception e) {
                   e.printStackTrace();
    } finally {
        try {
            if (ctx != null) ctx.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    return teamMap;
}

		//---------------------------

//---LDAP Update---------------------------------------------------------------------------
			// update team name in ldap
			public boolean updateCnInLdap(String oldCn, String newCn,String uid) throws NamingException{
				DirContext ctx = null;
				try{
					ctx = lc.getConnection();
		       if (ctx == null) {
            throw new NamingException("Connection failed.");
        }

        // Old DN
        String oldDn = "cn=" + oldCn + ",ou=Teams,dc=krushna,dc=com";
        // New DN
        String newDn = "cn=" + newCn + ",ou=Teams,dc=krushna,dc=com";

        // Rename entry
        ctx.rename(oldDn, newDn);
	      return true;
        	

				} finally{
						if (ctx != null) {
                                        	 try {
                                                 ctx.close();
                                          	} catch (Exception e) {
                                                	 e.printStackTrace();
                                         		}
                                 		}

				
				}
			}
			
			//-------------------------------------------------------------------------------------
		
						//----- Delete from LDAP using uid 
public boolean deleteTeamFromLdap(int uid) throws NamingException {
    DirContext ctx = null;
    try {
        // Get LDAP connection
        ctx = lc.getConnection();
        if (ctx == null) {
            throw new NamingException("Connection to LDAP failed!");
        }

        // DN for the team entry using uid
        String dn = "uid=" + uid + ",ou=Teams,dc=krushna,dc=com";

        // Delete the team entry
        ctx.destroySubcontext(dn);

        System.out.println("Team with UID '" + uid + "' deleted successfully from LDAP.");
        return true;

    } finally {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


//------------------------------------------------------------------------
// delete team from ldap using team name 
public boolean deleteTeamFromLdapByCn(String teamName) throws NamingException {
    DirContext ctx = null;
    try {
       
        ctx = lc.getConnection();
        if (ctx == null) {
            throw new NamingException("LDAP connection failed!");
        }
        String searchBase = "ou=Teams,dc=krushna,dc=com";
        String searchFilter = "(cn=" + teamName + ")";

        SearchControls controls = new SearchControls();
        controls.setSearchScope(SearchControls.SUBTREE_SCOPE);

        NamingEnumeration<SearchResult> results = ctx.search(searchBase, searchFilter, controls);

        if (!results.hasMore()) {
            System.out.println("No LDAP entry found with cn = " + teamName);
            return false;
        }

        // Get the full DN 
        SearchResult result = results.next();
        String dn = result.getNameInNamespace();

        //  Delete the entry
        ctx.destroySubcontext(dn);

        System.out.println("Team with cn '" + teamName + "' deleted successfully (DN: " + dn + ")");
        return true;

    } finally {
        if (ctx != null) {
            try {
                ctx.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
  
}

	

}
