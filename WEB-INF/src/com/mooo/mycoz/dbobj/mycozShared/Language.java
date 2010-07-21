package com.mooo.mycoz.dbobj.mycozShared;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.HashMap;


import com.mooo.mycoz.dbobj.DBObject;

/**

 */
public class Language extends DBObject{

    private static Log log = LogFactory.getLog(Language.class);
    /**
     */
    public Language()
	throws SQLException{
	getConection("mycozShared");
	getStatement();
    } /* Language() */

    public HashMap getValues()
        throws SQLException {

	HashMap< String, String> lMap = new HashMap< String, String>();
	lMap.put(null,"--select--");
	ResultSet rs = null;
	String sql = "SELECT ID,Name FROM Language WHERE ID > 0";
	rs = getResultSet(sql);
	while(rs.next()) {
		lMap.put(rs.getString("ID"),rs.getString("Name"));
               }

	return lMap;
    } /* getValues() */

    public int getNextID()
        throws SQLException {

		int id = 0;
		ResultSet rs = null;
		String sql = "SELECT MAX(ID) AS MaxId FROM Language";
		rs = getResultSet(sql);
		if(rs.first()) id=rs.getInt("MaxId")+1;

	return id;
    } /* getNextID() */

    public String getName(String id)
        throws SQLException {
		String name = "";
		ResultSet rs = null;
		String sql = "SELECT Name FROM Language WHERE ID='"+id+"'";
		rs = getResultSet(sql);
		if(rs.first()) name = rs.getString("Name");

	return name;
    }
}
