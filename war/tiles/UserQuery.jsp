<%@page import="java.util.Iterator"%>
<%@page import="za.google.gotowords.model.UserSearch"%>
<%@page import="java.util.Set"%>
<%@page import="za.google.gotowords.model.DbUtils"%>
<%@page import="com.google.appengine.api.users.User"%>
<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%
    UserService userService = UserServiceFactory.getUserService();
    User user = userService.getCurrentUser();   
    if (user != null)
    {
    %>
    <p> User is not null ... </p>
    <table>
   
    <%
      DbUtils utils = new DbUtils();
      Set<UserSearch> set = utils.getData(user);
      Iterator<UserSearch> it = set.iterator();
      for(;it.hasNext();)
      {
    	  UserSearch u_search = it.next();
    %>
     <tr><td><%=u_search.getQuery() %></td>
         <td><%=u_search.getType() %></td>
         <td><%=u_search.getFrequency() %></td></tr>
    <% 
      }
  %>
    </table>
  <%} // end if
  else{ 
  %>
  <p>User is null </p>
  <%}%>