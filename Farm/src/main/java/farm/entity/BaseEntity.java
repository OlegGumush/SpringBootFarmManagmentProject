package farm.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

import farm.utils.DateTimes;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private long insertedDateTime;
	
	private long updatedDateTime;
	
	private boolean isRemoved;

	public BaseEntity() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getInsertedDateTime() {
		return insertedDateTime;
	}

	public void setInsertedDateTime(long insertedDateTime) {
		this.insertedDateTime = insertedDateTime;
	}

	public long getUpdatedDateTime() {
		return updatedDateTime;
	}

	public void setUpdatedDateTime(long updatedDateTime) {
		this.updatedDateTime = updatedDateTime;
	}

	public boolean isRemoved() {
		return isRemoved;
	}

	public void setRemoved(boolean isRemoved) {
		this.isRemoved = isRemoved;
	}
	
	@PrePersist
	public void prePersist() {
		this.insertedDateTime = DateTimes.EpochSecondsUtcNow();
		this.updatedDateTime = insertedDateTime;
	}
	
	@PreUpdate
	public void preUpdate() {
		this.updatedDateTime = DateTimes.EpochSecondsUtcNow();
	}
	
	@PreRemove
	public void preDelete() {
		this.updatedDateTime = DateTimes.EpochSecondsUtcNow();
	}
}
