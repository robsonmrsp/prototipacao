package br.com.m2msolutions.controleviagens.client;

import com.google.gwt.user.client.rpc.IsSerializable;

public class DTOTrip implements IsSerializable {
	
	public enum Status {ATRASADO,ADIANTADO,NO_HORARIO,FINALIZADA,EXTRA_EXEC,EXTRA_FINAL}
	
	private long controlPointId;
	private String controlPointName;
	private String vehicleCode;
	private Status status;
	private long horarioPlanejado;
	private long horarioExecutado;
	private long horarioFim;
	private String diferencaPlanExec;
	private String hora;
	private String totalMinAtAd;

	public String getVehicleCode() {
		return vehicleCode;
	}

	public Status getStatus() {
		return status;
	}

	public String getHora() {
		return hora;
	}

	public String getTotalMinAtAd() {
		return totalMinAtAd;
	}

	public void setVehicleCode(String vehicleCode) {
		this.vehicleCode = vehicleCode;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public void setTotalMinAtAd(String totalMinAtAd) {
		this.totalMinAtAd = totalMinAtAd;
	}
	
	public long getControlPointId() {
		return controlPointId;
	}

	public String getControlPointName() {
		return controlPointName;
	}

	public void setControlPointId(long controlPointId) {
		this.controlPointId = controlPointId;
	}

	public void setControlPointName(String controlPointName) {
		this.controlPointName = controlPointName;
	}

	@Override
	public String toString() {
		return vehicleCode;
	}

	public String getDiferencaPlanExec() {
		return diferencaPlanExec;
	}

	public void setDiferencaPlanExec(String diferencaPlanExec) {
		this.diferencaPlanExec = diferencaPlanExec;
	}

	public long getHorarioPlanejado() {
		return horarioPlanejado;
	}

	public long getHorarioExecutado() {
		return horarioExecutado;
	}

	public long getHorarioFim() {
		return horarioFim;
	}

	public void setHorarioPlanejado(long horarioPlanejado) {
		this.horarioPlanejado = horarioPlanejado;
	}

	public void setHorarioExecutado(long horarioExecutado) {
		this.horarioExecutado = horarioExecutado;
	}

	public void setHorarioFim(long horarioFim) {
		this.horarioFim = horarioFim;
	}

	
}
