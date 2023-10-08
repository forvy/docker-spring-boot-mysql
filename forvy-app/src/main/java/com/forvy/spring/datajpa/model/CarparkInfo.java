package com.forvy.spring.datajpa.model;

import java.time.OffsetDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "carpark_info")
public class CarparkInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "carpark_number")
	private String carParkNumber;

	@Column(name = "total_lots")
	private int totalLots;

	@Column(name = "available_lots")
	private int availableLots;

	@Column(name = "update_datetime")
	private OffsetDateTime updateDatetime;

	public CarparkInfo() {
	}

	public CarparkInfo(String carParkNumber, int totalLots, int availableLots, OffsetDateTime updateDatetime) {
		this.carParkNumber = carParkNumber;
		this.totalLots = totalLots;
		this.availableLots = availableLots;
		this.updateDatetime = updateDatetime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCarParkNumber() {
		return carParkNumber;
	}

	public void setCarParkNumber(String carParkNumber) {
		this.carParkNumber = carParkNumber;
	}

	public int getTotalLots() {
		return totalLots;
	}

	public void setTotalLots(int totalLots) {
		this.totalLots = totalLots;
	}

	public int getAvailableLots() {
		return availableLots;
	}

	public void setAvailableLots(int availableLots) {
		this.availableLots = availableLots;
	}

	public OffsetDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(OffsetDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	@Override
	public String toString() {
		return "CarparkInfo{" +
				"id=" + id +
				", carParkNumber='" + carParkNumber + '\'' +
				", totalLots=" + totalLots +
				", availableLots=" + availableLots +
				", updateDatetime=" + updateDatetime +
				'}';
	}
}