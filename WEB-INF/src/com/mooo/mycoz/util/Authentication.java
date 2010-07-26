package com.mooo.mycoz.util;

public class Authentication {
	
    private static Object initLock = new Object();
    private static Authentication factory = null;
    
    private Integer userID;
    private String action;
    private String method;
    
    private Authentication(Integer userID,String action,String method){
		this.userID=userID;
		this.action=action;
		this.method=method;
    }
    
    public static Authentication getInstance(Integer userID,String action,String method) {

        if (factory == null) {
            synchronized(initLock) {
                if (factory == null) {
                	factory = new Authentication(userID,action,method);
                }
            }
        }
        return factory;
    }
    
	public Boolean checkAuth() {
		if (checkGroup() || checkRole() )
			return true;
		else
			return false;
	}
	
	public Boolean checkGroup() {
		Boolean state = false;
		try {

			if (userID == 1)
				return true;

			String sql = "";

			sql += "select {us.*},{ug.*},{gm.*},{co.*},{ac.*},{ag.*}"
					+ "from User us ,UserGroup ug, GroupMember gm, Controller co,Action ac, AuthGroup ag "
					+ "where us.id=gm.userId "
					+ "and ug.id=gm.groupId "
					+ "and ag.controllerID=co.id "
					+ "and ag.actionId=ac.id "
					+ "and ag.groupID=ug.id "
					+ "and us.id=:userID and co.name=action and ac.name=:method";

			sql += " AND userID='"+userID+"'";
			sql += " AND action='"+action+"'";
			sql += " AND method='"+method+"'";

				state = true;

		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return state;
	}

	public Boolean checkRole() {
		Boolean state = false;
		try {
			if (userID == 1)
				return true;

			String sql = "";

			sql += "select {us.*},{ro.*},{rm.*},{co.*},{ac.*},{ar.*}"
					+ "from User us ,UserGroup ug, GroupMember gm, Controller co,Action ac, AuthGroup ag "
					+ "where us.id=rm.userId "
					+ "and ro.id=rm.roleId "
					+ "and ar.controllerID=co.id "
					+ "and ar.actionId=ac.id "
					+ "and ar.roleId=ro.id "
					+ "and us.id=:userID and co.name=action and ac.name=:method";

			sql += " AND userID='"+userID+"'";
			sql += " AND action='"+action+"'";
			sql += " AND method='"+method+"'";

				state = true;

		} catch (Exception e) {
			e.fillInStackTrace();
		}
		return state;
	}
}
