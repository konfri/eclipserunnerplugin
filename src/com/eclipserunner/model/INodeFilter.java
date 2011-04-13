package com.eclipserunner.model;

/**
 * Filter node indicate if an object provided to filter methods should be skipped
 *
 * @author vachacz
 */
public interface INodeFilter {

	boolean filter(ILaunchNode launchNode);
	boolean filter(ICategoryNode categoryNode);

	void setFilterProperty(String key, String value);
	String getFilterProperty(String key);
	
}
