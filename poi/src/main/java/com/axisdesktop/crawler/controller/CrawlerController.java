package com.axisdesktop.crawler.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.axisdesktop.crawler.entity.CrawlerProxy;

import ch.rasc.extclassgenerator.ModelGenerator;
import ch.rasc.extclassgenerator.OutputFormat;

@Controller
@RequestMapping( "/crawler" )
public class CrawlerController {
	@RequestMapping( "models.js" )
	public void user( HttpServletRequest request, HttpServletResponse response ) throws IOException {
		ModelGenerator.writeModel( request, response, CrawlerProxy.class, OutputFormat.EXTJS5 );
	}

}
