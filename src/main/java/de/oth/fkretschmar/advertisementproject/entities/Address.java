/*
 * Copyright (C) 2016 fkre
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package de.oth.fkretschmar.advertisementproject.entities;


import de.oth.fkretschmar.advertisementproject.entities.base.AbstractAutoGenerateKeyedEntity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents an address of an user.
 * 
 * @author  fkre    Floyd Kretschmar
 */
@Entity(name = "T_ADDRESS")
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class Address extends AbstractAutoGenerateKeyedEntity {
    
    // --------------- Private fields ---------------
    
    /**
     * Stores the textual representation of the area code.
     */
    @NotNull
    @Column(name = "AREA_CODE")
    @Getter
    @Setter
    private String areaCode;
    
    /**
     * Stores the name of the city in which the street can be found.
     */
    @NotNull
    @Column(name = "CITY")
    @Getter
    @Setter
    private String city;
    
    /**
     * Stores the text that represents the country in which the address can be 
     * found.
     */
    @Column(name = "COUNTRY")
    @Getter
    @Setter
    private String country;
    
    /**
     * Stores the textual representation of the street including the street 
     * number.
     */
    @NotNull
    @Column(name = "STREET")
    @Getter
    @Setter
    private String street;
    
    
    // --------------- Private constructors ---------------
    
    /**
     * Creates a new instance of {@link Address} using the specified area code,
     * city, country and street.
     * 
     * @param areaCode      the textual representation of the area code.
     * @param city          the name of the city in which the street can be 
     *                      found.
     * @param countrythe    text that represents the country in which the 
     *                      address can be found.
     * @param street        the textual representation of the street including 
     *                      the street number.
     */
    private Address(
            String areaCode, 
            String city, 
            String country, 
            String street) {
        super();
        this.areaCode = areaCode;
        this.city = city;
        this.country = country;
        this.street = street;
    }
    
    // --------------- Private static methods ---------------
    
    
    /**
     * The method that builds the basis of the auto generated builder:
     * Validates the input and creates the corresponding {@link Address}.
     * 
     * @param areaCode      the textual representation of the area code.
     * @param city          the name of the city in which the street can be 
     *                      found.
     * @param countrythe    text that represents the country in which the 
     *                      address can be found.
     * @param street        the textual representation of the street including 
     *                      the street number.
     * @return the built {@link Address}.
     */
    @Builder(
            builderMethodName = "create", 
            builderClassName = "AddressBuilder",
            buildMethodName = "build")
    private static Address validateAndCreateAddress(
            String areaCode, 
            String city, 
            String country, 
            String street) {
        if(areaCode == null || areaCode.isEmpty())
            throw new BuilderValidationException(
                    Address.class, 
                    "The area code can not be null or empty.");
        
        if(city == null || city.isEmpty())
            throw new BuilderValidationException(
                    Address.class, 
                    "The city can not be null or empty.");
        
        if(street == null || street.isEmpty())
            throw new BuilderValidationException(
                    Address.class, 
                    "The street can not be null or empty.");
        
        return new Address(areaCode, city, country, street);
    }
    
}
