/*
 * Copyright (c) 2016 Memorial Sloan-Kettering Cancer Center.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY, WITHOUT EVEN THE IMPLIED WARRANTY OF MERCHANTABILITY OR FITNESS
 * FOR A PARTICULAR PURPOSE. The software and documentation provided hereunder
 * is on an "as is" basis, and Memorial Sloan-Kettering Cancer Center has no
 * obligations to provide maintenance, support, updates, enhancements or
 * modifications. In no event shall Memorial Sloan-Kettering Cancer Center be
 * liable to any party for direct, indirect, special, incidental or
 * consequential damages, including lost profits, arising out of the use of this
 * software and its documentation, even if Memorial Sloan-Kettering Cancer
 * Center has been advised of the possibility of such damage.
 */

/*
 * This file is part of cBioPortal CMO-Pipelines.
 *
 * cBioPortal is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package org.cbioportal.genomeNexus.annotation.pipeline;

import org.cbioportal.genomeNexus.models.AnnotatedRecord;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author Zachary Heins
 */
public class MutationRecordProcessor implements ItemProcessor<AnnotatedRecord, String>{
     @Override
    public String process(AnnotatedRecord i) throws Exception {
        String to_write = "";
        for (String field : i.getFieldNames()) {
            to_write += i.getClass().getMethod("get" + field).invoke(i) + "\t";
        }
        
        for (String additionalValue : i.getAdditionalProperties().values()) {
            to_write += additionalValue + "\t";
        }
        
        return to_write.substring(0, to_write.length());
    }   
}
