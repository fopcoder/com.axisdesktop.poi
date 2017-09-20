package com.axisdesktop.crawler_new.impl;

import java.util.List;

import com.axisdesktop.crawler_new.base.CrawlerQueue;
import com.axisdesktop.crawler_new.base.Link;
import com.axisdesktop.crawler_new.base.QueueStatus;

public class QueueSimple implements CrawlerQueue {

	@Override
	public boolean add(List<Link> links) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(Link link) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Link get() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateStatus(Link link, QueueStatus status) {
		// TODO Auto-generated method stub
		return false;
	}

}
