package com.ipl.dbConnection;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.*;
import java.util.Hashtable;

public class LdapConnection{

		public DirContext getConnection(){
	
			Hashtable<String,String> env = new Hashtable<>();
		
			env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.PROVIDER_URL,"ldap://localhost:389");
			env.put(Context.SECURITY_PRINCIPAL,"cn=admin,dc=krushna,dc=com");
			env.put(Context.SECURITY_AUTHENTICATION,"simple");
			env.put(Context.SECURITY_CREDENTIALS,"Sena@120");
		
			try{
				DirContext ctx = new InitialDirContext(env);
				System.out.println("Connected to LDAP server ");
				return ctx;  // returns LDAP connection
			} catch (NamingException e){
				System.out.println("LDAP Error: ");
				e.printStackTrace();
			}
		return null;
		}	
}
