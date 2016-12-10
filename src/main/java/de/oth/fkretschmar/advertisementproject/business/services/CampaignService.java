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
package de.oth.fkretschmar.advertisementproject.business.services;

import de.oth.fkretschmar.advertisementproject.business.repositories.CampaignRepository;
import de.oth.fkretschmar.advertisementproject.business.repositories.UserRepository;

import de.oth.fkretschmar.advertisementproject.entities.Campaign;
import de.oth.fkretschmar.advertisementproject.entities.CampaignState;
import de.oth.fkretschmar.advertisementproject.entities.Content;
import de.oth.fkretschmar.advertisementproject.entities.User;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;

/**
 * The service that offers functionality relatetd to the generation and 
 * management of {@link Campaign} instances.
 *
 * @author fkre
 */
@RequestScoped
public class CampaignService implements Serializable {

    // --------------- Private fields ---------------
    
    /**
     * Stores the repository used to manage {@link Campaign} entites.
     */
    @Inject
    private CampaignRepository campaignRepository;

    /**
     * Stores the service used to manage {@link Content} entites.
     */
    @Inject
    private ContentService contentService;

    /**
     * Stores the repository used to manage {@link User} entites.
     */
    @Inject
    private UserRepository userRepository;
    

    // --------------- Public methods ---------------
    
    /**
     * Creates a new {@link Campaign} and links it to the specified user.
     * 
     * @param   user        the user for which the campaign will be created.
     * @param   campaign   the campaign that will be saved.
     * @return              the saved campaign.
     */
    @Transactional
    public User createCampaignForUser(User user, Campaign campaign) {
        // the campaign is being created for a user that already has an account
        // therefore no .persist is needed for an account
        // also: bill will be added later, when the first payments for the 
        // campaign come in, so there can not be bills right now either.
        
        // 1. create the contents
        campaign.getContents().forEach(content -> this.contentService.createContent(content));
        
        // 2. merge and set user on campaign
        user = this.userRepository.merge(user);
        campaign.setComissioner(user);
        
        // 3. save the campaign
        this.campaignRepository.persist(campaign);
        
        // 4. add the campaign to the user:
        user.addCampaign(campaign);
        
        return user;
    }
    
    /**
     * Cancels the specified {@link Campaign}.
     * 
     * @param   campaign    that will be cancelled.
     * @return              the cancelled campaign.
     */
    @Transactional
    public Campaign cancelCampaign(Campaign campaign) {        
        campaign = this.campaignRepository.merge(campaign);
        campaign.setCampaignState(CampaignState.CANCELLED);
        this.deleteCampaignContents(campaign);
        return campaign;
    }
    
    
    /**
     * Ends the specified {@link Campaign}.
     * 
     * @param campaign  that will be ended.
     * @return          the ended campaign.
     */
    @Transactional
    public Campaign endCampaign(Campaign campaign) {
        campaign = this.campaignRepository.merge(campaign);
        campaign.setCampaignState(CampaignState.ENDED);
        this.deleteCampaignContents(campaign);
        return campaign;
    }

    // --------------- Private methods ---------------
    
    
    /**
     * Deletes all campaign contents form the specified campaign.
     * @param campaign 
     */
    @Transactional
    private void deleteCampaignContents(Campaign campaign) {        
        // remove all contents
        Object[] contents = campaign.getContents().toArray();
        
        for (int i = 0; i < contents.length; i++) {
            if(contents[i] instanceof Content){
                campaign.removeContent((Content)contents[i]);
                this.contentService.deleteContent((Content)contents[i]);
            }
        }
    }
}
