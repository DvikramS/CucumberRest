package com.dcom.APIDriver;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class RestParameters {
	
	private Map<String,Object> path=new HashMap<String, Object>();
	private Map<String,Object> query=new HashMap<String, Object>();
	private Map<String,Object> header=new HashMap<String, Object>();
	
	public RestParameters() {
		
	}

	public Map<String, Object> getPath() {
		return path;
	}

	public void setPath(Map<String, Object> path) {
		this.path = path;
	}

	public Map<String, Object> getQuery() {
		return query;
	}

	public void setQuery(Map<String, Object> query) {
		this.query = query;
	}

	public Map<String, Object> getHeader() {
		return header;
	}

	public void setHeader(Map<String, Object> header) {
		this.header = header;
	}

	public <T> void setQuery(T... objects) {

		for (int i = 0; i < objects.length; i += 2) {
			if (!(objects[i] instanceof String)) {
			}

			this.query.put((String) objects[i], objects[i + 1]);
		}

	}
	
	public <T> void setHeader(T... objects) {

		for (int i = 0; i < objects.length; i += 2) {
			if (!(objects[i] instanceof String)) {
			}

			this.header.put((String) objects[i], objects[i + 1]);
		}

	}
	
	public <T> void setPath(T... objects) {

		for (int i = 0; i < objects.length; i += 2) {
			if (!(objects[i] instanceof String)) {
			}

			this.path.put((String) objects[i], objects[i + 1]);
		}

	}
}
