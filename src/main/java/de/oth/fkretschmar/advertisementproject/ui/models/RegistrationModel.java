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
package de.oth.fkretschmar.advertisementproject.ui.models;

import de.oth.fkretschmar.advertisementproject.business.services.PasswordService;
import de.oth.fkretschmar.advertisementproject.business.services.UserService;
import de.oth.fkretschmar.advertisementproject.business.services.UserServiceException;
import de.oth.fkretschmar.advertisementproject.entities.user.Address;
import de.oth.fkretschmar.advertisementproject.entities.user.Password;
import de.oth.fkretschmar.advertisementproject.entities.user.User;
import de.oth.fkretschmar.advertisementproject.ui.models.base.AbstractModel;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author fkre
 */
@Named
@RequestScoped
public class RegistrationModel extends AbstractModel {
    
    // --------------- Private fields ---------------
    
    /**
     * Stores the area code of the region the user lives in.
     */
    @Getter
    @Setter
    private String areaCode;
    
    /**
     * Stores the city the user lives in.
     */
    @Getter
    @Setter
    private String city;
    
    /**
     * Stores the company the user works for.
     */
    @Getter
    @Setter
    private String company;
    
    /**
     * Stores the country the user lives in.
     */
    @Getter
    @Setter
    private String country;
    
    /**
     * Stores the e-mail address of the user, used to authenticate with the 
     * system.
     */
    @Getter
    @Setter
    private String eMailAddress;
    
    /**
     * Stores the first name of the user.
     */
    @Getter
    @Setter
    private String firstName;
    
    /**
     * Stores the house number of the user.
     */
    @Getter
    @Setter
    private String houseNumber;
    
    /**
     * Stores the last name of the user.
     */
    @Getter
    @Setter
    private String lastName;
    
    /**
     * Stores the user password.
     */
    @Getter
    @Setter
    private String password;
    
    /**
     * Stores the street the user lives in.
     */
    @Getter
    @Setter
    private String street;
    
    /**
     * Stores the service used to manage the entire application.
     */
    @Inject
    private UserService userService;
    
    // --------------- Public methods ---------------
    
    /**
     * Registers a new user.
     * 
     * @return the target page to navigate to after registration.
     */
    public String register() {
        Address address = Address.createAddress()
                .areaCode(this.areaCode.trim())
                .city(this.city.trim())
                .country(this.country.trim())
                .street(String.format(
                        "%s %s", 
                        this.street.trim(), 
                        this.houseNumber.trim()))
                .build();
        
        Password generatedPassword = PasswordService.generate(
                this.password.toCharArray());
        
        User user = User.createUser()
                .address(address)
                .company(this.company.trim())
                .eMailAddress(this.eMailAddress.trim())
                .firstName(this.firstName.trim())
                .lastName(this.lastName.trim())
                .password(generatedPassword).build();

        try {
            this.userService.createUser(user);
        } catch (UserServiceException ex) {
            // TODO: show error in the UI if the registration fails.
        }
        
        this.areaCode = "";
        this.city = "";
        this.company = "";
        this.country = "";
        this.firstName = "";
        this.houseNumber = "";
        this.lastName = "";
        this.password = "";
        this.street = "";
        
        return "index";
    }
}