package com.naddame.repository.custom;

import com.naddame.model.Sequence;
import com.naddame.repository.exceptions.SequenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

/**
 * 
 * @author <a href="mailto:djamelhamas@gmail.com"> Djamel Hamas</a>
 *
 */
public class SequenceRepositoryImpl implements SequenceCustomRepository {

	@Autowired
	private MongoTemplate template;

	@Override
	public Long nextValue(String key) {

		//prepare query
		final Query query = new Query(Criteria.where("_id").is(key));

		//prepare update
		final Update update = new Update();
		update.inc("seq", 1);

		//options
		final FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);
		options.upsert(true);
		

		// handle query
		final Sequence seq = this.template.findAndModify(query, update, options, Sequence.class);

		if (seq == null) {
			throw new SequenceException("Unable to get sequence id for key : " + key);
		}

		return seq.getSeq();
	}


}
