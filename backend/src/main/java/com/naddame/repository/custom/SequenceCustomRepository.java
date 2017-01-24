package com.naddame.repository.custom;

/**
 * 
 * @author <a href="mailto:djamelhamas@gmail.com"> Djamel Hamas</a>
 *
 */
public interface SequenceCustomRepository {
	Long nextValue(String key);
}
