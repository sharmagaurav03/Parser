package com.ef.entity;

import static com.ef.Constant.ILLEGAL_PROGRAM_ARGUMENT_ERROR;

public class ProgramArgument {

	private String startDate;
	private String duration;
	private int threshold;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public int getThreshold() {
		return threshold;
	}

	public void setThreshold(String threshold) {
		try {
			this.threshold = Integer.parseInt(threshold);
		} catch (IllegalArgumentException exception) {
			throw new RuntimeException(ILLEGAL_PROGRAM_ARGUMENT_ERROR, exception);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((duration == null) ? 0 : duration.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + threshold;
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
		ProgramArgument other = (ProgramArgument) obj;
		if (duration == null) {
			if (other.duration != null)
				return false;
		} else if (!duration.equals(other.duration))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (threshold != other.threshold)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ProgramArgument [startDate=" + startDate + ", duration=" + duration + ", threshold=" + threshold + "]";
	}

}
