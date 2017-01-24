package com.naddame.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Objects;

/**
 * 
 * @author <a href="mailto:djamelhamas@gmail.com"> Djamel Hamas</a>
 *
 */
@SuppressWarnings("serial")
@Document(collection = "pl_sequence")
public class Sequence implements Serializable {

	@Id
	private String id;
	private Long seq;

	public Sequence() {
		super();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getSeq() {
		return seq;
	}

	public void setSeq(Long seq) {
		this.seq = seq;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Sequence cca = (Sequence) o;

		if ( ! Objects.equals(id, cca.id)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

}
