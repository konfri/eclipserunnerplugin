package com.eclipserunner.model.impl;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.debug.core.ILaunchConfiguration;

import com.eclipserunner.model.IActionEnablement;
import com.eclipserunner.model.ILaunchConfigurationCategory;
import com.eclipserunner.model.ILaunchConfigurationChangeListener;
import com.eclipserunner.model.ILaunchConfigurationNode;

/**
 * @author vachacz
 */
public class LaunchConfigurationNode implements ILaunchConfigurationNode, IActionEnablement {

	private ILaunchConfiguration launchConfiguration;
	private ILaunchConfigurationCategory launchConfigurationCategory;
	private boolean bookmarked;

	private Set<ILaunchConfigurationChangeListener> launchConfigurationChangeListeners = new HashSet<ILaunchConfigurationChangeListener>();

	public LaunchConfigurationNode() {
	}

	public void setLaunchConfiguration(ILaunchConfiguration launchConfiguration) {
		this.launchConfiguration = launchConfiguration;
	}

	public ILaunchConfiguration getLaunchConfiguration() {
		return launchConfiguration;
	}

	public void setLaunchConfigurationCategory(ILaunchConfigurationCategory launchConfigurationCategory) {
		this.launchConfigurationCategory = launchConfigurationCategory;
	}

	public ILaunchConfigurationCategory getLaunchConfigurationCategory() {
		return launchConfigurationCategory;
	}

	public void setBookmarked(boolean state) {
		this.bookmarked = state;
		fireLaunchConfigurationChangedEvent();
	}

	public boolean isBookmarked() {
		return bookmarked;
	}

	public void addLaunchConfigurationChangeListener(ILaunchConfigurationChangeListener listener) {
		launchConfigurationChangeListeners.add(listener);
	}

	public void removeLaunchConfigurationChangeListener(ILaunchConfigurationChangeListener listener) {
		launchConfigurationChangeListeners.remove(listener);
	}

	private void fireLaunchConfigurationChangedEvent() {
		for (ILaunchConfigurationChangeListener launchConfigurationChangeListener : launchConfigurationChangeListeners) {
			launchConfigurationChangeListener.launchConfigurationChanged();
		}
	}

	public boolean isRemovable() {
		return true;
	}

	public boolean isRenamable() {
		return true;
	}

	// TODO BARY code review ...
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ILaunchConfigurationNode) {
			ILaunchConfigurationNode node = (ILaunchConfigurationNode) obj;
			return launchConfiguration.equals(node.getLaunchConfiguration());
		}
		return super.equals(obj);
	}

}
