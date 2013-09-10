package eu.trentorise.smartcampus.universiadi.controller;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

import org.apache.commons.httpclient.util.HttpURLConnection;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lobobrowser.html.renderer.RRelative;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
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
import eu.trentorise.smartcampus.universiadi.model.FAQObj;
import eu.trentorise.smartcampus.universiadi.model.UserObj;

@Controller("FaqController")
public class FaqController {
	@Autowired(required = false)
	MongoTemplate db;

	@PostConstruct
	public void init() {

	}

	public static String httpGet(String urlStr) throws IOException {
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet(urlStr);
		HttpResponse response = client.execute(request);
		String str = "";

		// Get the response
		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		String line = "";
		while ((line = rd.readLine()) != null) {
			str = str + line;
		}
		return str;
	}

	public static String[] getSinonimi(String tag) throws IOException,
			JSONException {
		String firstPartString = "http://thesaurus.altervista.org/thesaurus/v1?word=";
		String secondPartString = "&language=it_IT&key=LzMxaSPiL9Uxyk3RmbWr&output=json";
		JSONObject jBig = null;

		String compositeUrlString = firstPartString + tag + secondPartString;
		String jsonStringSinonimi = httpGet(compositeUrlString);
		String[] arrSinonimi = null;

		ArrayList<String> listaSinonimi = new ArrayList<String>();

		if (!jsonStringSinonimi.contains("error")) {
			jBig = new JSONObject(jsonStringSinonimi);
			JSONArray respArr = jBig.getJSONArray("response");
			JSONObject jMid = respArr.getJSONObject(0);
			JSONObject jLitt = jMid.getJSONObject("list");

			String sinonimiRaw = jLitt.getString("synonyms");

			arrSinonimi = sinonimiRaw.split("\\|");
			return arrSinonimi;

		} else {
			return arrSinonimi;
		}

	}

	public ArrayList<ExtendedAnswer> getAnswerFromTag(MongoTemplate db,
			ArrayList<String> tagList) {
		ArrayList<ExtendedAnswer> mResult = new ArrayList<ExtendedAnswer>();
		Map<Integer, Integer> mapID = new HashMap<Integer, Integer>();

		for (String tag : tagList) {
			DBCollection coll = db.getCollection("idTagObject");
			BasicDBObject queryForID = new BasicDBObject("tag", tag);
			DBCursor cursorID = coll.find(queryForID);

			while (cursorID.hasNext()) {
				ArrayList<Integer> listID = (ArrayList<Integer>) cursorID
						.next().get("id");
				for (Integer id : listID) {
					if (mapID.containsKey(id))
						mapID.put(id, mapID.get(id).intValue() + 1);
					else
						mapID.put(id, 1);
				}

			}
		}

		ValueComparator bvc = new ValueComparator(mapID);
		TreeMap<Integer, Integer> mapIDSorted = new TreeMap<Integer, Integer>(
				bvc);
		mapIDSorted.putAll(mapID);

		DBCollection linkFAQ = db.getCollection("faqObject");
		ArrayList<ExtendedAnswer> extAnswer = new ArrayList<FaqController.ExtendedAnswer>();
		Object[] mapArray = mapIDSorted.entrySet().toArray();

		for (Object obj : mapArray) {
			Entry<Integer, Integer> entry = (Entry<Integer, Integer>) obj;
			BasicDBObject queryForAQ = new BasicDBObject("_id",
					(Integer) entry.getKey());
			DBCursor cursor = linkFAQ.find(queryForAQ);

			while (cursor.hasNext()) {
				DBObject aqFromDB = cursor.next();
				ExtendedAnswer index = new ExtendedAnswer();

				index.setAnswer((String) aqFromDB.get("risposta"));
				index.setQuestion((String) aqFromDB.get("domanda"));
				index.setNumberOfOccurence((Integer) entry.getValue());

				extAnswer.add(index);
			}
		}
		return extAnswer;
	}

	private class ValueComparator implements Comparator<Integer> {

		Map<Integer, Integer> base;

		public ValueComparator(Map<Integer, Integer> base) {
			this.base = base;
		}

		// Note: this comparator imposes orderings that are inconsistent with
		// equals.
		public int compare(Integer a, Integer b) {
			if (base.get(b) - base.get(a) == 0)
				return 1;
			return (base.get(b) - base.get(a));
		}
	}

	private class ExtendedAnswer {
		private String question;
		private String answer;
		private int numberOfOccurence;

		public ExtendedAnswer(String question, String answer,
				int numberOfOccurence) {
			super();
			this.question = question;
			this.answer = answer;
			this.numberOfOccurence = numberOfOccurence;
		}

		public ExtendedAnswer() {
			this(null, null, -1);
		}

		public String getQuestion() {
			return question;
		}

		public void setQuestion(String question) {
			this.question = question;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public int getNumberOfOccurence() {
			return numberOfOccurence;
		}

		public void setNumberOfOccurence(int numberOfOccurence) {
			this.numberOfOccurence = numberOfOccurence;
		}

	}

	public void insertTagWithID(MongoTemplate db, ArrayList<String> listTag,
			Integer ID) {

		DBCollection coll = db.getCollection("idTagObject");
		BasicDBObject getAllRowWithTag = new BasicDBObject();
		for (String simpleTag : listTag) {

			getAllRowWithTag.append("tag", simpleTag);
			DBCursor cursor = coll.find(getAllRowWithTag).limit(1);

			if (cursor.size() != 0) {
				DBObject obj = cursor.next();
				ArrayList<Integer> listID = (ArrayList<Integer>) obj.get("id");
				ArrayList<Integer> listSecondID = (ArrayList<Integer>) listID
						.clone();
				listSecondID.add(ID);

				BasicDBObject newObj = new BasicDBObject();
				newObj.put("id", listSecondID);
				newObj.put("tag", simpleTag);
				coll.update(obj, newObj);
			} else {
				ArrayList<Integer> listID = new ArrayList<Integer>();
				listID.add(ID);
				BasicDBObject newObj = new BasicDBObject();
				newObj.put("id", listID);
				newObj.put("tag", simpleTag);
				coll.insert(newObj);
			}
		}
	}

	public void dbinsert(String domanda, String risposta) throws IOException,
			JSONException {
		DBCollection coll = db.getCollection("faqObject");

		DBCursor cur = coll.find().sort(new BasicDBObject("_id", -1));
		Integer a = null;
		Integer nid = null;
		if (!cur.hasNext()) {
			nid = 0;
		} else {
			a = (Integer) cur.next().get("_id");
			nid = a + 1;
		}
		FAQObj x = new FAQObj();
		x.setId(nid);
		x.setDomanda(domanda);
		x.setRisposta(risposta);
		db.save(x, "faqObject");

		// estrapolazione tag
		domanda = domanda.toLowerCase();
		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", "");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("  ", " ");
		domanda = domanda.replace("%3F", " ");
		domanda = domanda.replace("?", " ");
		domanda = domanda.replace("%3F=", " ");
		domanda = domanda.replace("Ã¨", "è");

		String stringTempL;
		String[] stringRichiesta = domanda.split(" ");
		ArrayList<String> listTag = new ArrayList<String>();
		for (String stringTemp : stringRichiesta) {
			if (stringTemp.length() > 3) {

				listTag.add(stringTemp.toLowerCase());
				stringTempL = stringTemp.toLowerCase();

				String[] sinonimiTag = getSinonimi(stringTempL);
				if (sinonimiTag != null) {

					for (String simpleSin : sinonimiTag) {
						listTag.add(simpleSin);
					}
				}

			}
		}
		insertTagWithID(db, listTag, nid);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insertfaq")
	public @ResponseBody
	void insertfaq(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestBody FAQObj domanda)
			throws DataException, IOException, NotFoundException, JSONException {

		dbinsert(domanda.getDomanda(), domanda.getRisposta());
	}

	@RequestMapping(method = RequestMethod.POST, value = "/sinonimi")
	public @ResponseBody
	String[] sinonimi(HttpServletRequest request, HttpServletResponse response,
			HttpSession session, @RequestBody String parola)
			throws DataException, IOException, NotFoundException, JSONException {

		String[] s = getSinonimi(parola);
		return s;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/insertmultifaq")
	public @ResponseBody
	void insertmultifaq(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody String domandaobj) throws DataException, IOException,
			NotFoundException {
		try {
			JSONArray domanda = (new JSONObject(domandaobj))
					.getJSONArray("item");
			for (int o = 0; o < domanda.length(); o++) {

				// String valueUTF8 = new String(valueISO.getBytes(), "UTF-8");
				String qst = new String(domanda.getJSONObject(o)
						.getString("question").getBytes(), "UTF-8");
				String answ = new String(domanda.getJSONObject(o)
						.getString("answer").getBytes(), "UTF-8");
				dbinsert(qst, answ);

			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@RequestMapping(method = RequestMethod.POST, value = "/domanda")
	public @ResponseBody
	ArrayList<FAQObj> domandaFaq(HttpServletRequest request,
			HttpServletResponse response, HttpSession session,
			@RequestBody String domanda) throws DataException, IOException,
			NotFoundException {

		domanda = domanda.replace("%27", " ");
		domanda = domanda.replace("+", " ");
		domanda = domanda.replace("=", "");
		domanda = domanda.replace("%3B", " ");
		domanda = domanda.replace("%2C", " ");
		domanda = domanda.replace(".", " ");
		domanda = domanda.replace("%21", " ");
		domanda = domanda.replace("Ã¨", "è");

		domanda = domanda.replace("  ", " ");
		// domanda = domanda.replaceAll("\\s+$", "");

		String[] stringRichiesta = domanda.split(" ");

		ArrayList<String> listString = new ArrayList<String>();

		for (String stringTemp : stringRichiesta) {

			if (stringTemp.length() > 3) {
				listString.add(stringTemp.toLowerCase());
			}
		}
		ArrayList<FAQObj> answ = new ArrayList<FAQObj>();
		Integer tottag = listString.size();
		ArrayList<ExtendedAnswer> answerList = getAnswerFromTag(db, listString);

		for (ExtendedAnswer answer : answerList) {
			Integer occ = answer.getNumberOfOccurence();
			FAQObj simpleAnsw = new FAQObj();
			simpleAnsw.setRisposta(answer.getAnswer());
			simpleAnsw.setDomanda(answer.getQuestion());
			simpleAnsw.setTotalTag(tottag);
			simpleAnsw.setUsefulTag(occ);

			if ((simpleAnsw.getTotalTag() / simpleAnsw.getUsefulTag()) <= 2)
				answ.add(simpleAnsw);
		}
		// String valueUTF8 = new String(valueISO.getBytes(), "UTF-8");
		return answ;

	}

}
