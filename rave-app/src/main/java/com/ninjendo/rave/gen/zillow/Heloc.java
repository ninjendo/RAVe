//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Heloc complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Heloc"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="loanAmount" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="loanAmountPairs" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}loanAmountPairs" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Heloc", propOrder = {
    "loanAmount",
    "result",
    "loanAmountPairs"
})
public class Heloc {

    @XmlElement(required = true)
    protected BigInteger loanAmount;
    @XmlElement(required = true)
    protected String result;
    @XmlElement(required = true)
    protected List<LoanAmountPairs> loanAmountPairs;

    /**
     * Gets the value of the loanAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getLoanAmount() {
        return loanAmount;
    }

    /**
     * Sets the value of the loanAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setLoanAmount(BigInteger value) {
        this.loanAmount = value;
    }

    /**
     * Gets the value of the result property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResult() {
        return result;
    }

    /**
     * Sets the value of the result property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResult(String value) {
        this.result = value;
    }

    /**
     * Gets the value of the loanAmountPairs property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the loanAmountPairs property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getLoanAmountPairs().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link LoanAmountPairs }
     * 
     * 
     */
    public List<LoanAmountPairs> getLoanAmountPairs() {
        if (loanAmountPairs == null) {
            loanAmountPairs = new ArrayList<LoanAmountPairs>();
        }
        return this.loanAmountPairs;
    }

}
