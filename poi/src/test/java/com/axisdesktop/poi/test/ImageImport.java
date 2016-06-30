package com.axisdesktop.poi.test;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.DirectoryStream;
import java.nio.file.DirectoryStream.Filter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.axisdesktop.poi.config.AppConf;

@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration( classes = { AppConf.class } )
@TestPropertySource( properties = { //
		"crawler.file.store.path = C:/Temp/crawler", //
		"poi.image.import.path = C:/Temp/poi/imgimport", //
		"poi.image.ready.path = C:/Temp/poi/imgready", //
		"poi.image.path = C:/Temp/poi/images" } )

public class ImageImport {
	@Autowired
	private Environment env;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test() {
		Path importPath = Paths.get( env.getRequiredProperty( "poi.image.import.path" ) );
		Path poiPath = Paths.get( env.getRequiredProperty( "poi.image.path" ) );
		Path readyPath = Paths.get( env.getRequiredProperty( "poi.image.ready.path" ) );

		Filter<Path> filterDir = new Filter<Path>() {
			@Override
			public boolean accept( Path entry ) throws IOException {
				return Files.isDirectory( entry );
			}
		};

		Filter<Path> filterImg = new Filter<Path>() {
			@Override
			public boolean accept( Path entry ) throws IOException {
				return entry.getFileName().toString().toLowerCase().endsWith( ".jpg" )
						|| entry.getFileName().toString().toLowerCase().endsWith( ".jpeg" )
						|| entry.getFileName().toString().toLowerCase().endsWith( ".png" );
			}

		};

		try( DirectoryStream<Path> importPathstream = Files.newDirectoryStream( importPath, filterDir ) ) {
			for( Path imgDir : importPathstream ) {
				System.err.println( imgDir.getFileName() );

				Path tmpDir = Files.createTempDirectory( "img_" );
				try( DirectoryStream<Path> dirStream = Files.newDirectoryStream( imgDir, filterImg ) ) {
					for( Path img : dirStream ) {

						String fn = img.getFileName().toString();
						String ext = fn.substring( fn.lastIndexOf( "." ) + 1 ).toLowerCase();

						int imgId = new Random().nextInt( 1_000_000 );
						String md5 = md5sum( String.valueOf( imgId ) );

						String newFn = String.valueOf( imgId ) + "." + ext;

						String dir1 = md5.substring( 0, 2 );
						String dir2 = md5.substring( 2, 4 );

						Path tmpImgDir = tmpDir.resolve( Paths.get( dir1, dir2 ) );
						Path tmpImgPath = tmpImgDir.resolve( newFn );

						Files.createDirectories( tmpImgDir );

						// System.err.println( tmpImgPath );

						// Files.createFile( tmpImgPath );

						Files.copy( img, tmpImgPath, StandardCopyOption.REPLACE_EXISTING );

						// System.err.println( md5 );
						// System.err.println( img.toAbsolutePath() );

						// System.err.println( tmpDir.toAbsolutePath() );
					}
				}
				catch( Exception e ) {
					e.printStackTrace();
					Files.deleteIfExists( tmpDir );
				}
				finally {
					if( Files.exists( tmpDir ) ) {
						Files.copy( tmpDir, poiPath, StandardCopyOption.REPLACE_EXISTING );
					}
					Files.deleteIfExists( tmpDir );
				}
			}
		}
		catch( Exception e ) {
			e.printStackTrace();
		}
	}

	public static String md5sum( String st ) {
		MessageDigest messageDigest = null;
		byte[] digest = new byte[0];

		try {
			messageDigest = MessageDigest.getInstance( "MD5" );
			messageDigest.reset();
			messageDigest.update( st.getBytes() );
			digest = messageDigest.digest();
		}
		catch( NoSuchAlgorithmException e ) {
			// тут можно обработать ошибку
			// возникает она если в передаваемый алгоритм в getInstance(,,,) не существует
			e.printStackTrace();
		}

		BigInteger bigInt = new BigInteger( 1, digest );
		String md5Hex = bigInt.toString( 16 );

		while( md5Hex.length() < 32 ) {
			md5Hex = "0" + md5Hex;
		}

		return md5Hex;
	}

}
