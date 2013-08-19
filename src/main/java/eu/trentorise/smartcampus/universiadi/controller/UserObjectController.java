package eu.trentorise.smartcampus.universiadi.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.CollectionCallback;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import eu.trentorise.smartcampus.presentation.common.exception.DataException;
import eu.trentorise.smartcampus.presentation.common.exception.NotFoundException;
import eu.trentorise.smartcampus.universiadi.model.UserObject;

@Controller("userObjectController")
public class UserObjectController {

	@Autowired(required = false)
	MongoTemplate db;

	@PostConstruct
	public void init() {

		/*
		 * UserObject x = new UserObject(); x.setUser("a"); x.setPassword("a");
		 * x.setToken("tokendia");
		 * 
		 * db.save(x);
		 */

	}

	@RequestMapping(method = RequestMethod.GET, value = "/user")
	public @ResponseBody
	ArrayList<DBObject> getAllUsers(HttpServletRequest request,
			HttpServletResponse response, HttpSession session) {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Users");
		// BasicDBObject query = new BasicDBObject("nome", sport);
		DBCursor cursorEv = coll.find();

		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login/{user}/{password}")
	public @ResponseBody
	ArrayList<DBObject> getAuthToken(HttpServletRequest request,
			@PathVariable("user") String user, HttpServletResponse response,
			@PathVariable("password") String password,
			HttpServletResponse response1, HttpSession session)
			throws JSONException {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Users");
		ArrayList<BasicDBObject> listParams = new ArrayList<BasicDBObject>();

		BasicDBObject par1 = new BasicDBObject("user", user);
		BasicDBObject par2 = new BasicDBObject("password", password);

		listParams.add(par1);
		listParams.add(par2);
		BasicDBObject query = new BasicDBObject();
		query.put("$and", listParams);

		DBCursor cursorEv = coll.find(query);
		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

	@RequestMapping(method = RequestMethod.GET, value = "/login/{token}")
	public @ResponseBody
	ArrayList<DBObject> getUserData(HttpServletRequest request,
			@PathVariable("token") String tok, HttpServletResponse response,
			HttpSession session) {

		ArrayList<DBObject> evappl = new ArrayList<DBObject>();
		DBCollection coll = db.getCollection("Users");
		BasicDBObject query = new BasicDBObject("token", tok);
		DBCursor cursorEv = coll.find(query);

		while (cursorEv.hasNext()) {
			DBObject obj = cursorEv.next();
			evappl.add(obj);
		}

		return evappl;
	}

}