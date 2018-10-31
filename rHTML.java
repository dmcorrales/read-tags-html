
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;


public class rHTML {

	/**
	 *   URL : Custom URL to read TAGS
	 *   tag: Custom tag to find
	 *   pPos: Custom value Iterator
	 */
	private void readPage(String URL, String tag, int pPos) {
		try {
			URL url = new URL(URL);
			if(!url.getHost().isEmpty()) {
				InputStream is = url.openStream();
				BufferedReader br = new BufferedReader(new InputStreamReader(is));
				try {
					String line;
					int tagsFound = 0;
					while ((line = br.readLine()) != null) {
						if(line.contains("<"+tag+">")) {
							tagsFound++;
							
							//Custom Tags
							String rs = line.trim().replace("<p itemprop=\"name\">", "");
							rs =	rs.trim().replace("</p>", "");
							//End Custom Tags
							System.out.println(line);
						}
					}
					if(tagsFound==0) System.out.println("No results found.");
				} finally {
					br.close();
				}
			}else throw new IOException();
		} catch (MalformedURLException e) {
			System.out.println("Invalid URL");
		} catch (IOException e) {
			System.out.println("Connection error");
		}
	}
	

	public static void main(String[] args) {
		rHTML p = new rHTML();
		for(int i=500;i<682;i++) {			
			p.readPage("https://www.laststicker.com/cards/panini_fifa_world_cup_2018/"+i, "p itemprop=\"name\"",i); 
		}
	}
}
