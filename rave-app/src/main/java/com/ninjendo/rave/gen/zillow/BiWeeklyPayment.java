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
 * <p>Java class for BiWeeklyPayment complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="BiWeeklyPayment"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="monthlyPrincipalAndInterest" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="interestSavings" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="payOffInYears" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="amortizationSchedule" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}BiWeeklyPaymentAmortizationSchedule" maxOccurs="unbounded"/&gt;
 *         &lt;element name="biWeeklyAmortizationSchedule" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}BiWeeklyPaymentAmortizationSchedule2" maxOccurs="unbounded"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "BiWeeklyPayment", propOrder = {
    "monthlyPrincipalAndInterest",
    "interestSavings",
    "payOffInYears",
    "result",
    "amortizationSchedule",
    "biWeeklyAmortizationSchedule"
})
public class BiWeeklyPayment {

    @XmlElement(required = true)
    protected BigInteger monthlyPrincipalAndInterest;
    @XmlElement(required = true)
    protected BigInteger interestSavings;
    @XmlElement(required = true)
    protected String payOffInYears;
    @XmlElement(required = true)
    protected String result;
    @XmlElement(required = true)
    protected List<BiWeeklyPaymentAmortizationSchedule> amortizationSchedule;
    @XmlElement(required = true)
    protected List<BiWeeklyPaymentAmortizationSchedule2> biWeeklyAmortizationSchedule;

    /**
     * Gets the value of the monthlyPrincipalAndInterest property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getMonthlyPrincipalAndInterest() {
        return monthlyPrincipalAndInterest;
    }

    /**
     * Sets the value of the monthlyPrincipalAndInterest property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setMonthlyPrincipalAndInterest(BigInteger value) {
        this.monthlyPrincipalAndInterest = value;
    }

    /**
     * Gets the value of the interestSavings property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getInterestSavings() {
        return interestSavings;
    }

    /**
     * Sets the value of the interestSavings property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setInterestSavings(BigInteger value) {
        this.interestSavings = value;
    }

    /**
     * Gets the value of the payOffInYears property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPayOffInYears() {
        return payOffInYears;
    }

    /**
     * Sets the value of the payOffInYears property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPayOffInYears(String value) {
        this.payOffInYears = value;
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
     * Gets the value of the amortizationSchedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the amortizationSchedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAmortizationSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BiWeeklyPaymentAmortizationSchedule }
     * 
     * 
     */
    public List<BiWeeklyPaymentAmortizationSchedule> getAmortizationSchedule() {
        if (amortizationSchedule == null) {
            amortizationSchedule = new ArrayList<BiWeeklyPaymentAmortizationSchedule>();
        }
        return this.amortizationSchedule;
    }

    /**
     * Gets the value of the biWeeklyAmortizationSchedule property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the biWeeklyAmortizationSchedule property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getBiWeeklyAmortizationSchedule().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link BiWeeklyPaymentAmortizationSchedule2 }
     * 
     * 
     */
    public List<BiWeeklyPaymentAmortizationSchedule2> getBiWeeklyAmortizationSchedule() {
        if (biWeeklyAmortizationSchedule == null) {
            biWeeklyAmortizationSchedule = new ArrayList<BiWeeklyPaymentAmortizationSchedule2>();
        }
        return this.biWeeklyAmortizationSchedule;
    }

}
