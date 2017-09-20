package com.axisdesktop.crawler_new.base;

import java.util.List;

public interface CrawlerQueue {
	boolean add(List<Link> links);

	boolean add(Link link);

	Link get();

	boolean updateStatus(Link link, QueueStatus status);
}
