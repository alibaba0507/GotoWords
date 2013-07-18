package za.google.gotowords.servlets;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.*;

import za.google.gotowords.model.DbUtils;
import za.google.gotowords.model.UserSearch;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.Filter;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

@SuppressWarnings("serial")
public class GotoWordsServlet extends HttpServlet {

	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();
		String param = req.getParameter("p");
		if (param == "add") {
			DbUtils utils = new DbUtils();
			String q = req.getParameter("query");

			UserSearch u_search = new UserSearch();
			u_search.setQuery(q);
			u_search.setType(req.getParameter("type"));
			u_search.setFrequency(req.getParameter("frequency"));
			utils.add(user, u_search);

		} // end if
		resp.sendRedirect("index.jsp");
	}

}
