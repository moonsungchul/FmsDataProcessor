package com.firemstar.fdp.core;

import java.util.ArrayList;
import java.util.List;

public class KomoranResultAr{
	private ArrayList<String> nArr;
	private ArrayList<String> vArr;
	private ArrayList<String> vaArr;
	
	public KomoranResultAr() {
		nArr = new ArrayList<String>();
		vArr = new ArrayList<String>();
		vaArr = new ArrayList<String>();
	}

	public ArrayList<String> getnArr() {
		return nArr;
	}

	public void setnArr(ArrayList<String> nArr) {
		this.nArr = nArr;
	}

	public ArrayList<String> getvArr() {
		return vArr;
	}

	public void setvArr(ArrayList<String> vArr) {
		this.vArr = vArr;
	}

	public ArrayList<String> getVaArr() {
		return vaArr;
	}

	public void setVaArr(ArrayList<String> vaArr) {
		this.vaArr = vaArr;
	}

	@Override
	public String toString() {
		return "KomoranResult [nArr=" + nArr + ", vArr=" + vArr + ", vaArr=" + vaArr + "]";
	}
	
	
	
	

}
