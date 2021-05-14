package com;

//For REST Service
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

//For XML
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

//For JSON
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.FundTest;

@Path("/FundTest")

public class FundManagement {
	FundTest fundtestObj = new FundTest();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readFund() {
		return fundtestObj.readFund();
	}

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertFund(@FormParam("amount") String amount, @FormParam("description") String description) {
		String output = fundtestObj.insertFund(amount, description);
		return output;
	}

	// update
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateFund(String fundtestData) {
//Convert the input string to a JSON object
		JsonObject fundtestObject = new JsonParser().parse(fundtestData).getAsJsonObject();
//Read the values from the JSON object
		String id = fundtestObject.get("id").getAsString();
		String amount = fundtestObject.get("amount").getAsString();
		String description = fundtestObject.get("description").getAsString();

		String output = fundtestObj.updateFund(id, amount, description);
		return output;
	}

	// all
	// delete
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteFund(String fundtestData) {
//Convert the input string to an XML document
		Document doc = Jsoup.parse(fundtestData, "", Parser.xmlParser());

		String id = doc.select("id").text();
		String output = fundtestObj.deleteFund(id);
		return output;
	}
}