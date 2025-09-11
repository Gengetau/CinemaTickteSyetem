package com.seiryo.pojo;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 11567
 * @version v1.0
 * @ClassName Cinema
 * @Description 电影实体类
 * @dateTime 2025/9/9 17:40
 */
public class Cinema {
	// 来自 CINEMA_INFO表
	private Long cinemaId;// 电影编号
	private String cinemaName;// 电影名
	private Date releaseTime;// 上映时间
	private BigDecimal cinemaPrice;// 票价
	private Date movieTime;// 观影时间
	private String cinemaState;// 电影状态
	private Integer cinemaSeats;// 座位数
	
	public Cinema() {
	}
	
	public Cinema(Long cinemaId, String cinemaName, Date releaseTime, BigDecimal cinemaPrice, Date movieTime, String cinemaState, Integer cinemaSeats) {
		this.cinemaId = cinemaId;
		this.cinemaName = cinemaName;
		this.releaseTime = releaseTime;
		this.cinemaPrice = cinemaPrice;
		this.movieTime = movieTime;
		this.cinemaState = cinemaState;
		this.cinemaSeats = cinemaSeats;
	}
	
	public Long getCinemaId() {
		return cinemaId;
	}
	
	public void setCinemaId(Long cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public String getCinemaName() {
		return cinemaName;
	}
	
	public void setCinemaName(String cinemaName) {
		this.cinemaName = cinemaName;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}
	
	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}
	
	public BigDecimal getCinemaPrice() {
		return cinemaPrice;
	}
	
	public void setCinemaPrice(BigDecimal cinemaPrice) {
		this.cinemaPrice = cinemaPrice;
	}
	
	public Date getMovieTime() {
		return movieTime;
	}
	
	public void setMovieTime(Date movieTime) {
		this.movieTime = movieTime;
	}
	
	public String getCinemaState() {
		return cinemaState;
	}
	
	public void setCinemaState(String cinemaState) {
		this.cinemaState = cinemaState;
	}
	
	public Integer getCinemaSeats() {
		return cinemaSeats;
	}
	
	public void setCinemaSeats(Integer cinemaSeats) {
		this.cinemaSeats = cinemaSeats;
	}
	
	@Override
	public String toString() {
		String price = cinemaPrice + "￥";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String retime = sdf.format(releaseTime);
		String meTime = sdf2.format(movieTime);
		return String.format("%-10d \t %-20s \t %-25s \t %-15s \t %-10s", cinemaId, cinemaName, retime, price, meTime);
	}
}
