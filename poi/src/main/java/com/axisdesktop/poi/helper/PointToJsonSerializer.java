package com.axisdesktop.poi.helper;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vividsolutions.jts.geom.Point;

public class PointToJsonSerializer extends JsonSerializer<Point> {

	@Override
	public void serialize( Point value, JsonGenerator jgen, SerializerProvider provider )
			throws IOException, JsonProcessingException {

		String jsonValue = "null";
		try {
			if( value != null ) {
				System.err.println( value );
				double lat = value.getY();
				double lon = value.getX();
				jsonValue = String.format( "POINT (%s %s)", lat, lon );
			}
		}
		catch( Exception e ) {}

		jgen.writeString( jsonValue );
	}

}