package br.cs.feed;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Characters;
import javax.xml.stream.events.XMLEvent;

import br.cs.component.util.DataUtil;
import br.cs.entity.Feed;
import br.cs.entity.FeedMessage;

public class RSSFeedParser {
	static final String TITLE = "title";
	static final String DESCRIPTION = "description";
	static final String CHANNEL = "channel";
	static final String LANGUAGE = "language";
	static final String COPYRIGHT = "copyright";
	static final String LINK = "link";
	static final String AUTHOR = "author";
	static final String ITEM = "item";
	static final String PUB_DATE = "pubDate";
	static final String LAST_BUILD_DATE = "lastBuildDate";
	static final String GUID = "guid";
	final URL url;

	public RSSFeedParser(String feedUrl) throws MalformedURLException {
		this.url = new URL(feedUrl);
	}

	public Feed readFeed() {
		Feed feed = null;
		try {
			boolean isFeedHeader = true;

			String description = "";
			String title = "";
			String link = "";
			String language = "";
			String copyright = "";
			String author = "";
			String pubdate = "";
			String lastBuildDate = "";
			String guid = "";

			XMLInputFactory inputFactory = XMLInputFactory.newInstance();

			InputStream in = read();
			XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
			while (eventReader.hasNext()) {
				XMLEvent event = eventReader.nextEvent();
				if (event.isStartElement()) {
					String localPart = event.asStartElement().getName().getLocalPart();
					String str1;
					switch ((str1 = localPart).hashCode()) {
					case -1724546052:
						if (str1.equals("description")) {
						}
						break;
					case -1613589672:
						if (str1.equals("language")) {
						}
						break;
					case -1406328437:
						if (str1.equals("author")) {
						}
						break;
					case -236564405:
						if (str1.equals("pubDate")) {
						}
						break;
					case 3184265:
						if (str1.equals("guid")) {
						}
						break;
					case 3242771:
						if (str1.equals("item")) {
							break;
						}
						break;
					case 3321850:
						if (str1.equals("link")) {
						}
						break;
					case 110371416:
						if (str1.equals("title")) {
						}
						break;
					case 1099127110:
						if (str1.equals("lastBuildDate")) {
						}
						break;
					case 1522889671:
						// if (!str1.equals("copyright")) {
						// continue;
						// if (isFeedHeader) {
						// isFeedHeader = false;
						// feed = new Feed(title, link, description, language,
						// copyright, lastBuildDate);
						// }
						// event = eventReader.nextEvent();
						// continue;
						//
						// title = getCharacterData(event, eventReader);
						// continue;
						//
						// description = getCharacterData(event, eventReader);
						// continue;
						//
						// link = getCharacterData(event, eventReader);
						// continue;
						//
						// guid = getCharacterData(event, eventReader);
						// continue;
						//
						// language = getCharacterData(event, eventReader);
						// continue;
						//
						// author = getCharacterData(event, eventReader);
						// continue;
						//
						// pubdate = getCharacterData(event, eventReader);
						// continue;
						//
						// lastBuildDate = getCharacterData(event, eventReader);
						// } else {
						// copyright = getCharacterData(event, eventReader);
						// }
						break;
					}
				} else if ((event.isEndElement()) && (event.asEndElement().getName().getLocalPart() == "item")) {
					FeedMessage message = new FeedMessage();
					message.setAuthor(author);
					message.setDescription(description);
					message.setGuid(guid);
					message.setLink(link);
					message.setTitle(title);
					message.setPubDate(DataUtil.parseToDate(pubdate));
					feed.getMessages().add(message);
					event = eventReader.nextEvent();
				}
			}
		} catch (XMLStreamException e) {
			throw new RuntimeException(e);
		}
		return feed;
	}

	private String getCharacterData(XMLEvent event, XMLEventReader eventReader) throws XMLStreamException {
		String result = "";
		event = eventReader.nextEvent();
		if ((event instanceof Characters)) {
			result = event.asCharacters().getData();
		}
		return result;
	}

	private InputStream read() {
		try {
			return this.url.openStream();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
