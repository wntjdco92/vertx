package com.hanbitplate.vertx;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;


public class MainVerticle extends AbstractVerticle {
	public static void main(String[] args) {
		Vertx vertx = Vertx.vertx();
		vertx.deployVerticle(new MainVerticle());
	}

	@Override
	public void start() throws Exception{
		Router router = Router.router(vertx);
		
		router.route("/api/main/section/:sectionCode/items").handler(ctx -> {
			HttpServerRequest request = ctx.request();
			HttpServerResponse response = ctx.response();
			
			String sectionCode = request.getParam("sectionCode");
					
			response.putHeader("content-type", "application/json;charset=UTF-8");
			response.sendFile("json/section" + sectionCode + ".items.json");
		});
		
		vertx.createHttpServer().requestHandler(router::accept).listen(8080);
	}
}
