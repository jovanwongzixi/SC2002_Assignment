package entity.movie;

import interfaces.SerializedData;

public class Ticket implements SerializedData{
	private boolean is3D;
	private boolean isBlockbuster;
	private boolean isPlatinum;
	private AgeGroup ageGroup;
	private boolean isDouble;
	private boolean isSpecial;
	
	public Ticket(boolean is3D, boolean isBlockbuster, boolean isPlatinum, AgeGroup ageGroup, boolean isDouble, boolean isSpecial) {
		
		this.is3D = is3D;
		this.isBlockbuster = isBlockbuster;
		this.isPlatinum = isPlatinum;
		this.ageGroup = ageGroup;
		this.isDouble = isDouble;
		this.isSpecial = isSpecial;
	}
	
	public boolean getIs3D() {
		return this.is3D;
	}
	
	public boolean getIsBlockbuster() {
		return this.isBlockbuster;
	}
	
	public boolean getIsPlatinum() {
		return this.isPlatinum;
	}
	
	public AgeGroup getAgeGroup() {
		return this.ageGroup;
	}
	
	public boolean getIsDouble() {
		return this.isDouble;
	}
	
	public boolean getIsSpecial() {
		return this.isSpecial;
	}
}
