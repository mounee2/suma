<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.util.*" %>
    <%@ page import="com.poll.dto.Canditate" %>
    <%@ page import="com.poll.dao.CanditateDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Hacker Pole Home</title>
</head>
<body>
<table>
<%
CanditateDao cd=new CanditateDao();
ArrayList<Canditate> canditateList =cd.getCanditateDetails();

for (Canditate canditate : canditateList) {
	
%>
<tr>
<td><input type="checkbox"name="CanditateName" value=" <%= canditate.getCanditateName()%>" />CanditateName</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getExpertlevel() %>" checked="chkboc_checked"/>ExpertLevel</td>
<td><input type="text"name="DatStruct" value=" <%=canditate.getDatastr() %>"/>DataStructure</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getAlgor()%>"/>Algorithm</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getCplu() %>"/>C++</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getJva() %>"/>Java</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getPyth() %>"/>Python</td>
<td><input type="text"name="ExpertLevel" value=" <%=canditate.getChlngsol()%>"/>ChallengeSolved</td>
</table>

<%
}
%> 
</body>
</html>