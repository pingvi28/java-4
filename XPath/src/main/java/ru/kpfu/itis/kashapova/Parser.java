package ru.kpfu.itis.kashapova;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.*;
import java.util.ArrayList;

/**
 * @author Kashapova Dilyara
 * 11-001
 * HW1
 *
 * парсинг страницы сайта (вытащить название, цену и url/url img)
 * без использования регулярок == xPath (тк он не строит dom-дерево из html - превращаем в xml (см.ContentSite)
 */

public class Parser {
    //private static final String siteAddress = "https://www.sela.ru/eshop/women/verkhnyaya-odezhda/";
    private static final String siteAddress = "https://www.sela.ru/eshop/women/komplekty/";
    private static final String fileURL = "src/main/resources/site.xml";
    private static NodeList nodesName;
    private static NodeList nodesPrice;
    private static NodeList nodesURl;

    private static final String xPathName = "//span[@class='product-name text-uppercase name_products_span']/a/text()";
    private static final String xPathPrice = "//span[@class='price' and @itemprop='offers']/ins/text()";
    //url - img
    //private static final String xPathURL = "//a[@class='product-image']/img[1]/@data-original";
    //url (самого товара)
    private static final String xPathURL = "//a[@class='product-image']/@href";
    private static final ArrayList<Product> productArrayList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        ContentSite content = new ContentSite(siteAddress);
        content.writeContentInSite();
        getData();

        for (Product product : productArrayList) {
            System.out.println(product.toString());
        }
    }

    private static void getData() throws XPathExpressionException {
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        XPath xpath = null;

        try {
            builder = builderFactory.newDocumentBuilder();
            doc = builder.parse(fileURL);
            XPathFactory xpathFactory = XPathFactory.newInstance();
            xpath = xpathFactory.newXPath();
        } catch (ParserConfigurationException | SAXException | IOException e) {
            System.out.println("#getData(): can't create doc or xpath");
            e.printStackTrace();
        }

        XPathExpression xPathExpressionName = xpath.compile(xPathName);
        nodesName = (NodeList) xPathExpressionName.evaluate(doc, XPathConstants.NODESET);

        XPathExpression xPathExpressionCost = xpath.compile(xPathPrice);
        nodesPrice = (NodeList) xPathExpressionCost.evaluate(doc, XPathConstants.NODESET);

        XPathExpression xPathExpressionURL = xpath.compile(xPathURL);
        nodesURl = (NodeList) xPathExpressionURL.evaluate(doc, XPathConstants.NODESET);

        //при поиске каартинки убрать : "https://www.sela.ru"
        int max = nodesName.getLength() >= nodesPrice.getLength() ? Math.min(nodesName.getLength(), nodesName.getLength()) : Math.max(nodesPrice.getLength(), nodesName.getLength());
        for (int i = 0; i < max; i++) {
            Product product = null;
            try {
                product = new Product(nodesName.item(i).getNodeValue(), nodesPrice.item(i).getNodeValue(), "https://www.sela.ru" + nodesURl.item(i).getNodeValue());
            }catch (NullPointerException ex){
                System.out.println("#getData(): NullPointerException, when you try create Product item");
            }
            productArrayList.add(product);
        }
    }
}
