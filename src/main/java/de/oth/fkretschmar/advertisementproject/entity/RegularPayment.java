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
package de.oth.fkretschmar.advertisementproject.entity;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author fkre
 */
@Entity(name = "T_REGULAR_PAYMENT")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class RegularPayment extends Payment {
    
//    /**
//     * Stores the end date of the regular payment.
//     */
//    private Calendar endDate;
//    
//    
//    /**
//     * Stores the 
//     */
//    private PaymentInterval interval;
//    
//    private Calendar startDate;
    
}