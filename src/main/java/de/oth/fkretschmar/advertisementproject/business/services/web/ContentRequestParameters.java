/*
 * Copyright (C) 2017 fkre
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
package de.oth.fkretschmar.advertisementproject.business.services.web;

import de.oth.fkretschmar.advertisementproject.entities.campaign.ContentFormat;
import de.oth.fkretschmar.advertisementproject.entities.campaign.TargetAge;
import de.oth.fkretschmar.advertisementproject.entities.campaign.TargetGender;
import de.oth.fkretschmar.advertisementproject.entities.campaign.TargetMaritalStatus;
import de.oth.fkretschmar.advertisementproject.entities.campaign.TargetPurposeOfUse;
import java.io.Serializable;
import java.util.EnumSet;
import java.util.Set;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import lombok.Setter;
import lombok.Getter;

/**
 * Represents the transfer class that only publishes a small subsection of
 * information for content requests.
 * 
 * Implementation as seen at http://stackoverflow.com/questions/21179098/soap-ws
 * -make-webparam-optional
 *
 * @author fkre
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "ContentRequestParameters")
public class ContentRequestParameters implements Serializable {

    // --------------- Private fields ---------------

    /**
     * Stores the enum that indicates the actual format of the content.
     */
    @Setter
    @Getter
    @XmlElement(name = "format", required = true)
    private ContentFormat format;

    /**
     * Stores the age groups that the requested content should cater to.
     */
    @Setter
    @XmlElement(name = "targetAgeGroups", required = false)
    private Set<TargetAge> targetAgeGroups;

    /**
     * Stores the gender groups that the requested content should cater to.
     */
    @Setter
    @XmlElement(name = "targetGenderGroups", required = false)
    private Set<TargetGender> targetGenderGroups;

    /**
     * Stores the marital status groups that the requested content should cater
     * to.
     */
    @Setter
    @XmlElement(name = "targetMaritalStatusGroups", required = false)
    private Set<TargetMaritalStatus> targetMaritalStatusGroups;

    /**
     * Stores the purpose of use groups that the requested content should cater
     * to.
     */
    @Setter
    @XmlElement(name = "targetPurposeOfUseGroups", required = false)
    private Set<TargetPurposeOfUse> targetPurposeOfUseGroups;

    /**
     * Stores the text that identifies the source of the request.
     */
    @Setter
    @Getter
    @XmlElement(name = "source", required = true)
    private String source;

    // --------------- Public getter and setter ---------------
    
    
    /**
     * Gets the specified target age groups or all target ages if none where
     * specified in the request.
     * 
     * @return 
     */
    public EnumSet<TargetAge> getTargetAgeGroups() {
        return this.targetAgeGroups.isEmpty()? EnumSet.copyOf(this.targetAgeGroups) 
                : EnumSet.allOf(TargetAge.class);
    }
    
    
    /**
     * Gets the specified target gender groups or all target genders if none where
     * specified in the request.
     * 
     * @return 
     */
    public EnumSet<TargetGender> getTargetGenderGroups() {
        return this.targetGenderGroups.isEmpty()? EnumSet.copyOf(this.targetGenderGroups) 
                : EnumSet.allOf(TargetGender.class);
    }
    
    
    /**
     * Gets the specified target marital status groups or all target marital status if none where
     * specified in the request.
     * 
     * @return 
     */
    public EnumSet<TargetMaritalStatus> getTargetMaritalStatusGroups() {
        return this.targetMaritalStatusGroups.isEmpty()? EnumSet.copyOf(this.targetMaritalStatusGroups) 
                : EnumSet.allOf(TargetMaritalStatus.class);
    }
    
    
    /**
     * Gets the specified target purpose of use groups or all target purposes of use if none where
     * specified in the request.
     * 
     * @return 
     */
    public EnumSet<TargetPurposeOfUse> getTargetPurposeOfUseGroups() {
        return this.targetPurposeOfUseGroups.isEmpty()? EnumSet.copyOf(this.targetPurposeOfUseGroups) 
                : EnumSet.allOf(TargetPurposeOfUse.class);
    }

    
    /**
     * Gets the value indicating whether or not the requested content should be
     * targeted at any target group.
     * @return 
     */
    public boolean isTargeted() {
        return this.targetAgeGroups.isEmpty() || this.targetGenderGroups.isEmpty()
                || this.targetMaritalStatusGroups.isEmpty()|| this.targetPurposeOfUseGroups.isEmpty();
    }
}
