package br.com.m2msolutions.controleviagens.client;

import java.util.Comparator;

public class TripSortListByDepartureExec implements Comparator<DTOTrip>{
	
	private SortMode sortMode;
	
	public TripSortListByDepartureExec(){
		sortMode = SortMode.ASCENDING;
	} 
	public TripSortListByDepartureExec(SortMode sortMode){
		this.sortMode = sortMode;
	}
	
	@Override
	public int compare(DTOTrip dto1, DTOTrip dto2) {
		if(this.sortMode == SortMode.ASCENDING){
			return compareSorteModeAsc(dto1, dto2);
		}else{
			return compareSorteModeDesc(dto1, dto2);
		}
	}
	
	private int compareSorteModeAsc(DTOTrip dto1, DTOTrip dto2) {
		long dtimeExec1 = dto1.getHorarioExecutado();
		long dtimeExec2 = dto2.getHorarioExecutado();
		int returnValue = 0;
		
		if(dtimeExec1 == 0)
			return -1;
		
		if(dtimeExec2 == 0)
			return 1;
		
		if(dtimeExec1 > dtimeExec2){
			returnValue = 1;
		}else if(dtimeExec1< dtimeExec2){
			returnValue = -1;
		}
		
		return returnValue;
	}
	
	private int compareSorteModeDesc(DTOTrip dto1, DTOTrip dto2) {
		long dtimeExec1 = dto1.getHorarioExecutado();
		long dtimeExec2 = dto2.getHorarioExecutado();
		int returnValue = 0;
		
		if(dtimeExec1 == 0)
			return 1;
		
		if(dtimeExec2 == 0)
			return -1;
		
		if(dtimeExec1 > dtimeExec2){
			returnValue = -1;
		}else if(dtimeExec1< dtimeExec2){
			returnValue = 1;
		}
		
		return returnValue;
	}
	
	public SortMode getSortMode() {
		return sortMode;
	}
	public void setSortMode(SortMode sortMode) {
		this.sortMode = sortMode;
	}
}
