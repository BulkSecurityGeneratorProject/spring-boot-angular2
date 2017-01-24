package com.naddame.repository;

import com.naddame.model.Sequence;
import com.naddame.repository.custom.SequenceCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * 
 * @author <a href="mailto:djamelhamas@gmail.com"> Djamel Hamas</a>
 *
 */
@Repository
public interface SequenceRepository extends MongoRepository<Sequence, String>, SequenceCustomRepository {
}
