package br.inpe.dpi.terrabrasilis.util;

import java.io.Serializable;

/**
 * 
 * @author jether
 *
 */
public final class Constants implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * paths
	 */
	public static final String API = "api/";
	public static final String V1 = "v1/";
	
	/**
	 * resources
	 */
	public static final String VISION = "vision";
	public static final String LAYER = "layer";
	public static final String SUBDOMAIN = "subdomain";
	public static final String DATASOURCE = "datasource";
	public static final String TOOL = "tool";
	public static final String DOWNLOAD = "download";
	public static final String VISION_TO_VISION = "vision-to-vision";
	
	/**
	 * spring profiles
	 */
	public static final String SPRING_PROFILE_DEVELOPMENT = "dev";
	public static final String SPRING_PROFILE_DOCER = "docker";
        public static final String SPRING_PROFILE_PRODUCTION = "prod";
        
        
	/**
         * cache names
         */		
        public static final String CACHE_DEFORESTATION = "deforestation";
}
