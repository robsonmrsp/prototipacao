/*******************************************************************************
 * Copyright 2011 Google Inc. All Rights Reserved.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package br.com.m2msolutions.controleviagens.server;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import br.com.m2msolutions.controleviagens.client.ControleViagensService;
import br.com.m2msolutions.controleviagens.client.DTOTrip;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ControleViagensServiceImpl extends RemoteServiceServlet implements ControleViagensService {
	
	private static final long serialVersionUID = 1L;

	@Override
	public List<DTOTrip> getTripList() {
		
		List<DTOTrip> list = new ArrayList<DTOTrip>();
		fillDTOTripListPlanFinalizado(list);
		fillDTOTripListPlanEmExec(list);
		fillDTOTripListPlan(list);
		fillDTOTripListExec(list);
		
		return list;
	}
	
	private void fillDTOTripListExec(List<DTOTrip> list){
		
		DTOTrip dto = new DTOTrip();
		
		dto = new DTOTrip();
		dto.setVehicleCode("87200");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioExecutado(getTimeInMilis(9,00,0));
		dto.setHora("1h 01'");
		dto.setHorarioFim(getTimeInMilis(9,30,0));
		dto.setStatus(DTOTrip.Status.EXTRA_FINAL);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("13200");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioExecutado(getTimeInMilis(9,06,0));
		dto.setHora("1h 55'");
		dto.setHorarioFim(getTimeInMilis(9,36,0));
		dto.setStatus(DTOTrip.Status.EXTRA_FINAL);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("14200");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioExecutado(getTimeInMilis(9,15,0));
		dto.setHora("1h 55'");
		dto.setStatus(DTOTrip.Status.EXTRA_EXEC);
		list.add(dto);
		
		
		
		dto = new DTOTrip();
		dto.setVehicleCode("89200");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioExecutado(getTimeInMilis(9,01,0));
		dto.setHora("1h 01'");
		dto.setHorarioFim(getTimeInMilis(9,35,0));
		dto.setStatus(DTOTrip.Status.EXTRA_FINAL);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("13000");
		dto.setControlPointId(2);
		dto.setControlPointName("Alvorada");
		dto.setHorarioExecutado(getTimeInMilis(9,06,0));
		dto.setHora("1h 55'");
		dto.setHorarioFim(getTimeInMilis(9,46,0));
		dto.setStatus(DTOTrip.Status.EXTRA_FINAL);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("14200");
		dto.setControlPointId(2);
		dto.setControlPointName("Alvorada");
		dto.setHorarioExecutado(getTimeInMilis(9,15,0));
		dto.setHora("1h 53'");
		dto.setStatus(DTOTrip.Status.EXTRA_EXEC);
		list.add(dto);
		
	}
	
	private void fillDTOTripListPlan(List<DTOTrip> list){
		
		DTOTrip dto = new DTOTrip();
		
		dto = new DTOTrip();
		dto.setVehicleCode("87200");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,11,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87201");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,15,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("80034");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,25,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56234");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,35,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87094");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,40,0));
		list.add(dto);
		
		
		
		dto = new DTOTrip();
		dto.setVehicleCode("56234");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(9,15,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56200");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(9,30,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56201");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(9,45,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56034");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(9,55,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("57234");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(10,05,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56094");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(10,10,0));
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56394");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(10,20,0));
		list.add(dto);
		
	}
	
	private void fillDTOTripListPlanEmExec(List<DTOTrip> list){
		
		DTOTrip dto = new DTOTrip();
		
		dto = new DTOTrip();
		dto.setVehicleCode("87200");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(9,00,0));
		dto.setHorarioExecutado(getTimeInMilis(9,00,0));
		dto.setHora("1h 01'");
		dto.setDiferencaPlanExec("0");
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("0");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87201");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(9,15,0));
		dto.setHorarioExecutado(getTimeInMilis(9,20,0));
		dto.setDiferencaPlanExec("5");
		dto.setStatus(DTOTrip.Status.ATRASADO);
		dto.setTotalMinAtAd("5");
		dto.setHora("0h 55'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("80034");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(9,20,0));
		dto.setHorarioExecutado(getTimeInMilis(9,20,0));
		dto.setStatus(DTOTrip.Status.NO_HORARIO);
		dto.setTotalMinAtAd("0");
		dto.setHora("0h 30'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56234");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(12,15,0));
		dto.setHorarioExecutado(getTimeInMilis(12,06,0));
		dto.setStatus(DTOTrip.Status.ATRASADO);
		dto.setHora("1h 30'");
		dto.setTotalMinAtAd("1");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87094");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHora("1h 15'");
		dto.setHorarioPlanejado(getTimeInMilis(12,25,0));
		dto.setHorarioExecutado(getTimeInMilis(12,25,0));
		dto.setStatus(DTOTrip.Status.NO_HORARIO);
		dto.setTotalMinAtAd("0");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("12394");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHora("0h 10'");
		dto.setHorarioPlanejado(getTimeInMilis(12,35,0));
		dto.setHorarioExecutado(getTimeInMilis(12,30,0));
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("5");
		list.add(dto);
		
		
		
		
		dto = new DTOTrip();
		dto.setVehicleCode("56234");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("1h 10'");
		dto.setHorarioPlanejado(getTimeInMilis(10,30,0));
		dto.setHorarioExecutado(getTimeInMilis(10,20,0));
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("10");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56200");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("1h 01'");
		dto.setHorarioPlanejado(getTimeInMilis(11,00,0));
		dto.setHorarioExecutado(getTimeInMilis(10,55,0));
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("5");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56201");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("0h 55'");
		dto.setHorarioPlanejado(getTimeInMilis(11,15,0));
		dto.setHorarioExecutado(getTimeInMilis(11,20,0));
		dto.setStatus(DTOTrip.Status.ATRASADO);
		dto.setTotalMinAtAd("5");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56034");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("0h 30'");
		dto.setHorarioPlanejado(getTimeInMilis(11,30,0));
		dto.setHorarioExecutado(getTimeInMilis(11,30,0));
		dto.setStatus(DTOTrip.Status.NO_HORARIO);
		dto.setTotalMinAtAd("0");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("57234");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("1h 30'");
		dto.setHorarioPlanejado(getTimeInMilis(12,05,0));
		dto.setHorarioExecutado(getTimeInMilis(12,06,0));
		dto.setStatus(DTOTrip.Status.ATRASADO);
		dto.setTotalMinAtAd("1");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56094");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("1h 15'");
		dto.setHorarioPlanejado(getTimeInMilis(12,25,0));
		dto.setHorarioExecutado(getTimeInMilis(12,25,0));
		dto.setStatus(DTOTrip.Status.NO_HORARIO);
		dto.setTotalMinAtAd("0");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56394");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("0h 10'");
		dto.setHorarioPlanejado(getTimeInMilis(12,35,0));
		dto.setHorarioExecutado(getTimeInMilis(12,30,0));
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("5");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("67394");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("0h 10'");
		dto.setHorarioPlanejado(getTimeInMilis(12,35,0));
		dto.setHorarioExecutado(getTimeInMilis(12,30,0));
		dto.setStatus(DTOTrip.Status.ADIANTADO);
		dto.setTotalMinAtAd("5");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("58034");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHora("0h 30'");
		dto.setHorarioPlanejado(getTimeInMilis(11,30,0));
		dto.setHorarioExecutado(getTimeInMilis(11,30,0));
		dto.setStatus(DTOTrip.Status.NO_HORARIO);
		dto.setTotalMinAtAd("0");
		list.add(dto);
	}
	
	private void fillDTOTripListPlanFinalizado(List<DTOTrip> list){
		
		DTOTrip dto = new DTOTrip();
		
		dto = new DTOTrip();
		dto.setVehicleCode("23456");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,0,0));
		dto.setHorarioExecutado(getTimeInMilis(8,5,0));
		dto.setHorarioFim(getTimeInMilis(8,36,0));
		dto.setDiferencaPlanExec("5");
		dto.setHora("0h 36'");
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("19348");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,15,0));
		dto.setHorarioExecutado(getTimeInMilis(8,16,0));
		dto.setHorarioFim(getTimeInMilis(9,46,0));
		dto.setDiferencaPlanExec("1");
		dto.setHora("0h 10'");
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87234");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,30,0));
		dto.setHorarioExecutado(getTimeInMilis(8,33,0));
		dto.setHorarioFim(getTimeInMilis(9,10,0));
		dto.setDiferencaPlanExec("3");
		dto.setHora("0h 40'");
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56093");
		dto.setControlPointId(1);
		dto.setControlPointName("Alvorada");
		dto.setHorarioPlanejado(getTimeInMilis(8,45,0));
		dto.setHorarioExecutado(getTimeInMilis(8,50,0));
		dto.setHorarioFim(getTimeInMilis(9,25,0));
		dto.setDiferencaPlanExec("5");
		dto.setHora("0h 40'");
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56456");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(8,10,0));
		dto.setHorarioExecutado(getTimeInMilis(8,11,0));
		dto.setDiferencaPlanExec("1");
		dto.setHorarioFim(getTimeInMilis(8,35,0));
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		dto.setHora("0h 36'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("56348");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(8,25,0));
		dto.setHorarioExecutado(getTimeInMilis(8,25,0));
		dto.setDiferencaPlanExec("0");
		dto.setHorarioFim(getTimeInMilis(8,57,0));
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		dto.setHora("0h 32'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("23456");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(8,35,0));
		dto.setHorarioExecutado(getTimeInMilis(8,36,0));
		dto.setDiferencaPlanExec("1");
		dto.setHorarioFim(getTimeInMilis(9,10,0));
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		dto.setHora("0h 34'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("19348");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(8,40,0));
		dto.setHorarioExecutado(getTimeInMilis(8,45,0));
		dto.setDiferencaPlanExec("5");
		dto.setHorarioFim(getTimeInMilis(9,28,0));
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		dto.setHora("0h 40'");
		list.add(dto);
		
		dto = new DTOTrip();
		dto.setVehicleCode("87234");
		dto.setControlPointId(2);
		dto.setControlPointName("Santa Cruz");
		dto.setHorarioPlanejado(getTimeInMilis(8,50,0));
		dto.setHorarioExecutado(getTimeInMilis(8,52,0));
		dto.setDiferencaPlanExec("2");
		dto.setHorarioFim(getTimeInMilis(9,30,0));
		dto.setStatus(DTOTrip.Status.FINALIZADA);
		dto.setHora("0h 45'");
		list.add(dto);
	}
	
	private long getTimeInMilis(int h, int m, int s){
		
		GregorianCalendar g = new GregorianCalendar();
		g.set(GregorianCalendar.HOUR_OF_DAY, h);
		g.set(GregorianCalendar.MINUTE, m);
		g.set(GregorianCalendar.SECOND, s);
		
		return g.getTimeInMillis();
	}
	
}
