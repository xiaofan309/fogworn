package com.desun.webservice;

import javax.jws.WebService;

@WebService
public interface DesunService{
	public String getVisibility(String userName);
}
