package de.seifi.service_test.security;

public class RestResource {
    private static final String VERSION_ONE = "/v1";
    public static final String ROOT_PATH_V1 = "/api" + VERSION_ONE;
    public static final String AUTHENTICATION_PATH = ROOT_PATH_V1 + "/auth/login";
    
    private RestResource() {
    	
    }
}