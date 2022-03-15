package ru.kpfu.itis.kashapova;

import org.htmlcleaner.CleanerProperties;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.PrettyXmlSerializer;
import org.htmlcleaner.TagNode;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ContentSite {
    private String pageAddress = null;
    protected CleanerProperties props;

    public ContentSite(String pageAddress) {
        this.pageAddress = pageAddress;
    }

    /*
    сначала получаем html -> меняем через jsoup на xml
    и снова через cleaner чистим от нененужного
     */
    public void writeContentInSite() throws IOException {
        props = new CleanerProperties();
        TagNode tagNode = null;
        try {
            tagNode = new HtmlCleaner(props).clean(getContentOfHTTPPage());
        } catch (Exception e) {
            System.out.println("#writeContentInSite(): create tagNode");
        }

        new PrettyXmlSerializer(props).writeToFile(tagNode, "src/main/resources/site.xml", "utf-8");
    }

    private String getContentOfHTTPPage() throws Exception {
        StringBuilder sb = new StringBuilder();
        URL pageURL = new URL(pageAddress);
        URLConnection uc = pageURL.openConnection();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()))) {
            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
        }
        //превращение html в xml (не всеегда корректно == cleaner)
        final Document document = Jsoup.parse(sb.toString());
        document.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        return document.toString();
    }

    public String getPageAddress() {
        return pageAddress;
    }
    public void setPageAddress(String pageAddress) {
        this.pageAddress = pageAddress;
    }
}
