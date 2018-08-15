package com.ythd.ower.data.entity.upm;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 物流实体类
 * 
 * @author ryy
 */
public class Logistics implements Serializable {
	private static final long serialVersionUID = 1L;
	public String area_id;

	public String area_name;

	public String create_time;

	public String first_kg;

	public String first_price;

	public String send_time;

	public String province_id;
	/**
	 * 省市级列表
	 */
	List<PClist> pclist;
	/**
	 * 续重列表
	 */
	List<Contnuheavy> chlist;
	
	public String getArea_id() {
		return area_id;
	}

	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}

	public String getArea_name() {
		return area_name;
	}

	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getFirst_kg() {
		return first_kg;
	}

	public void setFirst_kg(String first_kg) {
		this.first_kg = first_kg;
	}

	public String getFirst_price() {
		return first_price;
	}

	public void setFirst_price(String first_price) {
		this.first_price = first_price;
	}

	public String getSend_time() {
		return send_time;
	}

	public void setSend_time(String send_time) {
		this.send_time = send_time;
	}

	public String getProvince_id() {
		return province_id;
	}

	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}

	public List<PClist> getPclist() {
		return pclist;
	}

	public void setPclist(List<PClist> pclist) {
		this.pclist = pclist;
	}

	public List<Contnuheavy> getChlist() {
		return chlist;
	}

	public void setChlist(List<Contnuheavy> chlist) {
		this.chlist = chlist;
	}

	public Logistics(String area_id, String area_name, String first_kg, String first_price, String send_time,
			String create_time, List<PClist> pclist,List<Contnuheavy> chlist) {
		super();
		this.area_id = area_id;
		this.area_name = area_name;
		this.first_kg = first_kg;
		this.first_price = first_price;
		this.send_time = send_time;
		this.create_time = create_time;
		this.pclist = pclist;
		this.chlist = chlist;
	}

	public static class PClist{

		public String pId;
		public String pName;
		public List<ProCity> c_list;
		public String cName;

		public PClist(String pId, String pName, List<ProCity> c_list, String cName) {
			super();
			this.pId = pId;
			this.pName = pName;
			this.c_list = c_list;
			this.cName = cName;
		}

		public String getpId() {
			return pId;
		}

		public void setpId(String pId) {
			this.pId = pId;
		}

		public String getpName() {
			return pName;
		}

		public void setpName(String pName) {
			this.pName = pName;
		}

		public List<ProCity> getC_list() {
			return c_list;
		}

		public void setC_list(List<ProCity> c_list) {
			this.c_list = c_list;
		}

		public String getcName() {
			return cName;
		}

		public void setcName(String cName) {
			this.cName = cName;
		}

	}
	
	public static class ProCity {

		public String region_id;
		public String parent_id;
		public String region_name;

		public ProCity(String region_id, String parent_id, String region_name) {
			super();
			this.region_id = region_id;
			this.parent_id = parent_id;
			this.region_name = region_name;
		}

		public String getRegion_id() {
			return region_id;
		}

		public void setRegion_id(String region_id) {
			this.region_id = region_id;
		}

		public String getParent_id() {
			return parent_id;
		}

		public void setParent_id(String parent_id) {
			this.parent_id = parent_id;
		}

		public String getRegion_name() {
			return region_name;
		}

		public void setRegion_name(String region_name) {
			this.region_name = region_name;
		}

	}
	
	public static class Contnuheavy {

		public String contnuId;
		public String contnuName;
		public String contnuKg;
		public String contnuPrice;
		
		public Contnuheavy(String contnuId, String contnuName, String contnuKg, String contnuPrice) {
			super();
			this.contnuId = contnuId;
			this.contnuName = contnuName;
			this.contnuKg = contnuKg;
			this.contnuPrice = contnuPrice;
		}
		public String getContnuId() {
			return contnuId;
		}

		public void setContnuId(String contnuId) {
			this.contnuId = contnuId;
		}

		public String getContnuName() {
			return contnuName;
		}

		public void setContnuName(String contnuName) {
			this.contnuName = contnuName;
		}

		public String getContnuKg() {
			return contnuKg;
		}

		public void setContnuKg(String contnuKg) {
			this.contnuKg = contnuKg;
		}

		public String getContnuPrice() {
			return contnuPrice;
		}

		public void setContnuPrice(String contnuPrice) {
			this.contnuPrice = contnuPrice;
		}

	}

}
