package com.yuqing.component;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.elasticsearch.action.admin.indices.create.CreateIndexRequestBuilder;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.exists.indices.IndicesExistsResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.yuqing.entity.Pile;

@Component
public class IndexStartup implements CommandLineRunner {
	
	private static final Logger logger = LoggerFactory.getLogger(IndexStartup.class);
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		indexDocument();
	}
	
	private List<Pile> fetchAllPiles() {
		List<Pile> results = jdbcTemplate.query("select id,pile_name,dev_status,run_status,city_id,longitude,latitude from t_charging_pile where del = 0", 
				new RowMapper<Pile>() {

					public Pile mapRow(ResultSet rs, int index) throws SQLException {
						// TODO Auto-generated method stub
						Pile pile = new Pile();
						pile.setId(rs.getString("id"));
						pile.setName(rs.getString("pile_name"));
						pile.setDevStatus(rs.getInt("dev_status"));
						pile.setStatus(rs.getInt("run_status"));
						pile.setCityId(rs.getString("city_id"));
						pile.setLongitude(rs.getDouble("longitude"));
						pile.setLatitude(rs.getDouble("latitude"));
						return pile;
					}
			
		});
		
		logger.info("Find " + results.size() + " results.");
		
		return results;
	}
	
	private void insertOrUpdatePiles(List<Pile> piles) throws UnknownHostException {
		TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
				.addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"), 9300));
		
		createGeoMapping(client);
		
		buildIndex(client, piles);
		
		client.close();
	}
	
	private void buildIndex(TransportClient client, List<Pile> piles) {
		for (Pile pile : piles) {
			IndexResponse response = client.prepareIndex("charge", "pile", pile.getId())
					.setSource(buildIndexJson(pile), XContentType.JSON).get();
			
			logger.info("id={},name={}", response.getId(), pile.getName());
		}
	}
	
	private void createGeoMapping(TransportClient client) {
		Map<String, Object> mapping = new HashMap<String, Object>();
		Map<String, Object> pile = new HashMap<String, Object>();
		Map<String, Object> properties = new HashMap<String, Object>();
		Map<String, Object> type = new HashMap<String, Object>();
		mapping.put("pile", pile);
		pile.put("properties", properties);
		properties.put("location", type);
		type.put("type", "geo_point");
		String source = JSONObject.toJSONString(pile);
		
		IndicesExistsResponse res = client.admin().indices().prepareExists("charge").execute().actionGet();

		if (!res.isExists()) {
			CreateIndexRequestBuilder indexResponseBuilder = client.admin().indices().prepareCreate("charge");
			indexResponseBuilder.addMapping("pile", source);
			
			CreateIndexResponse response = indexResponseBuilder.execute().actionGet();
			
			logger.info("mapping index={}", response.index());
		}
	}
	
	private String buildIndexJson(Pile pile) {
		Map<String, Object> source = new HashMap<String, Object>();
		
		Map<String, Double> location = new HashMap<String, Double>();
		
		source.put("devStatus", pile.getDevStatus());
		source.put("status", pile.getStatus());
		source.put("name", pile.getName());
		source.put("cityId", pile.getCityId());
		source.put("location", location);
		
		location.put("lat", pile.getLatitude());
		location.put("lon", pile.getLongitude());
		
		return JSONObject.toJSONString(source);
	}
	
	private void indexDocument() throws UnknownHostException {
		List<Pile> piles = fetchAllPiles();
		insertOrUpdatePiles(piles);
	}

}
