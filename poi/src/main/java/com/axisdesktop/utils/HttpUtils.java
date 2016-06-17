package com.axisdesktop.utils;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

import com.axisdesktop.poi.helper.BaseRequestBody;
import com.axisdesktop.poi.helper.Sorter;

import ch.ralscha.extdirectspring.bean.ExtDirectStoreReadRequest;
import ch.ralscha.extdirectspring.bean.SortDirection;
import ch.ralscha.extdirectspring.bean.SortInfo;

public class HttpUtils {
	public static String mime2ext( String mime ) {
		String ext = null;

		if( mime == null ) {}
		else if( mime.contains( "jpeg" ) ) {
			ext = "jpg";
		}
		else if( mime.contains( "png" ) ) {
			ext = "png";
		}
		else if( mime.contains( "gif" ) ) {
			ext = "gif";
		}
		else if( mime.contains( "gif" ) ) {
			ext = "jpg";
		}

		return ext;
	}

	public static String mime2ext( String mime, String def ) {
		String ext = HttpUtils.mime2ext( mime );
		if( ext == null ) ext = def;

		return ext;
	}

	public static Pageable createPageable( ExtDirectStoreReadRequest request ) {

		List<Order> orders = new ArrayList<>();
		for( SortInfo sortInfo : request.getSorters() ) {

			if( sortInfo.getDirection() == SortDirection.ASCENDING ) {
				orders.add( new Order( Direction.ASC, sortInfo.getProperty() ) );
			}
			else {
				orders.add( new Order( Direction.DESC, sortInfo.getProperty() ) );
			}
		}

		// Ext JS pages starts with 1, Spring Data starts with 0
		int page = Math.max( request.getPage() - 1, 0 );

		if( orders.isEmpty() ) {
			return new PageRequest( page, request.getLimit() );
		}

		Sort sort = new Sort( orders );
		return new PageRequest( page, request.getLimit(), sort );

	}

	public static Pageable createPageable( BaseRequestBody data ) {
		List<Order> orders = new ArrayList<>();

		for( Sorter s : data.getSorters() ) {
			Direction direction = s.getDirection().equalsIgnoreCase( "desc" ) ? Direction.DESC : Direction.ASC;
			orders.add( new Order( direction, s.getProperty() ) );
		}

		PageRequest pageRequest;

		if( orders.isEmpty() ) {
			pageRequest = new PageRequest( data.getStart(), data.getLimit() );
		}
		else {
			Sort sort = new Sort( orders );
			pageRequest = new PageRequest( data.getStart(), data.getLimit(), sort );
		}

		return pageRequest;
	}
}
