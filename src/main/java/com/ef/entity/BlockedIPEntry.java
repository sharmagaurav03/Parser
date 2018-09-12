package com.ef.entity;

public class BlockedIPEntry {
	private Long id;
	private String blockedIp;
	private Integer attemptedCount;
	private String reason;
	
	public BlockedIPEntry(String blockedIp, Integer attemptedCount, String reason) {
		this.blockedIp = blockedIp;
		this.attemptedCount = attemptedCount;
		this.reason = reason;
	}

	public String getBlockedIp() {
		return blockedIp;
	}

	public void setBlockedIp(String blockedIp) {
		this.blockedIp = blockedIp;
	}

	public Integer getAttemptedCount() {
		return attemptedCount;
	}

	public void setAttemptedCount(Integer attemptedCount) {
		this.attemptedCount = attemptedCount;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public void setId(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attemptedCount == null) ? 0 : attemptedCount.hashCode());
		result = prime * result + ((blockedIp == null) ? 0 : blockedIp.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((reason == null) ? 0 : reason.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BlockedIPEntry other = (BlockedIPEntry) obj;
		if (attemptedCount == null) {
			if (other.attemptedCount != null)
				return false;
		} else if (!attemptedCount.equals(other.attemptedCount))
			return false;
		if (blockedIp == null) {
			if (other.blockedIp != null)
				return false;
		} else if (!blockedIp.equals(other.blockedIp))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (reason == null) {
			if (other.reason != null)
				return false;
		} else if (!reason.equals(other.reason))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BlockedIPEntry [id=" + id + ", blockedIp=" + blockedIp + ", attemptedCount=" + attemptedCount
				+ ", reason=" + reason + "]";
	}
	
}
