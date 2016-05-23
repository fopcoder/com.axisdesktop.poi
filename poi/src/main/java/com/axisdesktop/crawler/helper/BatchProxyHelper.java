package com.axisdesktop.crawler.helper;

import org.hibernate.validator.constraints.NotEmpty;

public class BatchProxyHelper {
	@NotEmpty
	public String proxyText;

	public String getProxyText() {
		return proxyText;
	}

	public void setProxyText( String proxyText ) {
		this.proxyText = proxyText;
	}

	@Override
	public String toString() {
		return "BatchProxyHelper [proxyText=" + proxyText + "]";
	}

}
