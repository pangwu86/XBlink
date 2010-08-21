package org.xblink.domdrivers;

import org.xblink.adapter.XMLAdapter;

public interface DomDriver {

	boolean canWork();
	XMLAdapter getAdapter();
}
