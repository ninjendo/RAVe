//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.01.11 at 07:33:22 PM EST 
//


package com.ninjendo.rave.gen.zillow;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for FMRProperty complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="FMRProperty"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="address" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}RestrictedAddress"/&gt;
 *         &lt;element name="detailPageLink" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="price" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/&gt;
 *         &lt;element name="lastSoldDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="lastSoldPrice" type="{http://www.w3.org/2001/XMLSchema}unsignedInt" minOccurs="0"/&gt;
 *         &lt;element name="zestimate" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}SimpleZestimate" minOccurs="0"/&gt;
 *         &lt;element name="bathrooms" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="bedrooms" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/&gt;
 *         &lt;element name="finishedSqFt" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/&gt;
 *         &lt;element name="lotSqFt" type="{http://www.w3.org/2001/XMLSchema}unsignedInt"/&gt;
 *         &lt;element name="homeType" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}HomeType"/&gt;
 *         &lt;element name="homeStatus" type="{http://www.zillow.com/static/xsd/ZillowTypes.xsd}HomeStatus"/&gt;
 *         &lt;element name="imageLink" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="largeImageLink" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="fsboLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="forSaleLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="makeMeMoveLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="recentlySoldLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="localPageLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="neighborhood" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "FMRProperty", propOrder = {
    "address",
    "detailPageLink",
    "price",
    "lastSoldDate",
    "lastSoldPrice",
    "zestimate",
    "bathrooms",
    "bedrooms",
    "finishedSqFt",
    "lotSqFt",
    "homeType",
    "homeStatus",
    "imageLink",
    "largeImageLink",
    "fsboLink",
    "forSaleLink",
    "makeMeMoveLink",
    "recentlySoldLink",
    "localPageLink",
    "neighborhood"
})
public class FMRProperty {

    @XmlElement(required = true)
    protected RestrictedAddress address;
    @XmlElement(required = true)
    protected String detailPageLink;
    @XmlSchemaType(name = "unsignedInt")
    protected long price;
    protected String lastSoldDate;
    @XmlSchemaType(name = "unsignedInt")
    protected Long lastSoldPrice;
    protected SimpleZestimate zestimate;
    @XmlElement(required = true)
    protected BigDecimal bathrooms;
    @XmlSchemaType(name = "unsignedInt")
    protected long bedrooms;
    @XmlSchemaType(name = "unsignedInt")
    protected long finishedSqFt;
    @XmlSchemaType(name = "unsignedInt")
    protected long lotSqFt;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected HomeType homeType;
    @XmlElement(required = true)
    @XmlSchemaType(name = "string")
    protected HomeStatus homeStatus;
    @XmlElement(required = true)
    protected String imageLink;
    @XmlElement(required = true)
    protected String largeImageLink;
    protected String fsboLink;
    protected String forSaleLink;
    protected String makeMeMoveLink;
    protected String recentlySoldLink;
    protected String localPageLink;
    protected String neighborhood;

    /**
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link RestrictedAddress }
     *     
     */
    public RestrictedAddress getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link RestrictedAddress }
     *     
     */
    public void setAddress(RestrictedAddress value) {
        this.address = value;
    }

    /**
     * Gets the value of the detailPageLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailPageLink() {
        return detailPageLink;
    }

    /**
     * Sets the value of the detailPageLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailPageLink(String value) {
        this.detailPageLink = value;
    }

    /**
     * Gets the value of the price property.
     * 
     */
    public long getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     */
    public void setPrice(long value) {
        this.price = value;
    }

    /**
     * Gets the value of the lastSoldDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSoldDate() {
        return lastSoldDate;
    }

    /**
     * Sets the value of the lastSoldDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSoldDate(String value) {
        this.lastSoldDate = value;
    }

    /**
     * Gets the value of the lastSoldPrice property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getLastSoldPrice() {
        return lastSoldPrice;
    }

    /**
     * Sets the value of the lastSoldPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setLastSoldPrice(Long value) {
        this.lastSoldPrice = value;
    }

    /**
     * Gets the value of the zestimate property.
     * 
     * @return
     *     possible object is
     *     {@link SimpleZestimate }
     *     
     */
    public SimpleZestimate getZestimate() {
        return zestimate;
    }

    /**
     * Sets the value of the zestimate property.
     * 
     * @param value
     *     allowed object is
     *     {@link SimpleZestimate }
     *     
     */
    public void setZestimate(SimpleZestimate value) {
        this.zestimate = value;
    }

    /**
     * Gets the value of the bathrooms property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getBathrooms() {
        return bathrooms;
    }

    /**
     * Sets the value of the bathrooms property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setBathrooms(BigDecimal value) {
        this.bathrooms = value;
    }

    /**
     * Gets the value of the bedrooms property.
     * 
     */
    public long getBedrooms() {
        return bedrooms;
    }

    /**
     * Sets the value of the bedrooms property.
     * 
     */
    public void setBedrooms(long value) {
        this.bedrooms = value;
    }

    /**
     * Gets the value of the finishedSqFt property.
     * 
     */
    public long getFinishedSqFt() {
        return finishedSqFt;
    }

    /**
     * Sets the value of the finishedSqFt property.
     * 
     */
    public void setFinishedSqFt(long value) {
        this.finishedSqFt = value;
    }

    /**
     * Gets the value of the lotSqFt property.
     * 
     */
    public long getLotSqFt() {
        return lotSqFt;
    }

    /**
     * Sets the value of the lotSqFt property.
     * 
     */
    public void setLotSqFt(long value) {
        this.lotSqFt = value;
    }

    /**
     * Gets the value of the homeType property.
     * 
     * @return
     *     possible object is
     *     {@link HomeType }
     *     
     */
    public HomeType getHomeType() {
        return homeType;
    }

    /**
     * Sets the value of the homeType property.
     * 
     * @param value
     *     allowed object is
     *     {@link HomeType }
     *     
     */
    public void setHomeType(HomeType value) {
        this.homeType = value;
    }

    /**
     * Gets the value of the homeStatus property.
     * 
     * @return
     *     possible object is
     *     {@link HomeStatus }
     *     
     */
    public HomeStatus getHomeStatus() {
        return homeStatus;
    }

    /**
     * Sets the value of the homeStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link HomeStatus }
     *     
     */
    public void setHomeStatus(HomeStatus value) {
        this.homeStatus = value;
    }

    /**
     * Gets the value of the imageLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImageLink() {
        return imageLink;
    }

    /**
     * Sets the value of the imageLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImageLink(String value) {
        this.imageLink = value;
    }

    /**
     * Gets the value of the largeImageLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLargeImageLink() {
        return largeImageLink;
    }

    /**
     * Sets the value of the largeImageLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLargeImageLink(String value) {
        this.largeImageLink = value;
    }

    /**
     * Gets the value of the fsboLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFsboLink() {
        return fsboLink;
    }

    /**
     * Sets the value of the fsboLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFsboLink(String value) {
        this.fsboLink = value;
    }

    /**
     * Gets the value of the forSaleLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getForSaleLink() {
        return forSaleLink;
    }

    /**
     * Sets the value of the forSaleLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setForSaleLink(String value) {
        this.forSaleLink = value;
    }

    /**
     * Gets the value of the makeMeMoveLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMakeMeMoveLink() {
        return makeMeMoveLink;
    }

    /**
     * Sets the value of the makeMeMoveLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMakeMeMoveLink(String value) {
        this.makeMeMoveLink = value;
    }

    /**
     * Gets the value of the recentlySoldLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecentlySoldLink() {
        return recentlySoldLink;
    }

    /**
     * Sets the value of the recentlySoldLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecentlySoldLink(String value) {
        this.recentlySoldLink = value;
    }

    /**
     * Gets the value of the localPageLink property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLocalPageLink() {
        return localPageLink;
    }

    /**
     * Sets the value of the localPageLink property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLocalPageLink(String value) {
        this.localPageLink = value;
    }

    /**
     * Gets the value of the neighborhood property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNeighborhood() {
        return neighborhood;
    }

    /**
     * Sets the value of the neighborhood property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNeighborhood(String value) {
        this.neighborhood = value;
    }

}
