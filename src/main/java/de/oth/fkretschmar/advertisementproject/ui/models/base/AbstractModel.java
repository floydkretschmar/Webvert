/*
 * Copyright (C) 2016 Floyd
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
package de.oth.fkretschmar.advertisementproject.ui.models.base;

import de.oth.fkretschmar.advertisementproject.ui.converters.AccountConverter;
import java.io.Serializable;
import javax.inject.Inject;

/**
 * The abstract base implementation of an ui model.
 * 
 * @author Floyd
 */
public abstract class AbstractModel implements Serializable {

    /**
     * Stores the converter used to convert accounts for visualization.
     */
    @Inject
    private AccountConverter accountConverter;
    
    // --------------- Public getters and setters ---------------
    
    
    /**
     * Gets the converter used to convert accounts for visualization.
     * 
     * @return the converter.
     */
    public AccountConverter getAccountConverter() {
        return this.accountConverter;
    }
    
    // --------------- Public methods ---------------
    
    /**
     * Initializes the model data for display operations.
     */
    public void initialize() {
    }
}
