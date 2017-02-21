package com.axisdesktop.crawler.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axisdesktop.crawler.entity.CrawlerProxy;
import com.axisdesktop.crawler.entity.CrawlerProxyStatus;
import com.axisdesktop.crawler.entity.ProviderData;
import com.axisdesktop.crawler.entity.ProviderDataType;
import com.axisdesktop.crawler.entity.ProviderUrl;
import com.axisdesktop.crawler.entity.ProviderUrlStatus;

import ch.rasc.extclassgenerator.ModelGenerator;
import ch.rasc.extclassgenerator.OutputFormat;

@Controller
@RequestMapping( "/crawler" )
public class CrawlerController {
	@RequestMapping( value = "models.js" )
	public void user( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		StringBuilder sb = new StringBuilder();
		sb.append( ModelGenerator.generateJavascript( CrawlerProxy.class, OutputFormat.EXTJS5, false ) );
		sb.append( ModelGenerator.generateJavascript( CrawlerProxyStatus.class, OutputFormat.EXTJS5, false ) );

		sb.append( ModelGenerator.generateJavascript( ProviderUrl.class, OutputFormat.EXTJS5, false ) );
		sb.append( ModelGenerator.generateJavascript( ProviderUrlStatus.class, OutputFormat.EXTJS5, false ) );

		sb.append( ModelGenerator.generateJavascript( ProviderData.class, OutputFormat.EXTJS5, false ) );
		sb.append( ModelGenerator.generateJavascript( ProviderDataType.class, OutputFormat.EXTJS5, false ) );

		response.getWriter().println( sb.toString() );
		response.setContentType( "application/javascript" );
	}

}
