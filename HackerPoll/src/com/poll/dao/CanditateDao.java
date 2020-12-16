package com.poll.dao;
import com.poll.dto.*;
import com.poll.util.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


public class CanditateDao
{
	public ArrayList<Canditate> getCanditateDetails()
	{
		ArrayList<Canditate> ar=new ArrayList<Canditate>();
		Connection connection = DBConnect.getConnection();
		PreparedStatement ps = null;
		ResultSet rs = null;
		Canditate canditate=null;
		try 
		{
			connection = DBConnect.getConnection();
			ps = connection.prepareStatement("select * from canditate_t");
			rs = ps.executeQuery();
			if (rs.next())
			{
				canditate = new Canditate();
				canditate.setCanditateName(rs.getString(1));
				canditate.setChlngsol(rs.getInt(2));
				canditate.setExpertlevel(rs.getInt(3));
				canditate.setDatastr(rs.getInt(4));
				canditate.setAlgor(rs.getInt(5));
				canditate.setCplu(rs.getInt(6));
				canditate.setJva(rs.getInt(7));
				canditate.setPyth(rs.getInt(8));

			}
			
	     }
		catch(SQLException e)
		{
			System.out.println("SQl exception occured");
		}
		finally
		{
		}
		ar.add(canditate);
		return ar;

     }
}
