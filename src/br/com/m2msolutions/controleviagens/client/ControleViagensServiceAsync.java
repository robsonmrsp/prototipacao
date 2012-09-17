package br.com.m2msolutions.controleviagens.client;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("controleViagens")
public interface ControleViagensServiceAsync {
	void getTripList(AsyncCallback<List<DTOTrip>> callback) throws IllegalArgumentException;
}

