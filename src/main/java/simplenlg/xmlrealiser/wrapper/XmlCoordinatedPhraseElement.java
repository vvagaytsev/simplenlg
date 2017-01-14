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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CoordinatedPhraseElement complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CoordinatedPhraseElement">
 *   &lt;complexContent>
 *     &lt;extension base="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement">
 *       &lt;sequence>
 *         &lt;element name="coord" type="{http://simplenlg.googlecode.com/svn/trunk/res/xml}NLGElement" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attGroup ref="{http://simplenlg.googlecode.com/svn/trunk/res/xml}CoordinatedPhraseAtts"/>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CoordinatedPhraseElement", propOrder = {
    "coord"
})
public class XmlCoordinatedPhraseElement
    extends XmlNLGElement
{

    @XmlElement(required = true)
    protected List<XmlNLGElement> coord;
    @XmlAttribute(name = "conj")
    protected String conj;
    @XmlAttribute(name = "cat")
    protected XmlPhraseCategory cat;
    @XmlAttribute(name = "APPOSITIVE")
    protected Boolean appositive;
    @XmlAttribute(name = "CONJUNCTION_TYPE")
    protected String conjunctiontype;
    @XmlAttribute(name = "MODAL")
    protected String modal;
    @XmlAttribute(name = "NEGATED")
    protected Boolean negated;
    @XmlAttribute(name = "NUMBER")
    protected XmlNumberAgreement number;
    @XmlAttribute(name = "PERSON")
    protected XmlPerson person;
    @XmlAttribute(name = "POSSESSIVE")
    protected Boolean possessive;
    @XmlAttribute(name = "PROGRESSIVE")
    protected Boolean progressive;
    @XmlAttribute(name = "RAISE_SPECIFIER")
    protected Boolean raisespecifier;
    @XmlAttribute(name = "SUPRESSED_COMPLEMENTISER")
    protected Boolean supressedcomplementiser;
    @XmlAttribute(name = "TENSE")
    protected XmlTense tense;

    /**
     * Gets the value of the coord property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the coord property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCoord().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link XmlNLGElement }
     * 
     * 
     */
    public List<XmlNLGElement> getCoord() {
        if (coord == null) {
            coord = new ArrayList<>();
        }
        return this.coord;
    }

    /**
     * Gets the value of the conj property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConj() {
        if (conj == null) {
            return "and";
        } else {
            return conj;
        }
    }

    /**
     * Sets the value of the conj property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConj(String value) {
        this.conj = value;
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
     * Gets the value of the appositive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAPPOSITIVE() {
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
    public void setAPPOSITIVE(Boolean value) {
        this.appositive = value;
    }

    /**
     * Gets the value of the conjunctiontype property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONJUNCTIONTYPE() {
        return conjunctiontype;
    }

    /**
     * Sets the value of the conjunctiontype property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONJUNCTIONTYPE(String value) {
        this.conjunctiontype = value;
    }

    /**
     * Gets the value of the modal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMODAL() {
        return modal;
    }

    /**
     * Sets the value of the modal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMODAL(String value) {
        this.modal = value;
    }

    /**
     * Gets the value of the negated property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNEGATED() {
        return negated;
    }

    /**
     * Sets the value of the negated property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNEGATED(Boolean value) {
        this.negated = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return
     *     possible object is
     *     {@link XmlNumberAgreement }
     *     
     */
    public XmlNumberAgreement getNUMBER() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlNumberAgreement }
     *     
     */
    public void setNUMBER(XmlNumberAgreement value) {
        this.number = value;
    }

    /**
     * Gets the value of the person property.
     * 
     * @return
     *     possible object is
     *     {@link XmlPerson }
     *     
     */
    public XmlPerson getPERSON() {
        return person;
    }

    /**
     * Sets the value of the person property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlPerson }
     *     
     */
    public void setPERSON(XmlPerson value) {
        this.person = value;
    }

    /**
     * Gets the value of the possessive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPOSSESSIVE() {
        return possessive;
    }

    /**
     * Sets the value of the possessive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPOSSESSIVE(Boolean value) {
        this.possessive = value;
    }

    /**
     * Gets the value of the progressive property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isPROGRESSIVE() {
        return progressive;
    }

    /**
     * Sets the value of the progressive property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setPROGRESSIVE(Boolean value) {
        this.progressive = value;
    }

    /**
     * Gets the value of the raisespecifier property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isRAISESPECIFIER() {
        return raisespecifier;
    }

    /**
     * Sets the value of the raisespecifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setRAISESPECIFIER(Boolean value) {
        this.raisespecifier = value;
    }

    /**
     * Gets the value of the supressedcomplementiser property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isSUPRESSEDCOMPLEMENTISER() {
        return supressedcomplementiser;
    }

    /**
     * Sets the value of the supressedcomplementiser property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setSUPRESSEDCOMPLEMENTISER(Boolean value) {
        this.supressedcomplementiser = value;
    }

    /**
     * Gets the value of the tense property.
     * 
     * @return
     *     possible object is
     *     {@link XmlTense }
     *     
     */
    public XmlTense getTENSE() {
        return tense;
    }

    /**
     * Sets the value of the tense property.
     * 
     * @param value
     *     allowed object is
     *     {@link XmlTense }
     *     
     */
    public void setTENSE(XmlTense value) {
        this.tense = value;
    }

}
