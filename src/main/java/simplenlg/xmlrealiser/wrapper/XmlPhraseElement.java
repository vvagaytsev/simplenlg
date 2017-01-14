//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.02.04 at 09:14:07 AM EST 
//


package simplenlg.xmlrealiser.wrapper;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for PhraseElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="PhraseElement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement">
 *       &lt;sequence>
 *         &lt;element name="frontMod" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="preMod" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="compl" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="postMod" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="head" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}WordElement" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="cat" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}phraseCategory" />
 *       &lt;attribute name="discourseFunction" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}discourseFunction" />
 *       &lt;attribute name="appositive" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PhraseElement", propOrder = {
    "frontMod",
    "preMod",
    "compl",
    "postMod",
    "head"
})
@XmlSeeAlso({
    XmlPPPhraseSpec.class,
    XmlAdjPhraseSpec.class,
    XmlAdvPhraseSpec.class,
    XmlNPPhraseSpec.class,
    XmlVPPhraseSpec.class,
    XmlSPhraseSpec.class
})
public abstract class XmlPhraseElement
    extends XmlNLGElement
{

    protected List<XmlNLGElement> frontMod;
    protected List<XmlNLGElement> preMod;
    protected List<XmlNLGElement> compl;
    protected List<XmlNLGElement> postMod;
    protected XmlWordElement head;
    @XmlAttribute(name = "cat")
    protected XmlPhraseCategory cat;
    @XmlAttribute(name = "discourseFunction")
    protected XmlDiscourseFunction discourseFunction;
    @XmlAttribute(name = "appositive")
    protected Boolean appositive;

    /**
     * Gets the value of the frontMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the frontMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getFrontMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlNLGElement }
     * 
     * 
     */
    public List<XmlNLGElement> getFrontMod() {
        if (frontMod == null) {
            frontMod = new ArrayList<>();
        }
        return this.frontMod;
    }

    /**
     * Gets the value of the preMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the preMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPreMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlNLGElement }
     * 
     * 
     */
    public List<XmlNLGElement> getPreMod() {
        if (preMod == null) {
            preMod = new ArrayList<>();
        }
        return this.preMod;
    }

    /**
     * Gets the value of the compl property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the compl property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCompl().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlNLGElement }
     * 
     * 
     */
    public List<XmlNLGElement> getCompl() {
        if (compl == null) {
            compl = new ArrayList<>();
        }
        return this.compl;
    }

    /**
     * Gets the value of the postMod property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postMod property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostMod().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlNLGElement }
     * 
     * 
     */
    public List<XmlNLGElement> getPostMod() {
        if (postMod == null) {
            postMod = new ArrayList<>();
        }
        return this.postMod;
    }

    /**
     * Gets the value of the head property.
     * 
     * @return
     *     possible object is
     *     {@link XmlWordElement }
     *     
     */
    public XmlWordElement getHead() {
        return head;
    }

    /**
     * Sets the value of the head property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlWordElement }
     *     
     */
    public void setHead(XmlWordElement value) {
        this.head = value;
    }

    /**
     * Gets the value of the cat property.
     * 
     * @return
     *     possible object is
     *     {@link XmlPhraseCategory }
     *     
     */
    public XmlPhraseCategory getCat() {
        return cat;
    }

    /**
     * Sets the value of the cat property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlPhraseCategory }
     *     
     */
    public void setCat(XmlPhraseCategory value) {
        this.cat = value;
    }

    /**
     * Gets the value of the discourseFunction property.
     * 
     * @return
     *     possible object is
     *     {@link XmlDiscourseFunction }
     *     
     */
    public XmlDiscourseFunction getDiscourseFunction() {
        return discourseFunction;
    }

    /**
     * Sets the value of the discourseFunction property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlDiscourseFunction }
     *     
     */
    public void setDiscourseFunction(XmlDiscourseFunction value) {
        this.discourseFunction = value;
    }

    /**
     * Gets the value of the appositive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAppositive() {
        return appositive;
    }

    /**
     * Sets the value of the appositive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAppositive(Boolean value) {
        this.appositive = value;
    }

}
