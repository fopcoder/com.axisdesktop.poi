package com.axisdesktop.crawler.base;

public class WebWorker implements Worker {
	private Crawler crawler;
	private String url;

	public WebWorker() {
	}

	public WebWorker( Crawler crawler, String url ) {
		this.crawler = crawler;
		this.url = url;
		// String uri = provUrl.getUrl();
		// int code = 0;
		// String msg = null;
		//
		// CrawlerProxy crawlerProxy = this.getProxyService().getRandomActiveProxy();
		// ProxyContoller proxy = this.getProxy( crawlerProxy );
		//
		// try {
		// HttpURLConnection uc = this.getConnection( uri, proxy );
		//
		// if( uc == null ) {
		// System.err.println( "no free proxy" );
		// break;
		// }
		//
		// code = uc.getResponseCode();
		// msg = uc.getResponseMessage();
		//
		// if( code == 200 && uc.getContentType().contains( "text" ) ) {
		// try( BufferedReader br = new BufferedReader( new InputStreamReader( uc.getInputStream() ) ) ) {
		// StringBuilder sb = new StringBuilder();
		//
		// String str;
		// while( ( str = br.readLine() ) != null ) {
		// sb.append( str );
		// }
		//
		// Parser parser = new DorogaParser( sb.toString() );
		//
		// System.out.println( parser );
		//
		// Thread.sleep( 2_000 );
		//
		// // System.err.println( parser.itemLinks() );
		//
		// // for( String s : parser.itemLinks() ) {
		// // if( !this.provService.isUrlExist( this.provider.getId(), s ) ) {
		// // ProviderUrl pu = new ProviderUrl();
		// // pu.setStatusId( 4 );
		// // pu.setUrl( s );
		// // pu.setTypeId( 3 );
		// // pu.setProviderId( this.provider.getId() );
		// //
		// // this.provService.createUrl( pu );
		// // }
		// // }
		//
		// // System.err.println( parser.categoryLinks() );
		//
		// }
		// catch( Exception e ) { /* ignore */ }
		//
		// provUrl.setFetched( Calendar.getInstance() );
		// provUrl.setLog( null );
		// provUrl.setStatusId( 5 );
		// provUrl.setTries( 0 );
		//
		// this.provService.updateUrl( provUrl );
		// }
		// else {
		// provUrl.setStatusId( 3 );
		// provUrl.setLog( String.format( "%s\n%s", code, msg ) );
		// provUrl.setTries( provUrl.getTries() + 1 );
		// provUrl.setFetched( Calendar.getInstance() );
		//
		// this.provService.updateUrl( provUrl );
		// }
		//
		// }
		// catch( IOException e ) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}

	@Override
	public void run() {

	}

	@Override
	public Crawler getCrawler() {
		return crawler;
	}

	@Override
	public String getUrl() {
		return url;
	}

	@Override
	public String getUrlContent() {
		// TODO Auto-generated method stub
		return null;
	}

}
