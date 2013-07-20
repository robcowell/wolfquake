package controllers;

import java.util.ArrayList;

import models.*;

import org.geonames.Toponym;
import org.geonames.ToponymSearchCriteria;
import org.geonames.ToponymSearchResult;
import org.geonames.WebService;

import play.libs.WS;
import play.libs.WS.HttpResponse;
import play.mvc.Controller;

import com.google.gson.Gson;


public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void getLocation(String location) throws Exception
    {
	WebService.setUserName("robjcowell"); 
	
	ToponymSearchCriteria searchCriteria = new ToponymSearchCriteria();
	  searchCriteria.setQ(location);
	  ToponymSearchResult searchResult = WebService.search(searchCriteria);
	  for (Toponym toponym : searchResult.getToponyms())
	  {
	     getQuakes(toponym.getLatitude(), toponym.getLongitude());
	  }
	  
    }
    
    public static void getQuakes(double latitude, double longitude)
    {
	double north = latitude+5;
	double south = latitude-5;
	double east = longitude-5;
	double west = longitude+5;
	
	String url =  "http://api.geonames.org/earthquakesJSON?north=" + north + "&south=" + south + "&east=" + east + "&west=" + west + "&username=robjcowell";
	HttpResponse res = WS.url(url).get();
	
	Gson gson = new Gson();
	

	EarthquakeList quakes = gson.fromJson(res.getJson(), EarthquakeList.class);
	System.out.println(quakes.earthquakes.size());
	for (earthquake quake : quakes.earthquakes)
	{
	    System.out.println(quake.eqid);
	}
	
	ArrayList<earthquake> quakelist = (ArrayList<earthquake>) quakes.earthquakes;
	render(quakelist);
    }
    

}