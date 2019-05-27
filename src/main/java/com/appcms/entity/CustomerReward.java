package com.appcms.entity;

public class CustomerReward {

	int customer_reward_id;
	int customer_id;
	int order_id;
	String description;
	int points;
	String date_added;
	String date_vencimiento;
	int id_campana;
	int id_trx;
	int id_jos_ticket;
	int tipo_reward;

	public CustomerReward() {}
	
	public CustomerReward(int customer_reward_id, int customer_id, int order_id, String description, int points,
			String date_added, String date_vencimiento, int id_campana, int id_trx, int id_jos_ticket,
			int tipo_reward) {
		super();
		this.customer_reward_id = customer_reward_id;
		this.customer_id = customer_id;
		this.order_id = order_id;
		this.description = description;
		this.points = points;
		this.date_added = date_added;
		this.date_vencimiento = date_vencimiento;
		this.id_campana = id_campana;
		this.id_trx = id_trx;
		this.id_jos_ticket = id_jos_ticket;
		this.tipo_reward = tipo_reward;
	}
	public CustomerReward(int customer_id, int order_id, String description, int points,
			String date_added, String date_vencimiento, int id_campana, int id_trx, int id_jos_ticket,
			int tipo_reward) {
		super();
		this.customer_id = customer_id;
		this.order_id = order_id;
		this.description = description;
		this.points = points;
		this.date_added = date_added;
		this.date_vencimiento = date_vencimiento;
		this.id_campana = id_campana;
		this.id_trx = id_trx;
		this.id_jos_ticket = id_jos_ticket;
		this.tipo_reward = tipo_reward;
	}
	public int getCustomer_reward_id() {
		return customer_reward_id;
	}

	public void setCustomer_reward_id(int customer_reward_id) {
		this.customer_reward_id = customer_reward_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getDate_added() {
		return date_added;
	}

	public void setDate_added(String date_added) {
		this.date_added = date_added;
	}

	public String getDate_vencimiento() {
		return date_vencimiento;
	}

	public void setDate_vencimiento(String date_vencimiento) {
		this.date_vencimiento = date_vencimiento;
	}

	public int getId_campana() {
		return id_campana;
	}

	public void setId_campana(int id_campana) {
		this.id_campana = id_campana;
	}

	public int getId_trx() {
		return id_trx;
	}

	public void setId_trx(int id_trx) {
		this.id_trx = id_trx;
	}

	public int getId_jos_ticket() {
		return id_jos_ticket;
	}

	public void setId_jos_ticket(int id_jos_ticket) {
		this.id_jos_ticket = id_jos_ticket;
	}

	public int getTipo_reward() {
		return tipo_reward;
	}

	public void setTipo_reward(int tipo_reward) {
		this.tipo_reward = tipo_reward;
	}

	@Override
	public String toString() {
		return "CustomerReward [customer_reward_id=" + customer_reward_id + ", customer_id=" + customer_id
				+ ", order_id=" + order_id + ", description=" + description + ", points=" + points + ", date_added="
				+ date_added + ", date_vencimiento=" + date_vencimiento + ", id_campana=" + id_campana + ", id_trx="
				+ id_trx + ", id_jos_ticket=" + id_jos_ticket + ", tipo_reward=" + tipo_reward + "]";
	}

}
